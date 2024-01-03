package cn.iocoder.yudao.module.operation.controller.admin.inspection.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDate;
import java.util.*;
import javax.validation.constraints.*;
import java.util.*;

@Schema(description = "管理后台 - 年审新增/修改 Request VO")
@Data
public class InspectionSaveReqVO {

    @Schema(description = "编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "22017")
    private Long id;

    @Schema(description = "1车辆年审，2气瓶年审", example = "2")
    private Integer inspectionType;

    @Schema(description = "车辆Id", example = "15779")
    private Long vehicleId;

    @Schema(description = "车辆检测类别")
    private String inspectionClass;

    @Schema(description = "年审日期")
    private LocalDate inspectionDate;

    @Schema(description = "年审到期日期")
    private LocalDate inspectionEndDate;

    @Schema(description = "检测站")
    private String inspectionAddress;

    @Schema(description = "核审员", example = "16886")
    private String verifiedUserId;

    @Schema(description = "证件照片")
    private String pictures;

    @Schema(description = "1 已审 2待审", example = "2")
    private Integer status;

}