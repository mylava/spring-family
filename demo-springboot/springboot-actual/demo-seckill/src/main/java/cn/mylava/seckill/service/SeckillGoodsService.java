package cn.mylava.seckill.service;

import cn.mylava.seckill.bean.Goods;
import cn.mylava.seckill.bean.OrderInfo;
import cn.mylava.seckill.bean.SeckillGoods;
import cn.mylava.seckill.bean.SeckillUser;
import cn.mylava.seckill.dao.GoodsMapper;
import cn.mylava.seckill.dao.SeckillGoodsMapper;
import cn.mylava.seckill.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * comment:
 *
 * @author: lipengfei
 * @date: 30/08/2018
 * @company: (C) Copyright 58BTC 2018
 * @since: JDK 1.8
 * @description:
 */
@Service
public class SeckillGoodsService {
    //通常情况下，不建议引入其他domain的dao，而是引入其他domain的service
    @Autowired
    private SeckillGoodsMapper seckillGoodsMapper;

    @Autowired
    private OrderInfoService orderInfoService;

    @Transactional
    public OrderInfo doSeckill(SeckillUser user, GoodsVo goodsVo) {
        //减去秒杀库存
        SeckillGoods seckillGoods = new SeckillGoods();
        seckillGoods.setGoodsId(goodsVo.getId());
        seckillGoodsMapper.reduceStock(seckillGoods);
        //写订单(两个订单表: 订单表和秒杀订单表都要写)
        OrderInfo orderInfo = orderInfoService.createSeckillOrder(user,goodsVo);
        return orderInfo;
    }

    public List<GoodsVo> listSeckillGoods() {
        return seckillGoodsMapper.listSeckillGoods();
    }
}
