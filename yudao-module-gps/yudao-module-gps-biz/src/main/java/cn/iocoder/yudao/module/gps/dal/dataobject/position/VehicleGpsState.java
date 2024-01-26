package cn.iocoder.yudao.module.gps.dal.dataobject.position;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class VehicleGpsState {
    private Long vehicleId; // 车辆ID
    private String modelName;
    private String devcode;

    private BigDecimal longitude; //
    private BigDecimal latitude; //
    private Integer acc; // 点熄火状态(1点火，0熄火)
    private Integer online; // 是否在线(1在线，0离线)

    private Long lastTime;// 最后点时间
    private LocalDateTime machineTime; // 上报时间
    private Integer obdSpeed; // obd速度
    private Integer state;// 状态
    private Integer direction; // 方向

}
