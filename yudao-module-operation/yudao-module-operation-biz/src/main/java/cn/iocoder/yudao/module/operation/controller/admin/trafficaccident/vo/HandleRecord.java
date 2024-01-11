package cn.iocoder.yudao.module.operation.controller.admin.trafficaccident.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDate;

/**
 * 事故救援及处理记录
 */
@Data
@Schema(description = "事故救援及处理记录")
public class HandleRecord {
    /**
     * 事故救援及处理记录日期
     */
    @Schema(description = "事故救援及处理记录日期")
    private LocalDate handleDate;
    /**
     * 事故救援及处理记录事项
     */
    @Schema(description = "事故救援及处理记录事项")
    private String handleDesc;
}
