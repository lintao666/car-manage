package cn.iocoder.yudao.module.operation.service.trafficaccident;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.operation.controller.admin.trafficaccident.vo.TrafficAccidentPageReqVO;
import cn.iocoder.yudao.module.operation.controller.admin.trafficaccident.vo.TrafficAccidentSaveReqVO;
import cn.iocoder.yudao.module.operation.dal.dataobject.trafficaccident.TrafficAccidentDO;
import cn.iocoder.yudao.module.operation.dal.mysql.trafficaccident.TrafficAccidentMapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.operation.enums.ErrorCodeConstants.TRAFFIC_ACCIDENT_NOT_EXISTS;

/**
 * 交通事故 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class TrafficAccidentServiceImpl implements TrafficAccidentService {

    @Resource
    private TrafficAccidentMapper trafficAccidentMapper;

    @Override
    public Long createTrafficAccident(TrafficAccidentSaveReqVO createReqVO) {
        // 插入
        TrafficAccidentDO trafficAccident = BeanUtils.toBean(createReqVO, TrafficAccidentDO.class);
        trafficAccidentMapper.insert(trafficAccident);
        // 返回
        return trafficAccident.getId();
    }

    @Override
    public void updateTrafficAccident(TrafficAccidentSaveReqVO updateReqVO) {
        // 校验存在
        validateTrafficAccidentExists(updateReqVO.getId());
        // 更新
        TrafficAccidentDO updateObj = BeanUtils.toBean(updateReqVO, TrafficAccidentDO.class);
        trafficAccidentMapper.updateById(updateObj);
    }

    @Override
    public void deleteTrafficAccident(Long id) {
        // 校验存在
        validateTrafficAccidentExists(id);
        // 删除
        trafficAccidentMapper.deleteById(id);
    }

    private void validateTrafficAccidentExists(Long id) {
        if (trafficAccidentMapper.selectById(id) == null) {
            throw exception(TRAFFIC_ACCIDENT_NOT_EXISTS);
        }
    }

    @Override
    public TrafficAccidentDO getTrafficAccident(Long id) {
        return trafficAccidentMapper.selectById(id);
    }

    @Override
    public PageResult<TrafficAccidentDO> getTrafficAccidentPage(TrafficAccidentPageReqVO pageReqVO) {
        return trafficAccidentMapper.selectPage(pageReqVO);
    }

    @Override
    public Long getIdByCondition(Long vehicleId, Long driverId, LocalDate accidentDate) {
        List<TrafficAccidentDO> list = trafficAccidentMapper.selectList(Wrappers.<TrafficAccidentDO>lambdaQuery()
                .eq(TrafficAccidentDO::getVehicleId, vehicleId)
                .eq(TrafficAccidentDO::getDriverId, driverId)
                .eq(TrafficAccidentDO::getAccidentDate, accidentDate));
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }
        return list.get(0).getId();
    }

    @Override
    @Transactional
    public int batchSave(List<TrafficAccidentDO> list) {
        trafficAccidentMapper.insertBatch(list);
        return list.size();
    }

    @Override
    @Transactional
    public int batchUpdate(List<TrafficAccidentDO> list) {
        trafficAccidentMapper.updateBatch(list);
        return list.size();
    }

}