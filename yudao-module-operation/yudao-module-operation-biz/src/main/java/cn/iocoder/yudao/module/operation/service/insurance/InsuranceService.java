package cn.iocoder.yudao.module.operation.service.insurance;

import java.util.*;
import javax.validation.*;
import cn.iocoder.yudao.module.operation.controller.admin.insurance.vo.*;
import cn.iocoder.yudao.module.operation.dal.dataobject.insurance.InsuranceDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 保单 Service 接口
 *
 * @author 芋道源码
 */
public interface InsuranceService {

    /**
     * 创建保单
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createInsurance(@Valid InsuranceSaveReqVO createReqVO);

    /**
     * 更新保单
     *
     * @param updateReqVO 更新信息
     */
    void updateInsurance(@Valid InsuranceSaveReqVO updateReqVO);

    /**
     * 删除保单
     *
     * @param id 编号
     */
    void deleteInsurance(Long id);

    /**
     * 获得保单
     *
     * @param id 编号
     * @return 保单
     */
    InsuranceDO getInsurance(Long id);

    /**
     * 获得保单分页
     *
     * @param pageReqVO 分页查询
     * @return 保单分页
     */
    PageResult<InsuranceDO> getInsurancePage(InsurancePageReqVO pageReqVO);

}