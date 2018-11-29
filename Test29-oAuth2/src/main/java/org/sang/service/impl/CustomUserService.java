package org.sang.service.impl;


import org.sang.common.MapUtil;
import org.sang.model.Permission;
import org.sang.model.SysUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

//自定义userdetailservice
@Service
public class CustomUserService implements UserDetailsService {


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        //根据用户名称查询对应用户是否存在
        SysUser sysUser = MapUtil.getSysUser(username);

        List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
        if (sysUser != null) {
            System.err.println("id===============" + sysUser.getId());
            //获取用户的授权 1:根据用户角色 获取到对应的用户权限 这里使用假数据进行

            List<Permission> permissions = MapUtil.findByAdminUserId(sysUser.getId());
            //声明授权文件
            for (Permission permission : permissions) {
                if (permission != null && permission.getName() != null) {
                    GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(permission.getName());
                    grantedAuthorities.add(grantedAuthority);
                }
            }

        }
        return new User(sysUser.getName(), sysUser.getPassword(), grantedAuthorities);
    }
}
