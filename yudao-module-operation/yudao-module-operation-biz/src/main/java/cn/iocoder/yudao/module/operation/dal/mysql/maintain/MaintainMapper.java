package cn.iocoder.yudao.module.operation.dal.mysql.maintain;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.operation.dal.dataobject.maintain.MaintainDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.operation.controller.admin.maintain.vo.*;

/**
 * 保养/二级维护 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface MaintainMapper extends BaseMapperX<MaintainDO> {

    default PageResult<MaintainDO> selectPage(MaintainPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<MaintainDO>()
                .eqIfPresent(MaintainDO::getVehicleId, reqVO.getVehicleId())
                .eqIfPresent(MaintainDO::getCertificateNumber, reqVO.getCertificateNumber())
                .eqIfPresent(MaintainDO::getMaintainShop, reqVO.getMaintainShop())
                .betweenIfPresent(MaintainDO::getMaintainDate, reqVO.getMaintainDate())
                .betweenIfPresent(MaintainDO::getMaintainEndDate, reqVO.getMaintainEndDate())
                .eqIfPresent(MaintainDO::getMaintainMileage, reqVO.getMaintainMileage())
                .eqIfPresent(MaintainDO::getMaintainEndMileage, reqVO.getMaintainEndMileage())
                .eqIfPresent(MaintainDO::getPic, reqVO.getPic())
                .betweenIfPresent(MaintainDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(MaintainDO::getId));
    }

}