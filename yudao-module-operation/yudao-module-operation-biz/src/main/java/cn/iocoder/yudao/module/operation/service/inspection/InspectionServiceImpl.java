package cn.iocoder.yudao.module.operation.service.inspection;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.operation.controller.admin.inspection.vo.*;
import cn.iocoder.yudao.module.operation.dal.dataobject.inspection.InspectionDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.operation.dal.mysql.inspection.InspectionMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.operation.enums.ErrorCodeConstants.*;

/**
 * 年审 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class InspectionServiceImpl implements InspectionService {

    @Resource
    private InspectionMapper inspectionMapper;

    @Override
    public Long createInspection(InspectionSaveReqVO createReqVO) {
        // 插入
        InspectionDO inspection = BeanUtils.toBean(createReqVO, InspectionDO.class);
        inspectionMapper.insert(inspection);
        // 返回
        return inspection.getId();
    }

    @Override
    public void updateInspection(InspectionSaveReqVO updateReqVO) {
        // 校验存在
        validateInspectionExists(updateReqVO.getId());
        // 更新
        InspectionDO updateObj = BeanUtils.toBean(updateReqVO, InspectionDO.class);
        inspectionMapper.updateById(updateObj);
    }

    @Override
    public void deleteInspection(Long id) {
        // 校验存在
        validateInspectionExists(id);
        // 删除
        inspectionMapper.deleteById(id);
    }

    private void validateInspectionExists(Long id) {
        if (inspectionMapper.selectById(id) == null) {
            throw exception(INSPECTION_NOT_EXISTS);
        }
    }

    @Override
    public InspectionDO getInspection(Long id) {
        return inspectionMapper.selectById(id);
    }

    @Override
    public PageResult<InspectionDO> getInspectionPage(InspectionPageReqVO pageReqVO) {
        return inspectionMapper.selectPage(pageReqVO);
    }

}