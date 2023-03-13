package com.mengzhilan.dao.menu;

import com.mengzhilan.entity.menu.MenuItem;
import org.xlp.mv.IBaseDao;

import java.util.List;

/**
 * Create by xlp on 2022/5/2
 */
public interface MenuItemDao extends IBaseDao {
    /**
     * 获取所有的菜单条目对象
     *
     * @return
     */
    List<MenuItem> getAllMenuItems();

    /**
     * 更新菜单条目
     *
     * @param menuItem
     * @return
     */
    boolean updateMenuItem(MenuItem menuItem);

    /**
     * 验证菜单标题是否已存在
     *
     * @param menuItem
     * @return true： 存在，false：不存在
     */
    boolean verifyTitle(MenuItem menuItem);
}
