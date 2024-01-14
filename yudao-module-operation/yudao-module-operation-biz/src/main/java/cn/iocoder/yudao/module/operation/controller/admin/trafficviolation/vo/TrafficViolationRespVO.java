package cn.iocoder.yudao.module.operation.controller.admin.trafficviolation.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;
import cn.iocoder.yudao.framework.excel.core.annotations.DictFormat;
import cn.iocoder.yudao.framework.excel.core.convert.DictConvert;

@Schema(description = "管理后台 - 交通违法 Response VO")
@Data
@ExcelIgnoreUnannotated
public class TrafficViolationRespVO {

    @Schema(description = "编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "24349")
    @ExcelProperty("编号")
    private Long id;

    @Schema(description = "部门id", example = "6581")
    @ExcelProperty("部门id")
    private Long deptId;

    @Schema(description = "车辆id", example = "5864")
    @ExcelProperty("车辆id")
    private Long vehicleId;

    @Schema(description = "驾驶员id", example = "13552")
    @ExcelProperty("驾驶员id")
    private Long driverId;

    @Schema(description = "时间")
    @ExcelProperty("时间")
    private LocalDateTime violationDate;

    @Schema(description = "计分")
    @ExcelProperty("计分")
    private Short points;

    @Schema(description = "罚款")
    @ExcelProperty("罚款")
    private Short pay;

    @Schema(description = "地点")
    @ExcelProperty("地点")
    private String place;

    @Schema(description = "违法内容")
    @ExcelProperty("违法内容")
    private String violationDesc;

    @Schema(description = "车牌号")
    @ExcelProperty("车牌号")
    private String carNumber;

    @Schema(description = "违法代码", example = "1")
    @ExcelProperty("违法代码")
    private String violationType;

    @Schema(description = "信息来源：1-非现场 2-现场")
    @ExcelProperty("信息来源：1-非现场 2-现场")
    private Short source;

    @Schema(description = "支付状态：0-否；1-已交款", example = "1")
    @ExcelProperty(value = "支付状态：0-否；1-已交款", converter = DictConvert.class)
    @DictFormat("pay_status") // TODO 代码优化：建议设置到对应的 DictTypeConstants 枚举类中
    private Integer payStatus;

    @Schema(description = "处理状态：0-未处理1-已处理", example = "1")
    @ExcelProperty(value = "处理状态：0-未处理1-已处理", converter = DictConvert.class)
    @DictFormat("traffic_violation_handle_status") // TODO 代码优化：建议设置到对应的 DictTypeConstants 枚举类中
    private Integer handleStatus;

    @Schema(description = "决定书类别：1-简易程序  2-一般程序", example = "1")
    @ExcelProperty("决定书类别：1-简易程序  2-一般程序")
    private Short decisionType;

    @Schema(description = "违法驾驶员处理记录")
    @ExcelProperty("违法驾驶员处理记录")
    private String driverHandle;

    @Schema(description = "驾驶员检查及认识")
    @ExcelProperty("驾驶员检查及认识")
    private String driverSelfCriticism;

    @Schema(description = "相关人员教育培训记录")
    @ExcelProperty("相关人员教育培训记录")
    private String educationTraining;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}