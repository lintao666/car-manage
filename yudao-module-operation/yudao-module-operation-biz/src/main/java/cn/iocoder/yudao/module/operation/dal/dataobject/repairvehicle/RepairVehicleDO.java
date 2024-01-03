package cn.iocoder.yudao.module.operation.dal.dataobject.repairvehicle;

import lombok.*;

import java.time.LocalDate;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 维修 DO
 *
 * @author 芋道源码
 */
@TableName("operation_repair_vehicle")
@KeySequence("operation_repair_vehicle_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RepairVehicleDO extends BaseDO {

    /**
     * 编号
     */
    @TableId
    private Long id;
    /**
     * 维修类别1日常维修2事故维修3设备维修
     *
     * 枚举 {@link TODO repair_type 对应的类}
     */
    private Integer repairType;
    /**
     * 车辆id
     */
    private Long vehicleId;
    /**
     * 维修厂
     */
    private String repairAddress;
    /**
     * 维修主题/维修设备
     */
    private String repairTheme;
    /**
     * 受损等级
     *
     * 枚举 {@link TODO repair_level 对应的类}
     */
    private Integer repairLevel;
    /**
     * 更换项、设备维修时：1gps,2cng,3电子密标,4计价器,5其它
     */
    private String changeProject;
    /**
     * 维修日期
     */
    private LocalDate repairDate;
    /**
     * 价格/维修费用
     */
    private Double repairMoney;
    /**
     * 1正在维修，0维修完成
     *
     * 枚举 {@link TODO repairing 对应的类}
     */
    private Integer repairing;
    /**
     * 描述
     */
    private String repairDesc;
    /**
     * 事故表id(表示交通事故维修)
     */
    private Long accidentId;

}