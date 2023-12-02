package cn.iocoder.yudao.module.business.service.vehicle;

import java.util.*;
import javax.validation.*;
import cn.iocoder.yudao.module.business.controller.admin.vehicle.vo.*;
import cn.iocoder.yudao.module.business.dal.dataobject.vehicle.VehicleDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 车辆 Service 接口
 *
 * @author 芋道源码
 */
public interface VehicleService {

    /**
     * 创建车辆
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createVehicle(@Valid VehicleSaveReqVO createReqVO);

    /**
     * 更新车辆
     *
     * @param updateReqVO 更新信息
     */
    void updateVehicle(@Valid VehicleSaveReqVO updateReqVO);

    /**
     * 删除车辆
     *
     * @param id 编号
     */
    void deleteVehicle(Long id);

    /**
     * 获得车辆
     *
     * @param id 编号
     * @return 车辆
     */
    VehicleDO getVehicle(Long id);

    /**
     * 获得车辆分页
     *
     * @param pageReqVO 分页查询
     * @return 车辆分页
     */
    PageResult<VehicleDO> getVehiclePage(VehiclePageReqVO pageReqVO);

}