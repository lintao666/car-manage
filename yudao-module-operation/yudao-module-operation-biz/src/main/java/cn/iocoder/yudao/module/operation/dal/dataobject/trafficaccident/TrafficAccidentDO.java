package cn.iocoder.yudao.module.operation.dal.dataobject.trafficaccident;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import cn.iocoder.yudao.module.operation.controller.admin.trafficaccident.vo.HandleRecord;
import cn.iocoder.yudao.module.operation.controller.admin.trafficaccident.vo.InsuranceRecord;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 交通事故 DO
 *
 * @author 芋道源码
 */
@TableName(value = "operation_traffic_accident",autoResultMap = true)
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
     * 车辆自编号
     */
    private String vehicleMask;
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
     * <p>
     * 枚举 {@link TODO accident_responsibility 对应的类}
     */
    private Integer responsibility;
    /**
     * 事故类别
     */
    private String accidentCategory;
    /**
     * 认定书号
     */
    private String identificationRecordNum;
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
     * <p>
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
     * <p>
     * 枚举 {@link TODO handle_status 对应的类}
     */
    private Integer handleStatus;
    /**
     * 结案日期
     */
    private LocalDate settlementDate;
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
    @TableField(typeHandler = JacksonTypeHandler.class)
    private InsuranceRecord[] insuranceRecord;
    /**
     * 保险理赔总金额
     */
    private BigDecimal insuranceTotal;
    /**
     * 事故车辆数
     * <p>
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
    @TableField(typeHandler = JacksonTypeHandler.class)
    private HandleRecord[] handleRecord;
    /**
     * 处理进度
     */
    private String handleProcess;
    /**
     * 伤亡情况
     */
    private String injuredDeadDesc;

}