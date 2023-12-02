package cn.iocoder.yudao.module.business.service.driver;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.annotation.Resource;

import cn.iocoder.yudao.framework.test.core.ut.BaseDbUnitTest;

import cn.iocoder.yudao.module.business.controller.admin.driver.vo.*;
import cn.iocoder.yudao.module.business.dal.dataobject.driver.DriverDO;
import cn.iocoder.yudao.module.business.dal.mysql.driver.DriverMapper;
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
 * {@link DriverServiceImpl} 的单元测试类
 *
 * @author 芋道源码
 */
@Import(DriverServiceImpl.class)
public class DriverServiceImplTest extends BaseDbUnitTest {

    @Resource
    private DriverServiceImpl driverService;

    @Resource
    private DriverMapper driverMapper;

    @Test
    public void testCreateDriver_success() {
        // 准备参数
        DriverSaveReqVO createReqVO = randomPojo(DriverSaveReqVO.class).setId(null);

        // 调用
        Long driverId = driverService.createDriver(createReqVO);
        // 断言
        assertNotNull(driverId);
        // 校验记录的属性是否正确
        DriverDO driver = driverMapper.selectById(driverId);
        assertPojoEquals(createReqVO, driver, "id");
    }

    @Test
    public void testUpdateDriver_success() {
        // mock 数据
        DriverDO dbDriver = randomPojo(DriverDO.class);
        driverMapper.insert(dbDriver);// @Sql: 先插入出一条存在的数据
        // 准备参数
        DriverSaveReqVO updateReqVO = randomPojo(DriverSaveReqVO.class, o -> {
            o.setId(dbDriver.getId()); // 设置更新的 ID
        });

        // 调用
        driverService.updateDriver(updateReqVO);
        // 校验是否更新正确
        DriverDO driver = driverMapper.selectById(updateReqVO.getId()); // 获取最新的
        assertPojoEquals(updateReqVO, driver);
    }

    @Test
    public void testUpdateDriver_notExists() {
        // 准备参数
        DriverSaveReqVO updateReqVO = randomPojo(DriverSaveReqVO.class);

        // 调用, 并断言异常
        assertServiceException(() -> driverService.updateDriver(updateReqVO), DRIVER_NOT_EXISTS);
    }

    @Test
    public void testDeleteDriver_success() {
        // mock 数据
        DriverDO dbDriver = randomPojo(DriverDO.class);
        driverMapper.insert(dbDriver);// @Sql: 先插入出一条存在的数据
        // 准备参数
        Long id = dbDriver.getId();

        // 调用
        driverService.deleteDriver(id);
       // 校验数据不存在了
       assertNull(driverMapper.selectById(id));
    }

    @Test
    public void testDeleteDriver_notExists() {
        // 准备参数
        Long id = randomLongId();

        // 调用, 并断言异常
        assertServiceException(() -> driverService.deleteDriver(id), DRIVER_NOT_EXISTS);
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetDriverPage() {
       // mock 数据
       DriverDO dbDriver = randomPojo(DriverDO.class, o -> { // 等会查询到
           o.setCompanyId(null);
           o.setName(null);
           o.setIdNumber(null);
           o.setCarNumber(null);
           o.setPhoneNumber(null);
           o.setEmergencyTelephone(null);
           o.setResidentialAddress(null);
           o.setDriverLicenseNumber(null);
           o.setDrivingClass(null);
           o.setDriverLicenseStartTime(null);
           o.setDriverLicenseExpirationTime(null);
           o.setHeadPortrait(null);
           o.setAttachment(null);
           o.setStatus(null);
           o.setCreateTime(null);
       });
       driverMapper.insert(dbDriver);
       // 测试 companyId 不匹配
       driverMapper.insert(cloneIgnoreId(dbDriver, o -> o.setCompanyId(null)));
       // 测试 name 不匹配
       driverMapper.insert(cloneIgnoreId(dbDriver, o -> o.setName(null)));
       // 测试 idNumber 不匹配
       driverMapper.insert(cloneIgnoreId(dbDriver, o -> o.setIdNumber(null)));
       // 测试 carNumber 不匹配
       driverMapper.insert(cloneIgnoreId(dbDriver, o -> o.setCarNumber(null)));
       // 测试 phoneNumber 不匹配
       driverMapper.insert(cloneIgnoreId(dbDriver, o -> o.setPhoneNumber(null)));
       // 测试 emergencyTelephone 不匹配
       driverMapper.insert(cloneIgnoreId(dbDriver, o -> o.setEmergencyTelephone(null)));
       // 测试 residentialAddress 不匹配
       driverMapper.insert(cloneIgnoreId(dbDriver, o -> o.setResidentialAddress(null)));
       // 测试 driverLicenseNumber 不匹配
       driverMapper.insert(cloneIgnoreId(dbDriver, o -> o.setDriverLicenseNumber(null)));
       // 测试 drivingClass 不匹配
       driverMapper.insert(cloneIgnoreId(dbDriver, o -> o.setDrivingClass(null)));
       // 测试 driverLicenseStartTime 不匹配
       driverMapper.insert(cloneIgnoreId(dbDriver, o -> o.setDriverLicenseStartTime(null)));
       // 测试 driverLicenseExpirationTime 不匹配
       driverMapper.insert(cloneIgnoreId(dbDriver, o -> o.setDriverLicenseExpirationTime(null)));
       // 测试 headPortrait 不匹配
       driverMapper.insert(cloneIgnoreId(dbDriver, o -> o.setHeadPortrait(null)));
       // 测试 attachment 不匹配
       driverMapper.insert(cloneIgnoreId(dbDriver, o -> o.setAttachment(null)));
       // 测试 status 不匹配
       driverMapper.insert(cloneIgnoreId(dbDriver, o -> o.setStatus(null)));
       // 测试 createTime 不匹配
       driverMapper.insert(cloneIgnoreId(dbDriver, o -> o.setCreateTime(null)));
       // 准备参数
       DriverPageReqVO reqVO = new DriverPageReqVO();
       reqVO.setCompanyId(null);
       reqVO.setName(null);
       reqVO.setIdNumber(null);
       reqVO.setCarNumber(null);
       reqVO.setPhoneNumber(null);
       reqVO.setEmergencyTelephone(null);
       reqVO.setResidentialAddress(null);
       reqVO.setDriverLicenseNumber(null);
       reqVO.setDrivingClass(null);
       reqVO.setDriverLicenseStartTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));
       reqVO.setDriverLicenseExpirationTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));
       reqVO.setHeadPortrait(null);
       reqVO.setAttachment(null);
       reqVO.setStatus(null);
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));

       // 调用
       PageResult<DriverDO> pageResult = driverService.getDriverPage(reqVO);
       // 断言
       assertEquals(1, pageResult.getTotal());
       assertEquals(1, pageResult.getList().size());
       assertPojoEquals(dbDriver, pageResult.getList().get(0));
    }

}