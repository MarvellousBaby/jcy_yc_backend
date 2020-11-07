package com.jcy.jcyycback.dao;

import com.jcy.jcyycback.common.service.IBaseService;
import com.jcy.jcyycback.entity.system.Admin;

import java.util.List;

/**
 * @author ：Panyu
 * @date ：Created in 2020/11/7 上午 11:04:35
 * @description：用户Dao层接口
 * @modified By：
 * @version: $
 */
public interface AdminDao extends IBaseService<Admin> {


    List<Admin> login(Admin admin);

    Boolean checkUserName(Admin admin);

    Boolean updatePwd(Admin admin);


}
