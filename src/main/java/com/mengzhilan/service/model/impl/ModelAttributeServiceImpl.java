package com.mengzhilan.service.model.impl;

import com.mengzhilan.base.service.ApplicationBaseServiceAbstract;
import com.mengzhilan.dao.model.ModelAttributeDao;
import com.mengzhilan.entity.model.form.ModelFormDetailConfig;
import com.mengzhilan.exception.BusinessException;
import com.mengzhilan.form.FormConfig;
import com.mengzhilan.form.FormFieldInfo;
import com.mengzhilan.form.FormInfoBean;
import com.mengzhilan.helper.DaoHelper;
import com.mengzhilan.service.model.ModelAttributeService;

import java.util.List;

/**
 * Create by xlp on 2022/7/11
 */
public class ModelAttributeServiceImpl extends ApplicationBaseServiceAbstract
        implements ModelAttributeService {
    private ModelAttributeDao modelAttributeDao = DaoHelper.getModelAttributeDao();

    /**
     * 根据模型id获取模型属性集合
     *
     * @param modelId
     * @return
     * @throws BusinessException 假如未查到给定模型id的模型，则抛出该异常
     */
    @Override
    public List<FormFieldInfo> geFormFieldInfosByModelId(String modelId) throws BusinessException {
        FormInfoBean formInfoBean = FormConfig.findFormInfoBean(modelId);
        if (formInfoBean == null){
           throw new BusinessException("根据模型id（modelId）查询模型属性失败!");
        }
        List<FormFieldInfo> formFieldInfos = formInfoBean.getFormFieldInfos();
        //排序
        formFieldInfos.sort((o1, o2) -> {
            int orderNo1 = o1.getOrderNo();
            int orderNo2 = o2.getOrderNo();
            return Integer.compare(orderNo1, orderNo2);
        });
        return formFieldInfos;
    }

    /**
     * 根据模型id获取模型表单属性集合
     *
     * @param modelId
     * @return
     */
    @Override
    public List<ModelFormDetailConfig> getModelFormDetailConfigByModelId(String modelId) {
        return modelAttributeDao.getByModelId(modelId);
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
        return modelAttributeDao.getModelFormDetailConfig(modelId, attrId);
    }
}
