package com.mengzhilan.helper;

import com.mengzhilan.service.menu.MenuItemService;
import com.mengzhilan.service.model.ModelAttributeService;
import com.mengzhilan.service.model.ModelConfigService;
import com.mengzhilan.service.model.ModelService;
import com.mengzhilan.util.BeanCreator;
import org.xlp.mv.IBaseService;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Create by xlp on 2022/6/13
 *
 * 获取各个service工具类
 */
public class CommonServiceHelper {
    /**
     * 缓冲池，缓存各个service
     */
    private static final Map<String, IBaseService> SERVICE_MAP = new ConcurrentHashMap<>();

    /**
     * 获取菜单操作服务
     *
     * @return
     */
    public static MenuItemService getMenuItemService(){
        return (MenuItemService) getIBaseService("menuItemService");
    }

    /**
     * 获取服务
     *
     * @param serviceId 服务id， 在beanMapper.properties配置文件中的key
     * @return
     */
    public static IBaseService getIBaseService(String serviceId){
        if (serviceId == null) return null;
        IBaseService iBaseService = SERVICE_MAP.get(serviceId);
        if (iBaseService == null){
            iBaseService = BeanCreator.getBean(serviceId);
            if (iBaseService != null) {
                SERVICE_MAP.put(serviceId, iBaseService);
            }
        }
        return iBaseService;
    }

    /**
     * 获取模型操作服务
     *
     * @return
     */
    public static ModelService getModelService(){
        return (ModelService) getIBaseService("modelService");
    }

    /**
     * 获取模型配置操作服务
     *
     * @return
     */
    public static ModelConfigService getModelConfigService(){
        return (ModelConfigService) getIBaseService("modelConfigService");
    }

    /**
     * 获取模型属性操作服务
     *
     * @return
     */
    public static ModelAttributeService getModelAttributeService(){
        return (ModelAttributeService) getIBaseService("modelAttributeService");
    }
}
