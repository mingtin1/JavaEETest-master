package org.sang.common;

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
        SysRole syrole1 = new SysRole(1L, "ROLE_ADMIN");
        list1.add(syrole1);
        SysRole syrole2 = new SysRole(2L, "ROLE_USER");
        list2.add(syrole2);
        SysUser sysUser1 = new SysUser(1L, "root", "root", list1);
        SysUser sysUser2 = new SysUser(1L, "sang", "sang", list2);
        userMap.put("root", sysUser1);
        userMap.put("sang", sysUser2);
        return userMap.get(username);
    }
}
