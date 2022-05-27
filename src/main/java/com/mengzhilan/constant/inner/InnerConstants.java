package com.mengzhilan.constant.inner;

/**
 * Create by xlp on 2020/12/3
 * 项目内部使用常量
 */
public class InnerConstants {
    /**
     * class path路径标识
     */
    public static final String CLASS_PATH_PRE = "classpath:";

    /**
     *web.xml 中context-param的key值
     */
    public static final String CONTEXT_PARAM_KEY_IS_REPORT = "isReport";

    /**
     * web.xml 中context-param的key值
     */
    public static final String CONTEXT_PARAM_KEY_DRUID_PROPERTIES = "druid.config.file";

    /**
     * druid数据库连接池配置文件默认名称
     */
    public static final String DEFALUT_DRUID_PROPERTIES_FILE_NAME = "druid.properties";

    /**
     *web.xml 中context-param的key值
     * 配置Controller注解类加载包名，多个用英文逗号隔开
     */
    public static final String CONTEXT_PARAM_KEY_SCANNER_CONTROLLE_RPACKAGE = "scannerControllerPackage";
}
