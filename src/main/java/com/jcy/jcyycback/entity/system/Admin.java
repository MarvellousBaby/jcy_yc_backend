package com.jcy.jcyycback.entity.system;

import com.jcy.jcyycback.common.service.BaseEntity;
import lombok.Data;

/**
 * @description:
 * @author: PanYu
 * @create: 2020-11-06 17:01
 **/

@Data
public class Admin extends BaseEntity {

    private String userName;

    private String password;

    private String name;

    private Integer orgId;

    private String phone;

    /**
     *  0-正常，1-删除
     */
    private Integer enable;

    /**
     * 业务所需字段
     */
    /**
     * 旧密码
     */
    private String oldPassword;


}
