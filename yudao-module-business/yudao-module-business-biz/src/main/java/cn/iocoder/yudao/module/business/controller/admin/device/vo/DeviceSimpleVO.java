package cn.iocoder.yudao.module.business.controller.admin.device.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description = "管理后台 - 设备下拉列表 VO")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeviceSimpleVO {
    @Schema(description = "id",  example = "1024")
    private Long id;

    @Schema(description = "设备id", example = "")
    private String deviceId;

    @Schema(description = "名称", example = "小土豆")
    private String name;
}
