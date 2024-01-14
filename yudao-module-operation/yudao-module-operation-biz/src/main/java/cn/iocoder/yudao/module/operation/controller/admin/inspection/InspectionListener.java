package cn.iocoder.yudao.module.operation.controller.admin.inspection;


import cn.iocoder.yudao.framework.common.util.json.JsonUtils;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.business.api.vehicle.VehicleApi;
import cn.iocoder.yudao.module.operation.controller.admin.inspection.vo.InspectionImportRespVO;
import cn.iocoder.yudao.module.operation.controller.admin.inspection.vo.InspectionImportVO;
import cn.iocoder.yudao.module.operation.dal.dataobject.inspection.InspectionDO;
import cn.iocoder.yudao.module.operation.service.inspection.InspectionService;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.listener.ReadListener;
import com.alibaba.excel.util.ListUtils;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.util.*;

@Slf4j
public class InspectionListener implements ReadListener<InspectionImportVO> {
    /**
     * 每隔5条存储数据库，实际使用中可以100条，然后清理list,方便内存回收
     */
    private static final int BATCH_COUNT = 100;

    /**
     * 缓存的数据
     */
    private List<InspectionImportVO> cachedList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);
    private final InspectionService inspectionService;
    private final VehicleApi vehicleApi;

    private int insert = 0;
    private int update = 0;
    private Map<String, String> errorMap = new HashMap<>();

//    String result = "总数据行数: " + all + " 行, 成功行数: " + success + " 行, 失败行数: " + error + " 行, 新增: " + insert + " 行, 更新: " + update + " 行。";

    public InspectionImportRespVO getImportResp() {
        return InspectionImportRespVO.builder().creates(insert).updates(update).failureRecords(errorMap).build();
    }

    public InspectionListener(InspectionService inspectionService, VehicleApi vehicleApi) {
        this.inspectionService = inspectionService;
        this.vehicleApi = vehicleApi;
    }

    @Override
    public void invoke(InspectionImportVO InspectionImportVO, AnalysisContext analysisContext) {
        log.info("解析到一条数据：{}", JsonUtils.toJsonString(InspectionImportVO));
        cachedList.add(InspectionImportVO);
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
        List<InspectionDO> insertList = new ArrayList<>();
        List<InspectionDO> updateList = new ArrayList<>();
        for (InspectionImportVO lineData : cachedList) {
            //根据自编号和车牌号 判断车辆是否存在
            String vehicleMask = lineData.getVehicleMask();
            String carNumber = lineData.getCarNumber();
            LocalDate repairDate = lineData.getInspectionDate();
            Integer inspectionType = lineData.getInspectionType();

            Optional<Long> vehicleId = vehicleApi.getIdByMaskAndCarNumber(vehicleMask, carNumber);
            if (!vehicleId.isPresent()) {
                errorMap.put(carNumber, "车辆信息不存在");
                continue;
            }
            InspectionDO vehicleDo = BeanUtils.toBean(lineData, InspectionDO.class);

            //根据车牌号和年审时间 查询年审是否存在 存在即更新 不存在即新增
            Long repairId = inspectionService.getIdByCondition(vehicleId.get(), inspectionType, repairDate);
            if (Objects.nonNull(repairId)) {
                vehicleDo.setId(repairId);
                updateList.add(vehicleDo);
            } else {
                insertList.add(vehicleDo);
            }
        }
        inspectionService.batchSave(insertList);
        inspectionService.batchUpdate(updateList);

        insert = insert + insertList.size();
        update = update + updateList.size();
    }
}
