package com.mengzhilan.view.impl;

import com.mengzhilan.view.IValueFrom;

import java.util.ArrayList;
import java.util.List;

/**
 * Create by xlp on 2022/7/25
 *
 * 前台表单页面Boolean值实现类型
 */
public class BooleanValueImpl implements IValueFrom {
    /**
     * 获取可选值
     *
     * @return
     */
    @Override
    public List<OptionValue> getOptionValue() {
        List<OptionValue> optionValues = new ArrayList<>();
        OptionValue optionValue = new OptionValue(false, "否");
        optionValues.add(optionValue);
        optionValue = new OptionValue(true, "是");
        optionValues.add(optionValue);
        return optionValues;
    }
}
