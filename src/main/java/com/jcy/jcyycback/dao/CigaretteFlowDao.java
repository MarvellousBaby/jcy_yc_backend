package com.jcy.jcyycback.dao;

import com.jcy.jcyycback.common.service.IBaseService;
import com.jcy.jcyycback.common.service.PageHelper;
import com.jcy.jcyycback.entity.system.AttributionStatistic;
import com.jcy.jcyycback.entity.system.CigaretteFlow;
import com.jcy.jcyycback.entity.system.CigaretteStatistic;

import java.util.List;

/**
 * @author ：Panyu
 * @date ：Created in 2020/11/7 下午 01:25:48
 * @description：香烟外流Dao层
 * @modified By：
 * @version: $
 */
public interface CigaretteFlowDao extends IBaseService<CigaretteFlow> {

    //获取条件内的条数，价格
    CigaretteStatistic getTotal(PageHelper pageHelper);

    //查询香烟归属地及条数
    PageHelper<AttributionStatistic> getTotalByAttr(PageHelper pageHelper);


}
