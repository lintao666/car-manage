package cn.iocoder.yudao.module.gps.dal.dataobject.fence;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Schema(description = "电子围栏")
@TableName("gps_fence")
@Data
public class FenceDO {
    private Long id;
    @Schema(description = "机构ID")
    private String deptId;
    @Schema(description = "机构名称")
    private String deptName;
    @Schema(description = "围栏名称")
    private String fenceName;
    @Schema(description = "围栏类型")
    private String fenceType;
    @Schema(description = "驶入告警")
    private Integer alarmIn;
    @Schema(description = "驶出告警")
    private Integer alarmOut;
    @Schema(description = "启用时间")
    private String alarmDate;
    @Schema(description = "启用时间段开始")
    private String startHour;
    @Schema(description = "启用时间段结束")
    private String endHour;
    @Schema(description = "启用时间段开始")
    private String startDatespan;
    @Schema(description = "启用时间段结束")
    private String endDatespan;
    @Schema(description = "通知内容")
    private String alarmContent;
    @Schema(description = "围栏形状 1-圆形 2-矩形 3-多边形")
    private Integer fenceShape;
    @Schema(description = "围栏区域")
    private String fenceArea;
    @Schema(description = "使用状态")
    private Integer useState;
    @Schema(description = "预警数量")
    private Integer carLimit;
    @Schema(description = "实际车辆数量（只是查询车辆预警用）")
    private Integer carCount;
    @Schema(description = "围栏中心")
    private String fenceCenter;
    @Schema(description = "驶入围栏语音提示")
    private String ttsIn;
    @Schema(description = "驶出围栏语音提示")
    private String ttsOut;
    @Schema(description = "超速阀值")
    private Integer overSpeed;
    private List<String> deptlist;
    private List<Long> vehiclelist;
}
