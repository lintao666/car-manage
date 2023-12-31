package cn.iocoder.yudao.module.business.controller.admin.vehicle.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import javax.validation.constraints.*;
import java.util.*;

@Schema(description = "管理后台 - 车辆新增/修改 Request VO")
@Data
public class VehicleSaveReqVO {

    @Schema(description = "编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "17092")
    private Long id;

    @Schema(description = "所属分公司", requiredMode = Schema.RequiredMode.REQUIRED, example = "25787")
    @NotNull(message = "所属分公司不能为空")
    private Long companyId;

    @Schema(description = "车牌号", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "车牌号不能为空")
    private String carNumber;

    @Schema(description = "车辆品牌", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "车辆品牌不能为空")
    private String brand;

    @Schema(description = "车型", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "车型不能为空")
    private Integer vehicleModel;

    @Schema(description = "车辆能源类型", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "车辆能源类型不能为空")
    private Integer energyType;

    @Schema(description = "车辆类型", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "车辆类型不能为空")
    private Integer vehicleType;

    @Schema(description = "车架号", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "车架号不能为空")
    private String vin;

    @Schema(description = "发动机号", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "发动机号不能为空")
    private String engineNumber;

    @Schema(description = "设备列表", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "设备列表不能为空")
    private List<String> deviceIdList;

    @Schema(description = "司机列表", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "司机列表不能为空")
    private List<String> driverIdList;

    @Schema(description = "当前状态（非ACC状态）", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "当前状态（非ACC状态）不能为空")
    private Integer currentState;

    @Schema(description = "附件（多张图片）", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "附件（多张图片）不能为空")
    private String attachment;

    @Schema(description = "状态", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "状态不能为空")
    private Integer status;

}