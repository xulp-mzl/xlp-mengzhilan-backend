package com.mengzhilan.base;

import org.xlp.beancovert.BeanCovert;
import org.xlp.beancovert.BeanFieldConvertSettings;
import org.xlp.beancovert.exception.BeanConvertException;

/**
 * Create by xlp on 2023/3/11
 *
 * 把一个实现该接口对象转换成另一对象的接口
 */
public interface ICovert {
    /**
     * 把实现该接口对象转换成另一对象
     * @param targetClass 目标对象class
     * @param settings 字段转换配置类
     * @return 转换后的对象
     * @throws BeanConvertException
     */
    default <T> T covert(Class<T> targetClass, BeanFieldConvertSettings settings)
            throws BeanConvertException {
        BeanCovert covert = new BeanCovert(settings);
        return covert.covert(targetClass, this);
    }

    /**
     *把实现该接口对象转换成另一对象
     * @param targetClass 目标对象class
     * @return 转换后的对象
     * @throws BeanConvertException
     */
    default <T> T covert(Class<T> targetClass) throws BeanConvertException {
        return covert(targetClass, null);
    }
}
