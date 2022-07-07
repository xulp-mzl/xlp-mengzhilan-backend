package com.mengzhilan.dao.model;

import com.mengzhilan.entity.model.ModelFormAndTableBaseConfigInfo;
import org.xlp.mv.IBaseDao;

/**
 * Create by xlp on 2022/7/3
 * 模型配置
 */
public interface ModelConfigDao extends IBaseDao {
    /**
     * 通过模型id获取模型基本配置信息
     *
     * @param modelId
     * @return
     */
    ModelFormAndTableBaseConfigInfo findByModelId(String modelId);
}
