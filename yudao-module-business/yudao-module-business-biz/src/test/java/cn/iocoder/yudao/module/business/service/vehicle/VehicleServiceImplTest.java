package cn.iocoder.yudao.module.business.service.vehicle;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.annotation.Resource;

import cn.iocoder.yudao.framework.test.core.ut.BaseDbUnitTest;

import cn.iocoder.yudao.module.business.controller.admin.vehicle.vo.*;
import cn.iocoder.yudao.module.business.dal.dataobject.vehicle.VehicleDO;
import cn.iocoder.yudao.module.business.dal.mysql.vehicle.VehicleMapper;
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
 * {@link VehicleServiceImpl} 的单元测试类
 *
 * @author 芋道源码
 */
@Import(VehicleServiceImpl.class)
public class VehicleServiceImplTest extends BaseDbUnitTest {

    @Resource
    private VehicleServiceImpl vehicleService;

    @Resource
    private VehicleMapper vehicleMapper;

    @Test
    public void testCreateVehicle_success() {
        // 准备参数
        VehicleSaveReqVO createReqVO = randomPojo(VehicleSaveReqVO.class).setId(null);

        // 调用
        Long vehicleId = vehicleService.createVehicle(createReqVO);
        // 断言
        assertNotNull(vehicleId);
        // 校验记录的属性是否正确
        VehicleDO vehicle = vehicleMapper.selectById(vehicleId);
        assertPojoEquals(createReqVO, vehicle, "id");
    }

    @Test
    public void testUpdateVehicle_success() {
        // mock 数据
        VehicleDO dbVehicle = randomPojo(VehicleDO.class);
        vehicleMapper.insert(dbVehicle);// @Sql: 先插入出一条存在的数据
        // 准备参数
        VehicleSaveReqVO updateReqVO = randomPojo(VehicleSaveReqVO.class, o -> {
            o.setId(dbVehicle.getId()); // 设置更新的 ID
        });

        // 调用
        vehicleService.updateVehicle(updateReqVO);
        // 校验是否更新正确
        VehicleDO vehicle = vehicleMapper.selectById(updateReqVO.getId()); // 获取最新的
        assertPojoEquals(updateReqVO, vehicle);
    }

    @Test
    public void testUpdateVehicle_notExists() {
        // 准备参数
        VehicleSaveReqVO updateReqVO = randomPojo(VehicleSaveReqVO.class);

        // 调用, 并断言异常
        assertServiceException(() -> vehicleService.updateVehicle(updateReqVO), VEHICLE_NOT_EXISTS);
    }

    @Test
    public void testDeleteVehicle_success() {
        // mock 数据
        VehicleDO dbVehicle = randomPojo(VehicleDO.class);
        vehicleMapper.insert(dbVehicle);// @Sql: 先插入出一条存在的数据
        // 准备参数
        Long id = dbVehicle.getId();

        // 调用
        vehicleService.deleteVehicle(id);
       // 校验数据不存在了
       assertNull(vehicleMapper.selectById(id));
    }

    @Test
    public void testDeleteVehicle_notExists() {
        // 准备参数
        Long id = randomLongId();

        // 调用, 并断言异常
        assertServiceException(() -> vehicleService.deleteVehicle(id), VEHICLE_NOT_EXISTS);
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetVehiclePage() {
       // mock 数据
       VehicleDO dbVehicle = randomPojo(VehicleDO.class, o -> { // 等会查询到
           o.setCompanyId(null);
           o.setCarNumber(null);
           o.setBrand(null);
           o.setVehicleModel(null);
           o.setEnergyType(null);
           o.setVehicleType(null);
           o.setVin(null);
           o.setEngineNumber(null);
           o.setDeviceIdList(null);
           o.setDriverIdList(null);
           o.setCurrentState(null);
           o.setAttachment(null);
           o.setStatus(null);
           o.setCreateTime(null);
       });
       vehicleMapper.insert(dbVehicle);
       // 测试 companyId 不匹配
       vehicleMapper.insert(cloneIgnoreId(dbVehicle, o -> o.setCompanyId(null)));
       // 测试 carNumber 不匹配
       vehicleMapper.insert(cloneIgnoreId(dbVehicle, o -> o.setCarNumber(null)));
       // 测试 brand 不匹配
       vehicleMapper.insert(cloneIgnoreId(dbVehicle, o -> o.setBrand(null)));
       // 测试 vehicleModel 不匹配
       vehicleMapper.insert(cloneIgnoreId(dbVehicle, o -> o.setVehicleModel(null)));
       // 测试 energyType 不匹配
       vehicleMapper.insert(cloneIgnoreId(dbVehicle, o -> o.setEnergyType(null)));
       // 测试 vehicleType 不匹配
       vehicleMapper.insert(cloneIgnoreId(dbVehicle, o -> o.setVehicleType(null)));
       // 测试 vin 不匹配
       vehicleMapper.insert(cloneIgnoreId(dbVehicle, o -> o.setVin(null)));
       // 测试 engineNumber 不匹配
       vehicleMapper.insert(cloneIgnoreId(dbVehicle, o -> o.setEngineNumber(null)));
       // 测试 deviceIdList 不匹配
       vehicleMapper.insert(cloneIgnoreId(dbVehicle, o -> o.setDeviceIdList(null)));
       // 测试 driverIdList 不匹配
       vehicleMapper.insert(cloneIgnoreId(dbVehicle, o -> o.setDriverIdList(null)));
       // 测试 currentState 不匹配
       vehicleMapper.insert(cloneIgnoreId(dbVehicle, o -> o.setCurrentState(null)));
       // 测试 attachment 不匹配
       vehicleMapper.insert(cloneIgnoreId(dbVehicle, o -> o.setAttachment(null)));
       // 测试 status 不匹配
       vehicleMapper.insert(cloneIgnoreId(dbVehicle, o -> o.setStatus(null)));
       // 测试 createTime 不匹配
       vehicleMapper.insert(cloneIgnoreId(dbVehicle, o -> o.setCreateTime(null)));
       // 准备参数
       VehiclePageReqVO reqVO = new VehiclePageReqVO();
       reqVO.setCompanyId(null);
       reqVO.setCarNumber(null);
       reqVO.setBrand(null);
       reqVO.setVehicleModel(null);
       reqVO.setEnergyType(null);
       reqVO.setVehicleType(null);
       reqVO.setVin(null);
       reqVO.setEngineNumber(null);
       reqVO.setDeviceIdList(null);
       reqVO.setDriverIdList(null);
       reqVO.setCurrentState(null);
       reqVO.setAttachment(null);
       reqVO.setStatus(null);
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));

       // 调用
       PageResult<VehicleDO> pageResult = vehicleService.getVehiclePage(reqVO);
       // 断言
       assertEquals(1, pageResult.getTotal());
       assertEquals(1, pageResult.getList().size());
       assertPojoEquals(dbVehicle, pageResult.getList().get(0));
    }

}