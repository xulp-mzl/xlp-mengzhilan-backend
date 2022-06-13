package com.mengzhilan.base;

/**
 * Create by xlp on 2021/3/30
 *
 * 版本标识
 */
public interface Version {
    /**
     * 默认版本字段名称
     */
    String DEFAULT_VERSION_FIELD_NAME = "version";

    /**
     * 获取版本标识
     *
     * @return
     */
    int getVersion();

    /**
     * 设置版本标识
     *
     * @return
     */
    void setVersion(int version);

    default String getVersionFiledName(){
        return DEFAULT_VERSION_FIELD_NAME;
    }
}
