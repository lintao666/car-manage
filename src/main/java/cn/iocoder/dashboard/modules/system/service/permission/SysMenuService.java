package cn.iocoder.dashboard.modules.system.service.permission;

import cn.iocoder.dashboard.modules.system.controller.permission.vo.menu.SysMenuCreateReqVO;
import cn.iocoder.dashboard.modules.system.controller.permission.vo.menu.SysMenuListReqVO;
import cn.iocoder.dashboard.modules.system.controller.permission.vo.menu.SysMenuRespVO;
import cn.iocoder.dashboard.modules.system.controller.permission.vo.menu.SysMenuUpdateReqVO;
import cn.iocoder.dashboard.modules.system.dal.mysql.dataobject.permission.SysMenuDO;

import java.util.Collection;
import java.util.List;

/**
 * 菜单 Service 接口
 *
 * @author 芋道源码
 */
public interface SysMenuService {

    /**
     * 初始化菜单
     */
    void init();

    /**
     * 筛选菜单列表
     *
     * @param reqVO 筛选条件请求 VO
     * @return 菜单列表
     */
    List<SysMenuRespVO> listMenus(SysMenuListReqVO reqVO);

    /**
     * 获得所有菜单，从缓存中
     *
     * 任一参数为空时，则返回为空
     *
     * @param menuTypes 菜单类型数组
     * @param menusStatuses 菜单状态数组
     * @return 菜单列表
     */
    List<SysMenuDO> listMenusFromCache(Collection<Integer> menuTypes, Collection<Integer> menusStatuses);

    /**
     * 获得指定编号的菜单数组，从缓存中
     *
     * 任一参数为空时，则返回为空
     *
     * @param menuIds 菜单编号数组
     * @param menuTypes 菜单类型数组
     * @param menusStatuses 菜单状态数组
     * @return 菜单数组
     */
    List<SysMenuDO> listMenusFromCache(Collection<Long> menuIds, Collection<Integer> menuTypes,
                                       Collection<Integer> menusStatuses);

    /*
     * 创建菜单
     *
     * @param reqVO 菜单信息
     * @return 创建出来的菜单编号
     */
    Long createMenu(SysMenuCreateReqVO reqVO);

    /**
     * 更新菜单
     *
     * @param reqVO 菜单信息
     */
    void updateMenu(SysMenuUpdateReqVO reqVO);

    /**
     * 删除菜单
     *
     * @param id 菜单编号
     */
    void deleteMenu(Long id);

    /**
     * 获得菜单
     *
     * @param id 菜单编号
     * @return 菜单
     */
    SysMenuDO getMenu(Long id);

}
