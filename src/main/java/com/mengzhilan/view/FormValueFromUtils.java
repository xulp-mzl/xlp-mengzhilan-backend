package com.mengzhilan.view;

import com.mengzhilan.util.PropertiesFileReaderUtils;

import java.util.Properties;

/**
 * Create by xlp on 2022/7/25
 *
 * 加载表单选项值来源工具类
 */
public class FormValueFromUtils {
    private static Properties formValueFromConfig = new Properties();

    /**
     * 配置文件的名称
     */
    private static final String FORM_VALUE_FROM_CONFIG_FILE = "formValueFrom.properties";

    static {
        reload();
    }

    /**
     * 加载配置文件中的类容
     */
    public static void reload() {
        formValueFromConfig = PropertiesFileReaderUtils.load(FORM_VALUE_FROM_CONFIG_FILE);
    }

    /**
     * 获取表单可选择值实现类配置信息
     * @return key: 实现类全路径，value；描述
     */
    public static Properties getFormValueFromConfig(){
        return formValueFromConfig;
    }
}
