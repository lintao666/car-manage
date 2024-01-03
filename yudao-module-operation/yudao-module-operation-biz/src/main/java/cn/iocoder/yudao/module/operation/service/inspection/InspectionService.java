package cn.iocoder.yudao.module.operation.service.inspection;

import java.util.*;
import javax.validation.*;
import cn.iocoder.yudao.module.operation.controller.admin.inspection.vo.*;
import cn.iocoder.yudao.module.operation.dal.dataobject.inspection.InspectionDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 年审 Service 接口
 *
 * @author 芋道源码
 */
public interface InspectionService {

    /**
     * 创建年审
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createInspection(@Valid InspectionSaveReqVO createReqVO);

    /**
     * 更新年审
     *
     * @param updateReqVO 更新信息
     */
    void updateInspection(@Valid InspectionSaveReqVO updateReqVO);

    /**
     * 删除年审
     *
     * @param id 编号
     */
    void deleteInspection(Long id);

    /**
     * 获得年审
     *
     * @param id 编号
     * @return 年审
     */
    InspectionDO getInspection(Long id);

    /**
     * 获得年审分页
     *
     * @param pageReqVO 分页查询
     * @return 年审分页
     */
    PageResult<InspectionDO> getInspectionPage(InspectionPageReqVO pageReqVO);

}