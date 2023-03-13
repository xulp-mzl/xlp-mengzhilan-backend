package com.mengzhilan.service.menu.impl;

import com.mengzhilan.base.service.ApplicationBaseServiceAbstract;
import com.mengzhilan.dao.menu.MenuItemDao;
import com.mengzhilan.entity.menu.MenuItem;
import com.mengzhilan.helper.DaoHelper;
import com.mengzhilan.service.menu.MenuItemService;
import org.xlp.db.sql.CountSQL;
import org.xlp.utils.XLPStringUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Create by xlp on 2022/5/2
 */
public class MenuItemServiceImpl extends ApplicationBaseServiceAbstract implements MenuItemService {
    private MenuItemDao menuItemDao = DaoHelper.getMenuItemDao();

    /**
     * 获取所有的菜单条目对象
     *
     * @return
     */
    @Override
    public List<MenuItem> getTreeMenuItems() {
        List<MenuItem> menuItems = menuItemDao.getAllMenuItems();
        //根菜单集合
        List<MenuItem> rootItems = new ArrayList<>();
        menuItems.forEach(item -> {
            if (item.getParentId() == null)
                rootItems.add(item);
        });
        //去除根菜单
        menuItems.removeAll(rootItems);

        int size = menuItems.size();
        //子菜单集合
        List<MenuItem> childrenItems = rootItems;
        while (size > 0){
            List<MenuItem> tempItems = new ArrayList<>();
            for (MenuItem menuItem : menuItems) {
                for (MenuItem item : childrenItems) {
                    if (menuItem.getParentId().equals(item.getId())){
                        item.getChildren().add(menuItem);
                        tempItems.add(menuItem);
                    }
                }
            }

            //处理垃圾数据引起的死循环问题
            if(tempItems.isEmpty()){
                break;
            }

            //移除已处理的菜单项
            menuItems.removeAll(tempItems);
            size = menuItems.size();
            childrenItems = tempItems;
        }
        return rootItems;
    }

    /**
     * 保存菜单条目
     *
     * @param menuItem
     * @return 假如已存在名称或路由相同的菜单条目则返回false，否则返回true
     */
    @Override
    public boolean saveMenuItem(MenuItem menuItem) {
        if (XLPStringUtil.isEmpty(menuItem.getPath())){
            // 假如没设置path为name值
            menuItem.setPath("（自动设置）" + menuItem.getTitle());
        }
        boolean hasExists = menuItemDao.verifyTitle(menuItem);
        if (hasExists) return false;
        if (XLPStringUtil.isEmpty(menuItem.getParentId())){
            menuItem.setParentId(MenuItem.DEFAULT_PARENT_ID);
        }
        menuItem.setCreateTime(new Date());
        menuItem.setUpdateTime(menuItem.getCreateTime());
        this.save(menuItem);
        return true;
    }

    /**
     * 更新菜单条目
     *
     * @param menuItem
     * @return 假如已存在名称或路由相同的菜单条目则返回false，否则返回true
     */
    @Override
    public boolean updateMenuItem(MenuItem menuItem) {
        if (XLPStringUtil.isEmpty(menuItem.getPath())){
            // 假如没设置path为name值
            menuItem.setPath("（自动设置）" + menuItem.getTitle());
        }
        boolean hasExists = menuItemDao.verifyTitle(menuItem);
        if (hasExists) return false;
        menuItem.setUpdateTime(new Date());
        menuItemDao.updateMenuItem(menuItem);
        return true;
    }

    /**
     * 判断是否有子菜单
     *
     * @param menuItem
     * @return false：没有，true：有
     */
    @Override
    public boolean checkHasChildren(MenuItem menuItem) {
        CountSQL<MenuItem> countSQL = new CountSQL<>(MenuItem.class);
        countSQL.andEq("parentId", menuItem.getId());
        return count(countSQL) > 0;
    }
}
