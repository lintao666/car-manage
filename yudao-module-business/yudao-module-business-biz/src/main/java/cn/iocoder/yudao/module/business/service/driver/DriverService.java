package cn.iocoder.yudao.module.business.service.driver;

import java.util.*;
import javax.validation.*;
import cn.iocoder.yudao.module.business.controller.admin.driver.vo.*;
import cn.iocoder.yudao.module.business.dal.dataobject.driver.DriverDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.lang.Nullable;

/**
 * 司机 Service 接口
 *
 * @author 芋道源码
 */
public interface DriverService {

    /**
     * 创建司机
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createDriver(@Valid DriverSaveReqVO createReqVO);

    /**
     * 更新司机
     *
     * @param updateReqVO 更新信息
     */
    void updateDriver(@Valid DriverSaveReqVO updateReqVO);

    /**
     * 删除司机
     *
     * @param id 编号
     */
    void deleteDriver(Long id);

    /**
     * 获得司机
     *
     * @param id 编号
     * @return 司机
     */
    DriverDO getDriver(Long id);

    /**
     * 获得司机分页
     *
     * @param pageReqVO 分页查询
     * @return 司机分页
     */
    PageResult<DriverDO> getDriverPage(DriverPageReqVO pageReqVO);

    /**
     * 获得符合条件的司机列表
     * @param ids 司机编号数组。如果为空，不进行筛选
     * @param statuses 状态数组。如果为空，不进行筛选
     * @return 司机列表
     */
    List<DriverDO> getDriverList(@Nullable Collection<Long> ids, @Nullable Collection<Integer> statuses);


    Optional<Long> getIdByVehicleIdAndName(String vehicleId, String name);
}