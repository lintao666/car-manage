package cn.iocoder.yudao.module.operation.controller.admin.inspection.vo;

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

@Schema(description = "管理后台 - 年审记录excel 导入VO")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InspectionImportVO {

    @ExcelProperty(value = "审验类型",converter = DictConvert.class)
    @DictFormat(DictTypeConstants.OPERATION_INSPECTION_TYPE)
    private Integer inspectionType;

    @ExcelProperty("自编号")
    private String vehicleMask;
    @ExcelProperty("车牌号")
    private String carNumber;

    @ExcelProperty("计划审验时间")
    private LocalDate planInspectionDate;
    @ExcelProperty("通知审验时间")
    private LocalDate informInspectionDate;

    @ExcelProperty("通知人")
    private String informUser;
    @ExcelProperty("驾驶员签字")
    private String driverName;

    @ExcelProperty({"实际审验时间","年审日期"})
    private LocalDate inspectionDate;
    @ExcelProperty({"下次审验时间","年审到期日期"})
    private LocalDate inspectionEndDate;
    @ExcelProperty("检测站")
    private String inspectionAddress;

}