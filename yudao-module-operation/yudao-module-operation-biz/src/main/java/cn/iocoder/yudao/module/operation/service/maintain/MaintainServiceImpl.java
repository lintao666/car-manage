package cn.iocoder.yudao.module.operation.service.maintain;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.operation.controller.admin.maintain.vo.MaintainPageReqVO;
import cn.iocoder.yudao.module.operation.controller.admin.maintain.vo.MaintainSaveReqVO;
import cn.iocoder.yudao.module.operation.dal.dataobject.maintain.MaintainDO;
import cn.iocoder.yudao.module.operation.dal.mysql.maintain.MaintainMapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.operation.enums.ErrorCodeConstants.MAINTAIN_NOT_EXISTS;

/**
 * 保养/二级维护 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class MaintainServiceImpl implements MaintainService {

    @Resource
    private MaintainMapper maintainMapper;

    @Override
    public Long createMaintain(MaintainSaveReqVO createReqVO) {
        // 插入
        MaintainDO maintain = BeanUtils.toBean(createReqVO, MaintainDO.class);
        maintainMapper.insert(maintain);
        // 返回
        return maintain.getId();
    }

    @Override
    public void updateMaintain(MaintainSaveReqVO updateReqVO) {
        // 校验存在
        validateMaintainExists(updateReqVO.getId());
        // 更新
        MaintainDO updateObj = BeanUtils.toBean(updateReqVO, MaintainDO.class);
        maintainMapper.updateById(updateObj);
    }

    @Override
    public void deleteMaintain(Long id) {
        // 校验存在
        validateMaintainExists(id);
        // 删除
        maintainMapper.deleteById(id);
    }

    private void validateMaintainExists(Long id) {
        if (maintainMapper.selectById(id) == null) {
            throw exception(MAINTAIN_NOT_EXISTS);
        }
    }

    @Override
    public MaintainDO getMaintain(Long id) {
        return maintainMapper.selectById(id);
    }

    @Override
    public PageResult<MaintainDO> getMaintainPage(MaintainPageReqVO pageReqVO) {
        return maintainMapper.selectPage(pageReqVO);
    }

    @Override
    public Long getIdByVehicleIdAndMaintainDate(Long vehicleId, LocalDate maintainDate) {
        List<MaintainDO> list = maintainMapper.selectList(Wrappers.<MaintainDO>lambdaQuery()
                .eq(MaintainDO::getVehicleId, vehicleId)
                .eq(MaintainDO::getMaintainDate, maintainDate));
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }
        return list.get(0).getId();
    }

    @Override
    @Transactional
    public int batchSave(List<MaintainDO> list) {
        maintainMapper.insertBatch(list);
        return list.size();
    }

    @Override
    @Transactional
    public int batchUpdate(List<MaintainDO> list) {
        maintainMapper.updateBatch(list);
        return list.size();
    }

}