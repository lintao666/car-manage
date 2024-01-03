package cn.iocoder.yudao.module.operation.dal.dataobject.trafficaccident;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 交通事故 DO
 *
 * @author 芋道源码
 */
@TableName("operation_traffic_accident")
@KeySequence("operation_traffic_accident_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TrafficAccidentDO extends BaseDO {

    /**
     * 编号
     */
    @TableId
    private Long id;
    /**
     * 司机id
     */
    private Long driverId;
    /**
     * 车辆id
     */
    private Long vehicleId;
    /**
     * 事故时间
     */
    private LocalDateTime accidentDate;
    /**
     * 事故地点
     */
    private String place;
    /**
     * 事故责任
     *
     * 枚举 {@link TODO accident_responsibility 对应的类}
     */
    private Integer responsibility;
    /**
     * 事故类别
     */
    private String accidentCategory;
    /**
     * 处理部门
     */
    private String identificationDept;
    /**
     * 总计(事故损失)
     */
    private BigDecimal totalPay;
    /**
     * 事故级别
     *
     * 枚举 {@link TODO accident_level 对应的类}
     */
    private Integer level;
    /**
     * 事故简况
     */
    private String accidentDesc;
    /**
     * 本车损失
     */
    private BigDecimal ownVehiclePay;
    /**
     * 三者车损
     */
    private BigDecimal otherVehiclePay;
    /**
     * 医疗费
     */
    private BigDecimal hospitalPay;
    /**
     * 伤残或死亡
     */
    private BigDecimal injuredDeadPay;
    /**
     * 其他
     */
    private BigDecimal otherPay;
    /**
     * 事故相关照片
     */
    private String photos;
    /**
     * 处理状态
     *
     * 枚举 {@link TODO handle_status 对应的类}
     */
    private Integer handleStatus;
    /**
     * 结案日期
     */
    private String settlementDate;
    /**
     * 三者物损
     */
    private BigDecimal otherGoodsPay;
    /**
     * 财产损失小计
     */
    private BigDecimal vehicleTotalPay;
    /**
     * 人身损害小计
     */
    private BigDecimal personTotalPay;
    /**
     * 保险理赔记录
     */
    private String insuranceRecord;
    /**
     * 保险理赔总金额
     */
    private BigDecimal insuranceTotal;
    /**
     * 事故车辆数
     *
     * 枚举 {@link TODO vehicle_count 对应的类}
     */
    private Integer vehicleCount;
    /**
     * 受伤人数
     */
    private Short injuredPenson;
    /**
     * 死亡人数
     */
    private Short deadPerson;
    /**
     * 事故救援及处理记录
     */
    private String handleRecord;
    /**
     * 处理进度
     */
    private String handleProcess;
    /**
     * 伤亡情况
     */
    private String injuredDeadDesc;

}