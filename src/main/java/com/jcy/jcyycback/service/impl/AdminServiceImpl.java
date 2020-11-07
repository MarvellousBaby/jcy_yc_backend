package com.jcy.jcyycback.service.impl;

import com.jcy.jcyycback.common.service.PageHelper;
import com.jcy.jcyycback.common.service.RespCodeEnum;
import com.jcy.jcyycback.common.service.RespData;
import com.jcy.jcyycback.common.utils.CacheUtils;
import com.jcy.jcyycback.dao.AdminDao;
import com.jcy.jcyycback.entity.system.Admin;
import com.jcy.jcyycback.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;

/**
 * @author ：Panyu
 * @date ：Created in 2020/11/7 上午 09:42:32
 * @description：用户服务层实现类
 * @modified By：
 */
@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminDao adminDao;


    @Override
    public RespData<Admin> login(Admin admin) {
        RespData<Admin> respData = new RespData<>();
        List<Admin> admins = adminDao.login(admin);

        //判断你登录的帐号
        if (CollectionUtils.isEmpty(admins)) {
            respData.setRespCode(RespCodeEnum.EXCEPTION_306);
            return respData;
        } else {
            respData.setRespCode(RespCodeEnum.SUCCESS);
            respData.setData(admins.get(0));
            return respData;
        }
    }

    @Override
    public Boolean checkUserName(Admin admin) {
        return adminDao.checkUserName(admin);
    }

    @Override
    public Boolean updatePwd(Admin admin) {
        return adminDao.updatePwd(admin);
    }

    @Override
    public List<Admin> queryAll() throws Exception {
        return adminDao.queryAll();
    }

    @Override
    public PageHelper<Admin> queryList(PageHelper<Admin> paramPageHelper) throws Exception {
        return adminDao.queryList(paramPageHelper);
    }

    @Override
    public List<Admin> queryList(Admin paramT) throws Exception {
        return adminDao.queryList(paramT);
    }

    @Override
    public Admin get(Admin paramT) throws Exception {
        return adminDao.get(paramT);
    }

    @Override
    public Boolean save(Admin paramT) throws Exception {
        if (paramT.getId() == null) {
            paramT.setEnable(0);
            paramT.setCreateUser(CacheUtils.getAdminLoginName());
            paramT.setCreateTime(new Date());
            return adminDao.save(paramT);
        } else {
            Admin admin = adminDao.get(paramT);

            admin.setName(paramT.getName());
            admin.setOrgId(paramT.getOrgId());
            admin.setPhone(paramT.getPhone());
            admin.setRemark(paramT.getRemark());
            admin.setUpdateUser(CacheUtils.getAdminLoginName());
            admin.setUpdateTime(new Date());
            return adminDao.save(admin);
        }
    }

    @Override
    public Boolean delete(Admin paramT) throws Exception {
        if (paramT.getId() == null) {
            return false;
        }
        return adminDao.delete(paramT);
    }
}
