package cn.iocoder.yudao.module.operation.service.trafficviolation;

import java.util.*;
import javax.validation.*;
import cn.iocoder.yudao.module.operation.controller.admin.trafficviolation.vo.*;
import cn.iocoder.yudao.module.operation.dal.dataobject.trafficviolation.TrafficViolationDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 交通违法 Service 接口
 *
 * @author 芋道源码
 */
public interface TrafficViolationService {

    /**
     * 创建交通违法
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createTrafficViolation(@Valid TrafficViolationSaveReqVO createReqVO);

    /**
     * 更新交通违法
     *
     * @param updateReqVO 更新信息
     */
    void updateTrafficViolation(@Valid TrafficViolationSaveReqVO updateReqVO);

    /**
     * 删除交通违法
     *
     * @param id 编号
     */
    void deleteTrafficViolation(Long id);

    /**
     * 获得交通违法
     *
     * @param id 编号
     * @return 交通违法
     */
    TrafficViolationDO getTrafficViolation(Long id);

    /**
     * 获得交通违法分页
     *
     * @param pageReqVO 分页查询
     * @return 交通违法分页
     */
    PageResult<TrafficViolationDO> getTrafficViolationPage(TrafficViolationPageReqVO pageReqVO);

}