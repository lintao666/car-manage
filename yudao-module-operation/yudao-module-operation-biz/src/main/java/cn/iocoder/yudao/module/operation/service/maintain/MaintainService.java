package cn.iocoder.yudao.module.operation.service.maintain;

import java.util.*;
import javax.validation.*;
import cn.iocoder.yudao.module.operation.controller.admin.maintain.vo.*;
import cn.iocoder.yudao.module.operation.dal.dataobject.maintain.MaintainDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 保养/二级维护 Service 接口
 *
 * @author 芋道源码
 */
public interface MaintainService {

    /**
     * 创建保养/二级维护
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createMaintain(@Valid MaintainSaveReqVO createReqVO);

    /**
     * 更新保养/二级维护
     *
     * @param updateReqVO 更新信息
     */
    void updateMaintain(@Valid MaintainSaveReqVO updateReqVO);

    /**
     * 删除保养/二级维护
     *
     * @param id 编号
     */
    void deleteMaintain(Long id);

    /**
     * 获得保养/二级维护
     *
     * @param id 编号
     * @return 保养/二级维护
     */
    MaintainDO getMaintain(Long id);

    /**
     * 获得保养/二级维护分页
     *
     * @param pageReqVO 分页查询
     * @return 保养/二级维护分页
     */
    PageResult<MaintainDO> getMaintainPage(MaintainPageReqVO pageReqVO);

}