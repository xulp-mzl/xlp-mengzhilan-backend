package com.mengzhilan.service.model.impl;

import com.mengzhilan.base.service.ApplicationBaseServiceAbstract;
import com.mengzhilan.dao.model.ModelConfigDao;
import com.mengzhilan.entity.model.ModelFormAndTableBaseConfigInfo;
import com.mengzhilan.helper.DaoHelper;
import com.mengzhilan.service.model.ModelConfigService;
import com.mengzhilan.util.ModelBaseConfigReaderUtils;

/**
 * Create by xlp on 2022/7/3
 */
public class ModelConfigServiceImpl extends ApplicationBaseServiceAbstract
        implements ModelConfigService {
    private ModelConfigDao configDao = DaoHelper.getModelConfigDao();
    /**
     * 通过模型id获取模型基本配置信息
     *
     * @param modelId
     * @return
     */
    @Override
    public ModelFormAndTableBaseConfigInfo findByModelId(String modelId) {
        return configDao.findByModelId(modelId);
    }

    /**
     * 保存配置基本信息
     *
     * @param configInfo
     */
    @Override
    public void saveConfig(ModelFormAndTableBaseConfigInfo configInfo) {
        ModelFormAndTableBaseConfigInfo tempBaseConfigInfo = ModelBaseConfigReaderUtils.getBaseConfigInfoFromCache(
                configInfo.getModelId());
        //判断缓存中是否存在该对象，不存在，先保存进数据库，在更新缓存
        //否则先更新数据库，在更新缓存
        if (tempBaseConfigInfo == null){
            save(configInfo);
        } else {
            configInfo.setId(tempBaseConfigInfo.getId());
            update(configInfo);
            ModelBaseConfigReaderUtils.removeBaseConfigInfoFromCache(configInfo.getModelId());
        }
    }
}
