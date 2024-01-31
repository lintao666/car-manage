package cn.iocoder.yudao.module.gps.service.track;

import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.business.api.driver.DriverApi;
import cn.iocoder.yudao.module.business.api.driver.dto.DriverRespDTO;
import cn.iocoder.yudao.module.business.api.vehicle.VehicleApi;
import cn.iocoder.yudao.module.business.api.vehicle.dto.VehicleRespDTO;
import cn.iocoder.yudao.module.gps.controller.admin.position.vo.VehiclePositionVO;
import cn.iocoder.yudao.module.gps.dal.dataobject.position.VehiclePositionStatusDO;
import cn.iocoder.yudao.module.gps.dal.mysql.position.VehiclePositionMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class TrackService {

    @Autowired
    private VehiclePositionMapper vehiclePositionMapper;
    @Resource
    private VehicleApi vehicleApi;
    @Resource
    private DriverApi driverApi;

    public List<VehiclePositionStatusDO> getList() {
        return vehiclePositionMapper.selectList();
    }

    public List<VehiclePositionVO> list(String carNumber, String vehicleMask) {
        Map<Long, DriverRespDTO> driverMap = getDriverMap();
        List<VehicleRespDTO> list = new ArrayList<>();
        if (StringUtils.isBlank(carNumber) && StringUtils.isNoneBlank(vehicleMask)) {
            list = vehicleApi.list();
        }
        if (StringUtils.isNotBlank(carNumber)) {
            VehicleRespDTO vehicleRespDTO = vehicleApi.getByCarNumber(carNumber);
            if (Objects.nonNull(vehicleRespDTO)) {
                list = Collections.singletonList(vehicleRespDTO);
            }
        }
        if (StringUtils.isNotBlank(vehicleMask)) {
            VehicleRespDTO vehicleRespDTO = vehicleApi.getByMask(vehicleMask);
            if (Objects.nonNull(vehicleRespDTO)) {
                list = Collections.singletonList(vehicleRespDTO);
            }
        }

        List<VehiclePositionVO> voList = new ArrayList<>();
        for (VehicleRespDTO vehicle : list) {
            VehiclePositionVO vp = BeanUtils.toBean(vehicle, VehiclePositionVO.class);
            List<Long> driverIdList = vehicle.getDriverIdList();
            List<DriverRespDTO> drivers = getDriverList(driverMap, driverIdList);
            vp.setDriverList(drivers);
            voList.add(vp);
        }
        return voList;
    }

    private static List<DriverRespDTO> getDriverList(Map<Long, DriverRespDTO> driverMap, List<Long> driverIdList) {
        return driverMap.entrySet().stream().filter(entry -> driverIdList.contains(entry.getKey())).map(entry -> entry.getValue()).collect(Collectors.toList());
    }

    private Map<Long, DriverRespDTO> getDriverMap() {
        List<DriverRespDTO> driverList = driverApi.getAll();
        Map<Long, DriverRespDTO> vehicleDriverMap = driverList.stream().collect(Collectors.toMap(DriverRespDTO::getId, v -> v));
        return vehicleDriverMap;
    }
}
