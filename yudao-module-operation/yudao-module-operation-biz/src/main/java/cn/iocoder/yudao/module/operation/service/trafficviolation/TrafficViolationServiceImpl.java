package cn.iocoder.yudao.module.operation.service.trafficviolation;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.operation.controller.admin.trafficviolation.vo.TrafficViolationPageReqVO;
import cn.iocoder.yudao.module.operation.controller.admin.trafficviolation.vo.TrafficViolationSaveReqVO;
import cn.iocoder.yudao.module.operation.dal.dataobject.trafficviolation.TrafficViolationDO;
import cn.iocoder.yudao.module.operation.dal.mysql.trafficviolation.TrafficViolationMapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.operation.enums.ErrorCodeConstants.TRAFFIC_VIOLATION_NOT_EXISTS;

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

    @Override
    public Long getIdByCondition(Long vehicleId, Long driverId, String plateNo) {
        List<TrafficViolationDO> list = trafficViolationMapper.selectList(Wrappers.<TrafficViolationDO>lambdaQuery()
                .eq(TrafficViolationDO::getVehicleId, vehicleId)
                .eq(TrafficViolationDO::getDriverId, driverId));
//                .eq(TrafficViolationDO::getPlateNo, plateNo));
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }
        return list.get(0).getId();
    }

    @Override
    @Transactional
    public int batchSave(List<TrafficViolationDO> list) {
        trafficViolationMapper.insertBatch(list);
        return list.size();
    }

    @Override
    @Transactional
    public int batchUpdate(List<TrafficViolationDO> list) {
        trafficViolationMapper.updateBatch(list);
        return list.size();
    }
}