package com.jcy.jcyycback.dao.impl;

import com.jcy.jcyycback.common.service.PageHelper;
import com.jcy.jcyycback.dao.AdminDao;
import com.jcy.jcyycback.entity.system.Admin;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ：Panyu
 * @date ：Created in 2020/11/7 上午 11:05:57
 * @description：用户Dao层实现类
 * @modified By：
 */
@Repository
public class AdminDaoImpl implements AdminDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Override
    public List<Admin> login(Admin admin) {
        List<Object> params = new ArrayList<>();
        String sql = "select * from t_user where 1=1 and enable=0 and username=? and password=? ";
        params.add(admin.getUserName());
        params.add(admin.getPassword());

        return jdbcTemplate.queryForList(sql, Admin.class, params.toArray());
    }

    @Override
    public Boolean checkUserName(Admin admin) {
        List<Object> parmas = new ArrayList<>();
        String sql = "select * from t_user where 1=1 and enable=0 and username=? ";
        parmas.add(admin.getUserName());
        List<Admin> list = jdbcTemplate.queryForList(sql, Admin.class, parmas.toArray());
        if (CollectionUtils.isEmpty(list)) {
            return true;
        }

        return false;
    }

    @Override
    public Boolean updatePwd(Admin admin) {
        List<Object> params = new ArrayList<>();
        String sql = "select * from t_user where username=? and password=? ";
        params.add(admin.getUserName());
        params.add(admin.getOldPassword());

        List<Admin> list = jdbcTemplate.queryForList(sql,Admin.class,params.toArray());

        if (CollectionUtils.isEmpty(list)){
            return false;
        }

        params.clear();
        sql = "update t_user set password=? where username=? ";
        params.add(admin.getPassword());
        params.add(admin.getUserName());

        int result = jdbcTemplate.update(sql,params.toArray());

        return result!=0;
    }

    @Override
    public List<Admin> queryAll() throws Exception {
        String sql = " select * from t_user where enable=0 ";

        return jdbcTemplate.queryForList(sql, Admin.class);
    }

    @Override
    public PageHelper<Admin> queryList(PageHelper<Admin> paramPageHelper) throws Exception {
        Admin admin = new Admin();
        if (paramPageHelper.getObject() != null) {
            admin = paramPageHelper.getObject();
        }

        List<Object> params = new ArrayList<>();
        StringBuffer sbCount = new StringBuffer();
        StringBuffer sbSelect = new StringBuffer();

        sbSelect.append("select * from t_user where 1=1 and enable = 0 ");
        if (StringUtils.isNotBlank(admin.getName())) {
            sbSelect.append(" and name like '%?%' ");
            params.add(admin.getName());
        }
        if (admin.getOrgId() != null) {
            sbSelect.append(" and orgId=? ");
            params.add(admin.getOrgId());
        }

        if (StringUtils.isNotBlank(admin.getPhone())) {
            sbSelect.append(" and phone like '%?%' ");
            params.add(admin.getPhone());
        }
        if (StringUtils.isNotBlank(paramPageHelper.getStartDate()) && StringUtils.isNotBlank(paramPageHelper.getEndDate())) {
            sbSelect.append(" and updatetime between ? and ? ");
            params.add(paramPageHelper.getStartDate());
            params.add(paramPageHelper.getEndDate());
        }

        sbCount.append("select count(1) from (");
        sbCount.append(sbSelect);
        sbCount.append(")");

        long total = jdbcTemplate.queryForObject(sbCount.toString(), Long.class, params.toArray());

        sbSelect.append(" limit ? offset ? ");
        sbSelect.append(" order by updatetime desc");
        params.add(paramPageHelper.getPageSize());
        params.add(paramPageHelper.getLimitMin());

        List<Admin> objects = jdbcTemplate.queryForList(sbSelect.toString(), Admin.class, params.toArray());

        paramPageHelper.setTotal(total);
        paramPageHelper.setObjects(objects);

        return paramPageHelper;
    }

    @Override
    public List<Admin> queryList(Admin paramT) throws Exception {
        List<Object> params = new ArrayList<>();
        StringBuffer sb = new StringBuffer();

        sb.append(" select * from t_user where 1=1 ");
        sb.append(" and enable=0 ");

        if (StringUtils.isNotBlank(paramT.getName())) {
            sb.append(" and name like '%?%' ");
            params.add(paramT.getName());
        }
        if (paramT.getOrgId() != null) {
            sb.append(" and orgId=? ");
            params.add(paramT.getOrgId());
        }

        if (StringUtils.isNotBlank(paramT.getPhone())) {
            sb.append(" and phone like '%?%' ");
            params.add(paramT.getPhone());
        }


        sb.append(" order by updatetime desc");

        return jdbcTemplate.queryForList(sb.toString(), Admin.class, params.toArray());
    }

    @Override
    public Admin get(Admin paramT) throws Exception {
        List<Object> params = new ArrayList<>();
        String sql = "select * from t_user where 1=1 and enable = 0 and id=? ";
        params.add(paramT.getId());
        return jdbcTemplate.queryForObject(sql, Admin.class, params.toArray());
    }

    @Override
    public Boolean save(Admin paramT) throws Exception {
        List<Object> params = new ArrayList<>();
        StringBuffer sb = new StringBuffer();
        int result = 0;
        if (paramT.getId() == null) {
            sb.append("insert into t_user values(?,?,?,?,?,?,?,?,?,?,?,)");
            params.add(paramT.getUserName());
            params.add(paramT.getPassword());
            params.add(paramT.getName());
            params.add(paramT.getOrgId());
            params.add(paramT.getPhone());
            params.add(paramT.getEnable());
            params.add(paramT.getCreateUser());
            params.add(paramT.getCreateTime());
            params.add(paramT.getUpdateUser());
            params.add(paramT.getUpdateTime());
            params.add(paramT.getRemark());
            result = jdbcTemplate.update(sb.toString(), params.toArray());
        } else {
            sb.append("update t_user set " +
                    "   username=?," +
                    "   name=?," +
                    "   orgId=?," +
                    "   phone=?," +
                    "   updatetime=?," +
                    "   updateuser=?," +
                    "   remark=?    " +
                    "   where id=?  ");
            params.add(paramT.getUserName());
            params.add(paramT.getName());
            params.add(paramT.getOrgId());
            params.add(paramT.getPhone());
            params.add(paramT.getUpdateTime());
            params.add(paramT.getUpdateUser());
            params.add(paramT.getRemark());
            params.add(paramT.getId());
            result = jdbcTemplate.update(sb.toString(), params.toArray());

        }

        return result != 0;
    }

    @Override
    public Boolean delete(Admin paramT) throws Exception {
        List<Object> params = new ArrayList<>();
        String sql = " update t_user set enable = 1 where id = ? ";
        params.add(paramT.getId());
        int result = jdbcTemplate.update(sql, params.toArray());
        return result != 0;
    }
}
