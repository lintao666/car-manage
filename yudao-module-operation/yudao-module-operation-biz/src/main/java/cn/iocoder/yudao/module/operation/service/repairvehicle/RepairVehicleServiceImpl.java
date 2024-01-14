package cn.iocoder.yudao.module.operation.service.repairvehicle;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.operation.controller.admin.repairvehicle.vo.RepairVehicleImportRespVO;
import cn.iocoder.yudao.module.operation.controller.admin.repairvehicle.vo.RepairVehicleImportVO;
import cn.iocoder.yudao.module.operation.controller.admin.repairvehicle.vo.RepairVehiclePageReqVO;
import cn.iocoder.yudao.module.operation.controller.admin.repairvehicle.vo.RepairVehicleSaveReqVO;
import cn.iocoder.yudao.module.operation.dal.dataobject.repairvehicle.RepairVehicleDO;
import cn.iocoder.yudao.module.operation.dal.mysql.repairvehicle.RepairVehicleMapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.operation.enums.ErrorCodeConstants.REPAIR_VEHICLE_NOT_EXISTS;

/**
 * 维修 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class RepairVehicleServiceImpl implements RepairVehicleService {

    @Resource
    private RepairVehicleMapper repairVehicleMapper;

    @Override
    public Long createRepairVehicle(RepairVehicleSaveReqVO createReqVO) {
        // 插入
        RepairVehicleDO repairVehicle = BeanUtils.toBean(createReqVO, RepairVehicleDO.class);
        repairVehicleMapper.insert(repairVehicle);
        // 返回
        return repairVehicle.getId();
    }

    @Override
    @Transactional
    public void updateRepairVehicle(RepairVehicleSaveReqVO updateReqVO) {
        // 校验存在
        validateRepairVehicleExists(updateReqVO.getId());
        // 更新
        RepairVehicleDO updateObj = BeanUtils.toBean(updateReqVO, RepairVehicleDO.class);
        repairVehicleMapper.updateById(updateObj);
    }

    @Override
    public void deleteRepairVehicle(Long id) {
        // 校验存在
        validateRepairVehicleExists(id);
        // 删除
        repairVehicleMapper.deleteById(id);
    }

    private void validateRepairVehicleExists(Long id) {
        if (repairVehicleMapper.selectById(id) == null) {
            throw exception(REPAIR_VEHICLE_NOT_EXISTS);
        }
    }

    @Override
    public RepairVehicleDO getRepairVehicle(Long id) {
        return repairVehicleMapper.selectById(id);
    }

    @Override
    public PageResult<RepairVehicleDO> getRepairVehiclePage(RepairVehiclePageReqVO pageReqVO) {
        return repairVehicleMapper.selectPage(pageReqVO);
    }

    @Override
    @Transactional
    public int batchSave(List<RepairVehicleDO> list) {
        repairVehicleMapper.insertBatch(list);
        return list.size();
    }

    @Override
    @Transactional
    public int batchUpdate(List<RepairVehicleDO> list) {
        repairVehicleMapper.updateBatch(list);
        return list.size();
    }

    @Override
    public Long getIdByVehicleIdAndRepairDate(Long vehicleId, LocalDate repairDate) {
        List<RepairVehicleDO> list = repairVehicleMapper.selectList(Wrappers.<RepairVehicleDO>lambdaQuery()
                .eq(RepairVehicleDO::getVehicleId, vehicleId).eq(RepairVehicleDO::getRepairDate, repairDate));
        if(CollectionUtils.isEmpty(list)){
            return null;
        }
        return list.get(0).getId();
    }

    @Override
    public RepairVehicleImportRespVO importList(List<RepairVehicleImportVO> list, Boolean updateSupport) {
        //todo
        return null;
    }

}