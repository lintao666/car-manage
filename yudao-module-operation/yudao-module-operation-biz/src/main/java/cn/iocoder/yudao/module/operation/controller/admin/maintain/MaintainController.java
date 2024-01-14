package cn.iocoder.yudao.module.operation.controller.admin.maintain;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.framework.operatelog.core.annotations.OperateLog;
import cn.iocoder.yudao.module.business.api.vehicle.VehicleApi;
import cn.iocoder.yudao.module.operation.controller.admin.maintain.vo.*;
import cn.iocoder.yudao.module.operation.dal.dataobject.maintain.MaintainDO;
import cn.iocoder.yudao.module.operation.service.maintain.MaintainService;
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

@Tag(name = "管理后台 - 保养/二级维护")
@RestController
@RequestMapping("/operation/maintain")
@Validated
public class MaintainController {

    @Resource
    private MaintainService maintainService;
    @Resource
    private VehicleApi vehicleApi;

    @PostMapping("/create")
    @Operation(summary = "创建保养/二级维护")
    @PreAuthorize("@ss.hasPermission('operation:maintain:create')")
    public CommonResult<Long> createMaintain(@Valid @RequestBody MaintainSaveReqVO createReqVO) {
        return success(maintainService.createMaintain(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新保养/二级维护")
    @PreAuthorize("@ss.hasPermission('operation:maintain:update')")
    public CommonResult<Boolean> updateMaintain(@Valid @RequestBody MaintainSaveReqVO updateReqVO) {
        maintainService.updateMaintain(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除保养/二级维护")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('operation:maintain:delete')")
    public CommonResult<Boolean> deleteMaintain(@RequestParam("id") Long id) {
        maintainService.deleteMaintain(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得保养/二级维护")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('operation:maintain:query')")
    public CommonResult<MaintainRespVO> getMaintain(@RequestParam("id") Long id) {
        MaintainDO maintain = maintainService.getMaintain(id);
        return success(BeanUtils.toBean(maintain, MaintainRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得保养/二级维护分页")
    @PreAuthorize("@ss.hasPermission('operation:maintain:query')")
    public CommonResult<PageResult<MaintainRespVO>> getMaintainPage(@Valid MaintainPageReqVO pageReqVO) {
        PageResult<MaintainDO> pageResult = maintainService.getMaintainPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, MaintainRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出保养/二级维护 Excel")
    @PreAuthorize("@ss.hasPermission('operation:maintain:export')")
    @OperateLog(type = EXPORT)
    public void exportMaintainExcel(@Valid MaintainPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<MaintainDO> list = maintainService.getMaintainPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "保养/二级维护.xls", "数据", MaintainRespVO.class,
                        BeanUtils.toBean(list, MaintainRespVO.class));
    }

    @GetMapping("/get-import-template")
    @Operation(summary = "获得导入保养记录模板")
    public void importTemplate(HttpServletResponse response) throws IOException {
        // 手动创建导出 demo
        List<MaintainImportVO> list = Arrays.asList(
                MaintainImportVO.builder().vehicleMask("14623").carNumber("川ADU6291")
                        .maintainDate(LocalDate.of(2023, 4, 1))
                        .maintainEndDate(LocalDate.of(2023, 4, 1))
                        .maintainMileage(1000d).maintainEndMileage(3000d)
                        .planMaintainDate(LocalDate.of(2023, 4, 1))
                        .informMaintainDate(LocalDate.of(2023, 1, 12)).informUser("保修人11")
                        .driverName("司机11")
                        .maintainShop("成都市三宏汽车技术有限公司")
                        .certificateNumber("合格证号11111")
                        .build(),
                MaintainImportVO.builder().vehicleMask("14623").carNumber("川ADU6291")
                        .maintainDate(LocalDate.of(2023, 4, 1))
                        .maintainEndDate(LocalDate.of(2023, 4, 1))
                        .maintainMileage(1000d).maintainEndMileage(3000d)
                        .planMaintainDate(LocalDate.of(2023, 4, 1))
                        .informMaintainDate(LocalDate.of(2023, 1, 12)).informUser("保修人11")
                        .driverName("司机11")
                        .maintainShop("成都市三宏汽车技术有限公司")
                        .certificateNumber("合格证号11111")
                        .build()
        );
        ExcelUtils.write(response, "车辆保养导入模板.xls", "车辆保养记录", MaintainImportVO.class, list);
    }

    @PostMapping("/import")
    @Operation(summary = "导入保养记录 Excel")
    @PreAuthorize("@ss.hasPermission('operation:repair-vehicle:import')")
    @OperateLog(type = IMPORT)
    public CommonResult<MaintainImportRespVO> importExcel(@RequestParam(value = "file") MultipartFile file) throws IOException {
        MaintainListener listener = new MaintainListener(maintainService, vehicleApi);
        // 这里 需要指定读用哪个class去读，然后读取第一个sheet 文件流会自动关闭
        EasyExcel.read(file.getInputStream(), MaintainImportVO.class, listener)
                .sheet().doRead();
        return success(listener.getImportResp());
    }

}