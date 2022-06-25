package com.mengzhilan.controller.model;

import com.mengzhilan.annotation.*;
import com.mengzhilan.constant.MessageConst;
import com.mengzhilan.enumeration.RequestMethodType;
import com.mengzhilan.form.FormConfig;
import com.mengzhilan.form.FormInfoBean;
import com.mengzhilan.helper.CommonServiceHelper;
import com.mengzhilan.response.ResponseResult;
import com.mengzhilan.response.StatusCode;
import com.mengzhilan.service.model.ModelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xlp.json.JsonObject;
import org.xlp.utils.XLPStringUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Create by xlp on 2022/6/13
 */
@Controller
@RequestMapping("/models/")
public class ModelController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ModelController.class);

    private ModelService modelService = CommonServiceHelper.getModelService();

    /**
     * 获取所有的模型信息
     * @return
     */
    @ResponseCharset("utf-8")
    @RequestMapping(method = RequestMethodType.GET)
    public ResponseResult getAllModels(){
        try {
            List<FormInfoBean> formInfoBeans = FormConfig.getFormInfoBeans();
            List<Map<String, Object>> result = new ArrayList<>();
            Map<String, Object> item;
            for (FormInfoBean formInfoBean : formInfoBeans) {
                item = new HashMap<>();
                item.put("beanId", formInfoBean.getBeanId());
                item.put("beanName", formInfoBean.getBeanName());
                item.put("orderNo", formInfoBean.getOrderNo());
                if (formInfoBean.isHidden()) continue;
                result.add(item);
            }
            return ResponseResult.success(result);
        } catch (Exception e) {
            LOGGER.error("获取模型信息失败：", e);
            return ResponseResult.error(MessageConst.SERVER_ERROR_MSG);
        }
    }

    /**
     * 隐藏不需要操作的模型
     * @return
     */
    @ResponseCharset("utf-8")
    @RequestMapping(method = RequestMethodType.PUT)
    public ResponseResult hideModels(@RequestParam String modelIds){
        try {
           if (XLPStringUtil.isEmpty(modelIds)){
               return ResponseResult.error(StatusCode.REQUEST_PARAMETER_LOSE, "请选择要操作的数据！");
           }
           String[] modelIdArr = modelIds.split(",");
           modelService.hideModelByModelIds(modelIdArr);
           return ResponseResult.success();
        } catch (Exception e) {
            LOGGER.error("设置模型信息失败：", e);
            return ResponseResult.error(MessageConst.SERVER_ERROR_MSG);
        }
    }

    /**
     * 隐藏不需要操作的模型
     * @return
     */
    @ResponseCharset("utf-8")
    @RequestMapping(method = RequestMethodType.POST)
    public ResponseResult updateModel(@RequestBody String body){
        System.out.println(body);
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
