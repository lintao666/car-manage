package cn.iocoder.yudao.module.business.dal.mysql.driver;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.business.dal.dataobject.driver.DriverDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.business.controller.admin.driver.vo.*;

/**
 * 司机 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface DriverMapper extends BaseMapperX<DriverDO> {

    default List<DriverDO> selectList(Collection<Long> ids, Collection<Integer> statuses) {
        return selectList(new LambdaQueryWrapperX<DriverDO>()
                .inIfPresent(DriverDO::getId, ids)
                .inIfPresent(DriverDO::getStatus, statuses));
    }

    default PageResult<DriverDO> selectPage(DriverPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<DriverDO>()
                .eqIfPresent(DriverDO::getDeptId, reqVO.getDeptId())
                .likeIfPresent(DriverDO::getName, reqVO.getName())
                .eqIfPresent(DriverDO::getIdNumber, reqVO.getIdNumber())
                .eqIfPresent(DriverDO::getCarNumber, reqVO.getCarNumber())
                .eqIfPresent(DriverDO::getPhoneNumber, reqVO.getPhoneNumber())
                .eqIfPresent(DriverDO::getEmergencyTelephone, reqVO.getEmergencyTelephone())
                .eqIfPresent(DriverDO::getResidentialAddress, reqVO.getResidentialAddress())
                .eqIfPresent(DriverDO::getDriverLicenseNumber, reqVO.getDriverLicenseNumber())
                .eqIfPresent(DriverDO::getDrivingClass, reqVO.getDrivingClass())
                .betweenIfPresent(DriverDO::getDriverLicenseStartTime, reqVO.getDriverLicenseStartTime())
                .betweenIfPresent(DriverDO::getDriverLicenseExpirationTime, reqVO.getDriverLicenseExpirationTime())
                .eqIfPresent(DriverDO::getHeadPortrait, reqVO.getHeadPortrait())
                .eqIfPresent(DriverDO::getAttachment, reqVO.getAttachment())
                .eqIfPresent(DriverDO::getStatus, reqVO.getStatus())
                .betweenIfPresent(DriverDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(DriverDO::getId));
    }

}