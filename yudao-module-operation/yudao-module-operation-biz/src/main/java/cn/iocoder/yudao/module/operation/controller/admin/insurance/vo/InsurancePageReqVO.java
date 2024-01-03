package cn.iocoder.yudao.module.operation.controller.admin.insurance.vo;

import lombok.*;

import java.time.LocalDate;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 保单分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class InsurancePageReqVO extends PageParam {

    @Schema(description = "1 - 交强险，2 - 商业险,3-承运人责任险", example = "2")
    private Integer insuranceType;

    @Schema(description = "车辆Id", example = "9582")
    private Long vehicleId;

    @Schema(description = "保单号")
    private String insuranceNumber;

    @Schema(description = "保险公司")
    private String insuranceCompany;

    @Schema(description = "总保费")
    private Double insuranceFee;

    @Schema(description = "生效日期")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDate[] startDate;

    @Schema(description = "到期日期/到期状态判断")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDate[] endDate;

    @Schema(description = "保单照片")
    private String pictures;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}