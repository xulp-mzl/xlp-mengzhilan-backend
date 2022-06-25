package com.mengzhilan.service.model.impl;

import com.mengzhilan.base.service.ApplicationBaseServiceAbstract;
import com.mengzhilan.dao.model.ModelDao;
import com.mengzhilan.entity.model.ModelInfo;
import com.mengzhilan.form.FormConfig;
import com.mengzhilan.form.FormInfoBean;
import com.mengzhilan.helper.DaoHelper;
import com.mengzhilan.service.model.ModelService;
import org.xlp.db.tableoption.annotation.XLPTransaction;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Create by xlp on 2022/6/12
 */
public class ModelServiceImpl extends ApplicationBaseServiceAbstract implements ModelService {
    private ModelDao modelDao = DaoHelper.getModelDao();

    /**
     * 隐藏不需要操作的模型
     *
     * @param modelIds
     */
    @Override
    @XLPTransaction
    public void hideModelByModelIds(String[] modelIds) {
        List<ModelInfo> modelInfos = modelDao.findModelByModelIds(modelIds);
        modelDao.hideModelByModelIds(modelIds);
        // 处理不在数据库中的数据，直接插入一条新数据
        List<ModelInfo> notExists = new ArrayList<>();
        FormInfoBean formInfoBean;
        for (String modelId : modelIds) {
            formInfoBean = FormConfig.findFormInfoBean(modelId);
            if (formInfoBean != null) {
                boolean exists = false;
                for (ModelInfo modelInfo : modelInfos) {
                    if (modelInfo.getModelId().equals(modelId)) {
                        exists = true;
                        break;
                    }
                }
                if (!exists) {
                    ModelInfo modelInfo = new ModelInfo();
                    modelInfo.setDisabled(true);
                    modelInfo.setModelId(modelId);
                    modelInfo.setModelName(formInfoBean.getBeanName());
                    modelInfo.setCreateTime(new Date());
                    modelInfo.setUpdateTime(modelInfo.getCreateTime());
                    notExists.add(modelInfo);
                }
            }
        }
        //插入不在数据库表中的数据
        this.save(notExists);
        //处理缓存中的数据
        for (String modelId : modelIds) {
            formInfoBean = FormConfig.findFormInfoBean(modelId);
            if (formInfoBean != null) formInfoBean.setHidden(true);
        }
    }

    /**
     * 修改模型信息
     *
     * @param formInfoBean
     */
    @Override
    public void updateModelByFormInfoBean(FormInfoBean formInfoBean) {
        modelDao.updateModelByFormInfoBean(formInfoBean);
        FormInfoBean infoBean = FormConfig.findFormInfoBean(formInfoBean.getBeanId());
        if (infoBean != null){
            // 更新缓存中的数据
            infoBean.setBeanName(formInfoBean.getBeanName());
            infoBean.setOrderNo(formInfoBean.getOrderNo());
        }
    }
}
