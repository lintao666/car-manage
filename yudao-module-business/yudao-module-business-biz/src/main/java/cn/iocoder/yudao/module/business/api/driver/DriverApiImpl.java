package cn.iocoder.yudao.module.business.api.driver;

import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.business.api.driver.dto.DriverRespDTO;
import cn.iocoder.yudao.module.business.dal.dataobject.driver.DriverDO;
import cn.iocoder.yudao.module.business.service.driver.DriverService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

@Service
public class DriverApiImpl implements DriverApi {
    @Resource
    private DriverService driverService;

    @Override
    public Optional<Long> getIdByVehicleIdAndName(String vehicleId, String name) {
        return driverService.getIdByVehicleIdAndName(vehicleId, name);
    }

    @Override
    public List<DriverRespDTO> getAll() {
        List<DriverDO> driverList = driverService.getDriverList(null, null);
        return BeanUtils.toBean(driverList, DriverRespDTO.class);
    }
}
