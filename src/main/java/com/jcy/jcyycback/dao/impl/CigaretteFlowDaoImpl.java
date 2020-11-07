package com.jcy.jcyycback.dao.impl;

import com.jcy.jcyycback.common.service.PageHelper;
import com.jcy.jcyycback.dao.CigaretteFlowDao;
import com.jcy.jcyycback.entity.system.Admin;
import com.jcy.jcyycback.entity.system.AttributionStatistic;
import com.jcy.jcyycback.entity.system.CigaretteFlow;
import com.jcy.jcyycback.entity.system.CigaretteStatistic;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ：Panyu
 * @date ：Created in 2020/11/7 下午 02:22:45
 * @description：香烟信息Dao层实体类
 * @modified By：
 */
@Repository
public class CigaretteFlowDaoImpl implements CigaretteFlowDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Override
    public CigaretteStatistic getTotal(PageHelper pageHelper) {
        List<Object> params = new ArrayList<>();
        StringBuffer sb = new StringBuffer();

        sb.append("select sum(number) as pieces,sum(price) as price from t_cigaratte where 1=1 ");

        if (StringUtils.isNotBlank(pageHelper.getStartDate()) && StringUtils.isNotBlank(pageHelper.getEndDate())) {
            sb.append(" and investigatetime between ? and ? ");
            params.add(pageHelper.getStartDate());
            params.add(pageHelper.getEndDate());
        }

        return jdbcTemplate.queryForObject(sb.toString(),CigaretteStatistic.class,params.toArray());
    }

    @Override
    public PageHelper<AttributionStatistic> getTotalByAttr(PageHelper pageHelper) {
        List<Object> params = new ArrayList<>();
        StringBuffer sb = new StringBuffer();
        StringBuffer sbPage = new StringBuffer();
        StringBuffer sbCount = new StringBuffer();

        sb.append("select cigaretteattribution as name,sum(number) as number from t_cigaratte where 1=1 ");

        if (StringUtils.isNotBlank(pageHelper.getStartDate()) && StringUtils.isNotBlank(pageHelper.getEndDate())) {
            sb.append(" and investigatetime between ? and ? ");
            params.add(pageHelper.getStartDate());
            params.add(pageHelper.getEndDate());
        }

        sb.append(" group by cigaretteattribution ");

        sbPage.append("select T1.* from (");
        sbPage.append(sb);
        sbPage.append(") T1 ");

        sbCount.append("select count(*) from (");
        sbCount.append(sb);
        sbCount.append(") T1 ");

        long total = jdbcTemplate.queryForObject(sbCount.toString(),Long.class,params.toArray());

        sbPage.append(" limit ? offset ? ");
        params.add(pageHelper.getPageSize());
        params.add(pageHelper.getLimitMin());
        List<AttributionStatistic> objects = jdbcTemplate.queryForList(sbPage.toString(),AttributionStatistic.class,params.toArray());

        pageHelper.setObjects(objects);
        pageHelper.setTotal(total);


        return pageHelper;
    }

    @Override
    public List<CigaretteFlow> queryAll() throws Exception {
        String sql = "select * from t_cigaratte ";

        return jdbcTemplate.queryForList(sql,CigaretteFlow.class);
    }

    @Override
    public PageHelper<CigaretteFlow> queryList(PageHelper<CigaretteFlow> paramPageHelper) throws Exception {
        CigaretteFlow cigaretteFlow = new CigaretteFlow();
        if (paramPageHelper.getObject()!=null){
            cigaretteFlow = paramPageHelper.getObject();
        }

        StringBuffer sbSelect = new StringBuffer();
        StringBuffer sbCount = new StringBuffer();
        List<Object> params = new ArrayList<>();

        sbSelect.append("select * from t_cigaratte where 1=1 ");
        //查处地
        if (StringUtils.isNotBlank(cigaretteFlow.getInvestigateAddress())){
            sbSelect.append(" and investigateaddress like '%?%' ");
            params.add(cigaretteFlow.getInvestigateAddress());
        }
        //归属地
        if (StringUtils.isNotBlank(cigaretteFlow.getCigaretteAttribution())){
            sbSelect.append(" and cigaretteAttribution like '%?%' ");
            params.add(cigaretteFlow.getCigaretteAttribution());
        }

        //机构
        if (cigaretteFlow.getOrgId()!=null){
            sbSelect.append(" and orgid =? ");
            params.add(cigaretteFlow.getOrgId());
        }

        if (StringUtils.isNotBlank(paramPageHelper.getStartDate()) && StringUtils.isNotBlank(paramPageHelper.getEndDate())) {
            sbSelect.append(" and investigatetime between ? and ? ");
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

        List<CigaretteFlow> objects = jdbcTemplate.queryForList(sbSelect.toString(), CigaretteFlow.class, params.toArray());

        paramPageHelper.setTotal(total);
        paramPageHelper.setObjects(objects);

        return paramPageHelper;
    }

    @Override
    public List<CigaretteFlow> queryList(CigaretteFlow paramT) throws Exception {
        return null;
    }

    @Override
    public CigaretteFlow get(CigaretteFlow paramT) throws Exception {
        List<Object> params = new ArrayList<>();
        String sql = "select * from t_cigaratte where id=? ";
        params.add(paramT.getId());

        return jdbcTemplate.queryForObject(sql,CigaretteFlow.class,params.toArray());
    }

    @Override
    public Boolean save(CigaretteFlow paramT) throws Exception {
        List<Object> params = new ArrayList<>();
        StringBuffer sb = new StringBuffer();
        if (paramT.getId() == null){
            sb.append("insert into t_cigaratte values(?,?,?,?,?,?,?,?,?,?)");
            params.add(paramT.getOrgId());
            params.add(paramT.getInvestigateTime());
            params.add(paramT.getInvestigateAddress());
            params.add(paramT.getCigaretteAttribution());
            params.add(paramT.getNumber());
            params.add(paramT.getPrice());
            params.add(paramT.getCreateUser());
            params.add(paramT.getCreateTime());
            params.add(paramT.getUpdateUser());
            params.add(paramT.getUpdateTime());

        }else{
            sb.append(" update t_cigaratte " +
                    "   set orgid=?, " +
                    "    investigatetime=?, "+
                    "    investigateaddress=?, "+
                    "    cigaretteattribution=?, "+
                    "    number=?, "+
                    "    price=?, "+
                    "    updateuser=?, "+
                    "    updatetime=? " +
                    "   where id=? "
            );
            params.add(paramT.getOrgId());
            params.add(paramT.getInvestigateTime());
            params.add(paramT.getInvestigateAddress());
            params.add(paramT.getCigaretteAttribution());
            params.add(paramT.getNumber());
            params.add(paramT.getPrice());
            params.add(paramT.getUpdateUser());
            params.add(paramT.getUpdateTime());
            params.add(paramT.getId());

        }

        return jdbcTemplate.update(sb.toString(),params.toArray())!=0;
    }

    @Override
    public Boolean delete(CigaretteFlow paramT) throws Exception {
        List<Object> params = new ArrayList<>();
        String sql = "delete from t_cigaratte where id=? ";
        params.add(paramT.getId());

        return jdbcTemplate.update(sql,params.toArray())!=0;
    }
}
