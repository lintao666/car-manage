package cn.iocoder.yudao.module.business.dal.dataobject.vehicle;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 车辆 DO
 *
 * @author 芋道源码
 */
@TableName("business_vehicle")
@KeySequence("business_vehicle_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
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
    private String deviceIdList;
    /**
     * 司机列表
     */
    private String driverIdList;
    /**
     * 当前状态（非ACC状态）
     *
     * 枚举 {@link TODO vehicle_operation_state 对应的类}
     */
    private Integer currentState;
    /**
     * 附件（多张图片）
     */
    private String attachment;
    /**
     * 状态
     *
     * 枚举 {@link TODO common_status 对应的类}
     */
    private Integer status;

}