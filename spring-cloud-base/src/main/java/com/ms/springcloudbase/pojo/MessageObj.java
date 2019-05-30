package com.ms.springcloudbase.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class MessageObj implements Serializable {
    private String uuid;
    private Date date;
}
