package com.mengzhilan.controller.model;

import com.mengzhilan.annotation.Controller;
import com.mengzhilan.annotation.RequestMapping;
import com.mengzhilan.annotation.ResponseCharset;
import com.mengzhilan.constant.MessageConst;
import com.mengzhilan.enumeration.RequestMethodType;
import com.mengzhilan.form.FormConfig;
import com.mengzhilan.form.FormInfoBean;
import com.mengzhilan.response.ResponseResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
            Map<String, Object> item = new HashMap<>();
            for (FormInfoBean formInfoBean : formInfoBeans) {
                item.put("beanId", formInfoBean.getBeanId());
                item.put("beanName", formInfoBean.getBeanName());
                //item.put("")
            }
            return ResponseResult.success(result);
        } catch (Exception e) {
            LOGGER.error("获取模型信息失败：", e);
            return ResponseResult.error(MessageConst.SERVER_ERROR_MSG);
        }
    }
}
