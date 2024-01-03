package cn.iocoder.yudao.module.operation.controller.admin.inspection.vo;

import lombok.*;

import java.time.LocalDate;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 年审分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class InspectionPageReqVO extends PageParam {

    @Schema(description = "1车辆年审，2气瓶年审", example = "2")
    private Integer inspectionType;

    @Schema(description = "车辆Id", example = "15779")
    private Long vehicleId;

    @Schema(description = "车辆检测类别")
    private String inspectionClass;

    @Schema(description = "年审日期")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDate[] inspectionDate;

    @Schema(description = "年审到期日期")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDate[] inspectionEndDate;

    @Schema(description = "检测站")
    private String inspectionAddress;

    @Schema(description = "核审员", example = "16886")
    private String verifiedUserId;

    @Schema(description = "证件照片")
    private String pictures;

    @Schema(description = "1 已审 2待审", example = "2")
    private Integer status;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}