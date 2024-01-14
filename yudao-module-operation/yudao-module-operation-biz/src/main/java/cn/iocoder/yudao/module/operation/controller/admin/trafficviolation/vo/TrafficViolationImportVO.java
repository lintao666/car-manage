package cn.iocoder.yudao.module.operation.controller.admin.trafficviolation.vo;

import cn.iocoder.yudao.framework.excel.core.annotations.DictFormat;
import cn.iocoder.yudao.framework.excel.core.convert.DictConvert;
import cn.iocoder.yudao.module.operation.enums.DictTypeConstants;
import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 维修excel 导入VO")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TrafficViolationImportVO {

    //todo 是否删除该字段
    @ExcelProperty("受理单号")
    private String plateNo;
    @ExcelProperty("自编号")
    private String vehicleMask;
//
//
//    @ExcelProperty("违章行为")
//    private String violationBeh;
//
//    @ExcelProperty("处理结果")
//    private String disposeResult;
//    @ExcelProperty(value = "处理状态", converter = DictConvert.class)
//    @DictFormat(DictTypeConstants.OPERATION_HANDLE_STATUS)
//    private Integer handleStatus;
//    @ExcelProperty("处理人")
//    private String disposeId;

    @ExcelProperty("车牌号")
    private String carNumber;
    @ExcelProperty("驾驶员")
    private String driverName;
    @ExcelProperty({"违法时间", "违章时间"})
    private LocalDateTime violationDate;
    @ExcelProperty("违法地点")
    private String place;

    @ExcelProperty(value = "决定书类别", converter = DictConvert.class)
    @DictFormat(DictTypeConstants.DECISION_TYPE)
    private Short decisionType;
    @ExcelProperty({"违章详情", "违法内容"})
    private String violationDesc;
    @ExcelProperty(value = {"信息来源", "数据来源"}, converter = DictConvert.class)
    @DictFormat(DictTypeConstants.OPERATION_SOURCE)
    private Short source;
    //private Short vioOrigin;
    @ExcelProperty("违法代码")
    private String violationType;
    @ExcelProperty("罚款")
    private BigDecimal pay;
    @ExcelProperty("扣分")
    private Integer points;

}
