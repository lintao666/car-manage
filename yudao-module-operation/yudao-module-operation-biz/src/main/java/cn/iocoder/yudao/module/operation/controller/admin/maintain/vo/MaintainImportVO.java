package cn.iocoder.yudao.module.operation.controller.admin.maintain.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Schema(description = "管理后台 - 保养记录excel 导入VO")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MaintainImportVO {

    @ExcelProperty("自编号")
    private String vehicleMask;
    @ExcelProperty("车牌号")
    private String carNumber;

    @ExcelProperty("计划保养时间")
    private LocalDate planMaintainDate;
    @ExcelProperty("通知保养时间")
    private LocalDate informMaintainDate;
    @ExcelProperty("通知人")
    private String informUser;
    @ExcelProperty("驾驶员签字")
    private String driverName;
    @ExcelProperty("实际保养时间")
    private LocalDate maintainDate;
    @ExcelProperty("下次保养时间")
    private LocalDate maintainEndDate;

    @ExcelProperty({"保养里程","保养时里程"})
    private Double maintainMileage;

    @ExcelProperty({"到期里程", "保养到期里程"})
    private Double maintainEndMileage;

    @ExcelProperty("合格证号")
    private String certificateNumber;
    @ExcelProperty({"二保厂", "保养厂家"})
    private String maintainShop;

}