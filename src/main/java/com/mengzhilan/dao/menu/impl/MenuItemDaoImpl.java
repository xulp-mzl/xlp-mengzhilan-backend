package com.mengzhilan.dao.menu.impl;

import com.mengzhilan.dao.menu.MenuItemDao;
import com.mengzhilan.entity.menu.MenuItem;
import org.xlp.db.sql.CountSQL;
import org.xlp.db.sql.QuerySQL;
import org.xlp.db.sql.UpdateSQL;
import org.xlp.mv.BaseDao;
import org.xlp.utils.XLPStringUtil;

import java.util.List;

/**
 * Create by xlp on 2022/5/2
 */
public class MenuItemDaoImpl extends BaseDao implements MenuItemDao {
    /**
     * 获取所有的菜单条目对象
     *
     * @return
     */
    @Override
    public List<MenuItem> getAllMenuItems() {
        QuerySQL<MenuItem> querySQL = new QuerySQL<>(MenuItem.class);
        querySQL.asc("parentId").asc("weight");
        return list(querySQL);
    }

    /**
     * 更新菜单条目
     *
     * @param menuItem
     * @return
     */
    @Override
    public boolean updateMenuItem(MenuItem menuItem) {
        UpdateSQL<MenuItem> updateSQL = new UpdateSQL<>(menuItem);
        updateSQL.clearUpdate()
                .set("updateTime", menuItem.getUpdateTime())
                .set("title", XLPStringUtil.emptyTrim(menuItem.getTitle()))
                .set("icon", menuItem.getIcon())
                .set("iconType", menuItem.getIconType())
                .set("weight", menuItem.getWeight())
                .set("path", XLPStringUtil.emptyTrim(menuItem.getPath()));
        return update(updateSQL);
    }

    /**
     * 验证路由或菜单标题是否已存在
     *
     * @param menuItem
     * @return true： 存在，false：不存在
     */
    @Override
    public boolean verifyPathAndTitle(MenuItem menuItem) {
        CountSQL<MenuItem> countSQL = new CountSQL<>(MenuItem.class);
        countSQL.group().andEq("title", XLPStringUtil.emptyTrim(menuItem.getTitle()))
                .orEq("path", XLPStringUtil.emptyTrim(menuItem.getPath())).endGroup();
        if (!XLPStringUtil.isEmpty(menuItem.getId())){
            countSQL.andNotEq("id", menuItem.getId());
        }
        long count = this.count(countSQL);
        if (count > 0){
            return true;
        }
        return false;
    }
}
