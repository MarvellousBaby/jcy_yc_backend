package com.jcy.jcyycback.service.impl;

import com.jcy.jcyycback.common.service.PageHelper;
import com.jcy.jcyycback.dao.CigaretteFlowDao;
import com.jcy.jcyycback.entity.system.CigaretteFlow;
import com.jcy.jcyycback.service.CigaretteFlowService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author ：Panyu
 * @date ：Created in 2020/11/7 下午 01:20:19
 * @description：香烟外流服务层实现类
 * @modified By：
 */
public class CigaretteFlowServiceImpl implements CigaretteFlowService {

    @Autowired
    private CigaretteFlowDao cigaretteFlowDao;

    @Override
    public List<CigaretteFlow> queryAll() throws Exception {
        return null;
    }

    @Override
    public PageHelper<CigaretteFlow> queryList(PageHelper<CigaretteFlow> paramPageHelper) throws Exception {
        return cigaretteFlowDao.queryList(paramPageHelper);
    }

    @Override
    public List<CigaretteFlow> queryList(CigaretteFlow paramT) throws Exception {
        return null;
    }

    @Override
    public CigaretteFlow get(CigaretteFlow paramT) throws Exception {
        return null;
    }

    @Override
    public Boolean save(CigaretteFlow paramT) throws Exception {
        return null;
    }

    @Override
    public Boolean delete(CigaretteFlow paramT) throws Exception {
        return cigaretteFlowDao.delete(paramT);
    }
}
