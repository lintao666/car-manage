package cn.iocoder.yudao.module.gps.controller.admin.device;

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

@Tag(name = "管理后台 - GPS")
@RestController
@RequestMapping("gps/vehicle/position")
@Validated
public class PositionController {

    @Resource
    private PositionService positionService;

    @GetMapping
    @Operation(summary = "查询所有车辆定位信息")
    public List<VehiclePositionStatusDO> vehiclePositionList() {
        return positionService.getList();
    }
}