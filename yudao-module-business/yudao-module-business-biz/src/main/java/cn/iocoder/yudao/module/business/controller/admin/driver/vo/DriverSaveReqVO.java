package cn.iocoder.yudao.module.business.controller.admin.driver.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import javax.validation.constraints.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 司机新增/修改 Request VO")
@Data
public class DriverSaveReqVO {

    @Schema(description = "编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "31979")
    private Long id;

    @Schema(description = "所属分公司", requiredMode = Schema.RequiredMode.REQUIRED, example = "12384")
    @NotNull(message = "所属分公司不能为空")
    private Long companyId;

    @Schema(description = "司机姓名", requiredMode = Schema.RequiredMode.REQUIRED, example = "赵六")
    @NotEmpty(message = "司机姓名不能为空")
    private String name;

    @Schema(description = "身份证号", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "身份证号不能为空")
    private String idNumber;

    @Schema(description = "车牌号")
    private String carNumber;

    @Schema(description = "手机号码", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "手机号码不能为空")
    private String phoneNumber;

    @Schema(description = "紧急联系电话")
    private String emergencyTelephone;

    @Schema(description = "居住地址")
    private String residentialAddress;

    @Schema(description = "驾照号码")
    private String driverLicenseNumber;

    @Schema(description = "驾照等级")
    private String drivingClass;

    @Schema(description = "驾照起始时间")
    private LocalDateTime driverLicenseStartTime;

    @Schema(description = "驾照到期时间")
    private LocalDateTime driverLicenseExpirationTime;

    @Schema(description = "头像图片")
    private String headPortrait;

    @Schema(description = "附件（多张图片）", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "附件（多张图片）不能为空")
    private List<String> attachment;

    @Schema(description = "状态", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @NotNull(message = "状态不能为空")
    private Integer status;

}