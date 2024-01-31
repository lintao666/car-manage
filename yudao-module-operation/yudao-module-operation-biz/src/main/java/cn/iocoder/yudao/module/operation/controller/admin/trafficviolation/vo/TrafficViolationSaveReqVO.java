package cn.iocoder.yudao.module.operation.controller.admin.trafficviolation.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import javax.validation.constraints.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 交通违法新增/修改 Request VO")
@Data
public class TrafficViolationSaveReqVO {

    @Schema(description = "编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "24349")
    private Long id;

    @Schema(description = "部门id", example = "6581")
    private Long deptId;

    @Schema(description = "车辆id", example = "5864")
    private Long vehicleId;

    @Schema(description = "驾驶员id", example = "13552")
    private Long driverId;

    @Schema(description = "违法时间")
    private LocalDateTime violationDate;

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

    @Schema(description = "信息来源：字典：traffic_violation_source")
    private Short source;

    @Schema(description = "支付状态：字典：pay_status", example = "1")
    private Integer payStatus;

    @Schema(description = "处理状态：0-未处理1-已处理", example = "1")
    private Integer handleStatus;

    @Schema(description = "决定书类别：字典：decision_type", example = "1")
    private Short decisionType;

    @Schema(description = "违法驾驶员处理记录")
    private String driverHandle;

    @Schema(description = "驾驶员检查及认识")
    private String driverSelfCriticism;

    @Schema(description = "相关人员教育培训记录")
    private String educationTraining;

}