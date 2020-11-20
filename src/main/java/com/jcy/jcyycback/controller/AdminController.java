package com.jcy.jcyycback.controller;

import com.jcy.jcyycback.common.service.PageHelper;
import com.jcy.jcyycback.common.service.RespCodeEnum;
import com.jcy.jcyycback.common.service.RespData;
import com.jcy.jcyycback.entity.system.Admin;
import com.jcy.jcyycback.service.AdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @description:
 * @author: PanYu
 * @create: 2020-11-10 10:14
 **/
@RestController
@CrossOrigin
@Api(value = "用户控制器")
@RequestMapping({"/sys/user"})
@Slf4j
public class AdminController {

    @Autowired
    private AdminService adminService;


    @ApiOperation(value = "登录")
    @PostMapping(name = "/login")
    public RespData login(@RequestBody Admin admin,HttpServletRequest request) {
        RespData resp = new RespData();
        try {
            resp = adminService.login(admin);
            if (resp.getCode().intValue() == 200) {
                request.getSession().setAttribute("admin", resp.getData());
                request.getSession().setAttribute("admin_time", System.currentTimeMillis());
            }


        } catch (Exception e) {
            resp.setRespCode(RespCodeEnum.EXCEPTION);
            resp.setReason(e.getMessage());
        }
        return resp;
    }

    @GetMapping(value = {"/unAdmin"})
    public RespData unAdmin(HttpServletRequest request) {
        RespData resp = new RespData();
        resp.setRespCode(RespCodeEnum.UNADMIN);
        return resp;
    }

    @ApiOperation(value = "新增")
    @PostMapping(name = "/insert")
    public RespData insert(@RequestBody Admin admin) {
        RespData resp = new RespData();
        try {
            if (StringUtils.isBlank(admin.getUserName())){
                log.info("username不能为空！");
                resp.setRespCode(RespCodeEnum.FAIL);
                return resp;
            }

            if (adminService.checkUserName(admin)){
                log.info("username不能重复！");
                resp.setRespCode(RespCodeEnum.FAIL);
                return resp;
            }

            Boolean result = adminService.save(admin);
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

    @ApiOperation(value = "修改")
    @PostMapping(name = "/update")
    public RespData update(@RequestBody Admin admin) {
        RespData resp = new RespData();
        try {
            Boolean result = adminService.save(admin);
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


    @ApiOperation(value = "查询全部")
    @PostMapping(name = "/queryAll")
    public RespData queryAll() {
        RespData resp = new RespData();
        try {
            List<Admin> objects = adminService.queryAll();
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
    public RespData queryList(@RequestBody Admin admin) {
        RespData resp = new RespData();
        try {
            List<Admin> objects = adminService.queryList(admin);
            resp.setRespCode(RespCodeEnum.SUCCESS);
            resp.setData(objects);

        } catch (Exception e) {
            resp.setRespCode(RespCodeEnum.EXCEPTION);
            resp.setReason(e.getMessage());
        }
        return resp;
    }

    @ApiOperation(value = "查询详情")
    @PostMapping(name = "/get")
    public RespData get(@RequestBody Admin admin) {
        RespData resp = new RespData();
        try {
            admin = adminService.get(admin);
            resp.setRespCode(RespCodeEnum.SUCCESS);
            resp.setData(admin);

        } catch (Exception e) {
            resp.setRespCode(RespCodeEnum.EXCEPTION);
            resp.setReason(e.getMessage());
        }
        return resp;
    }

    @ApiOperation(value = "分页查询")
    @PostMapping(name = "/queryByPage")
    public RespData queryByPage(@RequestBody PageHelper<Admin> pageHelper) {
        RespData resp = new RespData();
        try {
            pageHelper = adminService.queryList(pageHelper);
            resp.setRespCode(RespCodeEnum.SUCCESS);
            resp.setData(pageHelper);

        } catch (Exception e) {
            resp.setRespCode(RespCodeEnum.EXCEPTION);
            resp.setReason(e.getMessage());
        }
        return resp;
    }


    @ApiOperation(value = "修改密码")
    @PostMapping(name = "/updatePwd")
    public RespData updatePwd(@RequestBody Admin admin) {
        RespData resp = new RespData();
        try {
            Boolean result = adminService.updatePwd(admin);
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

    @ApiOperation(value = "删除")
    @PostMapping(name = "/delete")
    public RespData delete(@RequestBody Admin admin) {
        RespData resp = new RespData();
        try {
            if (admin.getId() == null) {
                resp.setRespCode(RespCodeEnum.PARAM_FAIL);
                return resp;
            }
            if (StringUtils.isNotBlank(admin.getUserName()) && "admin".equalsIgnoreCase(admin.getUserName())) {
                resp.setRespCode(RespCodeEnum.FAIL);
                resp.setReason("admin不能删除");
                return resp;
            }
            Boolean result = adminService.delete(admin);
            if (result.booleanValue()) {
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
