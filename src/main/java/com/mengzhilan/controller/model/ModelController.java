package com.mengzhilan.controller.model;

import com.mengzhilan.annotation.*;
import com.mengzhilan.aop.ExceptionHandlerImpl;
import com.mengzhilan.enumeration.RequestMethodType;
import com.mengzhilan.exception.EnableExceptionHandler;
import com.mengzhilan.exception.ExceptionHandler;
import com.mengzhilan.form.FormConfig;
import com.mengzhilan.form.FormInfoBean;
import com.mengzhilan.helper.CommonServiceHelper;
import com.mengzhilan.response.ResponseResult;
import com.mengzhilan.response.StatusCode;
import com.mengzhilan.service.model.ModelService;
import org.xlp.json.JsonObject;
import org.xlp.utils.XLPStringUtil;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Create by xlp on 2022/6/13
 */
@EnableExceptionHandler
@ExceptionHandler(ExceptionHandlerImpl.class)
@Controller
@RequestMapping("/models/")
public class ModelController {
    private ModelService modelService = CommonServiceHelper.getModelService();

    /**
     * 获取所有的模型信息
     * @return
     */
    @ResponseCharset("utf-8")
    @RequestMapping(method = RequestMethodType.GET)
    public ResponseResult getAllModels(){
        List<FormInfoBean> formInfoBeans = FormConfig.getFormInfoBeans();
        List<FormInfoBean> result = formInfoBeans.stream()
                .filter((item) -> !item.isHidden()).collect(Collectors.toList());
        return ResponseResult.success(result);
    }

    /**
     * 隐藏不需要操作的模型
     * @return
     */
    @ResponseCharset("utf-8")
    @RequestMapping(method = RequestMethodType.PUT)
    public ResponseResult hideModels(@RequestParam String modelIds){
        if (XLPStringUtil.isEmpty(modelIds)){
            return ResponseResult.error(StatusCode.REQUEST_PARAMETER_LOSE, "请选择要操作的数据！");
        }
        String[] modelIdArr = modelIds.split(",");
        modelService.hideModelByModelIds(modelIdArr);
        return ResponseResult.success();
    }

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
        FormInfoBean formInfoBean = JsonObject.fromJsonString(body).toBean(FormInfoBean.class);
        if (XLPStringUtil.isEmpty(formInfoBean.getBeanName())){
            return ResponseResult.error(StatusCode.NAME_IS_NOT_EMPTY, "模型名称不能为空！");
        }
        modelService.updateModelByFormInfoBean(formInfoBean);
        return ResponseResult.success();
    }
}
