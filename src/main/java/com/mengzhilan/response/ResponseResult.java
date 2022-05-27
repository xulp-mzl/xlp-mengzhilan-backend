package com.mengzhilan.response;

import org.xlp.javabean.annotation.Bean;
import org.xlp.javabean.annotation.FieldName;

import java.io.Serializable;

/**
 * Create by xlp on 2022/5/3
 *
 * 统一响应返回对象
 */
@Bean
public class ResponseResult implements Serializable {
    private static final long serialVersionUID = 662496078380514304L;
    /**
     * 响应码
     */
    @FieldName
    private int code;

    /**
     * 错误信息
     */
    @FieldName
    private String errorMsg = "";

    /**
     * 状态码枚举对象
     */
    private StatusCode statusCode;

    /**
     * 响应消息
     */
    @FieldName
    private Object data;

    public int getCode() {
        return statusCode.getCode();
    }

    public void setStatusCode(StatusCode statusCode) {
        this.statusCode = statusCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    /**
     * 成功相应
     *
     * @return
     */
    public static ResponseResult success(){
        ResponseResult responseResult = new ResponseResult();
        responseResult.setStatusCode(StatusCode.SUCCESS);
        return responseResult;
    }

    /**
     * 相应失败
     *
     * @param errorMsg
     * @return
     */
    public static ResponseResult error(String errorMsg){
        return error(StatusCode.SERVER_ERROR, errorMsg);
    }

    /**
     * 相应成功
     * @param data
     * @return
     */
    public static ResponseResult success(Object data){
        ResponseResult responseResult = new ResponseResult();
        responseResult.setStatusCode(StatusCode.SUCCESS);
        responseResult.setData(data);
        return responseResult;
    }

    /**
     * 用错误响应码和错误消息构造该对象
     *
     * @param code
     * @param errorMsg
     * @return
     */
    public static ResponseResult error(StatusCode code, String errorMsg){
        ResponseResult responseResult = new ResponseResult();
        responseResult.setStatusCode(code);
        responseResult.setErrorMsg(errorMsg);
        return responseResult;
    }
}
