package cn.iocoder.yudao.module.operation.controller.admin.maintain.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.util.Map;

@Schema(description = "管理后台 - 用户维修记录 Response VO")
@Data
@Builder
public class MaintainImportRespVO {

    @Schema(description = "创建成功的车牌号数组", requiredMode = Schema.RequiredMode.REQUIRED)
    private int creates;

    @Schema(description = "更新成功的车牌号数组", requiredMode = Schema.RequiredMode.REQUIRED)
    private int updates;

    @Schema(description = "导入失败的维修记录集合，key 为车牌号，value 为失败原因", requiredMode = Schema.RequiredMode.REQUIRED)
    private Map<String, String> failureRecords;

}
