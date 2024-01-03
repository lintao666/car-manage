package cn.iocoder.yudao.module.operation.dal.dataobject.insurance;

import lombok.*;

import java.time.LocalDate;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 保单 DO
 *
 * @author 芋道源码
 */
@TableName("operation_insurance")
@KeySequence("operation_insurance_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InsuranceDO extends BaseDO {

    /**
     * 编号
     */
    @TableId
    private Long id;
    /**
     * 1 - 交强险，2 - 商业险,3-承运人责任险
     */
    private Integer insuranceType;
    /**
     * 车辆Id
     */
    private Long vehicleId;
    /**
     * 保单号
     */
    private String insuranceNumber;
    /**
     * 保险公司
     */
    private String insuranceCompany;
    /**
     * 总保费
     */
    private Double insuranceFee;
    /**
     * 生效日期
     */
    private LocalDate startDate;
    /**
     * 到期日期/到期状态判断
     */
    private LocalDate endDate;
    /**
     * 保单照片
     */
    private String pictures;

}