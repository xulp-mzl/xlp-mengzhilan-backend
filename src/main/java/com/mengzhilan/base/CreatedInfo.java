package com.mengzhilan.base;

import java.util.Date;

/**
 * Create by xlp on 2021/3/26
 *
 * 创建信息接口
 */
public interface CreatedInfo {
    /**
     * 获取更新时间
     *
     * @return
     */
    Date getUpdateTime();

    /**
     * 设置更新时间
     *
     * @param updateTime
     */
    void setUpdateTime(Date updateTime);

    /**
     * 获取创建时间
     *
     * @return
     */
    Date getCreateTime();

    /**
     * 设置创建时间
     *
     * @param createTime
     */
    void setCreateTime(Date createTime);

    /**
     * 创建人Id
     *
     * @return
     */
    String getCreatorId();

    /**
     * 设置创建人Id
     *
     * @param creatorId
     */
    void setCreatorId(String creatorId);

    /**
     * 获取修改人Id
     *
     * @return
     */
    String getUpdatedId();

    /**
     * 设置修改人Id
     *
     * @param updatedId
     */
    void setUpdatedId(String updatedId);
}
