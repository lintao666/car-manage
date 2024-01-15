package cn.iocoder.yudao.module.business.service.vehicle;

import cn.iocoder.yudao.framework.common.pojo.IdNameVO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.business.controller.admin.device.vo.DeviceSimpleVO;
import cn.iocoder.yudao.module.business.controller.admin.vehicle.vo.VehicleDetailVO;
import cn.iocoder.yudao.module.business.controller.admin.vehicle.vo.VehiclePageReqVO;
import cn.iocoder.yudao.module.business.controller.admin.vehicle.vo.VehicleRespVO;
import cn.iocoder.yudao.module.business.controller.admin.vehicle.vo.VehicleSaveReqVO;
import cn.iocoder.yudao.module.business.dal.dataobject.driver.DriverDO;
import cn.iocoder.yudao.module.business.dal.dataobject.vehicle.VehicleDO;
import cn.iocoder.yudao.module.business.dal.mysql.vehicle.VehicleMapper;
import cn.iocoder.yudao.module.business.service.device.DeviceService;
import cn.iocoder.yudao.module.business.service.driver.DriverService;
import cn.iocoder.yudao.module.system.api.dept.DeptApi;
import cn.iocoder.yudao.module.system.api.dept.dto.DeptRespDTO;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.AllArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import java.util.stream.Collectors;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.business.enums.ErrorCodeConstants.VEHICLE_NOT_EXISTS;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;

/**
 * 车辆 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
@AllArgsConstructor
public class VehicleServiceImpl implements VehicleService {

    private final VehicleMapper vehicleMapper;
    private final DeviceService deviceService;
    private final DriverService driverService;
    private final DeptApi deptApi;

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
    public VehicleDetailVO getVehicle(Long id) {
        VehicleDO vehicle = vehicleMapper.selectById(id);
        VehicleDetailVO vo = BeanUtils.toBean(vehicle, VehicleDetailVO.class);
        DeptRespDTO dept = deptApi.getDept(vehicle.getDeptId());
        List<DeviceSimpleVO> devices = deviceService.getSimpleList(vehicle.getDeviceIdList(), null);
        List<DriverDO> drivers = driverService.getDriverList(vehicle.getDriverIdList(), null);
        Map<Long, String> deviceMap = devices.stream().collect(Collectors.toMap(DeviceSimpleVO::getId, DeviceSimpleVO::getName));
        Map<Long, String> driverMap = drivers.stream().collect(Collectors.toMap(DriverDO::getId, DriverDO::getName));
        List<IdNameVO> deviceList = vehicle.getDeviceIdList().stream().map(deviceId -> new IdNameVO(deviceId, deviceMap.get(deviceId))).collect(toList());
        List<IdNameVO> driverList = vehicle.getDriverIdList().stream().map(driverId -> new IdNameVO(driverId, driverMap.get(driverId))).collect(toList());
        vo.setDeviceList(deviceList);
        vo.setDriverList(driverList);
        if (Objects.nonNull(dept)) {
            vo.setDeptName(dept.getName());
        }
        return vo;
    }

    @Override
    public PageResult<VehicleRespVO> getVehiclePage(VehiclePageReqVO pageReqVO) {
        PageResult<VehicleDO> page = vehicleMapper.selectPage(pageReqVO);
        Set<Long> deviceIds = page.getList().stream().map(VehicleDO::getDeviceIdList).flatMap(List::stream)
                .collect(toSet());
        Set<Long> driverIds = page.getList().stream().map(VehicleDO::getDriverIdList).flatMap(List::stream)
                .collect(toSet());
        Set<Long> deptIds = page.getList().stream().map(VehicleDO::getDeptId).collect(toSet());
        List<DeviceSimpleVO> devices = deviceService.getSimpleList(deviceIds, null);
        List<DriverDO> drivers = driverService.getDriverList(driverIds, null);

        Map<Long, String> deviceMap = devices.stream().collect(Collectors.toMap(DeviceSimpleVO::getId, DeviceSimpleVO::getName));
        Map<Long, String> driverMap = drivers.stream().collect(Collectors.toMap(DriverDO::getId, DriverDO::getName));
        Map<Long, DeptRespDTO> deptMap = deptApi.getDeptMap(deptIds);

        List<VehicleRespVO> list = page.getList().stream().map(item -> {
            VehicleRespVO vo = BeanUtils.toBean(item, VehicleRespVO.class);
            List<IdNameVO> deviceList = item.getDeviceIdList().stream().map(id -> new IdNameVO(id, deviceMap.get(id))).collect(toList());
            List<IdNameVO> driverList = item.getDriverIdList().stream().map(id -> new IdNameVO(id, driverMap.get(id))).collect(toList());
            vo.setDeviceList(deviceList);
            vo.setDriverList(driverList);
            vo.setDeptName(deptMap.get(vo.getDeptId()).getName());
            return vo;
        }).collect(toList());
        return new PageResult<>(list, page.getTotal());
    }

    @Override
    public Optional<Long> getIdByMaskAndCarNumber(String vehicleMask, String carNumber) {
        List<VehicleDO> list = vehicleMapper.selectList(Wrappers.<VehicleDO>lambdaQuery().eq(VehicleDO::getVehicleMask, vehicleMask).eq(VehicleDO::getCarNumber, carNumber));
        if (CollectionUtils.isEmpty(list)) {
            return Optional.empty();
        } else {
            return Optional.of(list.get(0).getId());
        }
    }

}