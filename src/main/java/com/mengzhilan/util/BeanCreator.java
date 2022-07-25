package com.mengzhilan.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xlp.assertion.AssertUtils;
import org.xlp.db.factory.XLPFactory;
import org.xlp.utils.XLPStringUtil;

import java.util.Properties;

/**
 * Create by xlp on 2022/5/2
 *
 * bean创建工具类
 * 依赖于beanMapper.properties配置文件类映射
 */
public class BeanCreator {
    private static final Logger LOGGER = LoggerFactory.getLogger(BeanCreator.class);

    private static  Properties beanMapper = new Properties();

    /**
     * 配置文件的名称
     */
    private static final String BEAN_MAPPER_CONFIG = "beanMapper.properties";

    static {
        reload();
    }

    /**
     * 重新加载配置文件
     */
    public static void reload(){
        beanMapper = PropertiesFileReaderUtils.load(BEAN_MAPPER_CONFIG);
    }

    /**
     * 根据beanId加载bean对象
     *
     * @param beanId （配置文件中对应的key）
     * @return 返回指定bean对象
     */
    public static <T> T getBean(String beanId){
        String className = beanMapper.getProperty(beanId);
        if (XLPStringUtil.isEmpty(className)){
            if (LOGGER.isErrorEnabled())
                LOGGER.error("从bean映射配置文件中加载id为【 " + beanId + "】bean对象失败，即未找到对应的bean对象。");
        } else {
            try {
                Class<?> beanClass = Class.forName(className);
                return XLPFactory.create(beanClass);
            } catch (ClassNotFoundException e) {
                if (LOGGER.isErrorEnabled())
                    LOGGER.error("从bean映射配置文件中加载id为【 " + beanId + "】bean对象失败！", e);
            }
        }
        return null;
    }

    /**
     * 根据beanClass加载bean对象
     *
     * @param beanClass bean类型
     * @return 返回指定bean对象
     */
    public static <T> T getBean(Class<T> beanClass){
        AssertUtils.isNotNull(beanClass, "参数不能为null！");
        return XLPFactory.create(beanClass);
    }

    /**
     * 根据beanId加载bean对象(非代理对象)
     *
     * @param beanId （配置文件中对应的key）
     * @return 返回指定bean对象
     */
    public static <T> T getSourceBean(String beanId){
        String className = beanMapper.getProperty(beanId);
        if (XLPStringUtil.isEmpty(className)){
            if (LOGGER.isErrorEnabled())
                LOGGER.error("从bean映射配置文件中加载id为【 " + beanId + "】bean对象失败，即未找到对应的bean对象。");
        } else {
            try {
                Class<?> beanClass = Class.forName(className);
                return (T) beanClass.newInstance();
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
                if (LOGGER.isErrorEnabled())
                    LOGGER.error("从bean映射配置文件中加载id为【 " + beanId + "】bean对象失败！", e);
            }
        }
        return null;
    }
}
