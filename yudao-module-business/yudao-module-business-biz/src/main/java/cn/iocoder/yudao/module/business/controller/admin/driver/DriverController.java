package cn.iocoder.yudao.module.business.controller.admin.driver;

import cn.iocoder.yudao.framework.common.enums.CommonStatusEnum;
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

import cn.iocoder.yudao.module.business.controller.admin.driver.vo.*;
import cn.iocoder.yudao.module.business.dal.dataobject.driver.DriverDO;
import cn.iocoder.yudao.module.business.service.driver.DriverService;

@Tag(name = "管理后台 - 司机")
@RestController
@RequestMapping("/business/driver")
@Validated
public class DriverController {

    @Resource
    private DriverService driverService;

    @PostMapping("/create")
    @Operation(summary = "创建司机")
    @PreAuthorize("@ss.hasPermission('business:driver:create')")
    public CommonResult<Long> createDriver(@Valid @RequestBody DriverSaveReqVO createReqVO) {
        return success(driverService.createDriver(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新司机")
    @PreAuthorize("@ss.hasPermission('business:driver:update')")
    public CommonResult<Boolean> updateDriver(@Valid @RequestBody DriverSaveReqVO updateReqVO) {
        driverService.updateDriver(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除司机")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('business:driver:delete')")
    public CommonResult<Boolean> deleteDriver(@RequestParam("id") Long id) {
        driverService.deleteDriver(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得司机")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('business:driver:query')")
    public CommonResult<DriverRespVO> getDriver(@RequestParam("id") Long id) {
        DriverDO driver = driverService.getDriver(id);
        return success(BeanUtils.toBean(driver, DriverRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得司机分页")
    @PreAuthorize("@ss.hasPermission('business:driver:query')")
    public CommonResult<PageResult<DriverRespVO>> getDriverPage(@Valid DriverPageReqVO pageReqVO) {
        PageResult<DriverDO> pageResult = driverService.getDriverPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, DriverRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出司机 Excel")
    @PreAuthorize("@ss.hasPermission('business:driver:export')")
    @OperateLog(type = EXPORT)
    public void exportDriverExcel(@Valid DriverPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<DriverDO> list = driverService.getDriverPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "司机.xls", "数据", DriverRespVO.class,
                        BeanUtils.toBean(list, DriverRespVO.class));
    }

    @GetMapping("/list-all-simple")
    @Operation(summary = "获取司机全列表", description = "只包含被开启的司机，主要用于前端的下拉选项")
    public CommonResult<List<IdNameVO>> getSimplePostList() {
        // 获得岗位列表，只要开启状态的
        List<DriverDO> list = driverService.getDriverList(null, Collections.singleton(CommonStatusEnum.ENABLE.getStatus()));
        // 排序后，返回给前端
//        list.sort(Comparator.comparing(DriverDO::getSort));
        return success(BeanUtils.toBean(list, IdNameVO.class));
    }

}