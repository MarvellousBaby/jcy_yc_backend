package com.jcy.jcyycback.common.utils;

import com.jcy.jcyycback.entity.system.Admin;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @description:
 * @author: PanYu
 * @create: 2020-11-06 16:51
 **/
public class CacheUtils {
    private static final Map<String, Object> map = new HashMap<>();

    public static void put(String key, Object obj) {
        map.put(key, obj);
    }

    public static Object get(String key) {
        return map.get(key);
    }

    public static <T> T get(String key, Class<T> clazz) {
        return clazz.cast(get(key));
    }

    public static void remove(String key) {
        map.remove(key);
    }

    public static String getAdminLoginName() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes == null)
            return null;
        HttpServletRequest request = attributes.getRequest();
        if (request == null)
            return null;
        Admin admin = (Admin)request.getSession().getAttribute("admin");
        if (admin == null)
            return null;
        return admin.getUserName();
    }

    public static Admin getAdmin() {
        ServletRequestAttributes attributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
        if (attributes == null)
            return null;
        HttpServletRequest request = attributes.getRequest();
        if (request == null)
            return null;
        Admin admin = (Admin)request.getSession().getAttribute("admin");
        if (admin == null)
            return null;
        return admin;
    }
}
