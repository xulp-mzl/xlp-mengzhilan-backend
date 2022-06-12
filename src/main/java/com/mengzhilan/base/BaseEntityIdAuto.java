package com.mengzhilan.base;

import org.xlp.db.tableoption.annotation.XLPColumn;
import org.xlp.db.tableoption.annotation.XLPId;
import org.xlp.db.tableoption.xlpenum.DataType;
import org.xlp.db.tableoption.xlpenum.PrimaryKeyType;
import org.xlp.javabean.annotation.FieldName;

import java.io.Serializable;

/**
 * Create by xlp on 2021/3/26
 *
 * 基本实体抽象类，主要提供两个必须属性
 */
public abstract class BaseEntityIdAuto implements Serializable {
   private static final long serialVersionUID = -5746897510175841125L;

   @FieldName
   @XLPId(type = PrimaryKeyType.AUTO, columnName = "id", dataType = DataType.BIGINT,
        isNull = false, descriptor = "主键，唯一标识")
   private Long id;

    @FieldName
    @XLPColumn(columnName = "classId", dataType = DataType.VARCHAR,
           length = 32, isNull = false, descriptor = "类标识", maxLength = 32)
   private String classId;

    /**
     * 获取id
     *
     * @return
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置id
     *
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取classId
     *
     * @return
     */
    public String getClassId() {
        if (classId == null){
            return this.getClass().getSimpleName();
        }
        return classId;
    }

    /**
     * 设置classId
     *
     * @param classId
     */
    public void setClassId(String classId) {
        this.classId = classId;
    }

    @Override
    public String toString() {
        return "BaseEntity{" +
                "id='" + id + '\'' +
                ", classId='" + classId + '\'' +
                '}';
    }
}
