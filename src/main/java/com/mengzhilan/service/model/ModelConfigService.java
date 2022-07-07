package com.mengzhilan.service.model;

import com.mengzhilan.entity.model.ModelFormAndTableBaseConfigInfo;
import org.xlp.mv.IBaseService;

/**
 * Create by xlp on 2022/7/3
 */
public interface ModelConfigService extends IBaseService {
    /**
     * 通过模型id获取模型基本配置信息
     *
     * @param modelId
     * @return
     */
    ModelFormAndTableBaseConfigInfo findByModelId(String modelId);

    /**
     * 保存配置基本信息
     *
     * @param configInfo
     */
    void saveConfig(ModelFormAndTableBaseConfigInfo configInfo);
}
