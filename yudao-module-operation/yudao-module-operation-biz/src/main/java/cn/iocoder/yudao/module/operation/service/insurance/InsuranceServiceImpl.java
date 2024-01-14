package cn.iocoder.yudao.module.operation.service.insurance;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.operation.controller.admin.insurance.vo.InsurancePageReqVO;
import cn.iocoder.yudao.module.operation.controller.admin.insurance.vo.InsuranceSaveReqVO;
import cn.iocoder.yudao.module.operation.dal.dataobject.insurance.InsuranceDO;
import cn.iocoder.yudao.module.operation.dal.mysql.insurance.InsuranceMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.operation.enums.ErrorCodeConstants.INSURANCE_NOT_EXISTS;

/**
 * 保单 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class InsuranceServiceImpl implements InsuranceService {

    @Resource
    private InsuranceMapper insuranceMapper;

    @Override
    public Long createInsurance(InsuranceSaveReqVO createReqVO) {
        // 插入
        InsuranceDO insurance = BeanUtils.toBean(createReqVO, InsuranceDO.class);
        insuranceMapper.insert(insurance);
        // 返回
        return insurance.getId();
    }

    @Override
    public void updateInsurance(InsuranceSaveReqVO updateReqVO) {
        // 校验存在
        validateInsuranceExists(updateReqVO.getId());
        // 更新
        InsuranceDO updateObj = BeanUtils.toBean(updateReqVO, InsuranceDO.class);
        insuranceMapper.updateById(updateObj);
    }

    @Override
    public void deleteInsurance(Long id) {
        // 校验存在
        validateInsuranceExists(id);
        // 删除
        insuranceMapper.deleteById(id);
    }

    private void validateInsuranceExists(Long id) {
        if (insuranceMapper.selectById(id) == null) {
            throw exception(INSURANCE_NOT_EXISTS);
        }
    }

    @Override
    public InsuranceDO getInsurance(Long id) {
        return insuranceMapper.selectById(id);
    }

    @Override
    public PageResult<InsuranceDO> getInsurancePage(InsurancePageReqVO pageReqVO) {
        return insuranceMapper.selectPage(pageReqVO);
    }

    @Override
    @Transactional
    public int batchSave(List<InsuranceDO> list) {
        insuranceMapper.insertBatch(list);
        return list.size();
    }

    @Override
    @Transactional
    public int batchUpdate(List<InsuranceDO> list) {
        insuranceMapper.updateBatch(list);
        return list.size();
    }
}