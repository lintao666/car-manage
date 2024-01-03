package cn.iocoder.yudao.module.operation.service.repairvehicle;

import java.util.*;
import javax.validation.*;
import cn.iocoder.yudao.module.operation.controller.admin.repairvehicle.vo.*;
import cn.iocoder.yudao.module.operation.dal.dataobject.repairvehicle.RepairVehicleDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 维修 Service 接口
 *
 * @author 芋道源码
 */
public interface RepairVehicleService {

    /**
     * 创建维修
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createRepairVehicle(@Valid RepairVehicleSaveReqVO createReqVO);

    /**
     * 更新维修
     *
     * @param updateReqVO 更新信息
     */
    void updateRepairVehicle(@Valid RepairVehicleSaveReqVO updateReqVO);

    /**
     * 删除维修
     *
     * @param id 编号
     */
    void deleteRepairVehicle(Long id);

    /**
     * 获得维修
     *
     * @param id 编号
     * @return 维修
     */
    RepairVehicleDO getRepairVehicle(Long id);

    /**
     * 获得维修分页
     *
     * @param pageReqVO 分页查询
     * @return 维修分页
     */
    PageResult<RepairVehicleDO> getRepairVehiclePage(RepairVehiclePageReqVO pageReqVO);

}