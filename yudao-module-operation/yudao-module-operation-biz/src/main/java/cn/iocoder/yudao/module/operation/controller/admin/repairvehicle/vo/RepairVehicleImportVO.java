package cn.iocoder.yudao.module.operation.controller.admin.repairvehicle.vo;

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
public class RepairVehicleImportVO {

    @ExcelProperty("自编号")
    private String vehicleMask;
    @ExcelProperty("车牌号")
    private String carNumber;
    @ExcelProperty("维修厂")
    private String repairAddress;
    @Schema(description = "维修主题/维修设备")
    @ExcelProperty("维修部位")
    private String repairTheme;
    @ExcelProperty(value = "损坏等级", converter = DictConvert.class)
    @DictFormat(DictTypeConstants.OPERATION_REPAIR_LEVEL)
    private Integer repairLevel;
    @Schema(description = "更换项、设备维修时：1gps,2cng,3电子密标,4计价器,5其它")
    @ExcelProperty("主要零部件更换项目")
    private String changeProject;
    @Schema(description = "维修日期")
    @ExcelProperty({"维修日期", "竣工日期"})
    private LocalDate repairDate;
    @Schema(description = "价格/维修费用")
    @ExcelProperty("维修金额")
    private Double repairMoney;
    @ExcelProperty("报修日期")
    private LocalDate reportDate;
    @ExcelProperty("报修人")
    private String reportUser;

}