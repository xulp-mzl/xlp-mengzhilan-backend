package com.mengzhilan.controller.model;

import com.mengzhilan.annotation.*;
import com.mengzhilan.aop.ExceptionHandlerImpl;
import com.mengzhilan.entity.model.form.ModelFormDetailConfig;
import com.mengzhilan.enumeration.RequestMethodType;
import com.mengzhilan.exception.BusinessException;
import com.mengzhilan.exception.EnableExceptionHandler;
import com.mengzhilan.exception.ExceptionHandler;
import com.mengzhilan.form.FormConfig;
import com.mengzhilan.form.FormFieldInfo;
import com.mengzhilan.form.FormInfoBean;
import com.mengzhilan.helper.CommonServiceHelper;
import com.mengzhilan.response.ResponseResult;
import com.mengzhilan.response.StatusCode;
import com.mengzhilan.service.model.ModelAttributeService;
import com.mengzhilan.util.ModelAttributeReaderUtils;
import org.xlp.utils.XLPCharsetUtil;
import org.xlp.utils.XLPStringUtil;

import java.util.List;

/**
 * Create by xlp on 2022/7/10
 */
@EnableExceptionHandler
@ExceptionHandler(ExceptionHandlerImpl.class)
@Controller
@RequestMapping("/model/attrs")
@ResponseCharset(XLPCharsetUtil.UTF8)
public class ModelAttributeController {
    /**
     * 模型属性操作服务
     */
    private ModelAttributeService service = CommonServiceHelper.getModelAttributeService();

    /**
     * 获取给定模型id的所有属性
     *
     * @param modelId
     * @return
     */
    @RequestMapping(method = RequestMethodType.GET)
    public ResponseResult getAllModelAttrs(@RequestParam("modelId") String modelId)
            throws BusinessException {
        if (XLPStringUtil.isEmpty(modelId)) {
            return ResponseResult.error(StatusCode.REQUEST_PARAMETER_LOSE, "缺失参数模型id（modelId）");
        }
        List<FormFieldInfo> formFieldInfos = service.geFormFieldInfosByModelId(modelId);
        return ResponseResult.success(formFieldInfos);
    }

    /**
     * 获取模型表单详细配置
     * @param modelId
     * @param attrId
     * @return
     */
    @RequestMapping(method = RequestMethodType.GET, value = "/{modelId}/{attrId}")
    public ResponseResult getModelAttribute(@PathVariable("modelId") String modelId,
                                            @PathVariable("attrId") String attrId)
            throws BusinessException {
        FormInfoBean formInfoBean = FormConfig.findFormInfoBean(modelId);
        if (formInfoBean == null){
            throw new BusinessException("根据模型id（modelId）查询对应的模型失败!");
        }
        //获取模型表单配置详细信息
        ModelFormDetailConfig modelFormDetailConfig = ModelAttributeReaderUtils
                .getModelFormDetailConfig(modelId, attrId);
        if (modelFormDetailConfig == null){
            modelFormDetailConfig = ModelFormDetailConfig.of();
        }
        return ResponseResult.success(modelFormDetailConfig);
    }

    @ResponseCharset("")
    public ResponseResult saveAttribute(){
        return ResponseResult.success();
    }
}
