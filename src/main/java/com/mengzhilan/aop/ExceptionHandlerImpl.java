package com.mengzhilan.aop;

import com.mengzhilan.constant.MessageConst;
import com.mengzhilan.exception.BusinessException;
import com.mengzhilan.exception.IExceptionHandler;
import com.mengzhilan.response.ResponseResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * Create by xlp on 2022/7/16
 *
 * 异常处理实现类
 */
public class ExceptionHandlerImpl implements IExceptionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionHandlerImpl.class);

    /**
     * 异常处理
     *
     * @param request
     * @param response
     * @param throwable
     * @return
     */
    @Override
    public Object exceptionHandle(ServletRequest request, ServletResponse response,
              Throwable throwable) {
        if (throwable == null) return null;

        if (throwable instanceof BusinessException) {
            return ResponseResult.error(throwable.getMessage());
        }
        if (LOGGER.isErrorEnabled()){
            LOGGER.error("系统错误：", throwable);
        }
        return ResponseResult.error(MessageConst.SERVER_ERROR_MSG);
    }
}
