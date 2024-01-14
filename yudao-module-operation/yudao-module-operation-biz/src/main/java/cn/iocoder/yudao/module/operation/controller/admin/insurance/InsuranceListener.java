package cn.iocoder.yudao.module.operation.controller.admin.insurance;


import cn.iocoder.yudao.framework.common.util.json.JsonUtils;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.business.api.vehicle.VehicleApi;
import cn.iocoder.yudao.module.operation.controller.admin.insurance.vo.InsuranceImportRespVO;
import cn.iocoder.yudao.module.operation.controller.admin.insurance.vo.InsuranceImportVO;
import cn.iocoder.yudao.module.operation.dal.dataobject.insurance.InsuranceDO;
import cn.iocoder.yudao.module.operation.service.insurance.InsuranceService;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.listener.ReadListener;
import com.alibaba.excel.util.ListUtils;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

@Slf4j
public class InsuranceListener implements ReadListener<InsuranceImportVO> {
    /**
     * 每隔5条存储数据库，实际使用中可以100条，然后清理list,方便内存回收
     */
    private static final int BATCH_COUNT = 100;

    /**
     * 缓存的数据
     */
    private List<InsuranceImportVO> cachedList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);
    private final InsuranceService insuranceService;
    private final VehicleApi vehicleApi;

    private int insert = 0;
    private int update = 0;
    private Map<String, String> errorMap = new HashMap<>();

//    String result = "总数据行数: " + all + " 行, 成功行数: " + success + " 行, 失败行数: " + error + " 行, 新增: " + insert + " 行, 更新: " + update + " 行。";

    public InsuranceImportRespVO getImportResp() {
        return InsuranceImportRespVO.builder().creates(insert).updates(update).failureRecords(errorMap).build();
    }

    public InsuranceListener(InsuranceService insuranceService, VehicleApi vehicleApi) {
        this.insuranceService = insuranceService;
        this.vehicleApi = vehicleApi;
    }

    @Override
    public void invoke(InsuranceImportVO insuranceImportVO, AnalysisContext analysisContext) {
        log.info("解析到一条数据：{}", JsonUtils.toJsonString(insuranceImportVO));
        cachedList.add(insuranceImportVO);
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
        List<InsuranceDO> insertList = new ArrayList<>();
        List<InsuranceDO> updateList = new ArrayList<>();
        for (InsuranceImportVO lineData : cachedList) {
            //根据自编号和车牌号 判断车辆是否存在
            String carNumber = lineData.getCarNumber();

            Optional<Long> vehicleId = vehicleApi.getIdByMaskAndCarNumber(null, carNumber);
            if (!vehicleId.isPresent()) {
                errorMap.put(carNumber, "车辆信息不存在");
                continue;
            }
            InsuranceDO insuranceDO = BeanUtils.toBean(lineData, InsuranceDO.class);

            //根据车牌号和竣工日期 查询维修记录是否存在 存在即更新 不存在即新增
//            Long repairId = insuranceService.getIdByVehicleIdAndRepairDate(vehicleId.get(), repairDate);
//            if (Objects.nonNull(repairId)) {
//                insuranceDO.setId(repairId);
//                updateList.add(insuranceDO);
//            } else {
//                insertList.add(insuranceDO);
//            }
            insertList.add(insuranceDO);
        }
        insuranceService.batchSave(insertList);
//        insuranceService.batchUpdate(updateList);

        insert = insert + insertList.size();
        update = update + updateList.size();
    }
}
