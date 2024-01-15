package cn.iocoder.yudao.module.business.controller.admin.driver.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 司机分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class DriverPageReqVO extends PageParam {

    @Schema(description = "部门id", example = "12384")
    private Long deptId;

    @Schema(description = "司机姓名", example = "赵六")
    private String name;

    @Schema(description = "身份证号")
    private String idNumber;

    @Schema(description = "车牌号")
    private String carNumber;

    @Schema(description = "手机号码")
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
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] driverLicenseStartTime;

    @Schema(description = "驾照到期时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] driverLicenseExpirationTime;

    @Schema(description = "头像图片")
    private String headPortrait;

    @Schema(description = "附件（多张图片）")
    private String attachment;

    @Schema(description = "状态", example = "2")
    private Integer status;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}