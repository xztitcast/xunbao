package com.bfox.xunbao.common.core.exception.custom;

/**
 * 统一的业务异常
 * @author eden
 * @date 2023/6/25 18:07:07
 */
public class XunbaoshuServiceException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private int code;

    public XunbaoshuServiceException(int code, String message){
        this(code, message, null);
    }

    public XunbaoshuServiceException(int code, String message, Throwable cause){
        super(message, cause);
        this.code = code;
    }

    public int getCode(){
        return this.code;
    }
}
