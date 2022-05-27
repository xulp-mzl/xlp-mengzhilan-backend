package com.mengzhilan.service.menu;

import com.mengzhilan.entity.menu.MenuItem;
import org.xlp.mv.IBaseService;

import java.util.List;

/**
 * Create by xlp on 2022/5/2
 */
public interface MenuItemService extends IBaseService {
    /**
     * 获取所有的菜单条目对象
     * @return
     */
    List<MenuItem> getTreeMenuItems();

    /**
     * 保存菜单条目
     * @param menuItem
     * @return 假如已存在名称或路由相同的菜单条目则返回false，否则返回true
     */
    boolean saveMenuItem(MenuItem menuItem);

    /**
     * 更新菜单条目
     *
     * @param menuItem
     * @return
     */
    boolean updateMenuItem(MenuItem menuItem);

    /**
     * 判断是否有子菜单
     *
     * @param menuItem
     * @return false：没有，true：有
     */
    boolean checkHasChildren(MenuItem menuItem);
}
