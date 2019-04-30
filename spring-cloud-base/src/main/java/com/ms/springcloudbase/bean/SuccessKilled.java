package com.ms.springcloudbase.bean;

import java.util.Date;

public class SuccessKilled extends SuccessKilledKey {
    private Byte state;

    private Date createTime;

    public SuccessKilled() {
    }

    public SuccessKilled(Long seckillId, Long userPhone, Byte state) {
        this.setSeckillId(seckillId);
        this.setUserPhone(userPhone);
        this.state = state;
    }

    public Byte getState() {
        return state;
    }

    public void setState(Byte state) {
        this.state = state;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}