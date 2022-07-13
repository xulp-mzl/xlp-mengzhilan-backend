package com.mengzhilan.helper;

import com.mengzhilan.dao.menu.MenuItemDao;
import com.mengzhilan.dao.model.ModelAttributeDao;
import com.mengzhilan.dao.model.ModelConfigDao;
import com.mengzhilan.dao.model.ModelDao;
import com.mengzhilan.util.BeanCreator;
import org.xlp.mv.IBaseDao;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Create by xlp on 2022/6/19
 * 获取dao实例工具类
 */
public class DaoHelper {
    /**
     * 缓冲池，缓存各个dao
     */
    private static final Map<String, IBaseDao> DAO_MAP = new ConcurrentHashMap<>();

    /**
     * 获取菜单操作DAO
     *
     * @return
     */
    public static MenuItemDao getMenuItemDao(){
        return (MenuItemDao) getIBaseDao("menuItemDao");
    }

    /**
     * 获取模型操作DAO
     *
     * @return
     */
    public static ModelDao getModelDao(){
        return (ModelDao) getIBaseDao("modelDao");
    }

    /**
     * 获取模型操作DAO
     *
     * @return
     */
    public static ModelConfigDao getModelConfigDao(){
        return (ModelConfigDao) getIBaseDao("modelConfigDao");
    }

    /**
     * 获取模型属性操作DAO
     *
     * @return
     */
    public static ModelAttributeDao getModelAttributeDao(){
        return (ModelAttributeDao) getIBaseDao("modelAttribute");
    }

    /**
     * 获取服务
     *
     * @param daoId 服务id， 在beanMapper.properties配置文件中的key
     * @return
     */
    public static IBaseDao getIBaseDao(String daoId){
        IBaseDao iBaseDao = DAO_MAP.get(daoId);
        if (iBaseDao == null){
            iBaseDao = BeanCreator.getBean(daoId);
            DAO_MAP.put(daoId, iBaseDao);
        }
        return iBaseDao;
    }

}
