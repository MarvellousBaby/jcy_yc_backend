package com.jcy.jcyycback.service.impl;

import com.jcy.jcyycback.common.service.PageHelper;
import com.jcy.jcyycback.common.utils.CacheUtils;
import com.jcy.jcyycback.dao.OrganizationDao;
import com.jcy.jcyycback.entity.system.Organization;
import com.jcy.jcyycback.service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.ws.Action;
import java.util.Date;
import java.util.List;

/**
 * @author ：Panyu
 * @date ：Created in 2020/11/6 下午 11:14:58
 * @description：组织接口实现类
 * @modified By：
 */
@Service
public class OrganizationServiceImpl implements OrganizationService {


    @Autowired
    private OrganizationDao organizationDao;


    @Override
    public List<Organization> queryAll() throws Exception {
        return organizationDao.queryAll();
    }

    @Override
    public PageHelper<Organization> queryList(PageHelper<Organization> paramPageHelper) throws Exception {

        return organizationDao.queryList(paramPageHelper);
    }

    @Override
    public List<Organization> queryList(Organization paramT) throws Exception {
        return organizationDao.queryList(paramT);
    }

    @Override
    public Organization get(Organization paramT) throws Exception {
        return organizationDao.get(paramT);
    }

    @Override
    public Boolean save(Organization paramT) throws Exception {

        if (paramT.getId() == null){
            paramT.setEnable(0);
            paramT.setCreateUser(CacheUtils.getAdminLoginName());
            paramT.setCreateTime(new Date());
        }
        paramT.setUpdateUser(CacheUtils.getAdminLoginName());
        paramT.setUpdateTime(new Date());

        return organizationDao.save(paramT);
    }

    @Override
    public Boolean delete(Organization paramT) throws Exception {
        return organizationDao.delete(paramT);
    }
}
