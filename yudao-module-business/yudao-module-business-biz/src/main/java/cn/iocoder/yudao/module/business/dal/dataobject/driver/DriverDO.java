package cn.iocoder.yudao.module.business.dal.dataobject.driver;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 司机 DO
 *
 * @author 芋道源码
 */
@TableName(value = "business_driver", autoResultMap = true)
@KeySequence("business_driver_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DriverDO extends BaseDO {

    /**
     * 编号
     */
    @TableId
    private Long id;
    /**
     * 部门id
     */
    private Long deptId;
    /**
     * 司机姓名
     */
    private String name;
    /**
     * 身份证号
     */
    private String idNumber;
    /**
     * 车牌号
     */
    private String carNumber;
    /**
     * 车辆id
     */
    private String vehicleId;
    /**
     * 手机号码
     */
    private String phoneNumber;
    /**
     * 紧急联系电话
     */
    private String emergencyTelephone;
    /**
     * 居住地址
     */
    private String residentialAddress;
    /**
     * 驾照号码
     */
    private String driverLicenseNumber;
    /**
     * 驾照等级
     */
    private String drivingClass;
    /**
     * 驾照起始时间
     */
    private LocalDateTime driverLicenseStartTime;
    /**
     * 驾照到期时间
     */
    private LocalDateTime driverLicenseExpirationTime;
    /**
     * 头像图片
     */
    private String headPortrait;
    /**
     * 附件（多张图片）
     */
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<String> attachment;
    /**
     * 状态
     * <p>
     * 枚举 {@link TODO common_status 对应的类}
     */
    private Integer status;

}