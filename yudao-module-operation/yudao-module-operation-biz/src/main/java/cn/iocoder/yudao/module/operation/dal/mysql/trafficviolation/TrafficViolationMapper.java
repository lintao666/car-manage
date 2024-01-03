package cn.iocoder.yudao.module.operation.dal.mysql.trafficviolation;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.operation.dal.dataobject.trafficviolation.TrafficViolationDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.operation.controller.admin.trafficviolation.vo.*;

/**
 * 交通违法 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface TrafficViolationMapper extends BaseMapperX<TrafficViolationDO> {

    default PageResult<TrafficViolationDO> selectPage(TrafficViolationPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<TrafficViolationDO>()
                .eqIfPresent(TrafficViolationDO::getDeptId, reqVO.getDeptId())
                .eqIfPresent(TrafficViolationDO::getVehicleId, reqVO.getVehicleId())
                .eqIfPresent(TrafficViolationDO::getDriverId, reqVO.getDriverId())
                .betweenIfPresent(TrafficViolationDO::getViolationDate, reqVO.getViolationDate())
                .eqIfPresent(TrafficViolationDO::getPoints, reqVO.getPoints())
                .eqIfPresent(TrafficViolationDO::getPay, reqVO.getPay())
                .eqIfPresent(TrafficViolationDO::getPlace, reqVO.getPlace())
                .eqIfPresent(TrafficViolationDO::getViolationDesc, reqVO.getViolationDesc())
                .eqIfPresent(TrafficViolationDO::getCarNumber, reqVO.getCarNumber())
                .eqIfPresent(TrafficViolationDO::getViolationType, reqVO.getViolationType())
                .eqIfPresent(TrafficViolationDO::getSource, reqVO.getSource())
                .eqIfPresent(TrafficViolationDO::getPayStatus, reqVO.getPayStatus())
                .eqIfPresent(TrafficViolationDO::getHandleStatus, reqVO.getHandleStatus())
                .eqIfPresent(TrafficViolationDO::getDecisionType, reqVO.getDecisionType())
                .eqIfPresent(TrafficViolationDO::getDriverHandle, reqVO.getDriverHandle())
                .eqIfPresent(TrafficViolationDO::getDriverSelfCriticism, reqVO.getDriverSelfCriticism())
                .eqIfPresent(TrafficViolationDO::getEducationTraining, reqVO.getEducationTraining())
                .betweenIfPresent(TrafficViolationDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(TrafficViolationDO::getId));
    }

}