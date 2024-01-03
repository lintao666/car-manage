package cn.iocoder.yudao.module.operation.service.trafficaccident;

import java.util.*;
import javax.validation.*;
import cn.iocoder.yudao.module.operation.controller.admin.trafficaccident.vo.*;
import cn.iocoder.yudao.module.operation.dal.dataobject.trafficaccident.TrafficAccidentDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 交通事故 Service 接口
 *
 * @author 芋道源码
 */
public interface TrafficAccidentService {

    /**
     * 创建交通事故
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createTrafficAccident(@Valid TrafficAccidentSaveReqVO createReqVO);

    /**
     * 更新交通事故
     *
     * @param updateReqVO 更新信息
     */
    void updateTrafficAccident(@Valid TrafficAccidentSaveReqVO updateReqVO);

    /**
     * 删除交通事故
     *
     * @param id 编号
     */
    void deleteTrafficAccident(Long id);

    /**
     * 获得交通事故
     *
     * @param id 编号
     * @return 交通事故
     */
    TrafficAccidentDO getTrafficAccident(Long id);

    /**
     * 获得交通事故分页
     *
     * @param pageReqVO 分页查询
     * @return 交通事故分页
     */
    PageResult<TrafficAccidentDO> getTrafficAccidentPage(TrafficAccidentPageReqVO pageReqVO);

}