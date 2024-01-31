
package cn.iocoder.yudao.module.gps.controller.admin.track.vo;

import cn.iocoder.yudao.module.business.api.driver.dto.DriverRespDTO;
import cn.iocoder.yudao.module.gps.enums.VehicleStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class VehiclePositionVO {
    @Schema(description = "车辆ID")
    private Long vehicleId;
    @Schema(description = "自编号")
    private String vehicleMask;
    @Schema(description = "车牌号")
    private String carNumber;
    @Schema(description = "车辆品牌")
    private String brand;
    @Schema(description = "OBD设备号")
    private String devcode;
    @Schema(description = "状态")
    private VehicleStatus state;


    @Schema(description = "经度")
    private BigDecimal longitude;
    @Schema(description = "纬度")
    private BigDecimal latitude;
    @Schema(description = "设备通过地图接口解析出的具体地址")
    private String district;
    @Schema(description = "obd速度")
    private Integer obdSpeed;

    @Schema(description = "点熄火状态(1点火，0熄火)")
    private Integer acc;
    @Schema(description = "是否在线(1在线，0离线)")
    private Integer online;
    @Schema(description = "方向")
    private Integer direction;
    @Schema(description = "司机")
    private List<DriverRespDTO> driverList;

}
