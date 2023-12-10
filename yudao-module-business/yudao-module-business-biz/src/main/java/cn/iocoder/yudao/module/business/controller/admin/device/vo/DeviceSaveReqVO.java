package cn.iocoder.yudao.module.business.controller.admin.device.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import javax.validation.constraints.*;
import java.util.*;

@Schema(description = "管理后台 - 设备新增/修改 Request VO")
@Data
public class DeviceSaveReqVO {

    @Schema(description = "编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "26504")
    private Long id;

    @Schema(description = "设备ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "aa3128612222")
    @NotEmpty(message = "设备ID不能为空")
    @Pattern(regexp = "^[a-zA-Z0-9]{12}$",message = "设备ID只能为12位英文+数字的组合")
    private String deviceId;

    @Schema(description = "设备类型", requiredMode = Schema.RequiredMode.REQUIRED, example = "1447")
    @NotNull(message = "设备类型不能为空")
    private Integer deviceType;

    @Schema(description = "设备绑定车牌号")
    private String boundCarNumber;

    @Schema(description = "状态", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "状态不能为空")
    private Integer status;

}