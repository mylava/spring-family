package cn.mylava.seckill.service;

import cn.mylava.seckill.bean.SeckillUser;
import cn.mylava.seckill.dao.SeckillUserMapper;
import cn.mylava.seckill.redis.RedisService;
import cn.mylava.seckill.redis.UserKey;
import cn.mylava.seckill.result.CodeMessage;
import cn.mylava.seckill.util.MD5Util;
import cn.mylava.seckill.util.UUIDUtil;
import cn.mylava.seckill.vo.LoginVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

/**
 * comment:
 *
 * @author: lipengfei
 * @date: 23/08/2018
 * @company: (C) Copyright 58BTC 2018
 * @since: JDK 1.8
 * @description:
 */
@Slf4j
@Service
public class SeckillUserService {
    public static final String COOKIE_NAME_TOKEN = "token";

    @Autowired
    private SeckillUserMapper seckillUserMapper;

    @Autowired
    private RedisService redisService;

    public SeckillUser getById(long id) {
        return seckillUserMapper.getById(id);
    }

    /**
     * 登录
     * @param response
     * @param vo
     * @return
     */
    public CodeMessage login(HttpServletResponse response, LoginVo vo) {
        if (null == vo) {
            return CodeMessage.LOGIN_FIELD_EMPT_ERROR;
        }
        SeckillUser seckillUser = getById(Long.parseLong(vo.getMobile()));
        if (null == seckillUser) {
            return CodeMessage.LOGIN_MOBILE_NOT_EXIST_ERROR;
        }
        String formPass = vo.getPassword();
        log.info("formpass:"+formPass);
        String dbSalt = seckillUser.getSalt();
        String pass = MD5Util.MD5Form(formPass, dbSalt);
        log.info("pass:"+pass);
        log.info("seckillUser.getPassword():"+seckillUser.getPassword());
        if (!pass.equals(seckillUser.getPassword())) {
            return CodeMessage.LOGIN_INCORRECT_PASSWORD_ERROR;
        }
        addCookie(response,seckillUser);
        return CodeMessage.SUCCESS;
    }

    /**
     * 新增cookie
     * @param response
     * @param seckillUser
     */
    private void addCookie(HttpServletResponse response,SeckillUser seckillUser) {
        String token = UUIDUtil.uuid();
        redisService.set(UserKey.PREFIX_TOKEN,token,seckillUser);
        Cookie cookie = new Cookie(COOKIE_NAME_TOKEN,token);
        cookie.setMaxAge(UserKey.PREFIX_TOKEN.getExpire());
        cookie.setPath("/");
        response.addCookie(cookie);
    }

    /**
     * 更新cookie过期时间
     * @param response
     * @param tokenKey
     */
    private void expireCookie(HttpServletResponse response, String tokenKey) {
        redisService.expire(UserKey.PREFIX_TOKEN,tokenKey);
        Cookie cookie = new Cookie(COOKIE_NAME_TOKEN,tokenKey);
        cookie.setMaxAge(UserKey.PREFIX_TOKEN.getExpire());
        cookie.setPath("/");
         response.addCookie(cookie);
    }

    /**
     * 通过token获取user，如果存在则更新缓存和cookie
     * @param response
     * @param tokenKey
     * @return
     */
    public SeckillUser getByToken(HttpServletResponse response, String tokenKey) {
        if (StringUtils.isEmpty(tokenKey)) {
            return null;
        }
        SeckillUser seckillUser = redisService.get(UserKey.PREFIX_TOKEN, tokenKey, SeckillUser.class);
        if (null!=seckillUser) {
            expireCookie(response, tokenKey);
        }
        return seckillUser;
    }


}
