package cn.iocoder.yudao.module.operation.dal.mysql.repairvehicle;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.operation.dal.dataobject.repairvehicle.RepairVehicleDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.operation.controller.admin.repairvehicle.vo.*;

/**
 * 维修 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface RepairVehicleMapper extends BaseMapperX<RepairVehicleDO> {

    default PageResult<RepairVehicleDO> selectPage(RepairVehiclePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<RepairVehicleDO>()
                .eqIfPresent(RepairVehicleDO::getRepairType, reqVO.getRepairType())
                .eqIfPresent(RepairVehicleDO::getVehicleId, reqVO.getVehicleId())
                .eqIfPresent(RepairVehicleDO::getRepairAddress, reqVO.getRepairAddress())
                .eqIfPresent(RepairVehicleDO::getRepairTheme, reqVO.getRepairTheme())
                .eqIfPresent(RepairVehicleDO::getRepairLevel, reqVO.getRepairLevel())
                .eqIfPresent(RepairVehicleDO::getChangeProject, reqVO.getChangeProject())
                .betweenIfPresent(RepairVehicleDO::getRepairDate, reqVO.getRepairDate())
                .eqIfPresent(RepairVehicleDO::getRepairMoney, reqVO.getRepairMoney())
                .eqIfPresent(RepairVehicleDO::getRepairing, reqVO.getRepairing())
                .eqIfPresent(RepairVehicleDO::getRepairDesc, reqVO.getRepairDesc())
                .eqIfPresent(RepairVehicleDO::getAccidentId, reqVO.getAccidentId())
                .betweenIfPresent(RepairVehicleDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(RepairVehicleDO::getId));
    }

}