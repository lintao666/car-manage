package cn.iocoder.yudao.module.business.dal.mysql.vehicle;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.business.dal.dataobject.vehicle.VehicleDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.business.controller.admin.vehicle.vo.*;

/**
 * 车辆 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface VehicleMapper extends BaseMapperX<VehicleDO> {

    default PageResult<VehicleDO> selectPage(VehiclePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<VehicleDO>()
                .eqIfPresent(VehicleDO::getCompanyId, reqVO.getCompanyId())
                .eqIfPresent(VehicleDO::getCarNumber, reqVO.getCarNumber())
                .eqIfPresent(VehicleDO::getBrand, reqVO.getBrand())
                .eqIfPresent(VehicleDO::getVehicleModel, reqVO.getVehicleModel())
                .eqIfPresent(VehicleDO::getEnergyType, reqVO.getEnergyType())
                .eqIfPresent(VehicleDO::getVehicleType, reqVO.getVehicleType())
                .eqIfPresent(VehicleDO::getVin, reqVO.getVin())
                .eqIfPresent(VehicleDO::getEngineNumber, reqVO.getEngineNumber())
                .eqIfPresent(VehicleDO::getDeviceIdList, reqVO.getDeviceIdList())
                .eqIfPresent(VehicleDO::getDriverIdList, reqVO.getDriverIdList())
                .eqIfPresent(VehicleDO::getCurrentState, reqVO.getCurrentState())
                .eqIfPresent(VehicleDO::getAttachment, reqVO.getAttachment())
                .eqIfPresent(VehicleDO::getStatus, reqVO.getStatus())
                .betweenIfPresent(VehicleDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(VehicleDO::getId));
    }

}