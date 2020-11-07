package com.jcy.jcyycback.common.service;

import lombok.Data;

import java.io.Serializable;

/**
 * @author ：Panyu
 * @date ：Created in 2020/11/7 上午 09:26:50
 * @description：返回公共类
 * @modified By：
 */

@Data
public class RespData<T> implements Serializable {
    private String id;

    private String serviceId;

    private Integer code;

    private String reason;

    private T data;


    @Override
    public String toString() {
        return "RespData(id=" + getId() + ", serviceId=" + getServiceId() + ", code=" + getCode() + ", reason=" + getReason() + ", data=" + getData() + ")";
    }


    public void setRespCode(IRespCodeEnum respCodeEnum) {
        this.code = Integer.valueOf(respCodeEnum.getCode());
        this.reason = respCodeEnum.getText();
    }
}

