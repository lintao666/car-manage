package cn.iocoder.yudao.module.business.dal.mysql.device;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.business.dal.dataobject.device.DeviceDO;
import cn.iocoder.yudao.module.business.dal.dataobject.driver.DriverDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.business.controller.admin.device.vo.*;

/**
 * 设备 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface DeviceMapper extends BaseMapperX<DeviceDO> {

    default List<DeviceDO> selectList(Collection<Long> ids, Collection<Integer> statuses) {
        return selectList(new LambdaQueryWrapperX<DeviceDO>()
                .inIfPresent(DeviceDO::getId, ids)
                .inIfPresent(DeviceDO::getStatus, statuses));
    }
    default PageResult<DeviceDO> selectPage(DevicePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<DeviceDO>()
                .eqIfPresent(DeviceDO::getDeviceId, reqVO.getDeviceId())
                .eqIfPresent(DeviceDO::getDeviceType, reqVO.getDeviceType())
                .eqIfPresent(DeviceDO::getBoundCarNumber, reqVO.getBoundCarNumber())
                .eqIfPresent(DeviceDO::getStatus, reqVO.getStatus())
                .betweenIfPresent(DeviceDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(DeviceDO::getId));
    }

}