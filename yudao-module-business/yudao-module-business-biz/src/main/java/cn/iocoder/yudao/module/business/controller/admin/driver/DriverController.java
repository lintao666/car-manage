package cn.iocoder.yudao.module.business.controller.admin.driver;

import cn.iocoder.yudao.framework.common.enums.CommonStatusEnum;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.IdNameVO;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.framework.operatelog.core.annotations.OperateLog;
import cn.iocoder.yudao.module.business.controller.admin.driver.vo.DriverPageReqVO;
import cn.iocoder.yudao.module.business.controller.admin.driver.vo.DriverRespVO;
import cn.iocoder.yudao.module.business.controller.admin.driver.vo.DriverSaveReqVO;
import cn.iocoder.yudao.module.business.dal.dataobject.driver.DriverDO;
import cn.iocoder.yudao.module.business.service.driver.DriverService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;
import static cn.iocoder.yudao.framework.operatelog.core.enums.OperateTypeEnum.EXPORT;

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
        PageResult<DriverRespVO> pageResult = driverService.getDriverPage(pageReqVO);
        return success(pageResult);
    }

    @GetMapping("/list")
    @Operation(summary = "司机列表")
    @PreAuthorize("@ss.hasPermission('business:driver:query')")
    public CommonResult<List<DriverRespVO>> getVehicleList(@Valid Long deptId) {
        List<DriverRespVO> list = driverService.getList(deptId);
        return success(list);
    }
    @GetMapping("/export-excel")
    @Operation(summary = "导出司机 Excel")
    @PreAuthorize("@ss.hasPermission('business:driver:export')")
    @OperateLog(type = EXPORT)
    public void exportDriverExcel(@Valid DriverPageReqVO pageReqVO,
                                  HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<DriverRespVO> list = driverService.getDriverPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "司机.xls", "数据", DriverRespVO.class, list);
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