package cn.iocoder.yudao.module.operation.controller.admin.trafficaccident;


import cn.iocoder.yudao.framework.common.util.json.JsonUtils;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.business.api.driver.DriverApi;
import cn.iocoder.yudao.module.business.api.vehicle.VehicleApi;
import cn.iocoder.yudao.module.operation.controller.admin.trafficaccident.vo.TrafficAccidentImportRespVO;
import cn.iocoder.yudao.module.operation.controller.admin.trafficaccident.vo.TrafficAccidentImportVO;
import cn.iocoder.yudao.module.operation.dal.dataobject.trafficaccident.TrafficAccidentDO;
import cn.iocoder.yudao.module.operation.service.trafficaccident.TrafficAccidentService;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.listener.ReadListener;
import com.alibaba.excel.util.ListUtils;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

@Slf4j
public class TrafficAccidentListener implements ReadListener<TrafficAccidentImportVO> {
    /**
     * 每隔5条存储数据库，实际使用中可以100条，然后清理list,方便内存回收
     */
    private static final int BATCH_COUNT = 100;

    /**
     * 缓存的数据
     */
    private List<TrafficAccidentImportVO> cachedList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);
    private final TrafficAccidentService trafficAccidentService;
    private final VehicleApi vehicleApi;
    private final DriverApi driverApi;

    private int insert = 0;
    private int update = 0;
    private Map<String, String> errorMap = new HashMap<>();

//    String result = "总数据行数: " + all + " 行, 成功行数: " + success + " 行, 失败行数: " + error + " 行, 新增: " + insert + " 行, 更新: " + update + " 行。";

    public TrafficAccidentImportRespVO getImportResp() {
        return TrafficAccidentImportRespVO.builder().creates(insert).updates(update).failureRecords(errorMap).build();
    }

    public TrafficAccidentListener(TrafficAccidentService trafficAccidentService, VehicleApi vehicleApi, DriverApi driverApi) {
        this.trafficAccidentService = trafficAccidentService;
        this.vehicleApi = vehicleApi;
        this.driverApi = driverApi;
    }

    @Override
    public void invoke(TrafficAccidentImportVO repairVehicleImportVO, AnalysisContext analysisContext) {
        log.info("解析到一条数据：{}", JsonUtils.toJsonString(repairVehicleImportVO));
        cachedList.add(repairVehicleImportVO);
        if (cachedList.size() >= BATCH_COUNT) {
            saveData();
            cachedList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        saveData();
        log.info("所有数据解析完成！");
    }

    private void saveData() {
        Map<String, String> errorMap = new HashMap<>();
        List<TrafficAccidentDO> insertList = new ArrayList<>();
        List<TrafficAccidentDO> updateList = new ArrayList<>();
        for (TrafficAccidentImportVO lineData : cachedList) {
            //根据自编号和车牌号 判断车辆是否存在
            String vehicleMask = lineData.getVehicleMask();
            String carNumber = lineData.getCarNumber();
            String driverName = lineData.getDriverName();
            Optional<Long> vehicleId = vehicleApi.getIdByMaskAndCarNumber(vehicleMask, carNumber);

            if (!vehicleId.isPresent()) {
                errorMap.put(carNumber, "车辆信息不存在");
                continue;
            }
            TrafficAccidentDO accidentDO = BeanUtils.toBean(lineData, TrafficAccidentDO.class);

            //根据车辆信息和驾驶员信息 查询车辆和驾驶员匹配关系是否存在
//            Optional<Long> driverId = driverApi.getIdByVehicleIdAndName(vehicleId.get(), driverName);
            Optional<Long> driverId = driverApi.getIdByVehicleIdAndName(carNumber, driverName);
            if (driverId.isPresent()) {
                accidentDO.setDriverId(driverId.get());
            } else {
                errorMap.put(driverName, "驾驶员与车辆不匹配");
                continue;
            }

            //根据 vehicleID driverID 出险时间查询事故记录是否存在
            Long accidentId = trafficAccidentService.getIdByCondition(vehicleId.get(), driverId.get(), lineData.getAccidentDate());
            if (Objects.nonNull(accidentId)) {
                accidentDO.setId(accidentId);
                updateList.add(accidentDO);
            } else {
                insertList.add(accidentDO);
            }
        }
        trafficAccidentService.batchSave(insertList);
        trafficAccidentService.batchUpdate(updateList);

        insert = insert + insertList.size();
        update = update + updateList.size();
    }
}
