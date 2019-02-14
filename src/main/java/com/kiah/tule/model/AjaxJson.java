package com.kiah.tule.model;

import java.io.Serializable;

/**
 * @author kiah
 * @version Version 1.0
 * @date 2018/11/10
 */
public class AjaxJson implements Serializable {
    private String code;
    private String msg;
    private Object result;

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

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }
}
