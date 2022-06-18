package com.mengzhilan.form;

import org.xlp.javabean.annotation.Bean;
import org.xlp.javabean.annotation.FieldName;

import java.util.LinkedList;
import java.util.List;

/**
 * Create by xlp on 2022/6/10
 *
 * 表单配置bean信息
 */
@Bean
public class FormInfoBean {
    /**
     * bean 类
     */
    private Class<?> sourceBeanClass;

    /**
     * bean名称
     */
    @FieldName
    private String beanName;

    /**
     * bean id
     */
    @FieldName
    private String beanId;

    /**
     * 排序号
     */
    @FieldName
    private int orderNo;

    private boolean hidden;

    /**
     * form 字段信息
     */
    @FieldName
    private List<FormFieldInfo> formFieldInfos = new LinkedList<>();

    public Class<?> getSourceBeanClass() {
        return sourceBeanClass;
    }

    public void setSourceBeanClass(Class<?> sourceBeanClass) {
        this.sourceBeanClass = sourceBeanClass;
    }

    public String getBeanName() {
        return beanName;
    }

    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }

    public List<FormFieldInfo> getFormFieldInfos() {
        return formFieldInfos;
    }

    public void setFormFieldInfos(List<FormFieldInfo> formFieldInfos) {
        if (formFieldInfos != null) {
            this.formFieldInfos = formFieldInfos;
        }
    }

    /**
     * 添加form 字段信息
     *
     * @param formFieldInfo
     */
    public void addFormFieldInfo(FormFieldInfo formFieldInfo) {
        if (formFieldInfo != null) {
            this.formFieldInfos.add(formFieldInfo);
        }
    }

    public String getBeanId() {
        return beanId;
    }

    public void setBeanId(String beanId) {
        this.beanId = beanId;
    }

    public int getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(int orderNo) {
        this.orderNo = orderNo;
    }

    public boolean isHidden() {
        return hidden;
    }

    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }

    @Override
    public String toString() {
        return "FormInfoBean{" +
                "sourceBeanClass=" + sourceBeanClass +
                ", beanName='" + beanName + '\'' +
                ", beanId='" + beanId + '\'' +
                ", orderNo=" + orderNo +
                ", hidden=" + hidden +
                ", formFieldInfos=" + formFieldInfos +
                '}';
    }
}
