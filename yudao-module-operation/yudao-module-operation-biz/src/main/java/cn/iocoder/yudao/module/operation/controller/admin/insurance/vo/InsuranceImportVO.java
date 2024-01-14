package cn.iocoder.yudao.module.operation.controller.admin.insurance.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Schema(description = "管理后台 -保险excel 导入VO")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InsuranceImportVO {

    @ExcelProperty("保险类型")
    private Integer insuranceType;
    //    @ExcelProperty("自编号")
//    private String vehicleMask;
    @ExcelProperty("车牌号")
    private String carNumber;
    @ExcelProperty("保险公司")
    private String insuranceCompany;
    @ExcelProperty({"保费", "保费总额"})
    private Double insuranceFee;
    @ExcelProperty("保单号")
    private String insuranceNumber;
    @ExcelProperty("生效日期")
    private LocalDate startDate;
    @ExcelProperty({"失效日期", "到期日期/到期状态判断"})
    private LocalDate endDate;

}