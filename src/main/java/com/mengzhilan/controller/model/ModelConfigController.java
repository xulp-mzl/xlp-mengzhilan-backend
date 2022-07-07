package com.mengzhilan.controller.model;

import com.mengzhilan.annotation.*;
import com.mengzhilan.constant.MessageConst;
import com.mengzhilan.entity.model.ModelFormAndTableBaseConfigInfo;
import com.mengzhilan.enumeration.RequestMethodType;
import com.mengzhilan.helper.CommonServiceHelper;
import com.mengzhilan.response.ResponseResult;
import com.mengzhilan.response.StatusCode;
import com.mengzhilan.service.model.ModelConfigService;
import com.mengzhilan.util.ModelBaseConfigReaderUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xlp.json.JsonObject;
import org.xlp.utils.XLPStringUtil;

/**
 * Create by xlp on 2022/6/13
 */
@Controller
@RequestMapping("/model/configs")
public class ModelConfigController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ModelConfigController.class);

    private ModelConfigService service = CommonServiceHelper.getModelConfigService();

    /**
     * 配置模型基本信息
     * @return
     */
    @ResponseCharset("utf-8")
    @RequestMapping(method = RequestMethodType.POST)
    public ResponseResult saveBaseConfig(@RequestBody String body){
        if (XLPStringUtil.isEmpty(body)){
            return ResponseResult.error(StatusCode.NOT_REQUEST_BODY, "请求修改的模型基本数据缺失！");
        }
        try {
            ModelFormAndTableBaseConfigInfo configInfo = JsonObject.fromJsonString(body)
                    .toBean(ModelFormAndTableBaseConfigInfo.class);
            service.saveConfig(configInfo);
            return ResponseResult.success();
        } catch (Exception e) {
            LOGGER.error("模型基本信息配置失败：", e);
            return ResponseResult.error(MessageConst.SERVER_ERROR_MSG);
        }
    }

    /**
     * 获取模型配置基本信息
     * @return
     */
    @ResponseCharset("utf-8")
    @RequestMapping(method = RequestMethodType.GET)
    public ResponseResult getBaseConfig(@RequestParam("modelId") String modelId){
        try {
            ModelFormAndTableBaseConfigInfo configInfo = ModelBaseConfigReaderUtils.getBaseConfigInfoFromCache(modelId);
            return ResponseResult.success(configInfo);
        } catch (Exception e) {
            LOGGER.error("获取模型基本信息配置失败：", e);
            return ResponseResult.error(MessageConst.SERVER_ERROR_MSG);
        }
    }
}
