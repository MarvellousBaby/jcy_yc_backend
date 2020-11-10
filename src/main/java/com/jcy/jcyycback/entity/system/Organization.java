package com.jcy.jcyycback.entity.system;

import com.jcy.jcyycback.common.service.BaseEntity;
import lombok.Data;

/**
 * @description:
 * @author: PanYu
 * @create: 2020-11-06 17:07
 **/
@Data
public class Organization extends BaseEntity {

    private String name;

    /**
     * 组织登记 0-局，1-所
     */
    private Integer type;

    /**
     *  0-正常，1-删除
     */
    private Integer enable;

}
