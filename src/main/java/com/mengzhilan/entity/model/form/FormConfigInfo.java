package com.mengzhilan.entity.model.form;

import com.mengzhilan.base.MZBaseEntity;
import com.mengzhilan.enumeration.FormInputType;
import org.xlp.db.ddl.annotation.XLPIndex;
import org.xlp.db.tableoption.annotation.XLPColumn;
import org.xlp.db.tableoption.annotation.XLPEntity;
import org.xlp.db.tableoption.xlpenum.DataType;
import org.xlp.javabean.annotation.Bean;
import org.xlp.javabean.annotation.FieldName;

/**
 * Create by xlp on 2022/6/25
 */
@Bean
@XLPEntity(tableName = "mz_form_config_info", descriptor = "模型对应的表单配置信息")
public class FormConfigInfo extends MZBaseEntity {
    private static final long serialVersionUID = -1112344813652278907L;

    /**
     * 模型id
     */
    @XLPIndex
    @FieldName
    @XLPColumn(dataType = DataType.VARCHAR, length = 64, descriptor = "模型id")
    private String modelId;

    /**
     * 是否可编辑
     */
    @FieldName
    @XLPColumn(dataType = DataType.BOOLEAN, descriptor = "是否可编辑该字段，true：可编辑，false：不可编辑")
    private Boolean edit;

    /**
     * 新建时，该字段是否可编辑
     */
    @FieldName
    @XLPColumn(dataType = DataType.BOOLEAN, descriptor = "新建时，该字段是否可编辑，true：可编辑，false：不可编辑")
    private Boolean adding;

    /**
     * 表单中是否显示该字段
     */
    @FieldName
    @XLPColumn(dataType = DataType.BOOLEAN, descriptor = "新建时，表单中是否显示该字段，true：显示，false：不显示")
    private Boolean addingShow;

    /**
     * 表单中是否显示该字段
     */
    @FieldName
    @XLPColumn(dataType = DataType.BOOLEAN, descriptor = "编辑时，表单中是否显示该字段，true：显示，false：不显示")
    private Boolean editShow;

    /**
     * 排序号
     */
    @FieldName
    @XLPColumn(dataType = DataType.INT, descriptor = "排序号")
    private Integer orderNo = 0;

    /**
     * 表格中是否可排序
     */
    @FieldName
    @XLPColumn(dataType = DataType.BOOLEAN, descriptor = "表格中是否可排序，true：可以，false：不可以")
    private Boolean sortable = false;

    /**
     * 表格中是否可搜索
     */
    @FieldName
    @XLPColumn(dataType = DataType.BOOLEAN, descriptor = "表格中是否可搜索，true：可以，false：不可以")
    private Boolean searchable = false;

    /**
     * 表单中该字段是否必填
     */
    @FieldName
    @XLPColumn(dataType = DataType.BOOLEAN, descriptor = "表单中该字段是否必填，true：必填，false：不必填")
    private Boolean required = false;

    /**
     * 默认值
     */
    @FieldName
    @XLPColumn(dataType = DataType.VARCHAR, length = 500, descriptor = "默认值")
    private String defaultValue;

    /**
     * 值来源
     */
    @FieldName
    @XLPColumn(dataType = DataType.VARCHAR, length = 255, descriptor = "值来源")
    private String valueFrom;

    /**
     * 表格中是否显示该列
     */
    @FieldName
    @XLPColumn(dataType = DataType.BOOLEAN, descriptor = "表格中是否显示该列，true：显示，false：不显示")
    private Boolean showColumn = false;

    /**
     * 文本最大长度（对应textarea输入域生效）
     */
    @FieldName
    @XLPColumn(dataType = DataType.INT, descriptor = "文本最大长度")
    private Integer textMaxLength = 0;

    /**
     * 表单输入框类型
     */
    @FieldName
    @XLPColumn(dataType = DataType.VARCHAR, length = 100, descriptor = "表单输入框类型")
    private FormInputType formInputType = FormInputType.TEXT;

    /**
     * 是否自定义输入框
     */
    @FieldName
    @XLPColumn(dataType = DataType.BOOLEAN, descriptor = "是否自定义输入框，true：是，false：否")
    private Boolean slot = false;

}
