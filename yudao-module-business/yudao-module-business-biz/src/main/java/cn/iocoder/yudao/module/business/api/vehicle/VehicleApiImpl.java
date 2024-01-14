package cn.iocoder.yudao.module.business.api.vehicle;

import cn.iocoder.yudao.module.business.service.vehicle.VehicleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Optional;

@Service
public class VehicleApiImpl implements VehicleApi {
    @Resource
    private VehicleService vehicleService;

    @Override
    public Optional<Long> getIdByMaskAndCarNumber(String vehicleMask, String carNumber) {
        return vehicleService.getIdByMaskAndCarNumber(vehicleMask, carNumber);
    }
}
