package com.mengzhilan.form;

import com.mengzhilan.enumeration.FormFieldType;
import org.xlp.javabean.annotation.Bean;
import org.xlp.javabean.annotation.FieldName;

/**
 * Create by xlp on 2022/6/10
 *
 * 表单字段信息
 */
@Bean
public class FormFieldInfo {
    /**
     * 字段对应的表单类型
     */
    @FieldName
    private FormFieldType formFieldType;

    /**
     * form 字段标识
     */
    @FieldName(name = "attrId", mark = "属性标识")
    private String formFieldId;

    /**
     * form 字段名称
     */
    @FieldName(name = "attrName", mark = "属性名称")
    private String formFieldName;

    /**
     * 排序号
     */
    @FieldName
    private int orderNo = 0;

    public FormFieldInfo(){}

    public FormFieldInfo(FormFieldType formFieldType, String formFieldId, String formFieldName) {
        this.formFieldType = formFieldType;
        this.formFieldId = formFieldId;
        this.formFieldName = formFieldName;
    }

    public FormFieldType getFormFieldType() {
        return formFieldType;
    }

    public void setFormFieldType(FormFieldType formFieldType) {
        this.formFieldType = formFieldType;
    }

    public String getFormFieldId() {
        return formFieldId;
    }

    public void setFormFieldId(String formFieldId) {
        this.formFieldId = formFieldId;
    }

    public String getFormFieldName() {
        return formFieldName;
    }

    public void setFormFieldName(String formFieldName) {
        this.formFieldName = formFieldName;
    }

    public int getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(int orderNo) {
        this.orderNo = orderNo;
    }

    @Override
    public String toString() {
        return "FormFieldInfo{" +
                "formFieldType=" + formFieldType +
                ", formFieldId='" + formFieldId + '\'' +
                ", formFieldName='" + formFieldName + '\'' +
                ", orderNo=" + orderNo +
                '}';
    }
}
