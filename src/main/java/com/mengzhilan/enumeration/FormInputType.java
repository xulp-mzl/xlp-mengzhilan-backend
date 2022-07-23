package com.mengzhilan.enumeration;

import org.xlp.utils.XLPVerifedUtil;

/**
 * Create by xlp on 2022/6/26
 * 表单输入框类型
 */
public enum  FormInputType {
    /**
     * 密码输入框
     */
    PASSWORD("密码"),
    /**
     * 普通文本输入框
     */
    TEXT("普通文本输入框"),

    /**
     * 可清除普通文本输入框
     */
    CLEARABLE_TEXT("可清除普通文本输入框"),

    /**
     * 文本域
     */
    TEXTAREA("文本域"),
    /**
     * 邮件地址输入框
     */
    EMAIL("邮件地址输入框", XLPVerifedUtil.EMAIL_REGEX),

    /**
     * 数字输入框
     */
    NUMBER("数字输入框", XLPVerifedUtil.REAL_NUMBER_REGEX),
    /**
     * 整数输入框
     */
    INTEGER("整数输入框", XLPVerifedUtil.INT_REGEX),
    /**
     * 非负整数输入框
     */
    UNSIGNED_INTEGER("非负整数输入框", XLPVerifedUtil.UNSIGNED_INT_REGEX),

    /**
     * 是否选择器
     */
    BOOLEAN("是否选择器"),

    /**
     * Switch按钮
     */
    SWITCH("Switch按钮"),
    /**
     * 日期时间选择框
     */
    DATETIME("日期时间选择框"),
    /**
     * 日期选择框
     */
    DATE("日期选择框"),
    /**
     * 时间选择框
     */
    TIME("时间选择框"),

    /**
     * 单选按钮
     */
    RADIO("单选按钮"),

    /**
     * 复选框
     */
    CHECKBOX("复选框"),

    /**
     * 下拉选择框
     */
    SELECTION("下拉选择框"),

    /**
     * 可清除下拉选择框
     */
    CLEARABLE_SELECTION("可清除下拉选择框"),

    /**
     * 多选下拉选择框
     */
    MULTSELECTION("多选下拉选择框"),

    /**
     * 可搜索下拉选择框
     */
    SEARCHABLE_SELECTION("可搜索下拉选择框"),

    /**
     * 弹框选择
     */
    NEW_WINDOW_SELECTION("弹框选择"),
    ;

    /**
     * 描述信息
     */
    private String description;

    /**
     * 正则验证
     */
    private String regex;

    FormInputType(String description){
        this.description = description;
    }

    FormInputType(String description, String regex){
        this.description = description;
        this.regex = regex;
    }

    public String getDescription(){
        return description;
    }

    public String getRegex(){
        return regex;
    }
}
