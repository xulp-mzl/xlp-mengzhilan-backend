package com.mengzhilan.entity.model.form;

import com.mengzhilan.base.MZBaseEntity;
import com.mengzhilan.enumeration.FormInputType;
import com.mengzhilan.enumeration.ValueFromType;
import com.mengzhilan.enumeration.attribute.AttributeType;
import org.xlp.db.ddl.annotation.XLPIndex;
import org.xlp.db.tableoption.annotation.XLPColumn;
import org.xlp.db.tableoption.annotation.XLPEntity;
import org.xlp.db.tableoption.xlpenum.DataType;
import org.xlp.javabean.annotation.Bean;
import org.xlp.javabean.annotation.FieldName;

/**
 * Create by xlp on 2022/7/7
 */
@Bean
@XLPEntity(tableName = "mz_form_detail_config", descriptor = "模型表单详细配置信息")
public class ModelFormDetailConfig extends MZBaseEntity {
    private static final long serialVersionUID = 2647662010182684770L;

    /**
     * 模型id
     */
    @XLPIndex
    @FieldName
    @XLPColumn(dataType = DataType.VARCHAR, length = 64, descriptor = "模型id")
    private String modelId;

    /**
     * 表单字段id
     */
    @XLPIndex
    @FieldName(name = "attrId")
    @XLPColumn(dataType = DataType.VARCHAR, length = 64, descriptor = "表单字段id")
    private String fieldId;

    /**
     * 表单字段名称
     */
    @FieldName(name = "attrName")
    @XLPColumn(dataType = DataType.VARCHAR, length = 100, descriptor = "表单字段名称")
    private String fieldName;

    /**
     * 表单条目输入类型
     */
    @FieldName
    @XLPColumn(dataType = DataType.VARCHAR, length = 64, descriptor = "表单条目输入类型")
    private FormInputType formInputType;

    /**
     * 表单条目排序号
     */
    @FieldName
    @XLPColumn(dataType = DataType.INT, descriptor = "排序号")
    private Integer orderNo = 0;

    /**
     * 是否可编辑
     */
    @FieldName
    @XLPColumn(dataType = DataType.BOOLEAN, descriptor = "编辑时，是否可编辑该字段，true：可编辑，false：不可编辑")
    private Boolean edit = false;

    /**
     * 新建时，该字段是否可编辑
     */
    @FieldName
    @XLPColumn(dataType = DataType.BOOLEAN, descriptor = "新建时，该字段是否可编辑，true：可编辑，false：不可编辑")
    private Boolean adding = false;

    /**
     * 表单中是否显示该字段
     */
    @FieldName
    @XLPColumn(dataType = DataType.BOOLEAN, descriptor = "新建时，表单中是否显示该字段，true：显示，false：不显示")
    private Boolean addingShow = false;

    /**
     * 表单中是否显示该字段
     */
    @FieldName
    @XLPColumn(dataType = DataType.BOOLEAN, descriptor = "编辑时，表单中是否显示该字段，true：显示，false：不显示")
    private Boolean editShow = false;

    /**
     * 表单字段提示信息
     */
    @FieldName
    @XLPColumn(dataType = DataType.VARCHAR, length = 100, descriptor = "表单字段提示信息")
    private String placeholder = "";

    /**
     * 表单中该字段是否必填
     */
    @FieldName
    @XLPColumn(dataType = DataType.BOOLEAN, descriptor = "表单中该字段是否必填，true：必填，false：不必填")
    private Boolean required = false;

    /**
     * 文本最大长度
     */
    @FieldName
    @XLPColumn(dataType = DataType.INT, descriptor = "文本最大长度")
    private Integer textMaxLength = 0;

    /**
     * 是否自定义输入框
     */
    @FieldName
    @XLPColumn(dataType = DataType.BOOLEAN, descriptor = "是否自定义输入框，true：是，false：否")
    private Boolean slot = false;

    /**
     * 表单字段自定义类名称
     */
    @FieldName
    @XLPColumn(dataType = DataType.VARCHAR, length = 100, descriptor = "表单字段自定义类名称")
    private String itemClassName = "";

    /**
     * 默认值
     */
    @FieldName
    @XLPColumn(dataType = DataType.VARCHAR, length = 500, descriptor = "默认值")
    private String defaultValue;

    /**
     * 值来源，实现指定接口的类全路径或自定义脚本值（json串）或外部链接url
     */
    @FieldName
    @XLPColumn(dataType = DataType.VARCHAR, length = 255, descriptor = "值来源")
    private String valueFrom;

