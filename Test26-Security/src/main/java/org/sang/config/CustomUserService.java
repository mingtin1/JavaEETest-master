package org.sang.config;

import org.sang.common.MapUtil;
import org.sang.model.SysUser;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


/**
 *
 */
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
        System.out.println("username:" + username);
        System.out.println("username:" + user.getUsername() + ";password:" + user.getPassword());
        return user;
    }
}
