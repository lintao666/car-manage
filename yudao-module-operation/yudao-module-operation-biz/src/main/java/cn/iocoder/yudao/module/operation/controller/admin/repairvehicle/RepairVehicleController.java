package cn.iocoder.yudao.module.operation.controller.admin.repairvehicle;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.framework.operatelog.core.annotations.OperateLog;
import cn.iocoder.yudao.module.business.api.vehicle.VehicleApi;
import cn.iocoder.yudao.module.operation.controller.admin.repairvehicle.vo.*;
import cn.iocoder.yudao.module.operation.dal.dataobject.repairvehicle.RepairVehicleDO;
import cn.iocoder.yudao.module.operation.service.repairvehicle.RepairVehicleService;
import com.alibaba.excel.EasyExcel;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;
import static cn.iocoder.yudao.framework.operatelog.core.enums.OperateTypeEnum.EXPORT;
import static cn.iocoder.yudao.framework.operatelog.core.enums.OperateTypeEnum.IMPORT;

@Tag(name = "管理后台 - 维修")
@RestController
@RequestMapping("/operation/repair-vehicle")
@Validated
public class RepairVehicleController {

    @Resource
    private RepairVehicleService repairVehicleService;
    @Resource
    private VehicleApi vehicleApi;

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

    @GetMapping("/get-import-template")
    @Operation(summary = "获得导入维修记录模板")
    public void importTemplate(HttpServletResponse response) throws IOException {
        // 手动创建导出 demo
        List<RepairVehicleImportVO> list = Arrays.asList(
                RepairVehicleImportVO.builder().vehicleMask("14623").carNumber("川ADU6291")
                        .repairDate(LocalDate.of(2023, 4, 1))
                        .reportDate(LocalDate.of(2023, 1, 12)).reportUser("保修人11")
                        .changeProject("常规保养 减速器油").repairTheme("日常维修").repairMoney(320d)
                        .repairAddress("成都市三宏汽车技术有限公司")
                        .repairLevel(1)
                        .build(),
                RepairVehicleImportVO.builder().vehicleMask("05452").carNumber("川ADT2069")
                        .reportDate(LocalDate.of(2023, 11, 6)).reportUser("保修人11")
                        .repairDate(LocalDate.of(2023, 12, 1))
                        .changeProject("常规保养 刹车油 防冻液").repairTheme("日常维修").repairMoney(320d)
                        .repairAddress("成都市三宏汽车技术有限公司")
                        .repairLevel(1)
                        .build()
        );
        ExcelUtils.write(response, "车辆维修记录导入模板.xls", "车辆维修记录", RepairVehicleImportVO.class, list);
    }

    @PostMapping("/import")
    @Operation(summary = "导入维修 Excel")
    @PreAuthorize("@ss.hasPermission('operation:repair-vehicle:import')")
    @OperateLog(type = IMPORT)
    public CommonResult<RepairVehicleImportRespVO> importExcel(@RequestParam(value = "file") MultipartFile file) throws IOException {
        RepairVehicleListener listener = new RepairVehicleListener(repairVehicleService, vehicleApi);
        // 这里 需要指定读用哪个class去读，然后读取第一个sheet 文件流会自动关闭
        EasyExcel.read(file.getInputStream(), RepairVehicleImportVO.class, listener)
                .sheet().doRead();
        return success(listener.getImportResp());
    }

//    @PostMapping("/import2")
//    @Operation(summary = "导入维修记录")
//    @Parameters({
//            @Parameter(name = "file", description = "Excel 文件", required = true),
//            @Parameter(name = "updateSupport", description = "是否支持更新，默认为 false", example = "true")
//    })
//    @PreAuthorize("@ss.hasPermission('system:user:import')")
//    public CommonResult<RepairVehicleImportRespVO> importExcel2(@RequestParam("file") MultipartFile file,
//                                                                @RequestParam(value = "updateSupport", required = false, defaultValue = "false") Boolean updateSupport) throws Exception {
//        List<RepairVehicleImportVO> list = ExcelUtils.read(file, RepairVehicleImportVO.class);
//        return success(repairVehicleService.importList(list, updateSupport));
//    }

}