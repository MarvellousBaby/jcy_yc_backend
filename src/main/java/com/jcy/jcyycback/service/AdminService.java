package com.jcy.jcyycback.service;

import com.jcy.jcyycback.common.service.IBaseService;
import com.jcy.jcyycback.common.service.RespData;
import com.jcy.jcyycback.entity.system.Admin;

/**
 * @author ：Panyu
 * @date ：Created in 2020/11/7 上午 09:23:24
 * @description：用户服务层
 * @modified By：
 * @version: 1.0.0$
 */
public interface AdminService extends IBaseService<Admin> {

    RespData<Admin> login(Admin admin);

    Boolean checkUserName(Admin admin);

    Boolean updatePwd(Admin admin);


}
