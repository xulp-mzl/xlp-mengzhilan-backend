package com.mengzhilan.form;

import com.mengzhilan.base.ExtendedAttr;
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

    /**
     * 是否不显示
     */
    @FieldName
    private boolean hidden;

    /**
     * 表示该form对应的模型是否可扩展字段
     */
    @FieldName
    private boolean canExtend;

    /**
     * 模型对应的表名称
     */
    @FieldName
    private String tableName;

    /**
     * form 字段信息
     */
    private List<FormFieldInfo> formFieldInfos = new LinkedList<>();

    public Class<?> getSourceBeanClass() {
        return sourceBeanClass;
    }

    public void setSourceBeanClass(Class<?> sourceBeanClass) {
        this.sourceBeanClass = sourceBeanClass;
        if (sourceBeanClass != null) {
            this.canExtend = ExtendedAttr.class.isAssignableFrom(sourceBeanClass);
        }
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

    public boolean isCanExtend() {
        return canExtend;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    @Override
    public String toString() {
        return "FormInfoBean{" +
                "sourceBeanClass=" + sourceBeanClass +
                ", beanName='" + beanName + '\'' +
                ", beanId='" + beanId + '\'' +
                ", orderNo=" + orderNo +
                ", hidden=" + hidden +
                ", canExtend=" + canExtend +
                ", tableName='" + tableName + '\'' +
                ", formFieldInfos=" + formFieldInfos +
                '}';
    }
}
