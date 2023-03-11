package com.mengzhilan.entity.model.table;

import com.mengzhilan.base.MZBaseEntity;
import org.xlp.db.ddl.annotation.XLPIndex;
import org.xlp.db.tableoption.annotation.XLPColumn;
import org.xlp.db.tableoption.annotation.XLPEntity;
import org.xlp.db.tableoption.xlpenum.DataType;
import org.xlp.javabean.annotation.Bean;
import org.xlp.javabean.annotation.FieldName;

/**
 * Create by xlp on 2022/8/7
 *
 * 模型表格配置
 */
@Bean
@XLPEntity(tableName = "mz_model_table_config", descriptor = "模型表格配置")
public class ModelTableConfig extends MZBaseEntity {
    private static final long serialVersionUID = -6109863249015369663L;

    /**
     * 模型id
     */
    @XLPIndex
    @FieldName
    @XLPColumn(dataType = DataType.VARCHAR, length = 64, descriptor = "模型id")
    private String modelId;

    /**
     * 表格列Id
     */
    @XLPIndex
    @FieldName(name = "prop")
    @XLPColumn(dataType = DataType.VARCHAR, length = 100, descriptor = "表格列Id")
    private String columnId;

    /**
     * 表格列名称
     */
    @FieldName(name = "label")
    @XLPColumn(dataType = DataType.VARCHAR, length = 255, descriptor = "表格列名称")
    private String columnName;

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
     * 表格中是否显示该列
     */
    @FieldName
    @XLPColumn(dataType = DataType.BOOLEAN, descriptor = "表格中是否显示该列，true：显示，false：不显示")
    private Boolean showColumn = false;

    /**
     * 是否自定义搜索框
     */
    @FieldName
    @XLPColumn(dataType = DataType.BOOLEAN, descriptor = "是否自定义搜索框，true：是，false：否")
    private Boolean slot = false;

    /**
     * 是否需要传给前端
     */
    @FieldName
    @XLPColumn(dataType = DataType.BOOLEAN, descriptor = "是否需要传给前端，true：是，false：否")
    private Boolean needToFrontEnd = false;

    /**
     * 列宽
     */
    @FieldName
    @XLPColumn(dataType = DataType.INT, descriptor = "列宽")
    private Integer width;

    /**
     * 列最小宽度
     */
    @FieldName
    @XLPColumn(dataType = DataType.INT, descriptor = "列最小宽度")
    private Integer minWidth;
}
