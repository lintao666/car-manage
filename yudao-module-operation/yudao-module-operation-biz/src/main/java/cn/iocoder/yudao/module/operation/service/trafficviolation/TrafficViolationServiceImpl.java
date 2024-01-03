package cn.iocoder.yudao.module.operation.service.trafficviolation;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.operation.controller.admin.trafficviolation.vo.*;
import cn.iocoder.yudao.module.operation.dal.dataobject.trafficviolation.TrafficViolationDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.operation.dal.mysql.trafficviolation.TrafficViolationMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.operation.enums.ErrorCodeConstants.*;

/**
 * 交通违法 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class TrafficViolationServiceImpl implements TrafficViolationService {

    @Resource
    private TrafficViolationMapper trafficViolationMapper;

    @Override
    public Long createTrafficViolation(TrafficViolationSaveReqVO createReqVO) {
        // 插入
        TrafficViolationDO trafficViolation = BeanUtils.toBean(createReqVO, TrafficViolationDO.class);
        trafficViolationMapper.insert(trafficViolation);
        // 返回
        return trafficViolation.getId();
    }

    @Override
    public void updateTrafficViolation(TrafficViolationSaveReqVO updateReqVO) {
        // 校验存在
        validateTrafficViolationExists(updateReqVO.getId());
        // 更新
        TrafficViolationDO updateObj = BeanUtils.toBean(updateReqVO, TrafficViolationDO.class);
        trafficViolationMapper.updateById(updateObj);
    }

    @Override
    public void deleteTrafficViolation(Long id) {
        // 校验存在
        validateTrafficViolationExists(id);
        // 删除
        trafficViolationMapper.deleteById(id);
    }

    private void validateTrafficViolationExists(Long id) {
        if (trafficViolationMapper.selectById(id) == null) {
            throw exception(TRAFFIC_VIOLATION_NOT_EXISTS);
        }
    }

    @Override
    public TrafficViolationDO getTrafficViolation(Long id) {
        return trafficViolationMapper.selectById(id);
    }

    @Override
    public PageResult<TrafficViolationDO> getTrafficViolationPage(TrafficViolationPageReqVO pageReqVO) {
        return trafficViolationMapper.selectPage(pageReqVO);
    }

}