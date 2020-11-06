package com.jcy.jcyycback.dao.impl;

import com.jcy.jcyycback.common.service.PageHelper;
import com.jcy.jcyycback.dao.OrganizationDao;
import com.jcy.jcyycback.entity.system.Organization;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ：Panyu
 * @date ：Created in 2020/11/7 上午 12:02:23
 * @description：组织Dao层实现类
 * @modified By：
 */
@Repository
public class OrganizationDaoImpl implements OrganizationDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Override
    public List<Organization> queryAll() throws Exception {
        String sql = " select * from t_org where enable=0 ";

        return jdbcTemplate.queryForList(sql, Organization.class);
    }

    @Override
    public PageHelper<Organization> queryList(PageHelper<Organization> paramPageHelper) throws Exception {
        Organization organization = new Organization();
        if (paramPageHelper.getObject() != null) {
            organization = paramPageHelper.getObject();
        }

        List<Object> params = new ArrayList<>();
        StringBuffer sbCount = new StringBuffer();
        StringBuffer sbSelect = new StringBuffer();

        sbSelect.append("select * from t_org where 1=1 and enable = 0 ");
        if (StringUtils.isNotBlank(organization.getName())) {
            sbSelect.append(" and name like '%?%' ");
            params.add(organization.getName());
        }
        if (StringUtils.isNotBlank(paramPageHelper.getStartDate()) && StringUtils.isNotBlank(paramPageHelper.getEndDate())) {
            sbSelect.append(" and updateTime between ? and ? ");
            params.add(paramPageHelper.getStartDate());
            params.add(paramPageHelper.getEndDate());
        }

        sbCount.append("select count(1) from (");
        sbCount.append(sbSelect);
        sbCount.append(")");

        long total = jdbcTemplate.queryForObject(sbCount.toString(), Long.class, params.toArray());

        sbSelect.append(" limit ? offset ? ");
        sbSelect.append(" order by updateTime desc");
        params.add(paramPageHelper.getPageSize());
        params.add(paramPageHelper.getLimitMin());

        List<Organization> objects = jdbcTemplate.queryForList(sbSelect.toString(), Organization.class, params.toArray());

        paramPageHelper.setTotal(total);
        paramPageHelper.setObjects(objects);

        return paramPageHelper;
    }

    @Override
    public List<Organization> queryList(Organization paramT) throws Exception {
        List<Object> params = new ArrayList<>();
        StringBuffer sb = new StringBuffer();

        sb.append(" select * from t_org where 1=1 ");
        sb.append(" and enable=0 ");

        if (StringUtils.isNotBlank(paramT.getName())) {
            sb.append(" and name like '%?%' ");
            params.add(paramT.getName());
        }

        sb.append(" order by updateTime desc");

        return jdbcTemplate.queryForList(sb.toString(), Organization.class, params.toArray());
    }

    @Override
    public Organization get(Organization paramT) throws Exception {
        List<Object> params = new ArrayList<>();
        String sql = "select * from t_org where 1=1 and enable = 0 and id=? ";
        params.add(paramT.getId());
        return jdbcTemplate.queryForObject(sql, Organization.class, params.toArray());
    }

    @Override
    public Boolean save(Organization paramT) throws Exception {
        List<Object> params = new ArrayList<>();
        StringBuffer sb = new StringBuffer();
        int result = 0;
        if (paramT.getId() == null) {
            sb.append("insert into t_org values(?,?,?,?,?,?,?)");
            params.add(paramT.getName());
            params.add(paramT.getEnable());
            params.add(paramT.getCreateUser());
            params.add(paramT.getCreateTime());
            params.add(paramT.getUpdateUser());
            params.add(paramT.getUpdateTime());
            params.add(paramT.getRemark());
            result = jdbcTemplate.update(sb.toString(), params.toArray());
        } else {
            sb.append("update t_org set name=?," +
                    "   updateTime=?," +
                    "   updateuser=?," +
                    "   remark=?    " +
                    "   where id=?  ");
            params.add(paramT.getName());
            params.add(paramT.getUpdateTime());
            params.add(paramT.getUpdateUser());
            params.add(paramT.getRemark());
            params.add(paramT.getId());
            result = jdbcTemplate.update(sb.toString(), params.toArray());

        }

        return result != 0;
    }

    @Override
    public Boolean delete(Organization paramT) throws Exception {
        List<Object> params = new ArrayList<>();
        String sql = " update t_org set enable = 1 where id = ? ";
        params.add(paramT.getId());
        int result = jdbcTemplate.update(sql, params.toArray());
        return result != 0;
    }
}
