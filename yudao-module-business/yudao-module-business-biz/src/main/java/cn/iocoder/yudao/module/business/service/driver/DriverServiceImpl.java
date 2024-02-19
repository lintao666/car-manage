package cn.iocoder.yudao.module.business.service.driver;

import cn.hutool.core.bean.BeanUtil;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.business.controller.admin.driver.vo.DriverPageReqVO;
import cn.iocoder.yudao.module.business.controller.admin.driver.vo.DriverRespVO;
import cn.iocoder.yudao.module.business.controller.admin.driver.vo.DriverSaveReqVO;
import cn.iocoder.yudao.module.business.dal.dataobject.driver.DriverDO;
import cn.iocoder.yudao.module.business.dal.dataobject.vehicle.VehicleDO;
import cn.iocoder.yudao.module.business.dal.mysql.driver.DriverMapper;
import cn.iocoder.yudao.module.system.api.dept.DeptApi;
import cn.iocoder.yudao.module.system.api.dept.dto.DeptRespDTO;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.AllArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.*;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.business.enums.ErrorCodeConstants.DRIVER_NOT_EXISTS;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;

/**
 * 司机 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
@AllArgsConstructor
public class DriverServiceImpl implements DriverService {

    private final DriverMapper driverMapper;
    private final DeptApi deptApi;

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
    public PageResult<DriverRespVO> getDriverPage(DriverPageReqVO pageReqVO) {
        PageResult<DriverDO> page = driverMapper.selectPage(pageReqVO);
        Set<Long> deptIds = page.getList().stream().map(DriverDO::getDeptId).collect(toSet());
        Map<Long, DeptRespDTO> deptMap = deptApi.getDeptMap(deptIds);
        List<DriverRespVO> list = page.getList().stream().map(item -> {
            DriverRespVO vo = BeanUtils.toBean(item, DriverRespVO.class);
            vo.setDeptName(deptMap.get(vo.getDeptId()).getName());
            return vo;
        }).collect(toList());
        return new PageResult<>(list, page.getTotal());
    }

    @Override
    public List<DriverDO> getDriverList(Collection<Long> ids, Collection<Integer> statuses) {
        return driverMapper.selectList(ids, statuses);
    }

    @Override
    public Optional<Long> getIdByVehicleIdAndName(String vehicleId, String name) {
        List<DriverDO> list = driverMapper.selectList(Wrappers.<DriverDO>lambdaQuery().eq(DriverDO::getCarNumber, vehicleId).eq(DriverDO::getName, name));
        if (CollectionUtils.isEmpty(list)) {
            return Optional.empty();
        } else {
            return Optional.of(list.get(0).getId());
        }
    }

    @Override
    public List<DriverRespVO> getList(Long deptId) {
        LambdaQueryWrapper<DriverDO> query = Wrappers.<DriverDO>lambdaQuery().eq(Objects.nonNull(deptId), DriverDO::getDeptId, deptId);
        List<DriverDO> list = driverMapper.selectList(query);
        return BeanUtil.copyToList(list, DriverRespVO.class);
    }

}