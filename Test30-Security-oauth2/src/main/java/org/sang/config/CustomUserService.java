package org.sang.config;

import org.sang.common.MapUtil;
import org.sang.model.Permission;
import org.sang.model.SysUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.List;


public class CustomUserService implements UserDetailsService {


    /**
     * 降低学习成本 将jpa方式取消 用假数据代替
     *
     * @param username :登录用户名称
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        SysUser user = MapUtil.getSysUser(username);
        if (user == null) {
            System.out.println(username + "用户名不存在");
            throw new UsernameNotFoundException("用户名不存在");
        }
        List<Permission> permissions = MapUtil.findByAdminUserId(user.getId());
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        for (Permission permission : permissions) {
            if (permission != null && permission.getName() != null) {
                GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(permission.getName());
                //1：此处将权限信息添加到 GrantedAuthority 对象中，在后面进行全权限验证时会使用GrantedAuthority 对象。
                grantedAuthorities.add(grantedAuthority);
            }
        }

        return new User(user.getUsername(), user.getPassword(), grantedAuthorities);
    }
}



