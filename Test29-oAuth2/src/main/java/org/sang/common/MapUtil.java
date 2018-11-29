package org.sang.common;

import org.sang.model.Permission;
import org.sang.model.SysRole;
import org.sang.model.SysUser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 模拟数据库数据
 */
public class MapUtil {

    static Map<String, SysUser> userMap = new HashMap<>(10);

    public static SysUser getSysUser(String username) {
        List<SysRole> list1 = new ArrayList<>();
        List<SysRole> list2 = new ArrayList<>();
        SysRole syrole1 = new SysRole(1, "ROLE_ADMIN");
        list1.add(syrole1);
        SysRole syrole2 = new SysRole(2, "ROLE_USER");
        list2.add(syrole2);
        SysUser sysUser1 = new SysUser(1, "root", "root", list1);
        SysUser sysUser2 = new SysUser(1, "sang", "sang", list2);
        userMap.put("root", sysUser1);
        userMap.put("sang", sysUser2);
        return userMap.get(username);
    }

    /**
     * 权限列表
     *
     * @param userid
     * @return
     */
    public static List<Permission> findByAdminUserId(Integer userid) {
        List<Permission> permissions = new ArrayList<>(10);
        permissions.add(new Permission(1, "admin_list", "首页列表", "", 0));
        permissions.add(new Permission(20, "ROLE_ADMIN", "首页列表", "/login", 0));
        permissions.add(new Permission(21, "ROLE_USER", "首页列表", "/abc", 0));
        permissions.add(new Permission(10, "login", "登录", "/login", 0));
        permissions.add(new Permission(11, "abc", "获取权限", "/abc", 0));
        permissions.add(new Permission(2, "admin_query", "首页查询", "", 1));
        permissions.add(new Permission(3, "admin_create", "首页新增", "", 1));
        permissions.add(new Permission(4, "admin_upadte", "首页修改", "", 1));
        permissions.add(new Permission(5, "admin_del", "首页删除", "", 1));
        return permissions;
    }

}
