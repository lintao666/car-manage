package cn.iocoder.yudao.module.business.controller.admin.driver.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;
import cn.iocoder.yudao.framework.excel.core.annotations.DictFormat;
import cn.iocoder.yudao.framework.excel.core.convert.DictConvert;

@Schema(description = "管理后台 - 司机 Response VO")
@Data
@ExcelIgnoreUnannotated
public class DriverRespVO {

    @Schema(description = "编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "31979")
    @ExcelProperty("编号")
    private Long id;

    @Schema(description = "部门id", requiredMode = Schema.RequiredMode.REQUIRED, example = "12384")
    @ExcelProperty("部门")
    private Long deptId;

    @Schema(description = "司机姓名", requiredMode = Schema.RequiredMode.REQUIRED, example = "赵六")
    @ExcelProperty("司机姓名")
    private String name;

    @Schema(description = "身份证号", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("身份证号")
    private String idNumber;

    @Schema(description = "车牌号")
    @ExcelProperty("车牌号")
    private String carNumber;

    @Schema(description = "手机号码", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("手机号码")
    private String phoneNumber;

    @Schema(description = "紧急联系电话")
    @ExcelProperty("紧急联系电话")
    private String emergencyTelephone;

    @Schema(description = "居住地址")
    @ExcelProperty("居住地址")
    private String residentialAddress;

    @Schema(description = "驾照号码")
    @ExcelProperty("驾照号码")
    private String driverLicenseNumber;

    @Schema(description = "驾照等级")
    @ExcelProperty("驾照等级")
    private String drivingClass;

    @Schema(description = "驾照起始时间")
    @ExcelProperty("驾照起始时间")
    private LocalDateTime driverLicenseStartTime;

    @Schema(description = "驾照到期时间")
    @ExcelProperty("驾照到期时间")
    private LocalDateTime driverLicenseExpirationTime;

    @Schema(description = "头像图片")
    @ExcelProperty("头像图片")
    private String headPortrait;

    @Schema(description = "附件（多张图片）", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("附件（多张图片）")
    private List<String> attachment;

    @Schema(description = "状态", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @ExcelProperty(value = "状态", converter = DictConvert.class)
    @DictFormat("common_status") // TODO 代码优化：建议设置到对应的 DictTypeConstants 枚举类中
    private Integer status;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}