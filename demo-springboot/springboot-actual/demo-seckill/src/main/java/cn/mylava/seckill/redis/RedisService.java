package cn.mylava.seckill.redis;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * comment:
 *
 * @author: lipengfei
 * @date: 21/08/2018
 */
@Service
public class RedisService {

    @Autowired
    private JedisPool jedisPool;

    /**
     * 取数据
     * @param prefix
     * @param key
     * @param clazz
     * @param <T>
     * @return
     */
    public <T> T get(KeyPrefix prefix, String key, Class<T> clazz) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            String str = jedis.get(prefix.getPrefix()+"."+key);
            T t = string2Bean(str, clazz);
            return  t;
        }finally {
            if (null != jedis) {
                jedis.close();
            }
        }
    }


    /**
     * 存数据
     * @param prefix
     * @param key
     * @param value
     * @param <T>
     * @return
     */
    public <T> String set(KeyPrefix prefix, String key, T value) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            String str = bean2String(value);
            if (null == str) {
                return "";
            }
            String result = jedis.setex(prefix.getPrefix()+"."+key, prefix.getExpire(), str);
            return  result;
        }finally {
            if (null != jedis) {
                jedis.close();
            }
        }
    }

    /**
     * 判断key是否存在
     * @param prefix
     * @param key
     * @return
     */
    public Boolean exists(KeyPrefix prefix, String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.exists(prefix.getPrefix()+"."+key);
        }finally {
            if (null != jedis) {
                jedis.close();
            }
        }
    }

    /**
     * 递增
     * @param prefix
     * @param key
     * @return
     */
    public Long incr(KeyPrefix prefix, String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            Long incr = jedis.incr(prefix.getPrefix() + "." + key);
            return  incr;
        }finally {
            if (null != jedis) {
                jedis.close();
            }
        }
    }

    /**
     * 递减
     * @param prefix
     * @param key
     * @return
     */
    public Long decr(KeyPrefix prefix, String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            Long incr = jedis.decr(prefix.getPrefix() + "." + key);
            return  incr;
        }finally {
            if (null != jedis) {
                jedis.close();
            }
        }
    }

    /**
     * 更新过期时间
     * @param prefix
     * @param key
     * @return
     */
    public void expire(KeyPrefix prefix, String key) {
        if (exists(prefix,key)) {
            Jedis jedis = null;
            try {
                jedis = jedisPool.getResource();
                jedis.expire(prefix.getPrefix() + "." + key, prefix.getExpire());
            } finally {
                if (null != jedis) {
                    jedis.close();
                }
            }
        }
    }

    private <T> String bean2String(T value) {
        if (null == value) {
            return null;
        }
        return JSON.toJSONString(value);
    }

    private <T> T string2Bean(String str, Class<T> clazz) {
        if (null == str || str.length()<=0 || null == clazz) {
            return null;
        }
        return  JSON.toJavaObject(JSON.parseObject(str),clazz);
    }


}
