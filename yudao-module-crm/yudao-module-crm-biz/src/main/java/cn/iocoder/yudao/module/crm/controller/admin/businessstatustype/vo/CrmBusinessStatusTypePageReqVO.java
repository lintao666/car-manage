package cn.iocoder.yudao.module.crm.controller.admin.businessstatustype.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Schema(description = "管理后台 - 商机状态类型分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class CrmBusinessStatusTypePageReqVO extends PageParam {

    @Schema(description = "状态类型名", example = "芋艿")
    private String name;

    @Schema(description = "开启状态", example = "1")
    private Boolean status;

}
