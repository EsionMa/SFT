package com.wangzhixuan.commons;

import com.wangzhixuan.commons.result.ErrorCode;

public class SysException extends RuntimeException {
    private String code;
    private String msg;

    public SysException(String msg) {
        super(msg);
        this.code = "SF8888";
        this.msg = msg;
    }

    public SysException(ErrorCode errorCode, Throwable e) {
        super(errorCode.getMsg(), e);
        this.code = errorCode.getCode();
        this.msg = errorCode.getMsg() + "," + e.getMessage();
    }

    public SysException(String msg, Throwable e) {
        super(msg, e);
        this.msg = msg + "," + e.getMessage();
    }

    public SysException(ErrorCode errorCode) {
        super(errorCode.getMsg());
        this.code = errorCode.getCode();
        this.msg = errorCode.getMsg();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    /**
     *
     */
    private static final long serialVersionUID = -4917822252877845759L;

}
