package cn.iocoder.yudao.module.business.service.device;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.annotation.Resource;

import cn.iocoder.yudao.framework.test.core.ut.BaseDbUnitTest;

import cn.iocoder.yudao.module.business.controller.admin.device.vo.*;
import cn.iocoder.yudao.module.business.dal.dataobject.device.DeviceDO;
import cn.iocoder.yudao.module.business.dal.mysql.device.DeviceMapper;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

import javax.annotation.Resource;
import org.springframework.context.annotation.Import;
import java.util.*;
import java.time.LocalDateTime;

import static cn.hutool.core.util.RandomUtil.*;
import static cn.iocoder.yudao.module.business.enums.ErrorCodeConstants.*;
import static cn.iocoder.yudao.framework.test.core.util.AssertUtils.*;
import static cn.iocoder.yudao.framework.test.core.util.RandomUtils.*;
import static cn.iocoder.yudao.framework.common.util.date.LocalDateTimeUtils.*;
import static cn.iocoder.yudao.framework.common.util.object.ObjectUtils.*;
import static cn.iocoder.yudao.framework.common.util.date.DateUtils.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * {@link DeviceServiceImpl} 的单元测试类
 *
 * @author 芋道源码
 */
@Import(DeviceServiceImpl.class)
public class DeviceServiceImplTest extends BaseDbUnitTest {

    @Resource
    private DeviceServiceImpl deviceService;

    @Resource
    private DeviceMapper deviceMapper;

    @Test
    public void testCreateDevice_success() {
        // 准备参数
        DeviceSaveReqVO createReqVO = randomPojo(DeviceSaveReqVO.class).setId(null);

        // 调用
        Long deviceId = deviceService.createDevice(createReqVO);
        // 断言
        assertNotNull(deviceId);
        // 校验记录的属性是否正确
        DeviceDO device = deviceMapper.selectById(deviceId);
        assertPojoEquals(createReqVO, device, "id");
    }

    @Test
    public void testUpdateDevice_success() {
        // mock 数据
        DeviceDO dbDevice = randomPojo(DeviceDO.class);
        deviceMapper.insert(dbDevice);// @Sql: 先插入出一条存在的数据
        // 准备参数
        DeviceSaveReqVO updateReqVO = randomPojo(DeviceSaveReqVO.class, o -> {
            o.setId(dbDevice.getId()); // 设置更新的 ID
        });

        // 调用
        deviceService.updateDevice(updateReqVO);
        // 校验是否更新正确
        DeviceDO device = deviceMapper.selectById(updateReqVO.getId()); // 获取最新的
        assertPojoEquals(updateReqVO, device);
    }

    @Test
    public void testUpdateDevice_notExists() {
        // 准备参数
        DeviceSaveReqVO updateReqVO = randomPojo(DeviceSaveReqVO.class);

        // 调用, 并断言异常
        assertServiceException(() -> deviceService.updateDevice(updateReqVO), DEVICE_NOT_EXISTS);
    }

    @Test
    public void testDeleteDevice_success() {
        // mock 数据
        DeviceDO dbDevice = randomPojo(DeviceDO.class);
        deviceMapper.insert(dbDevice);// @Sql: 先插入出一条存在的数据
        // 准备参数
        Long id = dbDevice.getId();

        // 调用
        deviceService.deleteDevice(id);
       // 校验数据不存在了
       assertNull(deviceMapper.selectById(id));
    }

    @Test
    public void testDeleteDevice_notExists() {
        // 准备参数
        Long id = randomLongId();

        // 调用, 并断言异常
        assertServiceException(() -> deviceService.deleteDevice(id), DEVICE_NOT_EXISTS);
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetDevicePage() {
       // mock 数据
       DeviceDO dbDevice = randomPojo(DeviceDO.class, o -> { // 等会查询到
           o.setDeviceId(null);
           o.setDeviceType(null);
           o.setBoundCarNumber(null);
           o.setStatus(null);
           o.setCreateTime(null);
       });
       deviceMapper.insert(dbDevice);
       // 测试 deviceId 不匹配
       deviceMapper.insert(cloneIgnoreId(dbDevice, o -> o.setDeviceId(null)));
       // 测试 deviceType 不匹配
       deviceMapper.insert(cloneIgnoreId(dbDevice, o -> o.setDeviceType(null)));
       // 测试 boundCarNumber 不匹配
       deviceMapper.insert(cloneIgnoreId(dbDevice, o -> o.setBoundCarNumber(null)));
       // 测试 status 不匹配
       deviceMapper.insert(cloneIgnoreId(dbDevice, o -> o.setStatus(null)));
       // 测试 createTime 不匹配
       deviceMapper.insert(cloneIgnoreId(dbDevice, o -> o.setCreateTime(null)));
       // 准备参数
       DevicePageReqVO reqVO = new DevicePageReqVO();
       reqVO.setDeviceId(null);
       reqVO.setDeviceType(null);
       reqVO.setBoundCarNumber(null);
       reqVO.setStatus(null);
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));

       // 调用
       PageResult<DeviceDO> pageResult = deviceService.getDevicePage(reqVO);
       // 断言
       assertEquals(1, pageResult.getTotal());
       assertEquals(1, pageResult.getList().size());
       assertPojoEquals(dbDevice, pageResult.getList().get(0));
    }

}