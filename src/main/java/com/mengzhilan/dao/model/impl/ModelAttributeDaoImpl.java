package com.mengzhilan.dao.model.impl;

import com.mengzhilan.dao.model.ModelAttributeDao;
import com.mengzhilan.entity.model.form.ModelFormDetailConfig;
import org.xlp.db.sql.DeleteSQL;
import org.xlp.db.sql.QuerySQL;
import org.xlp.mv.BaseDao;

import java.util.List;

/**
 * Create by xlp on 2022/7/13
 */
public class ModelAttributeDaoImpl extends BaseDao implements ModelAttributeDao {
    /**
     * 根据模型id获取模型表单属性集合
     *
     * @param modelId
     * @return
     */
    @Override
    public List<ModelFormDetailConfig> getByModelId(String modelId) {
        QuerySQL<ModelFormDetailConfig> querySQL = new QuerySQL<>(ModelFormDetailConfig.class);
        querySQL.clearQuery().queryName("id").queryName("fieldName").queryName("canDelete")
                .queryName("orderNo").queryName("attributeType").queryName("fieldId")
                .andEq("modelId", modelId);
        return list(querySQL);
    }

    /**
     * 根据模型id和属性id获取模型表单详细配置信息
     *
     * @param modelId
     * @param attrId
     * @return
     */
    @Override
    public ModelFormDetailConfig getModelFormDetailConfig(String modelId, String attrId) {
        QuerySQL<ModelFormDetailConfig> querySQL = new QuerySQL<>(ModelFormDetailConfig.class);
        querySQL.andEq("modelId", modelId).andEq("fieldId", attrId);
        return find(querySQL);
    }

    /**
     * 删除模型属性
     *
     * @param modelId
     * @param attrIds
     * @return
     */
    @Override
    public boolean deleteAttributes(String modelId, String[] attrIds) {
        DeleteSQL<ModelFormDetailConfig> deleteSQL = new DeleteSQL<>(ModelFormDetailConfig.class);
        deleteSQL.andEq("modelId", modelId)
                .andEq("canDelete", true)
                .andIn("fieldId", (Object[]) attrIds);
        return update(deleteSQL);
    }
}
