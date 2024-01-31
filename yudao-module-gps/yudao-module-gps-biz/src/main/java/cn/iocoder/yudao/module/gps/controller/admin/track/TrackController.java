package cn.iocoder.yudao.module.gps.controller.admin.track;

import cn.iocoder.yudao.module.gps.controller.admin.position.vo.SummaryVO;
import cn.iocoder.yudao.module.gps.controller.admin.position.vo.VehiclePositionVO;
import cn.iocoder.yudao.module.gps.dal.dataobject.position.VehiclePositionStatusDO;
import cn.iocoder.yudao.module.gps.service.track.TrackService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 此页面轨迹可调速播放，
 * 轨迹上需集成事件数据，
 * 可展示轨迹内的所有GPS点位数据并可导出，
 * 可展示本轨迹内所有事件信息并可导出
 */
@Tag(name = "管理后台 - GPS-车辆轨迹展示")
@RestController
@RequestMapping("gps/track")
@Validated
public class TrackController {

    @Resource
    private TrackService trackService;

    /**
     * 搜索具体车辆（仅可选有OBD设备ID绑定信息的），选择时间段，展示这个时间段的轨迹。
     */
    @GetMapping("list")
    @Operation(summary = "查询所有车辆定位信息()")
    public List<VehiclePositionStatusDO> gpsList(@RequestParam Long vehicleId, @RequestParam LocalDateTime startTime, @RequestParam LocalDateTime endTime) {

        return trackService.getList();
    }

    /**
     * 车辆定位展示
     * 位置，方向，车牌号，车辆品牌，OBD设备号，司机姓名，手机号，设备的经纬度，设备通过地图接口解析出的具体地址，当前车速，当前方向，当前状态
     */
    @GetMapping("position2")
    @Operation(summary = "查询所有车辆定位信息")
    public List<VehiclePositionVO> vehiclePositions(Long deptId, String carNumber, String vehicleMask) {
        return trackService.list(carNumber, vehicleMask);
    }

    /**
     * 统计当前时间的
     * 车辆总数（有OBD设备ID绑定信息），
     * 车辆在线数（ACC处于点火状态），
     * 车辆停留数（ACC处于熄火状态并不处于离线状态），
     * 车辆离线数（ACC处于熄火状态超过3天）
     */
    @GetMapping("summary")
    @Operation(summary = "车辆数统计")
    public SummaryVO vehicleSummary() {
        SummaryVO summary = new SummaryVO();
        summary.setTotal(2910);
        summary.setOnline(1465);
        summary.setOff(1377);
        summary.setOffline(68);
        return summary;
    }
}