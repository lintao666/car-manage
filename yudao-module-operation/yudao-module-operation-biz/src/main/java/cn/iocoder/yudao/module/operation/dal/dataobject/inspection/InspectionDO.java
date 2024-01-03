package cn.iocoder.yudao.module.operation.dal.dataobject.inspection;

import lombok.*;

import java.time.LocalDate;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 年审 DO
 *
 * @author 芋道源码
 */
@TableName("operation_inspection")
@KeySequence("operation_inspection_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InspectionDO extends BaseDO {

    /**
     * 编号
     */
    @TableId
    private Long id;
    /**
     * 1车辆年审，2气瓶年审
     *
     * 枚举 {@link TODO inspection_type 对应的类}
     */
    private Integer inspectionType;
    /**
     * 车辆Id
     */
    private Long vehicleId;
    /**
     * 车辆检测类别
     */
    private String inspectionClass;
    /**
     * 年审日期
     */
    private LocalDate inspectionDate;
    /**
     * 年审到期日期
     */
    private LocalDate inspectionEndDate;
    /**
     * 检测站
     */
    private String inspectionAddress;
    /**
     * 核审员
     */
    private String verifiedUserId;
    /**
     * 证件照片
     */
    private String pictures;
    /**
     * 1 已审 2待审
     *
     * 枚举 {@link TODO inspection_status 对应的类}
     */
    private Integer status;

}