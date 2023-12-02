package cn.iocoder.yudao.module.business.controller.admin.vehicle;

import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Operation;

import javax.validation.constraints.*;
import javax.validation.*;
import javax.servlet.http.*;
import java.util.*;
import java.io.IOException;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;

import cn.iocoder.yudao.framework.operatelog.core.annotations.OperateLog;
import static cn.iocoder.yudao.framework.operatelog.core.enums.OperateTypeEnum.*;

import cn.iocoder.yudao.module.business.controller.admin.vehicle.vo.*;
import cn.iocoder.yudao.module.business.dal.dataobject.vehicle.VehicleDO;
import cn.iocoder.yudao.module.business.service.vehicle.VehicleService;

@Tag(name = "管理后台 - 车辆")
@RestController
@RequestMapping("/business/vehicle")
@Validated
public class VehicleController {

    @Resource
    private VehicleService vehicleService;

    @PostMapping("/create")
    @Operation(summary = "创建车辆")
    @PreAuthorize("@ss.hasPermission('business:vehicle:create')")
    public CommonResult<Long> createVehicle(@Valid @RequestBody VehicleSaveReqVO createReqVO) {
        return success(vehicleService.createVehicle(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新车辆")
    @PreAuthorize("@ss.hasPermission('business:vehicle:update')")
    public CommonResult<Boolean> updateVehicle(@Valid @RequestBody VehicleSaveReqVO updateReqVO) {
        vehicleService.updateVehicle(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除车辆")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('business:vehicle:delete')")
    public CommonResult<Boolean> deleteVehicle(@RequestParam("id") Long id) {
        vehicleService.deleteVehicle(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得车辆")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('business:vehicle:query')")
    public CommonResult<VehicleRespVO> getVehicle(@RequestParam("id") Long id) {
        VehicleDO vehicle = vehicleService.getVehicle(id);
        return success(BeanUtils.toBean(vehicle, VehicleRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得车辆分页")
    @PreAuthorize("@ss.hasPermission('business:vehicle:query')")
    public CommonResult<PageResult<VehicleRespVO>> getVehiclePage(@Valid VehiclePageReqVO pageReqVO) {
        PageResult<VehicleDO> pageResult = vehicleService.getVehiclePage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, VehicleRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出车辆 Excel")
    @PreAuthorize("@ss.hasPermission('business:vehicle:export')")
    @OperateLog(type = EXPORT)
    public void exportVehicleExcel(@Valid VehiclePageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<VehicleDO> list = vehicleService.getVehiclePage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "车辆.xls", "数据", VehicleRespVO.class,
                        BeanUtils.toBean(list, VehicleRespVO.class));
    }

}