package com.mengzhilan.service.model.impl;

import com.mengzhilan.base.service.ApplicationBaseServiceAbstract;
import com.mengzhilan.dao.model.ModelAttributeDao;
import com.mengzhilan.entity.model.form.ModelFormDetailConfig;
import com.mengzhilan.enumeration.FormInputType;
import com.mengzhilan.enumeration.attribute.AttributeType;
import com.mengzhilan.exception.BusinessException;
import com.mengzhilan.form.FormConfig;
import com.mengzhilan.form.FormFieldInfo;
import com.mengzhilan.form.FormInfoBean;
import com.mengzhilan.helper.DaoHelper;
import com.mengzhilan.service.model.ModelAttributeService;
import com.mengzhilan.util.ModelAttributeReaderUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xlp.db.tableoption.annotation.XLPTransaction;

import java.util.ArrayList;
import java.util.List;

/**
 * Create by xlp on 2022/7/11
 */
public class ModelAttributeServiceImpl extends ApplicationBaseServiceAbstract
        implements ModelAttributeService {
    private final static Logger LOGGER = LoggerFactory.getLogger(ModelAttributeServiceImpl.class);

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
            modelFormDetailConfig.setId(config.getId());
            modelFormDetailConfig.setAttributeType(config.getAttributeType());
            modelFormDetailConfig.setCanDelete(config.getCanDelete());
            FormInputType formInputType = modelFormDetailConfig.getFormInputType();
            if (formInputType != null) modelFormDetailConfig.setRule(formInputType.getRegex());
            update(modelFormDetailConfig);
            //更新缓存中的数据
            FormFieldInfo formFieldInfo = FormConfig.findFormFieldInfo(formInfoBean,
                    modelFormDetailConfig.getFieldId());
            formFieldInfo.setFormFieldName(modelFormDetailConfig.getFieldName());
            formFieldInfo.setOrderNo(modelFormDetailConfig.getOrderNo());
            //删除缓存中的旧数据
            ModelAttributeReaderUtils.deleteFromCache(modelFormDetailConfig.getModelId(),
                    modelFormDetailConfig.getFieldId());
        } else {
            _save(modelFormDetailConfig, formInfoBean, false);
        }
    }

    private void _save(ModelFormDetailConfig modelFormDetailConfig, FormInfoBean formInfoBean,
                       boolean isAdd) throws BusinessException {
        FormFieldInfo formFieldInfo = FormConfig.findFormFieldInfo(formInfoBean,
                modelFormDetailConfig.getFieldId());
        if (formFieldInfo != null){
            if (isAdd){
                throw new BusinessException("【" + formInfoBean.getBeanId() + "】模型中已存在【" +
                        modelFormDetailConfig.getFieldId() + "】属性！");
            }
            modelFormDetailConfig.setAttributeType(formFieldInfo.getAttributeType());
            modelFormDetailConfig.setCanDelete(formFieldInfo.getCanDelete());
        } else {
            formFieldInfo = new FormFieldInfo();
            formInfoBean.addFormFieldInfo(formFieldInfo);
            modelFormDetailConfig.setAttributeType(AttributeType.EXTEND_ATTR);
            modelFormDetailConfig.setCanDelete(true);
        }
        FormInputType formInputType = modelFormDetailConfig.getFormInputType();
        if (formInputType != null) modelFormDetailConfig.setRule(formInputType.getRegex());
        modelFormDetailConfig.setId(null);
        save(modelFormDetailConfig);
        //更新缓存中的数据
        formFieldInfo.setOrderNo(modelFormDetailConfig.getOrderNo());
        formFieldInfo.setFormFieldName(modelFormDetailConfig.getFieldName());
        formFieldInfo.setFormFieldId(modelFormDetailConfig.getFieldId());
        formFieldInfo.setCanDelete(modelFormDetailConfig.getCanDelete());
        formFieldInfo.setAttributeType(modelFormDetailConfig.getAttributeType());
    }

    /**
     * 添加扩展属性
     *
     * @param modelFormDetailConfig
     * @throws BusinessException
     */
    @Override
    public void addModelFormDetailConfig(ModelFormDetailConfig modelFormDetailConfig) throws BusinessException {
        FormInfoBean formInfoBean = validate(modelFormDetailConfig.getModelId());
        _save(modelFormDetailConfig, formInfoBean, true);
    }

    /***
     * 批量设置模型表单配置信息
     *
     * @param modelId
     * @param attrIds
     * @throws BusinessException
     */
    @XLPTransaction
    @Override
    public void batchSetting(String modelId, String[] attrIds){
        FormInfoBean formInfoBean = FormConfig.findFormInfoBean(modelId);
        //存储未设置的属性id，设置了的不再重新设置
        List<String> notSettingAttrIds = new ArrayList<>();
        List<ModelFormDetailConfig> inDbConfigs = getModelFormDetailConfigByModelId(modelId);
        for (String attrId : attrIds) {
            boolean hasExist = false;
            for (ModelFormDetailConfig inDbConfig : inDbConfigs) {
                if (inDbConfig.getFieldId().equals(attrId)){
                    hasExist = true;
                    break;
                }
            }
            if (hasExist){
                if (LOGGER.isInfoEnabled() && formInfoBean != null) {
                    LOGGER.info("【" + formInfoBean.getBeanName() + "】该属性值【" + attrId + "】已设置过，不在重新设置！");
                }
                continue;
            }
            notSettingAttrIds.add(attrId);
        }

        List<ModelFormDetailConfig> configs = new ArrayList<>();
        notSettingAttrIds.forEach((item) -> {
            FormFieldInfo formFieldInfo = FormConfig.findFormFieldInfo(formInfoBean, item);
            if (formFieldInfo != null){
                //保存数据
                ModelFormDetailConfig config = ModelFormDetailConfig.of(formFieldInfo);
                config.setModelId(modelId);
                configs.add(config);
            } else if (LOGGER.isWarnEnabled() && formInfoBean != null) {
                LOGGER.warn("【" + formInfoBean.getBeanName() + "】中没有该属性值【" + item + "】");
            }
        });
        save(configs);
    }

    /**
     * 删除模型属性
     *
     * @param modelId
     * @param attrIds
     * @return
     */
    @Override
    public boolean deleteAttributes(String modelId, String attrIds) {
        String[] attrIdArr = attrIds.split(",");
        boolean success = modelAttributeDao.deleteAttributes(modelId, attrIdArr);
        if (success){
            // 移除缓存中的数据
            for (String attrId : attrIdArr) {
                FormConfig.removeFormFieldInfo(modelId, attrId);
            }
        }
        return success;
    }

    /**
     * 模型属性发布
     *
     * @param modelId
     * @param attrIds
     * @return
     */
    @Override
    public boolean publishAttributes(String modelId, String attrIds) throws BusinessException {
        FormInfoBean formInfoBean = validate(modelId);
        String[] attrIdArr = attrIds.split(",");
        boolean success = modelAttributeDao.publishAttributes(modelId, attrIdArr);
        if (success){
            FormFieldInfo formFieldInfo;
            // 更新缓存中的数据
            for (String attrId : attrIdArr) {
                formFieldInfo = FormConfig.findFormFieldInfo(formInfoBean, attrId);
                if (formFieldInfo != null) formFieldInfo.setCanDelete(false);
            }
        }
        return success;
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
            throw new BusinessException("根据模型id[" + modelId + "]查询模型信息失败!");
        }
        return formInfoBean;
    }
}
