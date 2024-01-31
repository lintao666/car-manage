package cn.iocoder.yudao.module.business.api.driver.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Schema(description = "司机")
@Data
public class DriverRespDTO {

    @Schema(description = "编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "31979")
    private Long id;

    @Schema(description = "部门id", requiredMode = Schema.RequiredMode.REQUIRED, example = "12384")
    private Long deptId;
    private String deptName;

    @Schema(description = "司机姓名", requiredMode = Schema.RequiredMode.REQUIRED, example = "赵六")
    private String name;

    @Schema(description = "身份证号", requiredMode = Schema.RequiredMode.REQUIRED)
    private String idNumber;

    @Schema(description = "车牌号")
    private String carNumber;

    @Schema(description = "手机号码", requiredMode = Schema.RequiredMode.REQUIRED)
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
    private List<String> attachment;

    @Schema(description = "状态", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    private Integer status;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    private LocalDateTime createTime;

}