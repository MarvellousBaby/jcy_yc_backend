package com.jcy.jcyycback.common.service;

import lombok.Data;

import java.util.Date;

/**
 * @description:
 * @author: PanYu
 * @create: 2020-11-06 16:59
 **/
@Data
public class BaseEntity {

    private Integer id;

    private Date createTime;

    private String createUser;

    private Date updateTime;

    private String updateUser;

    private String remark;


}
