package cn.iocoder.yudao.module.operation.controller.admin.trafficaccident.vo;

import cn.iocoder.yudao.framework.excel.core.annotations.DictFormat;
import cn.iocoder.yudao.framework.excel.core.convert.DictConvert;
import cn.iocoder.yudao.module.operation.enums.DictTypeConstants;
import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Schema(description = "管理后台 - 维修excel 导入VO")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TrafficAccidentImportVO {

    @ExcelProperty("出险日期")
    private LocalDate accidentDate;
    @ExcelProperty("自编号")
    private String vehicleMask;
    @ExcelProperty("车牌号")
    private String carNumber;

    @ExcelProperty("驾驶员")
    private String driverName;
    @ExcelProperty("事故简况")
    private String accidentDesc;
    @ExcelProperty(value = "责任情况", converter = DictConvert.class)
    @DictFormat(DictTypeConstants.OPERATION_RESPONSIBILITY)
    private Integer responsibility;
    @ExcelProperty({"受理机关", "责任认定部门"})
    private String identificationDept;
    @ExcelProperty({"责任认定(编号)", "认定书号"})
    private String identificationRecordNum;
    @ExcelProperty("伤亡情况")
    private String injuredDeadDesc;
    @ExcelProperty("经济损失")
    private Double totalPay;
    @ExcelProperty("处理情况")
    private String handleProcess;
    @ExcelProperty("结案日期")
    private LocalDate settlementDate;
    @ExcelProperty("保险理赔日期")
    private LocalDate insuranceDate1;
    @ExcelProperty("保险理赔金额")
    private Double insurancePay1;

}