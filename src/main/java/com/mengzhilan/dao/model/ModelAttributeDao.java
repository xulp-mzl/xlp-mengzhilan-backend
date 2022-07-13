package com.mengzhilan.dao.model;

import com.mengzhilan.entity.model.form.ModelFormDetailConfig;
import org.xlp.mv.IBaseDao;

import java.util.List;

/**
 * Create by xlp on 2022/7/13
 *
 * 模型属性
 */
public interface ModelAttributeDao extends IBaseDao {
    /**
     * 根据模型id获取模型表单属性集合
     *
     * @param modelId
     * @return
     */
    List<ModelFormDetailConfig> getByModelId(String modelId);
}
