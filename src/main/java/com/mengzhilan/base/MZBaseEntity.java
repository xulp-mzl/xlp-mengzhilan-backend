package com.mengzhilan.base;

import org.xlp.db.tableoption.annotation.XLPColumn;
import org.xlp.db.tableoption.xlpenum.DataType;
import org.xlp.javabean.annotation.FieldName;

import java.util.Date;

/**
 * Create by xlp on 2021/3/27
 *
 * 继承<code>{@link BaseEntity}</code>,实现<code>{@link CreatedInfo}</code>接口
 * 提供实体基本属性
 */
public abstract class MZBaseEntity extends BaseEntity implements CreatedInfo{
    private static final long serialVersionUID = -5274404784790659677L;

    @FieldName
    @XLPColumn(columnName = "updateTime", dataType = DataType.DATETIME,
            descriptor = "最近更新时间")
    private Date updateTime;

    @FieldName
    @XLPColumn(columnName = "createTime", dataType = DataType.DATETIME,
            descriptor = "创建时间", isNull = false)
    private Date createTime;

    @FieldName
    @XLPColumn(columnName = "creatorId", dataType = DataType.VARCHAR,
            length = 64, descriptor = "创建人id")
    private String creatorId;

    @FieldName
    @XLPColumn(columnName = "updatedId", dataType = DataType.VARCHAR,
            length = 64, descriptor = "最近更新人id")
    private String updatedId;

    @Override
    public Date getUpdateTime() {
        return updateTime;
    }

    @Override
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public Date getCreateTime() {
        return createTime;
    }

    @Override
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String getCreatorId() {
        return creatorId;
    }

    @Override
    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }

    @Override
    public String getUpdatedId() {
        return updatedId;
    }

    @Override
    public void setUpdatedId(String updatedId) {
        this.updatedId = updatedId;
    }

    @Override
    public String toString() {
        return "MZBaseEntity{" +
                "updateTime=" + updateTime +
                ", createTime=" + createTime +
                ", creatorId='" + creatorId + '\'' +
                ", updatedId='" + updatedId + '\'' +
                "} " + super.toString();
    }
}
