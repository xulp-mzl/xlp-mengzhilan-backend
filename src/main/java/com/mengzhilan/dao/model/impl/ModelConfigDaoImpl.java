package com.mengzhilan.dao.model.impl;

import com.mengzhilan.dao.model.ModelConfigDao;
import com.mengzhilan.entity.model.ModelFormAndTableBaseConfigInfo;
import org.xlp.db.sql.QuerySQL;
import org.xlp.db.sql.limit.Limit;
import org.xlp.mv.BaseDao;

/**
 * Create by xlp on 2022/7/3
 */
public class ModelConfigDaoImpl extends BaseDao implements ModelConfigDao {
    /**
     * 通过模型id获取模型基本配置信息
     *
     * @param modelId
     * @return
     */
    @Override
    public ModelFormAndTableBaseConfigInfo findByModelId(String modelId) {
        QuerySQL<ModelFormAndTableBaseConfigInfo> querySQL = new QuerySQL<>(ModelFormAndTableBaseConfigInfo.class);
        querySQL.limit(new Limit(0, 1)).andEq("modelId", modelId);
        return find(querySQL);
    }
}
