package cn.iocoder.yudao.module.business.dal.dataobject.vehicle;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import cn.iocoder.yudao.framework.mybatis.core.type.LongListTypeHandler;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import lombok.*;

import java.util.List;

/**
 * 车辆 DO
 *
 * @author 芋道源码
 */
@TableName(value = "business_vehicle", autoResultMap = true)
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VehicleDO extends BaseDO {

    /**
     * 编号
     */
    @TableId
    private Long id;
    /**
     * 所属分公司
     */
    private Long companyId;
    /**
     * 部门id
     */
    private Long deptId;
    /**
     * 自编号
     */
    private String vehicleMask;
    /**
     * 车牌号
     */
    private String carNumber;
    /**
     * 车辆品牌
     */
    private String brand;
    /**
     * 车型
     */
    private Integer vehicleModel;
    /**
     * 车辆能源类型
     */
    private Integer energyType;
    /**
     * 车辆类型
     */
    private Integer vehicleType;
    /**
     * 车架号
     */
    private String vin;
    /**
     * 发动机号
     */
    private String engineNumber;
    /**
     * 设备列表
     */
    @TableField(typeHandler = LongListTypeHandler.class)
    private List<Long> deviceIdList;
    /**
     * 司机列表
     */
    @TableField(typeHandler = LongListTypeHandler.class)
    private List<Long> driverIdList;
    /**
     * 当前状态（非ACC状态）
     * <p>
     * 枚举 {@link TODO vehicle_operation_state 对应的类}
     */
    private Integer currentState;
    /**
     * 附件（多张图片）
     */
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<String> attachment;
    /**
     * 状态
     * <p>
     * 枚举 {@link TODO common_status 对应的类}
     */
    private Integer status;

    /**
     * 保养时间周期(月)
     */
    private Byte maintainMonths;
    /**
     * 保养里程周期(KM)
     */
    private Integer maintainMileages;


}