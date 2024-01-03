package cn.iocoder.yudao.module.operation.controller.admin.repairvehicle.vo;

import lombok.*;

import java.time.LocalDate;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 维修分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class RepairVehiclePageReqVO extends PageParam {

    @Schema(description = "维修类别1日常维修2事故维修3设备维修", example = "2")
    private Integer repairType;

    @Schema(description = "车辆id", example = "5189")
    private Long vehicleId;

    @Schema(description = "维修厂")
    private String repairAddress;

    @Schema(description = "维修主题/维修设备")
    private String repairTheme;

    @Schema(description = "受损等级", example = "2")
    private Integer repairLevel;

    @Schema(description = "更换项、设备维修时：1gps,2cng,3电子密标,4计价器,5其它")
    private String changeProject;

    @Schema(description = "维修日期")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDate[] repairDate;

    @Schema(description = "价格/维修费用")
    private Double repairMoney;

    @Schema(description = "1正在维修，0维修完成")
    private Integer repairing;

    @Schema(description = "描述")
    private String repairDesc;

    @Schema(description = "事故表id(表示交通事故维修)", example = "21979")
    private Long accidentId;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}