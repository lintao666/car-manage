package cn.iocoder.yudao.module.operation.dal.mysql.insurance;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.operation.dal.dataobject.insurance.InsuranceDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.operation.controller.admin.insurance.vo.*;

/**
 * 保单 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface InsuranceMapper extends BaseMapperX<InsuranceDO> {

    default PageResult<InsuranceDO> selectPage(InsurancePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<InsuranceDO>()
                .eqIfPresent(InsuranceDO::getInsuranceType, reqVO.getInsuranceType())
                .eqIfPresent(InsuranceDO::getVehicleId, reqVO.getVehicleId())
                .eqIfPresent(InsuranceDO::getInsuranceNumber, reqVO.getInsuranceNumber())
                .eqIfPresent(InsuranceDO::getInsuranceCompany, reqVO.getInsuranceCompany())
                .eqIfPresent(InsuranceDO::getInsuranceFee, reqVO.getInsuranceFee())
                .betweenIfPresent(InsuranceDO::getStartDate, reqVO.getStartDate())
                .betweenIfPresent(InsuranceDO::getEndDate, reqVO.getEndDate())
                .eqIfPresent(InsuranceDO::getPictures, reqVO.getPictures())
                .betweenIfPresent(InsuranceDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(InsuranceDO::getId));
    }

}