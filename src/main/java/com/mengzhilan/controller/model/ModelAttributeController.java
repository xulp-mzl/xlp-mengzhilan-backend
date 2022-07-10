package com.mengzhilan.controller.model;

import com.mengzhilan.annotation.Controller;
import com.mengzhilan.annotation.RequestMapping;
import com.mengzhilan.annotation.RequestParam;
import com.mengzhilan.annotation.ResponseCharset;
import com.mengzhilan.enumeration.RequestMethodType;
import com.mengzhilan.form.FormConfig;
import com.mengzhilan.form.FormFieldInfo;
import com.mengzhilan.form.FormInfoBean;
import com.mengzhilan.response.ResponseResult;
import com.mengzhilan.response.StatusCode;
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
        FormInfoBean formInfoBean = FormConfig.findFormInfoBean(modelId);
        if (formInfoBean == null){
            return ResponseResult.error("根据模型id（modelId）查询模型属性失败");
        }
        List<FormFieldInfo> formFieldInfos = formInfoBean.getFormFieldInfos();
        //排序
        formFieldInfos.sort((o1, o2) -> {
            int orderNo1 = o1.getOrderNo();
            int orderNo2 = o2.getOrderNo();
            return Integer.compare(orderNo1, orderNo2);
        });
        return ResponseResult.success(formFieldInfos);
    }
}
