package com.we.pmp.common.exception;

/**
 * 自定义异常
 * @author we
 * @date 2021-05-06 14:34
 **/
public class CommonException extends RuntimeException {
    private String msg;
    private Integer code = 500;

    public CommonException(String msg){
        super(msg);
        this.msg = msg;
    }

    public CommonException(String msg,Throwable e){
        super(msg,e);
        this.msg = msg;
    }

    public CommonException(String msg,Integer code){
        super(msg);
        this.msg = msg;
        this.code = code;
    }

    public CommonException(String msg, int code, Throwable e) {
        super(msg, e);
        this.msg = msg;
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
