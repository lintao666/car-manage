package cn.iocoder.yudao.module.operation.controller.admin.repairvehicle.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDate;
import java.util.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;
import cn.iocoder.yudao.framework.excel.core.annotations.DictFormat;
import cn.iocoder.yudao.framework.excel.core.convert.DictConvert;

@Schema(description = "管理后台 - 维修 Response VO")
@Data
@ExcelIgnoreUnannotated
public class RepairVehicleRespVO {

    @Schema(description = "编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "24364")
    @ExcelProperty("编号")
    private Long id;

    @Schema(description = "维修类别1日常维修2事故维修3设备维修", example = "2")
    @ExcelProperty(value = "维修类别1日常维修2事故维修3设备维修", converter = DictConvert.class)
    @DictFormat("repair_type") // TODO 代码优化：建议设置到对应的 DictTypeConstants 枚举类中
    private Integer repairType;

    @Schema(description = "车辆id", example = "5189")
    @ExcelProperty("车辆id")
    private Long vehicleId;

    @Schema(description = "维修厂")
    @ExcelProperty("维修厂")
    private String repairAddress;

    @Schema(description = "维修主题/维修设备")
    @ExcelProperty("维修主题/维修设备")
    private String repairTheme;

    @Schema(description = "受损等级", example = "2")
    @ExcelProperty(value = "受损等级", converter = DictConvert.class)
    @DictFormat("repair_level") // TODO 代码优化：建议设置到对应的 DictTypeConstants 枚举类中
    private Integer repairLevel;

    @Schema(description = "更换项、设备维修时：1gps,2cng,3电子密标,4计价器,5其它")
    @ExcelProperty("更换项、设备维修时：1gps,2cng,3电子密标,4计价器,5其它")
    private String changeProject;

    @Schema(description = "维修日期")
    @ExcelProperty("维修日期")
    private LocalDate repairDate;

    @Schema(description = "价格/维修费用")
    @ExcelProperty("价格/维修费用")
    private Double repairMoney;

    @Schema(description = "1正在维修，0维修完成")
    @ExcelProperty(value = "1正在维修，0维修完成", converter = DictConvert.class)
    @DictFormat("repairing") // TODO 代码优化：建议设置到对应的 DictTypeConstants 枚举类中
    private Integer repairing;

    @Schema(description = "描述")
    @ExcelProperty("描述")
    private String repairDesc;

    @Schema(description = "事故表id(表示交通事故维修)", example = "21979")
    @ExcelProperty("事故表id(表示交通事故维修)")
    private Long accidentId;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}