package cn.mylava.seckill.service;

import cn.mylava.seckill.bean.OrderInfo;
import cn.mylava.seckill.bean.SeckillOrder;
import cn.mylava.seckill.bean.SeckillUser;
import cn.mylava.seckill.dao.OrderInfoMapper;
import cn.mylava.seckill.dao.SeckillOrderMapper;
import cn.mylava.seckill.vo.GoodsVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

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
public class OrderInfoService {

    @Autowired
    private OrderInfoMapper orderInfoMapper;

    @Autowired
    private SeckillOrderMapper seckillOrderMapper;

    public Boolean ifSeckilled(Long userId, Long goodsId) {
        SeckillOrder seckillOrder = orderInfoMapper.getSeckillOrderByUserIdAndGoodsId(userId,goodsId);
        log.info("查询秒杀订单，结果为{}",seckillOrder);
        if (null!=seckillOrder) {
            return true;
        }
        return false;
    }

    @Transactional
    public OrderInfo createSeckillOrder(SeckillUser user, GoodsVo goodsVo) {
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setCreateDate(new Date());
        orderInfo.setDeliveryAddrId(0L);
        orderInfo.setGoodsCount(1);
        orderInfo.setGoodsId(goodsVo.getId());
        orderInfo.setGoodsName(goodsVo.getGoodsName());
        orderInfo.setGoodsPrice(goodsVo.getSeckillPrice());
        orderInfo.setOrderChannel(1);
        orderInfo.setStatus(0);
        orderInfo.setUserId(user.getId());
        long orderId = orderInfoMapper.createOrder(orderInfo);

        SeckillOrder seckillOrder = new SeckillOrder();
        seckillOrder.setGoodsId(goodsVo.getId());
        seckillOrder.setOrderId(orderId);
        seckillOrder.setUserId(user.getId());
        seckillOrderMapper.createSeckillOrder(seckillOrder);

        return orderInfo;
    }
}
