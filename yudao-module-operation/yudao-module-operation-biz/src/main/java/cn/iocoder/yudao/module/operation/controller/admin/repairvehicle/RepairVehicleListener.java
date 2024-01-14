package cn.iocoder.yudao.module.operation.controller.admin.repairvehicle;


import cn.iocoder.yudao.framework.common.util.json.JsonUtils;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.business.api.vehicle.VehicleApi;
import cn.iocoder.yudao.module.operation.controller.admin.repairvehicle.vo.RepairVehicleImportRespVO;
import cn.iocoder.yudao.module.operation.controller.admin.repairvehicle.vo.RepairVehicleImportVO;
import cn.iocoder.yudao.module.operation.dal.dataobject.repairvehicle.RepairVehicleDO;
import cn.iocoder.yudao.module.operation.service.repairvehicle.RepairVehicleService;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.listener.ReadListener;
import com.alibaba.excel.util.ListUtils;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.util.*;

@Slf4j
public class RepairVehicleListener implements ReadListener<RepairVehicleImportVO> {
    /**
     * 每隔5条存储数据库，实际使用中可以100条，然后清理list,方便内存回收
     */
    private static final int BATCH_COUNT = 100;

    /**
     * 缓存的数据
     */
    private List<RepairVehicleImportVO> cachedList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);
    private final RepairVehicleService repairVehicleService;
    private final VehicleApi vehicleApi;

    private int insert = 0;
    private int update = 0;
    private Map<String, String> errorMap = new HashMap<>();

//    String result = "总数据行数: " + all + " 行, 成功行数: " + success + " 行, 失败行数: " + error + " 行, 新增: " + insert + " 行, 更新: " + update + " 行。";

    public RepairVehicleImportRespVO getImportResp() {
        return RepairVehicleImportRespVO.builder().creates(insert).updates(update).failureRecords(errorMap).build();
    }

    private RepairVehicleImportRespVO importResp;

    public RepairVehicleListener(RepairVehicleService repairVehicleService, VehicleApi vehicleApi) {
        this.repairVehicleService = repairVehicleService;
        this.vehicleApi = vehicleApi;
    }

    @Override
    public void invoke(RepairVehicleImportVO repairVehicleImportVO, AnalysisContext analysisContext) {
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
        List<RepairVehicleDO> insertList = new ArrayList<>();
        List<RepairVehicleDO> updateList = new ArrayList<>();
        for (RepairVehicleImportVO lineData : cachedList) {
            //根据自编号和车牌号 判断车辆是否存在
            String vehicleMask = lineData.getVehicleMask();
            String carNumber = lineData.getCarNumber();
            LocalDate repairDate = lineData.getRepairDate();

            Optional<Long> vehicleId = vehicleApi.getIdByMaskAndCarNumber(vehicleMask, carNumber);

            if (!vehicleId.isPresent()) {
                errorMap.put(carNumber, "车辆信息不存在");
                continue;
            }
            RepairVehicleDO vehicleDo = BeanUtils.toBean(lineData, RepairVehicleDO.class);
            vehicleDo.setRepairType(1);//日常维修

            //根据车牌号和竣工日期 查询维修记录是否存在 存在即更新 不存在即新增
            Long repairId = repairVehicleService.getIdByVehicleIdAndRepairDate(vehicleId.get(), repairDate);
            if (Objects.nonNull(repairId)) {
                vehicleDo.setId(repairId);
                updateList.add(vehicleDo);
            } else {
                insertList.add(vehicleDo);
            }
        }
        repairVehicleService.batchSave(insertList);
        repairVehicleService.batchUpdate(updateList);

        insert = insert + insertList.size();
        update = update + updateList.size();
    }
}
