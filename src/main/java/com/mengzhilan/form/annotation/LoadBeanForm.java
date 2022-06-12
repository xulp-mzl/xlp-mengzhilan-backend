package com.mengzhilan.form.annotation;

import java.lang.annotation.*;

/**
 * Create by xlp on 2022/6/9
 *
 * 要加在form bean的注解类
 */
@Retention(RetentionPolicy.RUNTIME) // 注解会在class字节码文件中存在，在运行时可以通过反射获取到
@Target(ElementType.TYPE)//定义注解的作用目标**作用范围字段 （作用在类或接口上或函数上）
@Documented//说明该注解将被包含在javadoc中
public @interface LoadBeanForm {
    /**
     * 要加在表单bean的包名
     *
     * @return
     */
    String[] packageName() default {};

    /**
     * 要加在表单bean的类全路径名称
     *
     * @return
     */
    String[] className() default {};

    /**
     * 要排除的bean的类全路径名称
     * @return
     */
    String[] excludeClassName() default {};
}
