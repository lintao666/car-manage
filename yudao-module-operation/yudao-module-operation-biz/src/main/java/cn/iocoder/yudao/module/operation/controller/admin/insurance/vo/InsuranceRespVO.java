package cn.iocoder.yudao.module.operation.controller.admin.insurance.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDate;
import java.util.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 保单 Response VO")
@Data
@ExcelIgnoreUnannotated
public class InsuranceRespVO {

    @Schema(description = "编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "27352")
    @ExcelProperty("编号")
    private Long id;

    @Schema(description = "1 - 交强险，2 - 商业险,3-承运人责任险", example = "2")
    @ExcelProperty("1 - 交强险，2 - 商业险,3-承运人责任险")
    private Integer insuranceType;

    @Schema(description = "车辆Id", example = "9582")
    @ExcelProperty("车辆Id")
    private Long vehicleId;

    @Schema(description = "保单号")
    @ExcelProperty("保单号")
    private String insuranceNumber;

    @Schema(description = "保险公司")
    @ExcelProperty("保险公司")
    private String insuranceCompany;

    @Schema(description = "总保费")
    @ExcelProperty("总保费")
    private Double insuranceFee;

    @Schema(description = "生效日期")
    @ExcelProperty("生效日期")
    private LocalDate startDate;

    @Schema(description = "到期日期/到期状态判断")
    @ExcelProperty("到期日期/到期状态判断")
    private LocalDate endDate;

    @Schema(description = "保单照片")
    @ExcelProperty("保单照片")
    private List<String> pictures;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}