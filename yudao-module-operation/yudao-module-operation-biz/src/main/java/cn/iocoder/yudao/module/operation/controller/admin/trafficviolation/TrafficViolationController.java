package cn.iocoder.yudao.module.operation.controller.admin.trafficviolation;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.framework.operatelog.core.annotations.OperateLog;
import cn.iocoder.yudao.module.business.api.driver.DriverApi;
import cn.iocoder.yudao.module.business.api.vehicle.VehicleApi;
import cn.iocoder.yudao.module.operation.controller.admin.trafficviolation.vo.*;
import cn.iocoder.yudao.module.operation.dal.dataobject.trafficviolation.TrafficViolationDO;
import cn.iocoder.yudao.module.operation.service.trafficviolation.TrafficViolationService;
import com.alibaba.excel.EasyExcel;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;
import static cn.iocoder.yudao.framework.operatelog.core.enums.OperateTypeEnum.EXPORT;
import static cn.iocoder.yudao.framework.operatelog.core.enums.OperateTypeEnum.IMPORT;

@Tag(name = "管理后台 - 交通违法")
@RestController
@RequestMapping("/operation/traffic-violation")
@Validated
@AllArgsConstructor
public class TrafficViolationController {

    private final TrafficViolationService trafficViolationService;
    private final VehicleApi vehicleApi;
    private final DriverApi driverApi;

    @PostMapping("/create")
    @Operation(summary = "创建交通违法")
    @PreAuthorize("@ss.hasPermission('operation:traffic-violation:create')")
    public CommonResult<Long> createTrafficViolation(@Valid @RequestBody TrafficViolationSaveReqVO createReqVO) {
        return success(trafficViolationService.createTrafficViolation(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新交通违法")
    @PreAuthorize("@ss.hasPermission('operation:traffic-violation:update')")
    public CommonResult<Boolean> updateTrafficViolation(@Valid @RequestBody TrafficViolationSaveReqVO updateReqVO) {
        trafficViolationService.updateTrafficViolation(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除交通违法")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('operation:traffic-violation:delete')")
    public CommonResult<Boolean> deleteTrafficViolation(@RequestParam("id") Long id) {
        trafficViolationService.deleteTrafficViolation(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得交通违法")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('operation:traffic-violation:query')")
    public CommonResult<TrafficViolationRespVO> getTrafficViolation(@RequestParam("id") Long id) {
        TrafficViolationDO trafficViolation = trafficViolationService.getTrafficViolation(id);
        return success(BeanUtils.toBean(trafficViolation, TrafficViolationRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得交通违法分页")
    @PreAuthorize("@ss.hasPermission('operation:traffic-violation:query')")
    public CommonResult<PageResult<TrafficViolationRespVO>> getTrafficViolationPage(@Valid TrafficViolationPageReqVO pageReqVO) {
        PageResult<TrafficViolationDO> pageResult = trafficViolationService.getTrafficViolationPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, TrafficViolationRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出交通违法 Excel")
    @PreAuthorize("@ss.hasPermission('operation:traffic-violation:export')")
    @OperateLog(type = EXPORT)
    public void exportTrafficViolationExcel(@Valid TrafficViolationPageReqVO pageReqVO,
                                            HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<TrafficViolationDO> list = trafficViolationService.getTrafficViolationPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "交通违法.xls", "数据", TrafficViolationRespVO.class,
                BeanUtils.toBean(list, TrafficViolationRespVO.class));
    }

    @GetMapping("/get-import-template")
    @Operation(summary = "获得导入交通违法模板")
    public void importTemplate(HttpServletResponse response) throws IOException {
        // 手动创建导出 demo
        List<TrafficViolationImportVO> list = Arrays.asList(
                TrafficViolationImportVO.builder().vehicleMask("14623").carNumber("川ADU6291")
                        .decisionType((short) 1)
                        .driverName("张三")
                        .violationDate(LocalDateTime.now())
                        .place("违法地点")
                        .violationType("违法代码")
                        .violationDesc("违法内容")
                        .source((short) 1)
                        .points(5)
                        .pay(new BigDecimal("100.44"))
                        .build(),
                TrafficViolationImportVO.builder().vehicleMask("14623").carNumber("川ADU6291")
                        .decisionType((short) 1)
                        .driverName("张三")
                        .violationDate(LocalDateTime.now())
                        .place("违法地点")
                        .violationType("违法代码")
                        .violationDesc("违法内容")
                        .source((short) 1)
                        .points(5)
                        .pay(new BigDecimal("100.44"))
                        .build()
        );
        ExcelUtils.write(response, "车辆交通违法信息导入模板.xls", "车辆交通违法信息", TrafficViolationImportVO.class, list);
    }

    @PostMapping("/import")
    @Operation(summary = "导入交通违法信息 Excel")
    @PreAuthorize("@ss.hasPermission('operation:repair-vehicle:import')")
    @OperateLog(type = IMPORT)
    public CommonResult<TrafficViolationImportRespVO> importExcel(@RequestParam(value = "file") MultipartFile file) throws IOException {
        TrafficViolationListener listener = new TrafficViolationListener(trafficViolationService, vehicleApi, driverApi);
        EasyExcel.read(file.getInputStream(), TrafficViolationImportVO.class, listener)
                .sheet().doRead();
        return success(listener.getImportResp());
    }

}