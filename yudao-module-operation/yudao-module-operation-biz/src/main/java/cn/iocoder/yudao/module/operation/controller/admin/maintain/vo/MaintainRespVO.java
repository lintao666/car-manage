package cn.iocoder.yudao.module.operation.controller.admin.maintain.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDate;
import java.util.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 保养/二级维护 Response VO")
@Data
@ExcelIgnoreUnannotated
public class MaintainRespVO {

    @Schema(description = "编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "10675")
    @ExcelProperty("编号")
    private Long id;

    @Schema(description = "车辆id", example = "27680")
    @ExcelProperty("车辆id")
    private Long vehicleId;

    @Schema(description = "合格证号")
    @ExcelProperty("合格证号")
    private String certificateNumber;

    @Schema(description = "维护保养厂")
    @ExcelProperty("维护保养厂")
    private String maintainShop;

    @Schema(description = "保养日期")
    @ExcelProperty("保养日期")
    private LocalDate maintainDate;

    @Schema(description = "保养到期日期/到期状态")
    @ExcelProperty("保养到期日期/到期状态")
    private LocalDate maintainEndDate;

    @Schema(description = "保养时里程")
    @ExcelProperty("保养时里程")
    private Double maintainMileage;

    @Schema(description = "保养到期里程")
    @ExcelProperty("保养到期里程")
    private Double maintainEndMileage;

    @Schema(description = "图片")
    @ExcelProperty("图片")
    private List<String> pic;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}