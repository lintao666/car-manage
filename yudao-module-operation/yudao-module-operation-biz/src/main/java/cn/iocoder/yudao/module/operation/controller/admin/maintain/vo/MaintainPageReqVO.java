package cn.iocoder.yudao.module.operation.controller.admin.maintain.vo;

import lombok.*;

import java.time.LocalDate;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 保养/二级维护分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class MaintainPageReqVO extends PageParam {

    @Schema(description = "车辆id", example = "27680")
    private Long vehicleId;

    @Schema(description = "合格证号")
    private String certificateNumber;

    @Schema(description = "维护保养厂")
    private String maintainShop;

    @Schema(description = "保养日期")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDate[] maintainDate;

    @Schema(description = "保养到期日期/到期状态")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDate[] maintainEndDate;

    @Schema(description = "保养时里程")
    private Double maintainMileage;

    @Schema(description = "保养到期里程")
    private Double maintainEndMileage;

    @Schema(description = "图片")
    private String pic;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}