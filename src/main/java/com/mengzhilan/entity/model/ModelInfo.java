package com.mengzhilan.entity.model;

import com.mengzhilan.base.MZBaseEntity;
import org.xlp.db.ddl.annotation.XLPIndex;
import org.xlp.db.ddl.type.IndexType;
import org.xlp.db.tableoption.annotation.XLPColumn;
import org.xlp.db.tableoption.annotation.XLPEntity;
import org.xlp.db.tableoption.xlpenum.DataType;
import org.xlp.db.tableoption.xlpenum.PrimaryKeyType;
import org.xlp.javabean.annotation.Bean;
import org.xlp.javabean.annotation.FieldName;

/**
 * Create by xlp on 2022/6/12
 *
 * 模型信息
 */
@Bean
@XLPEntity(descriptor = "模型信息", tableName = "mz_model_info",
        primaryKeyType = PrimaryKeyType.AUTO)
public class ModelInfo extends MZBaseEntity {
    private static final long serialVersionUID = 7462421299453032133L;
    /**
     * 模型id
     */
    @FieldName
    @XLPIndex(indexType = IndexType.UNIQUE)
    @XLPColumn(columnName = "modelId", dataType = DataType.VARCHAR,
            length = 100, descriptor = "模型id")
    private String modelId;

    /**
     * 模型名称
     */
    @FieldName
    @XLPColumn(columnName = "modelName", dataType = DataType.VARCHAR,
            length = 100, descriptor = "模型名称")
    private String modelName;

    /**
     * 模型权重
     */
    @FieldName
    @XLPColumn(columnName = "weight", dataType = DataType.INT, descriptor = "权重")
    private int weight;

    /**
     * 是否显示该模型
     */
    @FieldName
    @XLPColumn(columnName = "disabled", dataType = DataType.BOOLEAN,
            descriptor = "是否显示该模型，true：显示，false：不显示")
    private boolean disabled;

    public String getModelId() {
        return modelId;
    }

    public void setModelId(String modelId) {
        this.modelId = modelId;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public boolean isDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

    @Override
    public String toString() {
        return "ModelInfo{" +
                "modelId='" + modelId + '\'' +
                ", modelName='" + modelName + '\'' +
                ", weight=" + weight +
                ", disabled=" + disabled +
                "} " + super.toString();
    }
}
