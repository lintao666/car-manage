package cn.iocoder.yudao.module.business.controller.admin.vehicle.vo;

import cn.iocoder.yudao.framework.common.pojo.IdNameVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;
import cn.iocoder.yudao.framework.excel.core.annotations.DictFormat;
import cn.iocoder.yudao.framework.excel.core.convert.DictConvert;

import javax.validation.constraints.NotNull;

@Schema(description = "管理后台 - 车辆 Response VO")
@Data
@ExcelIgnoreUnannotated
public class VehicleRespVO {

    @Schema(description = "编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "17092")
    @ExcelProperty("编号")
    private Long id;

    @Schema(description = "所属分公司", requiredMode = Schema.RequiredMode.REQUIRED, example = "25787")
    @ExcelProperty("所属分公司")
    private Long companyId;

    @Schema(description = "部门id", requiredMode = Schema.RequiredMode.REQUIRED, example = "114")
    @ExcelProperty("所属部门")
    private Long deptId;

    @Schema(description = "自编号", example = "21294")
    @ExcelProperty("自编号")
    private String vehicleMask;

    @Schema(description = "车牌号", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("车牌号")
    private String carNumber;

    @Schema(description = "车辆品牌", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("车辆品牌")
    private String brand;

    @Schema(description = "车型", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("车型")
    private Integer vehicleModel;

    @Schema(description = "车辆能源类型", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @ExcelProperty("车辆能源类型")
    private Integer energyType;

    @Schema(description = "车辆类型", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @ExcelProperty("车辆类型")
    private Integer vehicleType;

    @Schema(description = "车架号", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("车架号")
    private String vin;

    @Schema(description = "发动机号", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("发动机号")
    private String engineNumber;

    @Schema(description = "设备列表", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("设备列表")
    private List<IdNameVO> deviceList;

    @Schema(description = "司机列表", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("司机列表")
    private List<IdNameVO> driverList;

    @Schema(description = "当前状态（非ACC状态）", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty(value = "当前状态（非ACC状态）", converter = DictConvert.class)
    @DictFormat("vehicle_operation_state") // TODO 代码优化：建议设置到对应的 DictTypeConstants 枚举类中
    private Integer currentState;

    @Schema(description = "附件（多张图片）", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("附件（多张图片）")
    private List<String> attachment;

    @Schema(description = "状态", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @ExcelProperty(value = "状态", converter = DictConvert.class)
    @DictFormat("common_status") // TODO 代码优化：建议设置到对应的 DictTypeConstants 枚举类中
    private Integer status;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}