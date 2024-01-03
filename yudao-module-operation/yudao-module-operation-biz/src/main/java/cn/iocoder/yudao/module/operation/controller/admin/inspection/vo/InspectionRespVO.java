package cn.iocoder.yudao.module.operation.controller.admin.inspection.vo;

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

@Schema(description = "管理后台 - 年审 Response VO")
@Data
@ExcelIgnoreUnannotated
public class InspectionRespVO {

    @Schema(description = "编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "22017")
    @ExcelProperty("编号")
    private Long id;

    @Schema(description = "1车辆年审，2气瓶年审", example = "2")
    @ExcelProperty(value = "1车辆年审，2气瓶年审", converter = DictConvert.class)
    @DictFormat("inspection_type") // TODO 代码优化：建议设置到对应的 DictTypeConstants 枚举类中
    private Integer inspectionType;

    @Schema(description = "车辆Id", example = "15779")
    @ExcelProperty("车辆Id")
    private Long vehicleId;

    @Schema(description = "车辆检测类别")
    @ExcelProperty("车辆检测类别")
    private String inspectionClass;

    @Schema(description = "年审日期")
    @ExcelProperty("年审日期")
    private LocalDate inspectionDate;

    @Schema(description = "年审到期日期")
    @ExcelProperty("年审到期日期")
    private LocalDate inspectionEndDate;

    @Schema(description = "检测站")
    @ExcelProperty("检测站")
    private String inspectionAddress;

    @Schema(description = "核审员", example = "16886")
    @ExcelProperty("核审员")
    private String verifiedUserId;

    @Schema(description = "证件照片")
    @ExcelProperty("证件照片")
    private String pictures;

    @Schema(description = "1 已审 2待审", example = "2")
    @ExcelProperty(value = "1 已审 2待审", converter = DictConvert.class)
    @DictFormat("inspection_status") // TODO 代码优化：建议设置到对应的 DictTypeConstants 枚举类中
    private Integer status;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}