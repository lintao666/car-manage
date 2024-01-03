package cn.iocoder.yudao.module.operation.dal.mysql.inspection;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.operation.dal.dataobject.inspection.InspectionDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.operation.controller.admin.inspection.vo.*;

/**
 * 年审 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface InspectionMapper extends BaseMapperX<InspectionDO> {

    default PageResult<InspectionDO> selectPage(InspectionPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<InspectionDO>()
                .eqIfPresent(InspectionDO::getInspectionType, reqVO.getInspectionType())
                .eqIfPresent(InspectionDO::getVehicleId, reqVO.getVehicleId())
                .eqIfPresent(InspectionDO::getInspectionClass, reqVO.getInspectionClass())
                .betweenIfPresent(InspectionDO::getInspectionDate, reqVO.getInspectionDate())
                .betweenIfPresent(InspectionDO::getInspectionEndDate, reqVO.getInspectionEndDate())
                .eqIfPresent(InspectionDO::getInspectionAddress, reqVO.getInspectionAddress())
                .eqIfPresent(InspectionDO::getVerifiedUserId, reqVO.getVerifiedUserId())
                .eqIfPresent(InspectionDO::getPictures, reqVO.getPictures())
                .eqIfPresent(InspectionDO::getStatus, reqVO.getStatus())
                .betweenIfPresent(InspectionDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(InspectionDO::getId));
    }

}