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

    /**
     * 根据模型id和属性id获取模型表单详细配置信息
     *
     * @param modelId
     * @param attrId
     * @return
     */
    ModelFormDetailConfig getModelFormDetailConfig(String modelId, String attrId);

    /**
     * 删除模型属性
     * @param modelId
     * @param attrIds
     * @return
     */
    boolean deleteAttributes(String modelId, String[] attrIds);
}
