package com.ms.springcloudbase.pojo;

import java.io.Serializable;

/**
 * @描述
 * @参数 
 * @返回值 
 * @创建人 Oliver.Liu
 * @创建时间 4/4/10/2019* @修改人和其它信息
 */
public class SecKillResult implements Serializable{
    private boolean success;
    private String message;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public SecKillResult(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public SecKillResult() {
    }
}
