package cn.iocoder.yudao.module.business.controller.admin.vehicle.vo;

import cn.iocoder.yudao.framework.excel.core.annotations.DictFormat;
import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Schema(description = "管理后台 - 车辆详情 VO")
@Data
@ExcelIgnoreUnannotated
public class VehicleDetailVO {

    @Schema(description = "编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "17092")
    private Long id;

    @Schema(description = "所属分公司", requiredMode = Schema.RequiredMode.REQUIRED, example = "25787")
    private Long companyId;

    @Schema(description = "部门id", requiredMode = Schema.RequiredMode.REQUIRED, example = "114")
    private Long deptId;

    @Schema(description = "车辆自编号", example = "21294")
    private String vehicleMask;

    @Schema(description = "车牌号", requiredMode = Schema.RequiredMode.REQUIRED)
    private String carNumber;

    @Schema(description = "车辆品牌", requiredMode = Schema.RequiredMode.REQUIRED)
    private String brand;

    @Schema(description = "车型", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer vehicleModel;

    @Schema(description = "车辆能源类型", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    private Integer energyType;

    @Schema(description = "车辆类型", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    private Integer vehicleType;

    @Schema(description = "车架号", requiredMode = Schema.RequiredMode.REQUIRED)
    private String vin;

    @Schema(description = "发动机号", requiredMode = Schema.RequiredMode.REQUIRED)
    private String engineNumber;

    @Schema(description = "设备列表", requiredMode = Schema.RequiredMode.REQUIRED)
    private List<Long> deviceIdList;

    @Schema(description = "司机列表", requiredMode = Schema.RequiredMode.REQUIRED)
    private List<Long> driverIdList;

    @Schema(description = "当前状态（非ACC状态）", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer currentState;

    @Schema(description = "附件（多张图片）", requiredMode = Schema.RequiredMode.REQUIRED)
    private List<String> attachment;

    @Schema(description = "状态", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @DictFormat("common_status") // TODO 代码优化：建议设置到对应的 DictTypeConstants 枚举类中
    private Integer status;

    @Schema(description = "保养时间周期(月)")
    private Byte maintainMonths;

    @Schema(description = "保养里程周期(KM)")
    private Integer maintainMileages;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    private LocalDateTime createTime;

}