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

    @Schema(description = "部门id", requiredMode = Schema.RequiredMode.REQUIRED, example = "114")

    private Long deptId;

    @Schema(description = "车辆自编号", example = "21294")
    @NotNull(message = "车辆自编号不能为空")
    private String vehicleMask;

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
    private List<Long> deviceIdList;

    @Schema(description = "司机列表", requiredMode = Schema.RequiredMode.REQUIRED)
    private List<Long> driverIdList;

    @Schema(description = "当前状态（非ACC状态）", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "当前状态（非ACC状态）不能为空")
    private Integer currentState;

    @Schema(description = "附件（多张图片）", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "附件（多张图片）不能为空")
    private List<String> attachment;

    @Schema(description = "状态", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    private Integer status;

    @Schema(description = "保养时间周期(月)", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @NotNull(message = "保养时间不能为空")
    private Byte maintainMonths;

    @Schema(description = "保养里程周期(KM)", requiredMode = Schema.RequiredMode.REQUIRED, example = "15000")
    @NotNull(message = "保养里程周期不能为空")
    private Integer maintainMileages;

}