package com.mengzhilan.response;

/**
 * Create by xlp on 2022/5/23
 *
 * 2xx 2开头表现成功
 * 3xx 3开头表示警告
 * 4xx 4开头表示参数有问题
 * 5xx 5开头表示服务处问题
 * 6xx 6开头表示表数据已存在相应条件的数据
 */
public enum StatusCode {
    /**
     * 响应成功
     */
    SUCCESS(200),

    /**
     *  请求体数据缺失
     */
    NOT_REQUEST_BODY(405),

    /**
     *  必要数据缺失
     */
    MUST_DATA_LOSE(406),

    /**
     *  请求参数缺失
     */
    REQUEST_PARAMETER_LOSE(407),

    /**
     * 服务异常
     */
    SERVER_ERROR(500),

    /**
     * 数据已存在
     */
    HAS_EXISTS(600),
    /**
     * 有子菜单
     */
    HAS_CHILDREN_MENU(601)

    ;
    /**
     * 响应状态吗
     */
    private int code;

    private StatusCode(int code){
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
