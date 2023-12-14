package cn.iocoder.yudao.module.business.service.device;

import cn.iocoder.yudao.framework.common.pojo.IdNameVO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.business.controller.admin.device.vo.DevicePageReqVO;
import cn.iocoder.yudao.module.business.controller.admin.device.vo.DeviceSaveReqVO;
import cn.iocoder.yudao.module.business.dal.dataobject.device.DeviceDO;
import cn.iocoder.yudao.module.business.dal.mysql.device.DeviceMapper;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.business.enums.ErrorCodeConstants.DEVICE_NOT_EXISTS;

/**
 * 设备 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class DeviceServiceImpl implements DeviceService {

    @Resource
    private DeviceMapper deviceMapper;


    @Override
    public Long createDevice(DeviceSaveReqVO createReqVO) {
        //todo 校验 设备绑定的车牌 OBD类设备只可添加没有绑定OBD类设备的车牌，2类可以添加所有车牌，可以为空
        // 插入
        DeviceDO device = BeanUtils.toBean(createReqVO, DeviceDO.class);
        deviceMapper.insert(device);
        // 返回
        return device.getId();
    }

    @Override
    public void updateDevice(DeviceSaveReqVO updateReqVO) {
        // 校验存在
        validateDeviceExists(updateReqVO.getId());
        // 更新
        DeviceDO updateObj = BeanUtils.toBean(updateReqVO, DeviceDO.class);
        deviceMapper.updateById(updateObj);
    }

    @Override
    public void deleteDevice(Long id) {
        // 校验存在
        validateDeviceExists(id);
        // 删除
        deviceMapper.deleteById(id);
    }

    private void validateDeviceExists(Long id) {
        if (deviceMapper.selectById(id) == null) {
            throw exception(DEVICE_NOT_EXISTS);
        }
    }

    @Override
    public DeviceDO getDevice(Long id) {
        return deviceMapper.selectById(id);
    }

    @Override
    public PageResult<DeviceDO> getDevicePage(DevicePageReqVO pageReqVO) {
        return deviceMapper.selectPage(pageReqVO);
    }

    @Override
    public List<DeviceDO> getDeviceList(Collection<Long> ids, Collection<Integer> statuses) {
        return deviceMapper.selectList(ids, statuses);
    }

    @Override
    public List<IdNameVO> getSimpleList(Collection<Long> ids, Collection<Integer> statuses) {
        List<DeviceDO> deviceList = getDeviceList(ids, statuses);
        return deviceList.stream().map(item -> new IdNameVO(item.getId(), item.getDeviceId())).collect(Collectors.toList());
    }

}