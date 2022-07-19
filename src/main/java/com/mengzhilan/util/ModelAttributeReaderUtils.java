package com.mengzhilan.util;

import com.mengzhilan.entity.model.form.ModelFormDetailConfig;
import com.mengzhilan.helper.CommonServiceHelper;
import com.mengzhilan.service.model.ModelAttributeService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Create by xlp on 2022/7/19
 *
 * 模型属性读取工具类
 */
public class ModelAttributeReaderUtils {
    private static final ModelAttributeService SERVICE = CommonServiceHelper.getModelAttributeService();
    /**
     * 缓存模型属性Map key：modelId + ":" + attrId
     */
    private static final Map<String, ModelFormDetailConfig> MODEL_ATTRIBUTE_MAP = new HashMap<>();

    static {
        //把所有模型属性加载到缓存中
        reload();
    }

    /**
     * 根据模型id和属性id获取缓存key
     *
     * @param modelId
     * @param attrId
     * @return
     */
    private static String getMapKey(String modelId, String attrId){
        return modelId + ':' + attrId;
    }

    /**
     * 根据模型id和属性id获取模型表单详细配置信息
     *
     * @param modelId
     * @param attrId
     * @return 假如未找到，则返回null
     */
    public static ModelFormDetailConfig getModelFormDetailConfig(String modelId, String attrId){
        String key = getMapKey(modelId, attrId);
        ModelFormDetailConfig modelFormDetailConfig = MODEL_ATTRIBUTE_MAP.get(key);
        if (modelFormDetailConfig == null){
            //假如未从缓存获取到，则去数据库中获取
            modelFormDetailConfig = SERVICE.getModelFormDetailConfig(modelId,attrId);
            if (modelFormDetailConfig != null) {
                MODEL_ATTRIBUTE_MAP.put(key, modelFormDetailConfig);
            }
        }
        return modelFormDetailConfig;
    }

    /**
     * 根据模型id和属性id从缓存中删除模型表单详细配置信息
     *
     * @param modelId
     * @param attrId
     * @return
     */
    public static void deleteFromCache(String modelId, String attrId){
        String key = getMapKey(modelId, attrId);
        MODEL_ATTRIBUTE_MAP.remove(key);
    }

    /**
     * 加载所有模型表单详细配置信息
     */
    public static void reload(){
        MODEL_ATTRIBUTE_MAP.clear();
        List<ModelFormDetailConfig> modelFormDetailConfigs = SERVICE.list(ModelFormDetailConfig.class);
        modelFormDetailConfigs.forEach((item) -> MODEL_ATTRIBUTE_MAP.put(getMapKey(item.getModelId(), item.getFieldId()),
                item));
    }
}
