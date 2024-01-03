package cn.iocoder.yudao.module.operation.controller.admin.repairvehicle;

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

import cn.iocoder.yudao.module.operation.controller.admin.repairvehicle.vo.*;
import cn.iocoder.yudao.module.operation.dal.dataobject.repairvehicle.RepairVehicleDO;
import cn.iocoder.yudao.module.operation.service.repairvehicle.RepairVehicleService;

@Tag(name = "管理后台 - 维修")
@RestController
@RequestMapping("/operation/repair-vehicle")
@Validated
public class RepairVehicleController {

    @Resource
    private RepairVehicleService repairVehicleService;

    @PostMapping("/create")
    @Operation(summary = "创建维修")
    @PreAuthorize("@ss.hasPermission('operation:repair-vehicle:create')")
    public CommonResult<Long> createRepairVehicle(@Valid @RequestBody RepairVehicleSaveReqVO createReqVO) {
        return success(repairVehicleService.createRepairVehicle(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新维修")
    @PreAuthorize("@ss.hasPermission('operation:repair-vehicle:update')")
    public CommonResult<Boolean> updateRepairVehicle(@Valid @RequestBody RepairVehicleSaveReqVO updateReqVO) {
        repairVehicleService.updateRepairVehicle(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除维修")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('operation:repair-vehicle:delete')")
    public CommonResult<Boolean> deleteRepairVehicle(@RequestParam("id") Long id) {
        repairVehicleService.deleteRepairVehicle(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得维修")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('operation:repair-vehicle:query')")
    public CommonResult<RepairVehicleRespVO> getRepairVehicle(@RequestParam("id") Long id) {
        RepairVehicleDO repairVehicle = repairVehicleService.getRepairVehicle(id);
        return success(BeanUtils.toBean(repairVehicle, RepairVehicleRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得维修分页")
    @PreAuthorize("@ss.hasPermission('operation:repair-vehicle:query')")
    public CommonResult<PageResult<RepairVehicleRespVO>> getRepairVehiclePage(@Valid RepairVehiclePageReqVO pageReqVO) {
        PageResult<RepairVehicleDO> pageResult = repairVehicleService.getRepairVehiclePage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, RepairVehicleRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出维修 Excel")
    @PreAuthorize("@ss.hasPermission('operation:repair-vehicle:export')")
    @OperateLog(type = EXPORT)
    public void exportRepairVehicleExcel(@Valid RepairVehiclePageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<RepairVehicleDO> list = repairVehicleService.getRepairVehiclePage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "维修.xls", "数据", RepairVehicleRespVO.class,
                        BeanUtils.toBean(list, RepairVehicleRespVO.class));
    }

}