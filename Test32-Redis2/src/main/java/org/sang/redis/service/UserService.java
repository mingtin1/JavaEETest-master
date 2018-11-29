package org.sang.redis.service;

import org.sang.redis.model.User;
import org.sang.redis.redis.RedisKeyPrefix;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;


@Service
public class UserService {
    private final static Logger logger = LoggerFactory.getLogger(UserService.class);

    @Resource
    private RedisTemplate<String, User> redisTemplate;

    /**
     * 通过id查询，如果查询到则进行缓存
     *
     * @param id 实体类id
     * @return 查询到的实现类
     */
    public User findOne(Integer id) {
        String key = RedisKeyPrefix.GIRL + id;
        // 缓存存在
        boolean hasKey = redisTemplate.hasKey(key);
        if (hasKey) { // 从缓存中取
            User user = redisTemplate.opsForValue().get(key);
            return user;
        }
        // 从数据库取，并存回缓存
        User user = new User(1, "admin", 20);
        // 放入缓存，并设置缓存时间
        redisTemplate.opsForValue().set(key, user, 600, TimeUnit.SECONDS);
        return user;
    }

    /**
     * 更新用户
     * 如果缓存存在，删除
     * 如果缓存不存在，不操作
     *
     * @param user 用户
     */
    public void updateUser(User user) {
        logger.info("更新用户start...");
        int userId = user.getId();
        // 缓存存在，删除缓存
        String key = RedisKeyPrefix.GIRL + userId;
        boolean hasKey = redisTemplate.hasKey(key);
        if (hasKey) {
            redisTemplate.delete(key);
            logger.info("更新用户时候，从缓存中删除用户 >> " + userId);
        }
    }

    /**
     * 删除用户
     * 如果缓存中存在，删除
     */
    public void deleteById(int id) {
        logger.info("删除用户start...");
        // 缓存存在，删除缓存
        String key = RedisKeyPrefix.GIRL + id;
        boolean hasKey = redisTemplate.hasKey(key);
        if (hasKey) {
            redisTemplate.delete(key);
            logger.info("删除用户时候，从缓存中删除用户 >> " + id);
        }
    }
}
