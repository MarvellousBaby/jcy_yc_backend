package com.jcy.jcyycback.interceptor;

import com.jcy.jcyycback.common.utils.CacheUtils;
import com.jcy.jcyycback.common.utils.DateTool;
import com.jcy.jcyycback.entity.system.Admin;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @description:
 * @author: PanYu
 * @create: 2020-11-06 16:48
 **/

@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        HttpSession session = httpServletRequest.getSession();
        Admin admin = (Admin)session.getAttribute("admin");
        if (admin!=null&&sessionTimeOut(session)){
            return true;
        }
        httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/sys/user/unAdmin");
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {}

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {}

    private Boolean sessionTimeOut(HttpSession session) {
        long lastTime = ((Long)session.getAttribute("admin_time")).longValue();
        long currTime = DateTool.getCurrentLongTime().longValue();
        if (currTime - lastTime > (30 * 60 * 1000))
            return Boolean.valueOf(true);
        return Boolean.valueOf(false);
    }

}
