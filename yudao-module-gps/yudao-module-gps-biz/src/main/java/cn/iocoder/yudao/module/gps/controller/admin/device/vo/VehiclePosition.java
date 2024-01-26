
package cn.iocoder.yudao.module.gps.controller.admin.device.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class VehiclePosition {

    /**
     * 点熄火状态(1点火，0熄火)
     */
    @Schema
    private Long acc;
    @Schema
    private Long alarmStatus;
    @Schema
    private String deptId;
    @Schema
    private String deptName;
    @Schema
    private String deptPhone;
    @Schema
    private String devcode;
    @Schema
    private Long direction;
    @Schema
    private String driverId;
    @Schema
    private String driverName;
    @Schema
    private String driverNum;
    @Schema
    private String driverPhoto;
    @Schema
    private String dvrVersion;
    @Schema
    private String gpsNumber;
    @Schema
    private Object lastTime;
    @Schema
    private Double latitude;
    @Schema
    private Double longitude;
    @Schema
    private String machineTime;
    @Schema
    private String modelName;
    @Schema
    private Long obdSpeed;
    @Schema
    private Long online;
    @Schema
    private Long orderState;
    @Schema
    private String phone;
    @Schema
    private String selfNum;
    @Schema
    private Object sendCount;
    @Schema
    private Object sendTime;
    @Schema
    private Object state;
    @Schema
    private String vehicleCode;
    @Schema
    private Long vehicleId;
    @Schema
    private String vehicleMask;

}
