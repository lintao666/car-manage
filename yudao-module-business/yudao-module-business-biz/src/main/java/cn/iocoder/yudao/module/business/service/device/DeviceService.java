package cn.iocoder.yudao.module.business.service.device;

import cn.iocoder.yudao.framework.common.pojo.IdNameVO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.business.controller.admin.device.vo.DevicePageReqVO;
import cn.iocoder.yudao.module.business.controller.admin.device.vo.DeviceSaveReqVO;
import cn.iocoder.yudao.module.business.dal.dataobject.device.DeviceDO;
import org.springframework.lang.Nullable;

import javax.validation.Valid;
import java.util.Collection;
import java.util.List;

/**
 * 设备 Service 接口
 *
 * @author 芋道源码
 */
public interface DeviceService {

    /**
     * 创建设备
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createDevice(@Valid DeviceSaveReqVO createReqVO);

    /**
     * 更新设备
     *
     * @param updateReqVO 更新信息
     */
    void updateDevice(@Valid DeviceSaveReqVO updateReqVO);

    /**
     * 删除设备
     *
     * @param id 编号
     */
    void deleteDevice(Long id);

    /**
     * 获得设备
     *
     * @param id 编号
     * @return 设备
     */
    DeviceDO getDevice(Long id);

    /**
     * 获得设备分页
     *
     * @param pageReqVO 分页查询
     * @return 设备分页
     */
    PageResult<DeviceDO> getDevicePage(DevicePageReqVO pageReqVO);

    /**
     * 获得符合条件的设备列表
     *
     * @param ids      编号数组。如果为空，不进行筛选
     * @param statuses 状态数组。如果为空，不进行筛选
     * @return 设备列表
     */
    List<DeviceDO> getDeviceList(@Nullable Collection<Long> ids, @Nullable Collection<Integer> statuses);

    /**
     * 获得符合条件的设备 下拉列表
     *
     * @param ids      编号数组。如果为空，不进行筛选
     * @param statuses 状态数组。如果为空，不进行筛选
     * @return 设备下拉列表
     */
    List<IdNameVO> getSimpleList(@Nullable Collection<Long> ids, @Nullable Collection<Integer> statuses);
}