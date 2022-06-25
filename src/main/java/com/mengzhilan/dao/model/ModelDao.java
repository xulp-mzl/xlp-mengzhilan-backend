package com.mengzhilan.dao.model;

import com.mengzhilan.entity.model.ModelInfo;
import com.mengzhilan.form.FormInfoBean;
import org.xlp.mv.IBaseDao;

import java.util.List;

/**
 * Create by xlp on 2022/6/19
 */
public interface ModelDao extends IBaseDao {
    /**
     * 隐藏不需要操作的模型
     */
    void hideModelByModelIds(String[] modelIds);

    /**
     * 根据模型id查询相应模型
     */
    List<ModelInfo> findModelByModelIds(String[] modelIds);

    /**
     * 修改模型信息
     *
     * @param formInfoBean
     */
    void updateModelByFormInfoBean(FormInfoBean formInfoBean);
}
