package com.jcy.jcyycback.service.impl;

import com.jcy.jcyycback.common.service.PageHelper;
import com.jcy.jcyycback.common.utils.CacheUtils;
import com.jcy.jcyycback.dao.CigaretteFlowDao;
import com.jcy.jcyycback.entity.system.Admin;
import com.jcy.jcyycback.entity.system.AttributionStatistic;
import com.jcy.jcyycback.entity.system.CigaretteFlow;
import com.jcy.jcyycback.entity.system.CigaretteStatistic;
import com.jcy.jcyycback.service.CigaretteFlowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author ：Panyu
 * @date ：Created in 2020/11/7 下午 01:20:19
 * @description：香烟外流服务层实现类
 * @modified By：
 */
@Service
public class CigaretteFlowServiceImpl implements CigaretteFlowService {

    @Autowired
    private CigaretteFlowDao cigaretteFlowDao;


    @Override
    public CigaretteStatistic getTotal(PageHelper pageHelper) {
        return cigaretteFlowDao.getTotal(pageHelper);
    }

    @Override
    public PageHelper<AttributionStatistic> getTotalByAttr(PageHelper pageHelper) {
        return cigaretteFlowDao.getTotalByAttr(pageHelper);
    }

    @Override
    public List<CigaretteFlow> queryAll() throws Exception {
        return cigaretteFlowDao.queryAll();
    }

    @Override
    public PageHelper<CigaretteFlow> queryList(PageHelper<CigaretteFlow> paramPageHelper) throws Exception {
        return cigaretteFlowDao.queryList(paramPageHelper);
    }

    @Override
    public List<CigaretteFlow> queryList(CigaretteFlow paramT) throws Exception {
        return cigaretteFlowDao.queryList(paramT);
    }

    @Override
    public CigaretteFlow get(CigaretteFlow paramT) throws Exception {
        return cigaretteFlowDao.get(paramT);
    }

    @Override
    public Boolean save(CigaretteFlow paramT) throws Exception {
        if (paramT.getId() == null) {
            paramT.setCreateUser(CacheUtils.getAdminLoginName());
            paramT.setCreateTime(new Date());
            paramT.setUpdateUser(CacheUtils.getAdminLoginName());
            paramT.setUpdateTime(new Date());
            return cigaretteFlowDao.save(paramT);
        } else {
            CigaretteFlow cigaretteFlow = cigaretteFlowDao.get(paramT);

            cigaretteFlow.setInvestigateTime(paramT.getInvestigateTime());
            cigaretteFlow.setOrgId(paramT.getOrgId());
            cigaretteFlow.setInvestigateAddress(paramT.getInvestigateAddress());
            cigaretteFlow.setCigaretteAttribution(paramT.getCigaretteAttribution());
            cigaretteFlow.setNumber(paramT.getNumber());
            cigaretteFlow.setPrice(paramT.getPrice());
            cigaretteFlow.setRemark(paramT.getRemark());
            cigaretteFlow.setUpdateUser(CacheUtils.getAdminLoginName());
            cigaretteFlow.setUpdateTime(new Date());
            return cigaretteFlowDao.save(cigaretteFlow);
        }
    }

    @Override
    public Boolean delete(CigaretteFlow paramT) throws Exception {
        return cigaretteFlowDao.delete(paramT);
    }
}
