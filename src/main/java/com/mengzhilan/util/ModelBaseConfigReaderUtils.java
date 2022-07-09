package com.mengzhilan.util;

import com.mengzhilan.entity.model.ModelFormAndTableBaseConfigInfo;
import com.mengzhilan.helper.CommonServiceHelper;
import com.mengzhilan.service.model.ModelConfigService;
import org.xlp.utils.XLPStringUtil;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Create by xlp on 2022/7/5
 *
 * 读取模型基本信息配置工具类
 */
public class ModelBaseConfigReaderUtils {
    /**
     * 缓存基本配置信息
     */
    private static final Map<String, ModelFormAndTableBaseConfigInfo> CONFIG_INFO_MAP = new ConcurrentHashMap<>();

    /**
     * 基本信息配置服务
     */
    private static final ModelConfigService configService = CommonServiceHelper.getModelConfigService();

    /**
     * 根据模型id获取模型基本信息对象
     *
     * @param modelId
     * @return
     */
    public static ModelFormAndTableBaseConfigInfo getBaseConfigInfoFromCache(String modelId){
        if (XLPStringUtil.isEmpty(modelId)) return null;
        ModelFormAndTableBaseConfigInfo baseConfigInfo = CONFIG_INFO_MAP.get(modelId);
        // 假如缓存中未取得，则从数据库中获取
        if(baseConfigInfo == null){
            baseConfigInfo = configService.findByModelId(modelId);
            if (baseConfigInfo != null) {
                CONFIG_INFO_MAP.put(modelId, baseConfigInfo);
            }
        }
        return baseConfigInfo;
    }

    /**
     * 根据模型id，移除缓存中的指定配置信息
     *
     * @param modelId
     */
    public static void removeBaseConfigInfoFromCache(String modelId){
        if (modelId != null) {
            CONFIG_INFO_MAP.remove(modelId);
        }
    }

    /**
     * 清除缓存
     */
    public static void clearCache(){
        CONFIG_INFO_MAP.clear();
    }
}
