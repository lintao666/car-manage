package cn.iocoder.yudao.module.business.controller.admin.device.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;
import cn.iocoder.yudao.framework.excel.core.annotations.DictFormat;
import cn.iocoder.yudao.framework.excel.core.convert.DictConvert;

@Schema(description = "管理后台 - 设备 Response VO")
@Data
@ExcelIgnoreUnannotated
public class DeviceRespVO {

    @Schema(description = "编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "26504")
    @ExcelProperty("编号")
    private Long id;

    @Schema(description = "设备ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "aa3128612222")
    @ExcelProperty("设备ID")
    private String deviceId;

    @Schema(description = "设备类型", requiredMode = Schema.RequiredMode.REQUIRED, example = "1447")
    @ExcelProperty(value = "设备类型", converter = DictConvert.class)
    @DictFormat("device_type") // TODO 代码优化：建议设置到对应的 DictTypeConstants 枚举类中
    private Integer deviceType;

    @Schema(description = "设备绑定车牌号")
    @ExcelProperty("设备绑定车牌号")
    private String boundCarNumber;

    @Schema(description = "状态", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @ExcelProperty(value = "状态", converter = DictConvert.class)
    @DictFormat("common_status") // TODO 代码优化：建议设置到对应的 DictTypeConstants 枚举类中
    private Integer status;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}