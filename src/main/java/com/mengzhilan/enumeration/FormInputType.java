package com.mengzhilan.enumeration;

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
     * 文本域
     */
    TEXTAREA("文本域"),
    /**
     * 邮件地址输入框
     */
    EMAIL("邮件地址输入框"),
    /**
     * 数字输入框
     */
    NUMBER("数字输入框"),
    /**
     * Switch按钮
     */
    BOOLEAN("Switch按钮"),
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
    ;

    /**
     * 描述信息
     */
    private String description;

    FormInputType(String description){
        this.description = description;
    }

    public String getDescription(){
        return description;
    }
}
