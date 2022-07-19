package com.mengzhilan.service.model;

import com.mengzhilan.entity.model.form.ModelFormDetailConfig;
import com.mengzhilan.exception.BusinessException;
import com.mengzhilan.form.FormFieldInfo;
import org.xlp.mv.IBaseService;

import java.util.List;

/**
 * Create by xlp on 2022/7/11
 *
 * 模型属性模型
 */
public interface ModelAttributeService extends IBaseService {
    /**
     * 根据模型id获取模型属性集合
     *
     * @param modelId
     * @return
     * @throws BusinessException
     */
    List<FormFieldInfo> geFormFieldInfosByModelId(String modelId) throws BusinessException;

    /**
     * 根据模型id获取模型表单属性集合
     *
     * @param modelId
     * @return
     */
    List<ModelFormDetailConfig> getModelFormDetailConfigByModelId(String modelId);

    /**
     * 根据模型id和属性id获取模型表单详细配置信息
     *
     * @param modelId
     * @param attrId
     * @return
     */
    ModelFormDetailConfig getModelFormDetailConfig(String modelId, String attrId);
}
