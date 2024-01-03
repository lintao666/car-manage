package cn.iocoder.yudao.module.operation.dal.mysql.trafficaccident;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.operation.dal.dataobject.trafficaccident.TrafficAccidentDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.operation.controller.admin.trafficaccident.vo.*;

/**
 * 交通事故 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface TrafficAccidentMapper extends BaseMapperX<TrafficAccidentDO> {

    default PageResult<TrafficAccidentDO> selectPage(TrafficAccidentPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<TrafficAccidentDO>()
                .eqIfPresent(TrafficAccidentDO::getDriverId, reqVO.getDriverId())
                .eqIfPresent(TrafficAccidentDO::getVehicleId, reqVO.getVehicleId())
                .betweenIfPresent(TrafficAccidentDO::getAccidentDate, reqVO.getAccidentDate())
                .eqIfPresent(TrafficAccidentDO::getPlace, reqVO.getPlace())
                .eqIfPresent(TrafficAccidentDO::getResponsibility, reqVO.getResponsibility())
                .eqIfPresent(TrafficAccidentDO::getAccidentCategory, reqVO.getAccidentCategory())
                .eqIfPresent(TrafficAccidentDO::getIdentificationDept, reqVO.getIdentificationDept())
                .eqIfPresent(TrafficAccidentDO::getTotalPay, reqVO.getTotalPay())
                .eqIfPresent(TrafficAccidentDO::getLevel, reqVO.getLevel())
                .eqIfPresent(TrafficAccidentDO::getAccidentDesc, reqVO.getAccidentDesc())
                .eqIfPresent(TrafficAccidentDO::getOwnVehiclePay, reqVO.getOwnVehiclePay())
                .eqIfPresent(TrafficAccidentDO::getOtherVehiclePay, reqVO.getOtherVehiclePay())
                .eqIfPresent(TrafficAccidentDO::getHospitalPay, reqVO.getHospitalPay())
                .eqIfPresent(TrafficAccidentDO::getInjuredDeadPay, reqVO.getInjuredDeadPay())
                .eqIfPresent(TrafficAccidentDO::getOtherPay, reqVO.getOtherPay())
                .eqIfPresent(TrafficAccidentDO::getPhotos, reqVO.getPhotos())
                .eqIfPresent(TrafficAccidentDO::getHandleStatus, reqVO.getHandleStatus())
                .betweenIfPresent(TrafficAccidentDO::getSettlementDate, reqVO.getSettlementDate())
                .eqIfPresent(TrafficAccidentDO::getOtherGoodsPay, reqVO.getOtherGoodsPay())
                .eqIfPresent(TrafficAccidentDO::getVehicleTotalPay, reqVO.getVehicleTotalPay())
                .eqIfPresent(TrafficAccidentDO::getPersonTotalPay, reqVO.getPersonTotalPay())
                .eqIfPresent(TrafficAccidentDO::getInsuranceRecord, reqVO.getInsuranceRecord())
                .eqIfPresent(TrafficAccidentDO::getInsuranceTotal, reqVO.getInsuranceTotal())
                .eqIfPresent(TrafficAccidentDO::getVehicleCount, reqVO.getVehicleCount())
                .eqIfPresent(TrafficAccidentDO::getInjuredPenson, reqVO.getInjuredPenson())
                .eqIfPresent(TrafficAccidentDO::getDeadPerson, reqVO.getDeadPerson())
                .eqIfPresent(TrafficAccidentDO::getHandleRecord, reqVO.getHandleRecord())
                .eqIfPresent(TrafficAccidentDO::getHandleProcess, reqVO.getHandleProcess())
                .eqIfPresent(TrafficAccidentDO::getInjuredDeadDesc, reqVO.getInjuredDeadDesc())
                .betweenIfPresent(TrafficAccidentDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(TrafficAccidentDO::getId));
    }

}