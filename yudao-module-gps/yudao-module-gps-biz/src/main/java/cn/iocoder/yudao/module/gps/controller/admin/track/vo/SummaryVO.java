package cn.iocoder.yudao.module.gps.controller.admin.track.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 车辆统计信息
 * 车辆总数：有OBD设备ID绑定信息的车辆
 * 在线数:ACC处于点火状态
 * 离线数：ACC处于熄火状态超过3天
 * 停留数：ACC处于熄火状态，并不处于离线状态
 */
@Data
@Schema(description = "车辆统计信息")
public class SummaryVO {
    @Schema(description = "总数(有OBD设备ID绑定信息的车辆)")
    private long total;
    @Schema(description = "在线数")
    private long online;
    @Schema(description = "停留数")
    private long off;
    //    @Schema(description = "重车数")
//    private long heavy;
//    @Schema(description = "空车数")
//    private long empty;
//    @Schema(description = "熄火数(ACC处于熄火状态并不处于离线状态)")
//    private long off;
    @Schema(description = "离线数")
    private long offline;
}
