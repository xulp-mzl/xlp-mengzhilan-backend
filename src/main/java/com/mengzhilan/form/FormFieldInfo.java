package com.mengzhilan.form;

import com.mengzhilan.enumeration.FormFieldType;
import com.mengzhilan.enumeration.attribute.AttributeType;
import org.xlp.javabean.annotation.Bean;
import org.xlp.javabean.annotation.FieldName;

import java.io.Serializable;

/**
 * Create by xlp on 2022/6/10
 *
 * 表单字段信息
 */
@Bean
public class FormFieldInfo implements Serializable {
    private static final long serialVersionUID = 6580242851536243478L;

    /**
     * 字段对应的表单类型
     */
    @FieldName
    private FormFieldType formFieldType = FormFieldType.INPUT;

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

    /**
     * 属性类型
     */
    @FieldName
    private AttributeType attributeType;

    /**
     * 是否可删除该字段
     */
    @FieldName
    private Boolean canDelete = false;

    /**
     * 对应数据表字段名称
     */
    @FieldName
    private String columnName;

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

    public AttributeType getAttributeType() {
        return attributeType;
    }

    public void setAttributeType(AttributeType attributeType) {
        this.attributeType = attributeType;
    }

    public Boolean getCanDelete() {
        return canDelete;
    }

    public void setCanDelete(Boolean canDelete) {
        this.canDelete = canDelete;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    @Override
    public String toString() {
        return "FormFieldInfo{" +
                "formFieldType=" + formFieldType +
                ", formFieldId='" + formFieldId + '\'' +
                ", formFieldName='" + formFieldName + '\'' +
                ", orderNo=" + orderNo +
                ", attributeType=" + attributeType +
                ", canDelete=" + canDelete +
                ", columnName='" + columnName + '\'' +
                '}';
    }
}
