package com.jcy.jcyycback.controller;

import com.jcy.jcyycback.common.service.PageHelper;
import com.jcy.jcyycback.common.service.RespCodeEnum;
import com.jcy.jcyycback.common.service.RespData;
import com.jcy.jcyycback.entity.system.Organization;
import com.jcy.jcyycback.service.OrganizationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author ：Panyu
 * @date ：Created in 2020/11/9 下午 09:10:28
 * @description：组织控制器
 * @modified By：
 */
@RestController
@Api(value = "组织控制器")
@RequestMapping(name = "/sys/org")
@CrossOrigin
public class OrganizationController {

    @Autowired
    private OrganizationService organizationService;


    @ApiOperation("查询单个")
    @PostMapping(value = {"/get"})
    public RespData get(@RequestBody Organization organization) {
        RespData resp = new RespData();
        try {
            organization = organizationService.get(organization);
            resp.setRespCode(RespCodeEnum.SUCCESS);
            resp.setData(organization);
        } catch (Exception e) {
            resp.setRespCode(RespCodeEnum.EXCEPTION);
            resp.setReason(e.getMessage());
        }
        return resp;
    }

    @ApiOperation("保存")
    @PostMapping(value = {"/insert"})
    public RespData insert(@RequestBody Organization organization) {
        RespData resp = new RespData();
        try {
            Boolean result = organizationService.save(organization);
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

    @ApiOperation("修改")
    @PostMapping(value = {"/update"})
    public RespData update(@RequestBody Organization organization) {
        RespData resp = new RespData();
        try {
            Boolean result = organizationService.save(organization);
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

    @ApiOperation("删除")
    @PostMapping(value = {"/delete"})
    public RespData delete(@RequestBody Organization organization) {
        RespData resp = new RespData();
        try {
            Boolean result = organizationService.delete(organization);
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


    @ApiOperation("查询全部")
    @PostMapping(value = {"/queryAll"})
    public RespData queryAll() {
        RespData resp = new RespData();
        try {
            List<Organization> data = organizationService.queryAll();
            resp.setRespCode(RespCodeEnum.SUCCESS);
            resp.setData(data);
        } catch (Exception e) {
            resp.setRespCode(RespCodeEnum.EXCEPTION);
            resp.setReason(e.getMessage());
        }
        return resp;
    }

    @ApiOperation("查询列表")
    @PostMapping(value = {"/queryList"})
    public RespData queryList(@RequestBody Organization organization) {
        RespData resp = new RespData();
        try {
            List<Organization> data = organizationService.queryList(organization);
            resp.setRespCode(RespCodeEnum.SUCCESS);
            resp.setData(data);
        } catch (Exception e) {
            resp.setRespCode(RespCodeEnum.EXCEPTION);
            resp.setReason(e.getMessage());
        }
        return resp;
    }

    @ApiOperation("分页查询")
    @PostMapping(value = {"/queryByPage"})
    public RespData queryByPage(@RequestBody PageHelper<Organization> pageHelper) {
        RespData resp = new RespData();
        try {
            pageHelper = organizationService.queryList(pageHelper);
            resp.setRespCode(RespCodeEnum.SUCCESS);
            resp.setData(pageHelper);
        } catch (Exception e) {
            resp.setRespCode(RespCodeEnum.EXCEPTION);
            resp.setReason(e.getMessage());
        }
        return resp;
    }

}
