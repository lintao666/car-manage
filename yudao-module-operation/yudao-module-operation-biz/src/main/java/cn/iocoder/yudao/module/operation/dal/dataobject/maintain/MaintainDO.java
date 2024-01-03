package cn.iocoder.yudao.module.operation.dal.dataobject.maintain;

import lombok.*;

import java.time.LocalDate;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 保养/二级维护 DO
 *
 * @author 芋道源码
 */
@TableName("operation_maintain")
@KeySequence("operation_maintain_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MaintainDO extends BaseDO {

    /**
     * 编号
     */
    @TableId
    private Long id;
    /**
     * 车辆id
     */
    private Long vehicleId;
    /**
     * 合格证号
     */
    private String certificateNumber;
    /**
     * 维护保养厂
     */
    private String maintainShop;
    /**
     * 保养日期
     */
    private LocalDate maintainDate;
    /**
     * 保养到期日期/到期状态
     */
    private LocalDate maintainEndDate;
    /**
     * 保养时里程
     */
    private Double maintainMileage;
    /**
     * 保养到期里程
     */
    private Double maintainEndMileage;
    /**
     * 图片
     */
    private String pic;

}