package cn.iocoder.yudao.module.gps.controller.admin.device;

import cn.iocoder.yudao.module.gps.controller.admin.device.vo.SummaryVO;
import cn.iocoder.yudao.module.gps.dal.dataobject.position.VehiclePositionStatusDO;
import cn.iocoder.yudao.module.gps.service.position.PositionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@Tag(name = "管理后台 - GPS-车辆定位展示")
@RestController
@RequestMapping("gps/vehicle/position")
@Validated
public class PositionController {

    @Resource
    private PositionService positionService;

    /**
     * 车辆定位展示
     * 此页面有
     */
    @GetMapping
    @Operation(summary = "查询所有车辆定位信息()")
    public List<VehiclePositionStatusDO> vehiclePositionList(Long deptId, String carNumber, String vehicleMask) {

        return positionService.getList();
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