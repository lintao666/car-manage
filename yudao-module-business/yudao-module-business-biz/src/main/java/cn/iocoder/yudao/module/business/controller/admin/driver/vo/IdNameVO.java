package cn.iocoder.yudao.module.business.controller.admin.driver.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description = "管理后台 - 下拉列表 VO")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class IdNameVO {
    @Schema(description = "id", requiredMode = Schema.RequiredMode.REQUIRED, example = "1024")
    @ExcelProperty("id")
    private Long id;

    @Schema(description = "名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "小土豆")
    @ExcelProperty("名称")
    private String name;
}
