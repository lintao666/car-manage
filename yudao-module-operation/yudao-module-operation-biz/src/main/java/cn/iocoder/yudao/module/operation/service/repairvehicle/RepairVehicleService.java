package cn.iocoder.yudao.module.operation.service.repairvehicle;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.operation.controller.admin.repairvehicle.vo.RepairVehicleImportRespVO;
import cn.iocoder.yudao.module.operation.controller.admin.repairvehicle.vo.RepairVehicleImportVO;
import cn.iocoder.yudao.module.operation.controller.admin.repairvehicle.vo.RepairVehiclePageReqVO;
import cn.iocoder.yudao.module.operation.controller.admin.repairvehicle.vo.RepairVehicleSaveReqVO;
import cn.iocoder.yudao.module.operation.dal.dataobject.repairvehicle.RepairVehicleDO;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

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


    /**
     * 批量新增
     *
     * @param list 车辆创建信息
     * @return 新增成功条数
     */
    int batchSave(@Valid List<RepairVehicleDO> list);

    /**
     * 批量修改
     * @param list 车辆修改信息
     * @return 修改成功条数
     */
    int batchUpdate(@Valid List<RepairVehicleDO> list);

    Long getIdByVehicleIdAndRepairDate(Long vehicleId, LocalDate repairDate);

    RepairVehicleImportRespVO importList(List<RepairVehicleImportVO> list, Boolean updateSupport);
}