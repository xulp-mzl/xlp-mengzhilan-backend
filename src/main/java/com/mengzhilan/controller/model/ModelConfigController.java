package com.mengzhilan.controller.model;

import com.mengzhilan.annotation.Controller;
import com.mengzhilan.annotation.RequestBody;
import com.mengzhilan.annotation.RequestMapping;
import com.mengzhilan.annotation.ResponseCharset;
import com.mengzhilan.constant.MessageConst;
import com.mengzhilan.enumeration.RequestMethodType;
import com.mengzhilan.form.FormInfoBean;
import com.mengzhilan.helper.CommonServiceHelper;
import com.mengzhilan.response.ResponseResult;
import com.mengzhilan.response.StatusCode;
import com.mengzhilan.service.model.ModelService;
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

    private ModelService modelService = CommonServiceHelper.getModelService();

    /**
     * 隐藏不需要操作的模型
     * @return
     */
    @ResponseCharset("utf-8")
    @RequestMapping(method = RequestMethodType.POST)
    public ResponseResult updateModel(@RequestBody String body){
        if (XLPStringUtil.isEmpty(body)){
            return ResponseResult.error(StatusCode.NOT_REQUEST_BODY, "请求修改的模型数据缺失！");
        }
        try {
            FormInfoBean formInfoBean = JsonObject.fromJsonString(body).toBean(FormInfoBean.class);
            if (XLPStringUtil.isEmpty(formInfoBean.getBeanName())){
                return ResponseResult.error(StatusCode.NAME_IS_NOT_EMPTY, "模型名称不能为空！");
            }
            modelService.updateModelByFormInfoBean(formInfoBean);
            return ResponseResult.success();
        } catch (Exception e) {
            LOGGER.error("设置模型信息失败：", e);
            return ResponseResult.error(MessageConst.SERVER_ERROR_MSG);
        }
    }
}
