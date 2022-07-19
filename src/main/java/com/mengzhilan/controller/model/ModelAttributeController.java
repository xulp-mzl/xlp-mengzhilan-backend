package com.mengzhilan.controller.model;

import com.mengzhilan.annotation.*;
import com.mengzhilan.aop.ExceptionHandlerImpl;
import com.mengzhilan.enumeration.RequestMethodType;
import com.mengzhilan.exception.BusinessException;
import com.mengzhilan.exception.EnableExceptionHandler;
import com.mengzhilan.exception.ExceptionHandler;
import com.mengzhilan.form.FormFieldInfo;
import com.mengzhilan.helper.CommonServiceHelper;
import com.mengzhilan.response.ResponseResult;
import com.mengzhilan.response.StatusCode;
import com.mengzhilan.service.model.ModelAttributeService;
import org.xlp.utils.XLPStringUtil;

import java.util.List;

/**
 * Create by xlp on 2022/7/10
 */
@EnableExceptionHandler
@ExceptionHandler(ExceptionHandlerImpl.class)
@Controller
@RequestMapping("/model/attrs")
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
    @ResponseCharset("utf-8")
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
    @ResponseCharset("utf-8")
    @RequestMapping(method = RequestMethodType.GET, value = "/{modelId}/{attrId}")
    public ResponseResult getModelAttribute(@PathVariable("modelId") String modelId,
                                            @PathVariable("attrId") String attrId){
        return ResponseResult.success();
    }
}
