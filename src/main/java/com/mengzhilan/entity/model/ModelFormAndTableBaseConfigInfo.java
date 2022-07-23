package com.mengzhilan.entity.model;

import com.mengzhilan.base.MZBaseEntity;
import com.mengzhilan.enumeration.FormItemSizeType;
import com.mengzhilan.enumeration.table.FirstColumnType;
import org.xlp.db.ddl.annotation.XLPIndex;
import org.xlp.db.tableoption.annotation.XLPColumn;
import org.xlp.db.tableoption.annotation.XLPEntity;
import org.xlp.db.tableoption.xlpenum.DataType;
import org.xlp.javabean.annotation.Bean;
import org.xlp.javabean.annotation.FieldName;

/**
 * Create by xlp on 2022/6/26
 *
 * 模型表单操作和table操作基本配置信息
 */
@Bean
@XLPEntity(tableName = "mz_form_table_config", descriptor = "模型表单操作和table操作基本配置信息")
public class ModelFormAndTableBaseConfigInfo extends MZBaseEntity {
    private static final long serialVersionUID = 1007582499166803526L;

    /**
     * 模型id
     */
    @XLPIndex
    @FieldName
    @XLPColumn(dataType = DataType.VARCHAR, length = 64, descriptor = "模型id")
    private String modelId;

    /**
     * 表格中是否可搜索
     */
    @FieldName
    @XLPColumn(dataType = DataType.BOOLEAN, descriptor = "表格中是否可搜索，true：可以，false：不可以")
    private Boolean filterable = false;

    /**
     * 表格中首列类型
     */
    @FieldName
    @XLPColumn(dataType = DataType.VARCHAR, length = 10,
            descriptor = "表格中首列类型，可取值为【index, selection, ''】")
    private FirstColumnType firstColumnType = FirstColumnType.none;

    /**
     * 表单宽度
     */
    @FieldName
    @XLPColumn(dataType = DataType.VARCHAR, length = 20, descriptor = "表单宽度")
    private String formWidth;

    /**
     * 表单项描述宽度
     */
    @FieldName
    @XLPColumn(dataType = DataType.VARCHAR, length = 20, descriptor = "表单宽度")
    private String formItemLabelWidth;

    /**
     * 表单是否全屏显示
     */
    @FieldName
    @XLPColumn(dataType = DataType.BOOLEAN, descriptor = "表单是否全屏显示，true：是，false：否")
    private Boolean fullscreen = false;

    /**
     * 表单输入项水平分成列数
     */
    @FieldName
    @XLPColumn(dataType = DataType.INT, descriptor = "表单输入项水平分成列数")
    private Integer splitColumnCount = 1;

    /**
     * 表单项输入框大小类型
     */
    @FieldName
    @XLPColumn(dataType = DataType.VARCHAR, length = 6, descriptor = "表单项输入框大小类型")
    private FormItemSizeType formItemSizeType = FormItemSizeType.SMALL;

    public String getModelId() {
        return modelId;
    }

    public void setModelId(String modelId) {
        this.modelId = modelId;
    }

    public Boolean getFilterable() {
        return filterable;
    }

    public void setFilterable(Boolean filterable) {
        this.filterable = filterable;
    }

    public FirstColumnType getFirstColumnType() {
        return firstColumnType;
    }

    public void setFirstColumnType(FirstColumnType firstColumnType) {
        this.firstColumnType = firstColumnType;
    }

    public String getFormWidth() {
        return formWidth;
    }

    public void setFormWidth(String formWidth) {
        this.formWidth = formWidth;
    }

    public Boolean getFullscreen() {
        return fullscreen;
    }

    public void setFullscreen(Boolean fullscreen) {
        this.fullscreen = fullscreen;
    }

    public Integer getSplitColumnCount() {
        return splitColumnCount;
    }

    public void setSplitColumnCount(Integer splitColumnCount) {
        this.splitColumnCount = splitColumnCount;
    }

    public String getFormItemLabelWidth() {
        return formItemLabelWidth;
    }

    public void setFormItemLabelWidth(String formItemLabelWidth) {
        this.formItemLabelWidth = formItemLabelWidth;
    }

    public FormItemSizeType getFormItemSizeType() {
        return formItemSizeType;
    }

    public void setFormItemSizeType(FormItemSizeType formItemSizeType) {
        this.formItemSizeType = formItemSizeType;
    }

    @Override
    public String toString() {
        return "ModelFormAndTableBaseConfigInfo{" +
                "modelId='" + modelId + '\'' +
                ", filterable=" + filterable +
                ", firstColumnType=" + firstColumnType +
                ", formWidth='" + formWidth + '\'' +
                ", formItemLabelWidth='" + formItemLabelWidth + '\'' +
                ", fullscreen=" + fullscreen +
                ", splitColumnCount=" + splitColumnCount +
                ", formItemSizeType=" + formItemSizeType +
                "} " + super.toString();
    }
}
