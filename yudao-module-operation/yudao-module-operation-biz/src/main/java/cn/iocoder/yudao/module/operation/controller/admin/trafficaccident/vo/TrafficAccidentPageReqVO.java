package cn.iocoder.yudao.module.operation.controller.admin.trafficaccident.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 交通事故分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class TrafficAccidentPageReqVO extends PageParam {

    @Schema(description = "司机id", example = "4565")
    private Long driverId;

    @Schema(description = "车辆id", example = "21294")
    private Long vehicleId;

    @Schema(description = "事故时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] accidentDate;

    @Schema(description = "事故地点")
    private String place;

    @Schema(description = "事故责任", example = "1")
    private Integer responsibility;

    @Schema(description = "事故类别")
    private String accidentCategory;

    @Schema(description = "处理部门")
    private String identificationDept;

    @Schema(description = "总计(事故损失)")
    private BigDecimal totalPay;

    @Schema(description = "事故级别", example = "1")
    private Integer level;

    @Schema(description = "事故简况")
    private String accidentDesc;

    @Schema(description = "本车损失")
    private BigDecimal ownVehiclePay;

    @Schema(description = "三者车损")
    private BigDecimal otherVehiclePay;

    @Schema(description = "医疗费")
    private BigDecimal hospitalPay;

    @Schema(description = "伤残或死亡")
    private BigDecimal injuredDeadPay;

    @Schema(description = "其他")
    private BigDecimal otherPay;

    @Schema(description = "事故相关照片")
    private String photos;

    @Schema(description = "处理状态", example = "1")
    private Integer handleStatus;

    @Schema(description = "结案日期")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private String[] settlementDate;

    @Schema(description = "三者物损")
    private BigDecimal otherGoodsPay;

    @Schema(description = "财产损失小计")
    private BigDecimal vehicleTotalPay;

    @Schema(description = "人身损害小计")
    private BigDecimal personTotalPay;

    @Schema(description = "保险理赔记录")
    private String insuranceRecord;

    @Schema(description = "保险理赔总金额")
    private BigDecimal insuranceTotal;

    @Schema(description = "事故车辆数", example = "1")
    private Integer vehicleCount;

    @Schema(description = "受伤人数")
    private Short injuredPenson;

    @Schema(description = "死亡人数")
    private Short deadPerson;

    @Schema(description = "事故救援及处理记录")
    private String handleRecord;

    @Schema(description = "处理进度")
    private String handleProcess;

    @Schema(description = "伤亡情况")
    private String injuredDeadDesc;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}