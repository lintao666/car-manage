package cn.iocoder.yudao.module.business.controller.admin.vehicle.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 车辆分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class VehiclePageReqVO extends PageParam {

    @Schema(description = "所属分公司", example = "25787")
    private Long companyId;

    @Schema(description = "部门id", requiredMode = Schema.RequiredMode.REQUIRED, example = "114")
    private Long deptId;

    @Schema(description = "车牌号")
    private String carNumber;

    @Schema(description = "自编号", example = "21294")
    private String vehicleMask;

    @Schema(description = "车辆品牌")
    private String brand;

    @Schema(description = "车型")
    private Integer vehicleModel;

    @Schema(description = "车辆能源类型", example = "1")
    private Integer energyType;

    @Schema(description = "车辆类型", example = "1")
    private Integer vehicleType;

    @Schema(description = "车架号")
    private String vin;

    @Schema(description = "发动机号")
    private String engineNumber;

    @Schema(description = "当前状态（非ACC状态）")
    private Integer currentState;

    @Schema(description = "附件（多张图片）")
    private String attachment;

    @Schema(description = "状态", example = "1")
    private Integer status;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}