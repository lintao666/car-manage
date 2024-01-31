package cn.iocoder.yudao.module.business.api.vehicle;

import cn.iocoder.yudao.module.business.api.vehicle.dto.VehicleRespDTO;

import java.util.List;
import java.util.Optional;

/**
 * 车辆 Service 接口
 *
 * @author 芋道源码
 */
public interface VehicleApi {

    /**
     * 查询车辆id
     *
     * @param vehicleMask 自编号
     * @param carNumber   车牌号
     * @return 车辆id
     */
    Optional<Long> getIdByMaskAndCarNumber(String vehicleMask, String carNumber);

    List<VehicleRespDTO> list();

    VehicleRespDTO getByCarNumber(String carNumber);
    VehicleRespDTO getByMask(String vehicleMask);
}