    /**
     * 值来源类型
     */
    @FieldName
    @XLPColumn(dataType = DataType.VARCHAR, length = 100, descriptor = "值来源类型")
    private ValueFromType valueFromType;

    /**
     * 属性类型
     */
    @FieldName
    @XLPColumn(dataType = DataType.VARCHAR, length = 20, descriptor = "属性类型")
    private AttributeType attributeType;

    /**
     * 是否可删除该字段
     */
    @FieldName
    @XLPColumn(dataType = DataType.BOOLEAN, descriptor = "是否可删除该字段，true：可删除，false：不可删除")
    private Boolean canDelete = false;

    /**
     * 属性验证规则
     */
    @FieldName
    @XLPColumn(dataType = DataType.VARCHAR, length = 255, descriptor = "属性验证规则")
    private String rule;

    public String getModelId() {
        return modelId;
    }

    public void setModelId(String modelId) {
        this.modelId = modelId;
    }

    public String getFieldId() {
        return fieldId;
    }

    public void setFieldId(String fieldId) {
        this.fieldId = fieldId;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public FormInputType getFormInputType() {
        return formInputType;
    }

    public void setFormInputType(FormInputType formInputType) {
        this.formInputType = formInputType;
    }

    public Integer getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(Integer orderNo) {
        this.orderNo = orderNo;
    }

    public Boolean getEdit() {
        return edit;
    }

    public void setEdit(Boolean edit) {
        this.edit = edit;
    }

    public Boolean getAdding() {
        return adding;
    }

    public void setAdding(Boolean adding) {
        this.adding = adding;
    }

    public Boolean getAddingShow() {
        return addingShow;
    }

    public void setAddingShow(Boolean addingShow) {
        this.addingShow = addingShow;
    }

    public Boolean getEditShow() {
        return editShow;
    }

    public void setEditShow(Boolean editShow) {
        this.editShow = editShow;
    }

    public String getPlaceholder() {
        return placeholder;
    }

    public void setPlaceholder(String placeholder) {
        this.placeholder = placeholder;
    }

    public Boolean getRequired() {
        return required;
    }

    public void setRequired(Boolean required) {
        this.required = required;
    }

    public Integer getTextMaxLength() {
        return textMaxLength;
    }

    public void setTextMaxLength(Integer textMaxLength) {
        this.textMaxLength = textMaxLength;
    }

    public Boolean getSlot() {
        return slot;
    }

    public void setSlot(Boolean slot) {
        this.slot = slot;
    }

    public String getItemClassName() {
        return itemClassName;
    }

    public void setItemClassName(String itemClassName) {
        this.itemClassName = itemClassName;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public String getValueFrom() {
        return valueFrom;
    }

    public void setValueFrom(String valueFrom) {
        this.valueFrom = valueFrom;
    }

    public ValueFromType getValueFromType() {
        return valueFromType;
    }

    public void setValueFromType(ValueFromType valueFromType) {
        this.valueFromType = valueFromType;
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

    public String getRule() {
        return rule;
    }

    public void setRule(String rule) {
        this.rule = rule;
    }

    /**
     * 创建一个空的模型表单详情对象，用于返回给前端使用
     *
     * @return
     */
    public static ModelFormDetailConfig of(){
        ModelFormDetailConfig modelFormDetailConfig = new ModelFormDetailConfig();
        modelFormDetailConfig.setFieldId("");
        modelFormDetailConfig.setFieldName("");
        modelFormDetailConfig.setModelId("");
        return modelFormDetailConfig;
    }

    @Override
    public String toString() {
        return "ModelFormDetailConfig{" +
                "modelId='" + modelId + '\'' +
                ", fieldId='" + fieldId + '\'' +
                ", fieldName='" + fieldName + '\'' +
                ", formInputType=" + formInputType +
                ", orderNo=" + orderNo +
                ", edit=" + edit +
                ", adding=" + adding +
                ", addingShow=" + addingShow +
                ", editShow=" + editShow +
                ", placeholder='" + placeholder + '\'' +
                ", required=" + required +
                ", textMaxLength=" + textMaxLength +
                ", slot=" + slot +
                ", itemClassName='" + itemClassName + '\'' +
                ", defaultValue='" + defaultValue + '\'' +
                ", valueFrom='" + valueFrom + '\'' +
                ", valueFromType=" + valueFromType +
                ", attributeType=" + attributeType +
                ", canDelete=" + canDelete +
                ", rule='" + rule + '\'' +
                "} " + super.toString();
    }
}
