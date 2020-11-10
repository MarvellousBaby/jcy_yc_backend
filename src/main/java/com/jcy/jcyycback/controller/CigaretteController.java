package com.jcy.jcyycback.controller;

import com.jcy.jcyycback.common.service.PageHelper;
import com.jcy.jcyycback.common.service.RespCodeEnum;
import com.jcy.jcyycback.common.service.RespData;
import com.jcy.jcyycback.entity.system.Admin;
import com.jcy.jcyycback.entity.system.AttributionStatistic;
import com.jcy.jcyycback.entity.system.CigaretteFlow;
import com.jcy.jcyycback.entity.system.CigaretteStatistic;
import com.jcy.jcyycback.service.CigaretteFlowService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @description:
 * @author: PanYu
 * @create: 2020-11-10 10:59
 **/
@RestController
@CrossOrigin
@Api(value = "香烟控制器")
@RequestMapping({"/sys/cigar"})
@Slf4j
public class CigaretteController {

    @Autowired
    private CigaretteFlowService cigaretteFlowService;

    @ApiOperation(value = "获取条件内的条数，价格")
    @PostMapping(name = "/getTotal")
    public RespData queryList(@RequestBody PageHelper pageHelper) {
        RespData resp = new RespData();
        try {
            CigaretteStatistic cigaretteStatistic = cigaretteFlowService.getTotal(pageHelper);
            resp.setRespCode(RespCodeEnum.SUCCESS);
            resp.setData(cigaretteStatistic);

        } catch (Exception e) {
            resp.setRespCode(RespCodeEnum.EXCEPTION);
            resp.setReason(e.getMessage());
        }
        return resp;
    }

    @ApiOperation(value = "查询香烟归属地及条数")
    @PostMapping(name = "/getTotalByAttr")
    public RespData getTotalByAttr(@RequestBody PageHelper pageHelper) {
        RespData resp = new RespData();
        try {
            PageHelper<AttributionStatistic> attr = cigaretteFlowService.getTotalByAttr(pageHelper);
            resp.setRespCode(RespCodeEnum.SUCCESS);
            resp.setData(attr);

        } catch (Exception e) {
            resp.setRespCode(RespCodeEnum.EXCEPTION);
            resp.setReason(e.getMessage());
        }
        return resp;
    }

    @ApiOperation(value = "查询全部")
    @PostMapping(name = "/queryAll")
    public RespData queryAll() {
        RespData resp = new RespData();
        try {
            List<CigaretteFlow> objects = cigaretteFlowService.queryAll();
            resp.setRespCode(RespCodeEnum.SUCCESS);
            resp.setData(objects);

        } catch (Exception e) {
            resp.setRespCode(RespCodeEnum.EXCEPTION);
            resp.setReason(e.getMessage());
        }
        return resp;
    }

    @ApiOperation(value = "查询列表")
    @PostMapping(name = "/queryList")
    public RespData queryList(@RequestBody CigaretteFlow cigaretteFlow) {
        RespData resp = new RespData();
        try {
            List<CigaretteFlow> objects = cigaretteFlowService.queryList(cigaretteFlow);
            resp.setRespCode(RespCodeEnum.SUCCESS);
            resp.setData(objects);

        } catch (Exception e) {
            resp.setRespCode(RespCodeEnum.EXCEPTION);
            resp.setReason(e.getMessage());
        }
        return resp;
    }

    @ApiOperation(value = "分页查询")
    @PostMapping(name = "/queryByPage")
    public RespData queryByPage(@RequestBody PageHelper<CigaretteFlow> pageHelper) {
        RespData resp = new RespData();
        try {
            pageHelper = cigaretteFlowService.queryList(pageHelper);
            resp.setRespCode(RespCodeEnum.SUCCESS);
            resp.setData(pageHelper);

        } catch (Exception e) {
            resp.setRespCode(RespCodeEnum.EXCEPTION);
            resp.setReason(e.getMessage());
        }
        return resp;
    }

    @ApiOperation(value = "修改")
    @PostMapping(name = "/update")
    public RespData update(@RequestBody CigaretteFlow cigaretteFlow) {
        RespData resp = new RespData();
        try {
            Boolean result = cigaretteFlowService.save(cigaretteFlow);
            if (result) {
                resp.setRespCode(RespCodeEnum.SUCCESS);
            } else {
                resp.setRespCode(RespCodeEnum.FAIL);
            }

        } catch (Exception e) {
            resp.setRespCode(RespCodeEnum.EXCEPTION);
            resp.setReason(e.getMessage());
        }
        return resp;
    }

    @ApiOperation(value = "新增")
    @PostMapping(name = "/insert")
    public RespData insert(@RequestBody CigaretteFlow cigaretteFlow) {
        RespData resp = new RespData();
        try {
            Boolean result = cigaretteFlowService.save(cigaretteFlow);
            if (result) {
                resp.setRespCode(RespCodeEnum.SUCCESS);
            } else {
                resp.setRespCode(RespCodeEnum.FAIL);
            }

        } catch (Exception e) {
            resp.setRespCode(RespCodeEnum.EXCEPTION);
            resp.setReason(e.getMessage());
        }
        return resp;
    }

    @ApiOperation(value = "查询单个")
    @PostMapping(name = "/get")
    public RespData get(@RequestBody CigaretteFlow cigaretteFlow) {
        RespData resp = new RespData();
        try {
            cigaretteFlow = cigaretteFlowService.get(cigaretteFlow);
            resp.setRespCode(RespCodeEnum.SUCCESS);
            resp.setData(cigaretteFlow);

        } catch (Exception e) {
            resp.setRespCode(RespCodeEnum.EXCEPTION);
            resp.setReason(e.getMessage());
        }
        return resp;
    }

    @ApiOperation(value = "删除")
    @PostMapping(name = "/delete")
    public RespData delete(@RequestBody CigaretteFlow cigaretteFlow) {
        RespData resp = new RespData();
        try {
            Boolean result = cigaretteFlowService.delete(cigaretteFlow);
            if (result) {
                resp.setRespCode(RespCodeEnum.SUCCESS);
            } else {
                resp.setRespCode(RespCodeEnum.FAIL);
            }

        } catch (Exception e) {
            resp.setRespCode(RespCodeEnum.EXCEPTION);
            resp.setReason(e.getMessage());
        }
        return resp;
    }


}
