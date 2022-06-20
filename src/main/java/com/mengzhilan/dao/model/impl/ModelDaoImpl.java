package com.mengzhilan.dao.model.impl;

import com.mengzhilan.base.service.ApplicationBaseServiceAbstract;
import com.mengzhilan.dao.model.ModelDao;
import com.mengzhilan.entity.model.ModelInfo;
import org.xlp.db.sql.QuerySQL;
import org.xlp.db.sql.UpdateSQL;

import java.util.Date;
import java.util.List;

/**
 * Create by xlp on 2022/6/19
 */
public class ModelDaoImpl extends ApplicationBaseServiceAbstract implements ModelDao {
    /**
     * 隐藏不需要操作的模型
     *
     * @param modelIds
     */
    @Override
    public void hideModelByModelIds(String[] modelIds) {
        UpdateSQL<ModelInfo> updateSQL = new UpdateSQL<>(ModelInfo.class);
        updateSQL.andIn("modelId", (Object[]) modelIds);
        updateSQL.clearUpdate().set("disabled", true).set("updateTime", new Date());
        this.update(updateSQL);
    }

    /**
     * 根据模型id查询相应模型
     *
     * @param modelIds
     */
    @Override
    public List<ModelInfo> findModelByModelIds(String[] modelIds) {
        QuerySQL<ModelInfo> querySQL = new QuerySQL<>(ModelInfo.class);
        querySQL.andIn("modelId", (Object[]) modelIds);
        return this.list(querySQL);
    }
}
