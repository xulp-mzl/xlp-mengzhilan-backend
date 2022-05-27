package com.mengzhilan.base;

/**
 * Create by xlp on 2021/3/23
 *
 * @Description 该接口用来指定是否需要拼音属性
 */
public interface Pinyin {
    /**
     * 获取拼音
     *
     * @return
     */
    String getPinyin();

    /**
     * 设置拼音
     *
     * @param pinyin
     */
    void setPinyin(String pinyin);

    /**
     * 获取拼音简写
     *
     * @return
     */
    String getSimplePinyin();

    /**
     * 设置拼音简写
     *
     * @param simplePinyin
     */
    void setSimplePinyin(String simplePinyin);
}
