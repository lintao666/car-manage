package cn.iocoder.yudao.module.operation.controller.admin.trafficviolation.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 交通违法分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class TrafficViolationPageReqVO extends PageParam {

    @Schema(description = "部门id", example = "6581")
    private Long deptId;

    @Schema(description = "车辆id", example = "5864")
    private Long vehicleId;

    @Schema(description = "驾驶员id", example = "13552")
    private Long driverId;

    @Schema(description = "时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] violationDate;

    @Schema(description = "计分")
    private Short points;

    @Schema(description = "罚款")
    private Short pay;

    @Schema(description = "地点")
    private String place;

    @Schema(description = "违法内容")
    private String violationDesc;

    @Schema(description = "车牌号")
    private String carNumber;

    @Schema(description = "违法代码", example = "1")
    private String violationType;

    @Schema(description = "信息来源：1-非现场 2-现场")
    private Short source;

    @Schema(description = "支付状态：0-否；1-已交款", example = "1")
    private Integer payStatus;

    @Schema(description = "处理状态：0-未处理1-已处理", example = "1")
    private Integer handleStatus;

    @Schema(description = "1-简易程序  2-一般程序", example = "1")
    private Short decisionType;

    @Schema(description = "违法驾驶员处理记录")
    private String driverHandle;

    @Schema(description = "驾驶员检查及认识")
    private String driverSelfCriticism;

    @Schema(description = "相关人员教育培训记录")
    private String educationTraining;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}