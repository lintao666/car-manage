package cn.iocoder.yudao.module.gps.dal.dataobject.position;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@TableName(value = "vehicle_gps_state", autoResultMap = true)
public class VehiclePositionStatusDO {
    @Schema(description = "车辆ID")
    private Long vehicleId;
    @Schema(description = "部门Id")
    private String deptId;
    private String devcode;
    @Schema(description = "经度")
    private BigDecimal longitude;
    @Schema(description = "纬度")
    private BigDecimal latitude;
    @Schema(description = "点熄火状态(1点火，0熄火)")
    private Integer acc;
    @Schema(description = "是否在线(1在线，0离线)")
    private Integer online;
    @Schema(description = "上报时间")
    private LocalDateTime machineTime;
    @Schema(description = "obd速度")
    private Integer obdSpeed;
    //    private Integer state;// 状态
    @Schema(description = "方向")
    private Integer direction;


}
