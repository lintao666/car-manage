package cn.iocoder.yudao.module.operation.dal.dataobject.trafficviolation;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 交通违法 DO
 *
 * @author 芋道源码
 */
@TableName(value = "operation_traffic_violation",autoResultMap = true)
@KeySequence("operation_traffic_violation_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TrafficViolationDO extends BaseDO {

    /**
     * 编号
     */
    @TableId
    private Long id;
    /**
     * 部门id
     */
    private Long deptId;
    /**
     * 车辆id
     */
    private Long vehicleId;
    /**
     * 驾驶员id
     */
    private Long driverId;
    /**
     * 时间
     */
    @TableField
    private LocalDateTime violationDate;
    /**
     * 计分
     */
    private Short points;
    /**
     * 罚款
     */
    private Short pay;
    /**
     * 地点
     */
    private String place;
    /**
     * 违法内容
     */
    private String violationDesc;
    /**
     * 车牌号
     */
    private String carNumber;
    /**
     * 违法代码
     */
    private String violationType;
    /**
     * 信息来源：1-非现场 2-现场
     */
    private Short source;
    /**
     * 支付状态：0-否；1-已交款
     *
     * 枚举 {@link TODO pay_status 对应的类}
     */
    private Integer payStatus;
    /**
     * 处理状态：0-未处理1-已处理
     *
     * 枚举 {@link TODO traffic_violation_handle_status 对应的类}
     */
    private Integer handleStatus;
    /**
     * 1-简易程序  2-一般程序
     */
    private Short decisionType;
    /**
     * 违法驾驶员处理记录
     */
    private String driverHandle;
    /**
     * 驾驶员检查及认识
     */
    private String driverSelfCriticism;
    /**
     * 相关人员教育培训记录
     */
    private String educationTraining;

}