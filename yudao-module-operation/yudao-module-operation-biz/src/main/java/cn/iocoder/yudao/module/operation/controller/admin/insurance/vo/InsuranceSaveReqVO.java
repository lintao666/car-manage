package cn.iocoder.yudao.module.operation.controller.admin.insurance.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDate;
import java.util.*;
import javax.validation.constraints.*;
import java.util.*;

@Schema(description = "管理后台 - 保单新增/修改 Request VO")
@Data
public class InsuranceSaveReqVO {

    @Schema(description = "编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "27352")
    private Long id;

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
    private LocalDate startDate;

    @Schema(description = "到期日期/到期状态判断")
    private LocalDate endDate;

    @Schema(description = "保单照片")
    private String pictures;

}