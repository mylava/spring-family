package cn.mylava.seckill.redis;

/**
 * comment:
 *
 * @author: lipengfei
 * @date: 23/08/2018
 * @company: (C) Copyright 58BTC 2018
 * @since: JDK 1.8
 * @description:
 */
public class UserKey extends BasePrefix{

    //默认token过期时间为2天
    private static final int TOKEN_EXPIRE = 3600*24*2;

    private UserKey(String prefix) {
        super(TOKEN_EXPIRE,prefix);
    }

    public static final UserKey PREFIX_TOKEN = new UserKey("token");
}
