package cn.iocoder.yudao.module.operation.service.maintain;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.operation.controller.admin.maintain.vo.*;
import cn.iocoder.yudao.module.operation.dal.dataobject.maintain.MaintainDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.operation.dal.mysql.maintain.MaintainMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.operation.enums.ErrorCodeConstants.*;

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

}