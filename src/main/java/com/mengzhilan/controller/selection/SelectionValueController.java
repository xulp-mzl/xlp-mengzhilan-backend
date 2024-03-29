package com.mengzhilan.controller.selection;

import com.mengzhilan.annotation.Controller;
import com.mengzhilan.annotation.RequestMapping;
import com.mengzhilan.annotation.ResponseCharset;
import com.mengzhilan.aop.ExceptionHandlerImpl;
import com.mengzhilan.enumeration.FormInputType;
import com.mengzhilan.enumeration.RequestMethodType;
import com.mengzhilan.exception.EnableExceptionHandler;
import com.mengzhilan.exception.ExceptionHandler;
import com.mengzhilan.response.ResponseResult;
import com.mengzhilan.view.FormValueFromUtils;
import org.xlp.utils.XLPCharsetUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Create by xlp on 2022/7/23
 * 下拉框数据获取Controller
 */
@EnableExceptionHandler
@ExceptionHandler(ExceptionHandlerImpl.class)
@Controller
@RequestMapping("/select/options")
@ResponseCharset(XLPCharsetUtil.UTF8)
public class SelectionValueController {
    /**
     * 获取表单条目输入框类型
     *
     * @return
     */
    @RequestMapping(method = RequestMethodType.GET, value = "/formInputType")
    public ResponseResult getFormInputTypeSelection(){
        List<Map<String, String>> result = new ArrayList<>();
        //获取表单条目输入框类型的所有枚举类型
        FormInputType[] formInputTypes = FormInputType.values();
        Map<String, String> map;
        for (FormInputType formInputType : formInputTypes) {
            map = new HashMap<>();
            map.put("label", formInputType.getDescription());
            map.put("value", formInputType.name());
            result.add(map);
        }
        return ResponseResult.success(result);
    }

    /**
     * 获取表单选项值来源
     *
     * @return
     */
    @RequestMapping(method = RequestMethodType.GET, value = "/formValueFrom")
    public ResponseResult getFormValueFromSelection(){
        return ResponseResult.success(FormValueFromUtils.getFormValueFromConfig());
    }
}
