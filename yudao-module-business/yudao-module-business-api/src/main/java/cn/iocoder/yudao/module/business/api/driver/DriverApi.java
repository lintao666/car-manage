package cn.iocoder.yudao.module.business.api.driver;

import cn.iocoder.yudao.module.business.api.driver.dto.DriverRespDTO;

import java.util.List;
import java.util.Optional;

/**
 * 司机Service 接口
 *
 * @author 芋道源码
 */
public interface DriverApi {

    /**
     * 查询司机id
     *
     * @param name      司机姓名
     * @param vehicleId 车辆id
     * @return 司机id
     */
    Optional<Long> getIdByVehicleIdAndName(String vehicleId, String name);
    List<DriverRespDTO> getAll();
}