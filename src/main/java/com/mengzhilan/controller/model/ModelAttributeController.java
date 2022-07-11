package com.mengzhilan.controller.model;

import com.mengzhilan.annotation.Controller;
import com.mengzhilan.annotation.RequestMapping;
import com.mengzhilan.annotation.RequestParam;
import com.mengzhilan.annotation.ResponseCharset;
import com.mengzhilan.constant.MessageConst;
import com.mengzhilan.enumeration.RequestMethodType;
import com.mengzhilan.exception.BusinessException;
import com.mengzhilan.form.FormFieldInfo;
import com.mengzhilan.helper.CommonServiceHelper;
import com.mengzhilan.response.ResponseResult;
import com.mengzhilan.response.StatusCode;
import com.mengzhilan.service.model.ModelAttributeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xlp.utils.XLPStringUtil;

import java.util.List;

/**
 * Create by xlp on 2022/7/10
 */
@Controller
@RequestMapping("/model/attrs")
public class ModelAttributeController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ModelAttributeController.class);

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
    public ResponseResult getAllModelAttrs(@RequestParam("modelId") String modelId) {
        if (XLPStringUtil.isEmpty(modelId)) {
            return ResponseResult.error(StatusCode.REQUEST_PARAMETER_LOSE, "缺失参数模型id（modelId）");
        }
        List<FormFieldInfo> formFieldInfos = null;
        try {
            formFieldInfos = service.geFormFieldInfosByModelId(modelId);
            return ResponseResult.success(formFieldInfos);
        } catch (BusinessException e) {
            return ResponseResult.error(e.getMessage());
        } catch (Exception e) {
            LOGGER.error("获取模型信息失败：", e);
            return ResponseResult.error(MessageConst.SERVER_ERROR_MSG);
        }
    }
}
