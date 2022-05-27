package com.mengzhilan.base;

import java.util.Map;

/**
 * Create by xlp on 2021/3/23
 *
 * 标记某个实体对象是否需要扩展属性
 */
public interface ExtendedAttr {
    /**
     * 根据扩展属性id获取扩展属性值
     *
     * @param attrId 扩展属性id
     * @return
     */
    Object getAttribute(String attrId);

    /**
     * 设置扩展属性值
     *
     * @param attrId
     * @param value
     */
    void setAttribute(String attrId, Object value);

    /**
     * 获取所有的扩展属性
     *
     * @return
     */
    Map<String, Object> getAttributes();

    /**
     * 设置所有的扩展属性
     *
     * @param attributes
     */
    void  setAttributes(Map<String, Object> attributes);
}
