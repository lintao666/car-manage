package cn.iocoder.yudao.module.business.service.vehicle;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.business.controller.admin.vehicle.vo.*;
import cn.iocoder.yudao.module.business.dal.dataobject.vehicle.VehicleDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.business.dal.mysql.vehicle.VehicleMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.business.enums.ErrorCodeConstants.*;

/**
 * 车辆 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class VehicleServiceImpl implements VehicleService {

    @Resource
    private VehicleMapper vehicleMapper;

    @Override
    public Long createVehicle(VehicleSaveReqVO createReqVO) {
        // 插入
        VehicleDO vehicle = BeanUtils.toBean(createReqVO, VehicleDO.class);
        vehicleMapper.insert(vehicle);
        // 返回
        return vehicle.getId();
    }

    @Override
    public void updateVehicle(VehicleSaveReqVO updateReqVO) {
        // 校验存在
        validateVehicleExists(updateReqVO.getId());
        // 更新
        VehicleDO updateObj = BeanUtils.toBean(updateReqVO, VehicleDO.class);
        vehicleMapper.updateById(updateObj);
    }

    @Override
    public void deleteVehicle(Long id) {
        // 校验存在
        validateVehicleExists(id);
        // 删除
        vehicleMapper.deleteById(id);
    }

    private void validateVehicleExists(Long id) {
        if (vehicleMapper.selectById(id) == null) {
            throw exception(VEHICLE_NOT_EXISTS);
        }
    }

    @Override
    public VehicleDO getVehicle(Long id) {
        return vehicleMapper.selectById(id);
    }

    @Override
    public PageResult<VehicleDO> getVehiclePage(VehiclePageReqVO pageReqVO) {
        return vehicleMapper.selectPage(pageReqVO);
    }

}