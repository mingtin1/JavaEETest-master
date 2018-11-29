package org.sang.common;

import org.sang.model.Permission;
import org.sang.model.SysRole;
import org.sang.model.SysUser;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;

import java.util.*;

/**
 * 模拟数据库数据
 */
public class MapUtil {


    public static SysUser getSysUser(String username) {
        Map<String, SysUser> userMap = getStringSysUserMap();
        return userMap.get(username);
    }

    private static Map<String, SysUser> getStringSysUserMap() {
        Map<String, SysUser> userMap = new HashMap<>(10);
        List<SysRole> list1 = new ArrayList<>();
        List<SysRole> list2 = new ArrayList<>();
        SysRole syrole1 = new SysRole(1L, "ROLE_ADMIN");
        list1.add(syrole1);
        SysRole syrole2 = new SysRole(2L, "ROLE_USER");
        list2.add(syrole2);
        SysUser sysUser1 = new SysUser(1L, "root", "123456", list1);
        SysUser sysUser2 = new SysUser(2L, "sang", "123456", list2);
        userMap.put("root", sysUser1);
        userMap.put("sang", sysUser2);
        return userMap;
    }

    public static Map findAllPermission() {
        Map map = new HashMap<>();
        Permission permission1 = new Permission(1L, "ROLE_HOME", "home", "/", null);
        Permission permission2 = new Permission(2L, "ROLE_ADMIN", "ABel", "/admin", null);
        List<Permission> permissions = new ArrayList<>(10);
        permissions.add(permission1);
        permissions.add(permission2);
        Collection<ConfigAttribute> array;
        ConfigAttribute cfg;
        for (Permission permission : permissions) {
            array = new ArrayList<>();
            cfg = new SecurityConfig(permission.getName());
            //此处只添加了用户的名字，其实还可以添加更多权限的信息，例如请求方法到ConfigAttribute的集合中去。此处添加的信息将会作为MyAccessDecisionManager类的decide的第三个参数。
            array.add(cfg);
            //用权限的getUrl() 作为map的key，用ConfigAttribute的集合作为 value，
            map.put(permission.getUrl(), array);
        }
        return map;
    }

    public static List<Permission> findByAdminUserId(Long id) {
        Map<Long, List<Permission>> permissionListMap = new HashMap<>(10);
        List<Permission> PermissionList1 = new ArrayList<>();
        List<Permission> PermissionList2 = new ArrayList<>();

        Permission permission1 = new Permission(1L, "ROLE_HOME", "home", "/", null);
        Permission permission2 = new Permission(2L, "ROLE_ADMIN", "ABel", "/admin", null);

        PermissionList1.add(permission1);
        PermissionList1.add(permission2);
        PermissionList2.add(permission1);

        permissionListMap.put(1L, PermissionList1);
        permissionListMap.put(2L, PermissionList2);

        return permissionListMap.get(id);
    }
}
