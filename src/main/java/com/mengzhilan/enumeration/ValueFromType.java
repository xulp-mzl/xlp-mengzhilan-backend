package com.mengzhilan.enumeration;

/**
 * Create by xlp on 2022/7/9
 *
 * 表单条目值来源了类型
 */
public enum ValueFromType {
    /**
     * 来自外部链接
     */
    URL,
    /**
     * 来自指定接口实现类
     */
    CLASS,
    /**
     * 来自用户自定义输入，暂时只支持json串
     */
    CUSTOM
}
