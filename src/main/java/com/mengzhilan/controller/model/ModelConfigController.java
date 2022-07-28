package com.mengzhilan.controller.model;

import com.mengzhilan.annotation.*;
import com.mengzhilan.aop.ExceptionHandlerImpl;
import com.mengzhilan.entity.model.ModelFormAndTableBaseConfigInfo;
import com.mengzhilan.enumeration.RequestMethodType;
import com.mengzhilan.exception.EnableExceptionHandler;
import com.mengzhilan.exception.ExceptionHandler;
import com.mengzhilan.helper.CommonServiceHelper;
import com.mengzhilan.response.ResponseResult;
import com.mengzhilan.response.StatusCode;
import com.mengzhilan.service.model.ModelConfigService;
import com.mengzhilan.util.ModelBaseConfigReaderUtils;
import org.xlp.json.JsonObject;
import org.xlp.utils.XLPCharsetUtil;
import org.xlp.utils.XLPStringUtil;

/**
 * Create by xlp on 2022/6/13
 */
@EnableExceptionHandler
@ExceptionHandler(ExceptionHandlerImpl.class)
@Controller
@RequestMapping("/model/configs")
@ResponseCharset(XLPCharsetUtil.UTF8)
public class ModelConfigController {
    private ModelConfigService service = CommonServiceHelper.getModelConfigService();

    /**
     * 配置模型基本信息
     * @return
     */
    @RequestMapping(method = RequestMethodType.POST)
    public ResponseResult saveBaseConfig(@RequestBody String body){
        if (XLPStringUtil.isEmpty(body)){
            return ResponseResult.error(StatusCode.NOT_REQUEST_BODY, "请求修改的模型基本数据缺失！");
        }
        ModelFormAndTableBaseConfigInfo configInfo = JsonObject.fromJsonString(body)
                .toBean(ModelFormAndTableBaseConfigInfo.class);
        service.saveConfig(configInfo);
        return ResponseResult.success();
    }

    /**
     * 获取模型配置基本信息
     * @return
     */
    @RequestMapping(method = RequestMethodType.GET)
    public ResponseResult getBaseConfig(@RequestParam("modelId") String modelId){
        ModelFormAndTableBaseConfigInfo configInfo = ModelBaseConfigReaderUtils.getBaseConfigInfoFromCache(modelId);
        return ResponseResult.success(configInfo);
    }
}
