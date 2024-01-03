package cn.iocoder.yudao.module.operation.controller.admin.trafficaccident.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.util.*;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;
import cn.iocoder.yudao.framework.excel.core.annotations.DictFormat;
import cn.iocoder.yudao.framework.excel.core.convert.DictConvert;

@Schema(description = "管理后台 - 交通事故 Response VO")
@Data
@ExcelIgnoreUnannotated
public class TrafficAccidentRespVO {

    @Schema(description = "编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "20790")
    @ExcelProperty("编号")
    private Long id;

    @Schema(description = "司机id", example = "4565")
    @ExcelProperty("司机id")
    private Long driverId;

    @Schema(description = "车辆id", example = "21294")
    @ExcelProperty("车辆id")
    private Long vehicleId;

    @Schema(description = "事故时间")
    @ExcelProperty("事故时间")
    private LocalDateTime accidentDate;

    @Schema(description = "事故地点")
    @ExcelProperty("事故地点")
    private String place;

    @Schema(description = "事故责任", example = "1")
    @ExcelProperty(value = "事故责任", converter = DictConvert.class)
    @DictFormat("accident_responsibility") // TODO 代码优化：建议设置到对应的 DictTypeConstants 枚举类中
    private Integer responsibility;

    @Schema(description = "事故类别")
    @ExcelProperty("事故类别")
    private String accidentCategory;

    @Schema(description = "处理部门")
    @ExcelProperty("处理部门")
    private String identificationDept;

    @Schema(description = "总计(事故损失)")
    @ExcelProperty("总计(事故损失)")
    private BigDecimal totalPay;

    @Schema(description = "事故级别", example = "1")
    @ExcelProperty(value = "事故级别", converter = DictConvert.class)
    @DictFormat("accident_level") // TODO 代码优化：建议设置到对应的 DictTypeConstants 枚举类中
    private Integer level;

    @Schema(description = "事故简况")
    @ExcelProperty("事故简况")
    private String accidentDesc;

    @Schema(description = "本车损失")
    @ExcelProperty("本车损失")
    private BigDecimal ownVehiclePay;

    @Schema(description = "三者车损")
    @ExcelProperty("三者车损")
    private BigDecimal otherVehiclePay;

    @Schema(description = "医疗费")
    @ExcelProperty("医疗费")
    private BigDecimal hospitalPay;

    @Schema(description = "伤残或死亡")
    @ExcelProperty("伤残或死亡")
    private BigDecimal injuredDeadPay;

    @Schema(description = "其他")
    @ExcelProperty("其他")
    private BigDecimal otherPay;

    @Schema(description = "事故相关照片")
    @ExcelProperty("事故相关照片")
    private String photos;

    @Schema(description = "处理状态", example = "1")
    @ExcelProperty(value = "处理状态", converter = DictConvert.class)
    @DictFormat("handle_status") // TODO 代码优化：建议设置到对应的 DictTypeConstants 枚举类中
    private Integer handleStatus;

    @Schema(description = "结案日期")
    @ExcelProperty("结案日期")
    private String settlementDate;

    @Schema(description = "三者物损")
    @ExcelProperty("三者物损")
    private BigDecimal otherGoodsPay;

    @Schema(description = "财产损失小计")
    @ExcelProperty("财产损失小计")
    private BigDecimal vehicleTotalPay;

    @Schema(description = "人身损害小计")
    @ExcelProperty("人身损害小计")
    private BigDecimal personTotalPay;

    @Schema(description = "保险理赔记录")
    @ExcelProperty("保险理赔记录")
    private String insuranceRecord;

    @Schema(description = "保险理赔总金额")
    @ExcelProperty("保险理赔总金额")
    private BigDecimal insuranceTotal;

    @Schema(description = "事故车辆数", example = "1")
    @ExcelProperty(value = "事故车辆数", converter = DictConvert.class)
    @DictFormat("vehicle_count") // TODO 代码优化：建议设置到对应的 DictTypeConstants 枚举类中
    private Integer vehicleCount;

    @Schema(description = "受伤人数")
    @ExcelProperty("受伤人数")
    private Short injuredPenson;

    @Schema(description = "死亡人数")
    @ExcelProperty("死亡人数")
    private Short deadPerson;

    @Schema(description = "事故救援及处理记录")
    @ExcelProperty("事故救援及处理记录")
    private String handleRecord;

    @Schema(description = "处理进度")
    @ExcelProperty("处理进度")
    private String handleProcess;

    @Schema(description = "伤亡情况")
    @ExcelProperty("伤亡情况")
    private String injuredDeadDesc;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}