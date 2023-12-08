package cn.iocoder.yudao.module.business.service.driver;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.business.controller.admin.driver.vo.*;
import cn.iocoder.yudao.module.business.dal.dataobject.driver.DriverDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.business.dal.mysql.driver.DriverMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.business.enums.ErrorCodeConstants.*;

/**
 * 司机 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class DriverServiceImpl implements DriverService {

    @Resource
    private DriverMapper driverMapper;

    @Override
    public Long createDriver(DriverSaveReqVO createReqVO) {
        // 插入
        DriverDO driver = BeanUtils.toBean(createReqVO, DriverDO.class);
        driverMapper.insert(driver);
        // 返回
        return driver.getId();
    }

    @Override
    public void updateDriver(DriverSaveReqVO updateReqVO) {
        // 校验存在
        validateDriverExists(updateReqVO.getId());
        // 更新
        DriverDO updateObj = BeanUtils.toBean(updateReqVO, DriverDO.class);
        driverMapper.updateById(updateObj);
    }

    @Override
    public void deleteDriver(Long id) {
        // 校验存在
        validateDriverExists(id);
        // 删除
        driverMapper.deleteById(id);
    }

    private void validateDriverExists(Long id) {
        if (driverMapper.selectById(id) == null) {
            throw exception(DRIVER_NOT_EXISTS);
        }
    }

    @Override
    public DriverDO getDriver(Long id) {
        return driverMapper.selectById(id);
    }

    @Override
    public PageResult<DriverDO> getDriverPage(DriverPageReqVO pageReqVO) {
        return driverMapper.selectPage(pageReqVO);
    }

    @Override
    public List<DriverDO> getDriverList(Collection<Long> ids, Collection<Integer> statuses) {
        return driverMapper.selectList(ids, statuses);
    }

}