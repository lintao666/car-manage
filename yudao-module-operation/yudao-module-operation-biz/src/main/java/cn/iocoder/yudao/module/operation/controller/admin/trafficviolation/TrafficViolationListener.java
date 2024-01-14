package cn.iocoder.yudao.module.operation.controller.admin.trafficviolation;


import cn.iocoder.yudao.framework.common.util.json.JsonUtils;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.business.api.driver.DriverApi;
import cn.iocoder.yudao.module.business.api.vehicle.VehicleApi;
import cn.iocoder.yudao.module.operation.controller.admin.trafficviolation.vo.TrafficViolationImportRespVO;
import cn.iocoder.yudao.module.operation.controller.admin.trafficviolation.vo.TrafficViolationImportVO;
import cn.iocoder.yudao.module.operation.dal.dataobject.trafficviolation.TrafficViolationDO;
import cn.iocoder.yudao.module.operation.service.trafficviolation.TrafficViolationService;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.listener.ReadListener;
import com.alibaba.excel.util.ListUtils;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

@Slf4j
public class TrafficViolationListener implements ReadListener<TrafficViolationImportVO> {
    /**
     * 每隔5条存储数据库，实际使用中可以100条，然后清理list,方便内存回收
     */
    private static final int BATCH_COUNT = 100;

    /**
     * 缓存的数据
     */
    private List<TrafficViolationImportVO> cachedList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);
    private final TrafficViolationService trafficViolationService;
    private final VehicleApi vehicleApi;
    private final DriverApi driverApi;

    private int insert = 0;
    private int update = 0;
    private Map<String, String> errorMap = new HashMap<>();

//    String result = "总数据行数: " + all + " 行, 成功行数: " + success + " 行, 失败行数: " + error + " 行, 新增: " + insert + " 行, 更新: " + update + " 行。";

    public TrafficViolationImportRespVO getImportResp() {
        return TrafficViolationImportRespVO.builder().creates(insert).updates(update).failureRecords(errorMap).build();
    }

    public TrafficViolationListener(TrafficViolationService trafficViolationService, VehicleApi vehicleApi, DriverApi driverApi) {
        this.trafficViolationService = trafficViolationService;
        this.vehicleApi = vehicleApi;
        this.driverApi = driverApi;
    }

    @Override
    public void invoke(TrafficViolationImportVO repairVehicleImportVO, AnalysisContext analysisContext) {
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
        List<TrafficViolationDO> insertList = new ArrayList<>();
        List<TrafficViolationDO> updateList = new ArrayList<>();
        for (TrafficViolationImportVO lineData : cachedList) {
            //根据自编号和车牌号 判断车辆是否存在
            String vehicleMask = lineData.getVehicleMask();
            String carNumber = lineData.getCarNumber();
            String driverName = lineData.getDriverName();

            Optional<Long> vehicleId = vehicleApi.getIdByMaskAndCarNumber(vehicleMask, carNumber);

            if (!vehicleId.isPresent()) {
                errorMap.put(carNumber, "车辆信息不存在");
                continue;
            }
            TrafficViolationDO violationDO = BeanUtils.toBean(lineData, TrafficViolationDO.class);
            //根据车辆信息和驾驶员信息 查询车辆和驾驶员匹配关系是否存在
//            Optional<Long> driverId = driverApi.getIdByVehicleIdAndName(vehicleId.get(), driverName);
            Optional<Long> driverId = driverApi.getIdByVehicleIdAndName(carNumber, driverName);
            if (driverId.isPresent()) {
                violationDO.setDriverId(driverId.get());
            } else {
                errorMap.put(driverName, "驾驶员与车辆不匹配");
                continue;
            }

            //根据 vehicleID driverId recordNum 查询记录是否存在
            Long violationId = trafficViolationService.getIdByCondition(vehicleId.get(), driverId.get(), lineData.getPlateNo());
            if (Objects.nonNull(violationId)) {
                violationDO.setId(violationId);
                updateList.add(violationDO);
            } else {
                insertList.add(violationDO);
            }
        }
        trafficViolationService.batchSave(insertList);
        trafficViolationService.batchUpdate(updateList);

        insert = insert + insertList.size();
        update = update + updateList.size();
    }
}
