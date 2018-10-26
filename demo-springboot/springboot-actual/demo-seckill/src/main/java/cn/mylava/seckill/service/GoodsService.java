package cn.mylava.seckill.service;

import cn.mylava.seckill.dao.GoodsMapper;
import cn.mylava.seckill.redis.RedisService;
import cn.mylava.seckill.vo.GoodsVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
public class GoodsService {

    @Autowired
    private GoodsMapper goodsMapper;

    @Autowired
    private RedisService redisService;


    public GoodsVo getGoodsVoById(Long id) {
        return goodsMapper.getGoodsVoById(id);
    }

}
