package com.mengzhilan.service.model.impl;

import com.mengzhilan.base.service.ApplicationBaseServiceAbstract;
import com.mengzhilan.dao.model.ModelAttributeDao;
import com.mengzhilan.entity.model.form.ModelFormDetailConfig;
import com.mengzhilan.enumeration.attribute.AttributeType;
import com.mengzhilan.exception.BusinessException;
import com.mengzhilan.form.FormConfig;
import com.mengzhilan.form.FormFieldInfo;
import com.mengzhilan.form.FormInfoBean;
import com.mengzhilan.helper.DaoHelper;
import com.mengzhilan.service.model.ModelAttributeService;
import com.mengzhilan.util.ModelAttributeReaderUtils;

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
        FormInfoBean formInfoBean = validate(modelId);
        List<FormFieldInfo> formFieldInfos = formInfoBean.getFormFieldInfos();
        //排序
        formFieldInfos.sort((o1, o2) -> {
            int orderNo1 = o1.getOrderNo();
            int orderNo2 = o2.getOrderNo();
            return Integer.compare(orderNo2, orderNo1);
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

    /**
     * 保存模型表单配置信息
     *
     * @param modelFormDetailConfig
     * @throws BusinessException
     */
    @Override
    public void saveModelFormDetailConfig(ModelFormDetailConfig modelFormDetailConfig)
            throws BusinessException {
        FormInfoBean formInfoBean = validate(modelFormDetailConfig.getModelId());
        //获取模型表单配置详细信息
        ModelFormDetailConfig config = ModelAttributeReaderUtils
                .getModelFormDetailConfig(modelFormDetailConfig.getModelId(), modelFormDetailConfig.getFieldId());
        if (config != null){
            config.setAttributeType(config.getAttributeType());
            config.setCanDelete(config.getCanDelete());
            update(config);
            //删除缓存中的旧数据
            ModelAttributeReaderUtils.deleteFromCache(modelFormDetailConfig.getModelId(),
                    modelFormDetailConfig.getFieldId());
        } else {
            modelFormDetailConfig.setId(null);
            modelFormDetailConfig.setAttributeType(AttributeType.EXTEND_ATTR);
            modelFormDetailConfig.setCanDelete(true);
            save(modelFormDetailConfig);
            //向缓存中添加新添的数据
            FormFieldInfo formFieldInfo = new FormFieldInfo();
            formFieldInfo.setOrderNo(modelFormDetailConfig.getOrderNo());
            formFieldInfo.setFormFieldName(modelFormDetailConfig.getFieldName());
            formFieldInfo.setFormFieldId(modelFormDetailConfig.getFieldId());
            formFieldInfo.setCanDelete(true);
            formFieldInfo.setAttributeType(AttributeType.EXTEND_ATTR);
            formInfoBean.addFormFieldInfo(formFieldInfo);
        }
    }

    /**
     * 验证指定的模型id对应的模型是否存在,存在返回模型信息，否则抛出异常
     *
     * @param modelId 指定的模型id
     * @throws BusinessException 不存在，则抛出该异常
     * @return
     */
    @Override
    public FormInfoBean validate(String modelId) throws BusinessException {
        FormInfoBean formInfoBean = FormConfig.findFormInfoBean(modelId);
        if (formInfoBean == null){
            throw new BusinessException("根据模型id（modelId）查询模型属性失败!");
        }
        return formInfoBean;
    }
}
