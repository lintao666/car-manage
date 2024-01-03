package cn.iocoder.yudao.module.operation.controller.admin.maintain.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDate;
import java.util.*;
import javax.validation.constraints.*;
import java.util.*;

@Schema(description = "管理后台 - 保养/二级维护新增/修改 Request VO")
@Data
public class MaintainSaveReqVO {

    @Schema(description = "编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "10675")
    private Long id;

    @Schema(description = "车辆id", example = "27680")
    private Long vehicleId;

    @Schema(description = "合格证号")
    private String certificateNumber;

    @Schema(description = "维护保养厂")
    private String maintainShop;

    @Schema(description = "保养日期")
    private LocalDate maintainDate;

    @Schema(description = "保养到期日期/到期状态")
    private LocalDate maintainEndDate;

    @Schema(description = "保养时里程")
    private Double maintainMileage;

    @Schema(description = "保养到期里程")
    private Double maintainEndMileage;

    @Schema(description = "图片")
    private String pic;

}