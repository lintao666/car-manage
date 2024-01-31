package cn.iocoder.yudao.module.business.api.vehicle;

import cn.hutool.core.bean.BeanUtil;
import cn.iocoder.yudao.module.business.api.vehicle.dto.VehicleRespDTO;
import cn.iocoder.yudao.module.business.controller.admin.vehicle.vo.VehicleRespVO;
import cn.iocoder.yudao.module.business.dal.dataobject.vehicle.VehicleDO;
import cn.iocoder.yudao.module.business.service.vehicle.VehicleService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

@Service
public class VehicleApiImpl implements VehicleApi {
    @Resource
    private VehicleService vehicleService;

    @Override
    public Optional<Long> getIdByMaskAndCarNumber(String vehicleMask, String carNumber) {
        return vehicleService.getIdByMaskAndCarNumber(vehicleMask, carNumber);
    }

    @Override
    public List<VehicleRespDTO> list() {
        List<VehicleRespVO> list = vehicleService.getList(null);
        return BeanUtil.copyToList(list, VehicleRespDTO.class);
    }

    @Override
    public VehicleRespDTO getByCarNumber(String carNumber) {
        if (StringUtils.isNoneBlank(carNumber)) {
            VehicleDO vehicleDO = vehicleService.getByCarNumber(carNumber);
            return BeanUtil.toBean(vehicleDO, VehicleRespDTO.class);
        }
        return null;
    }

    @Override
    public VehicleRespDTO getByMask(String vehicleMask) {
        if (StringUtils.isNoneBlank(vehicleMask)) {
            VehicleDO vehicleDO = vehicleService.getByMask(vehicleMask);
            return BeanUtil.toBean(vehicleDO, VehicleRespDTO.class);
        }
        return null;
    }
}
