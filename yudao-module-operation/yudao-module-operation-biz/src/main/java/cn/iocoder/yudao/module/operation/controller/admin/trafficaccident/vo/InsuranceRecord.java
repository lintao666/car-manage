package cn.iocoder.yudao.module.operation.controller.admin.trafficaccident.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * 保险理赔记录
 */
@Schema(description = "保险理赔记录")
@Data
public class InsuranceRecord {
    /**
     * 理赔日期
     */
    @Schema(description = "理赔日期")
    private LocalDate insuranceDate;
    /**
     * 理赔金额
     */
    @Schema(description = "理赔金额")
    private BigDecimal insurancePay;
    /**
     * 理赔说明
     */
    @Schema(description = "理赔说明")
    private String insuranceDesc;
}
