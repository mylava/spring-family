package cn.mylava.seckill.controller;

import cn.mylava.seckill.bean.OrderInfo;
import cn.mylava.seckill.bean.SeckillUser;
import cn.mylava.seckill.redis.RedisService;
import cn.mylava.seckill.result.CodeMessage;
import cn.mylava.seckill.service.GoodsService;
import cn.mylava.seckill.service.OrderInfoService;
import cn.mylava.seckill.service.SeckillGoodsService;
import cn.mylava.seckill.vo.GoodsVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
@Controller
public class SeckillGoodsController {


    @Autowired
    private RedisService redisService;

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private OrderInfoService orderInfoService;

    @Autowired
    private SeckillGoodsService seckillGoodsService;

    @RequestMapping("/seckillGoods/buy")
    public String buy(Model model,
                      SeckillUser user, @RequestParam("goodsId") long goodsId) {
        //用户是否登录
        if (null == user) {
            return "login";
        }
        //是否为秒杀商品
        GoodsVo goodsVo = goodsService.getGoodsVoById(goodsId);
        if (null == goodsVo) {
            model.addAttribute("error", CodeMessage.SECKILL_GOODS_INCORRECT_ERROR.getMessage());
            return "seckill_fail";
        }
        //秒杀商品是否有库存
        if (goodsVo.getStockCount()<=0) {
            model.addAttribute("error", CodeMessage.SECKILL_GOODS_OVER_ERROR.getMessage());
            return "seckill_fail";
        }
        //是否已经秒杀到该商品
        Boolean seckilled = orderInfoService.ifSeckilled(user.getId(), goodsId);
        if (seckilled) {
            model.addAttribute("error", CodeMessage.SECKILL_DUPLICATE_ERROR.getMessage());
            return "seckill_fail";
        }

        //减库存、写入订单、写入秒杀订单   这三步是一个事务操作

        OrderInfo orderInfo = seckillGoodsService.doSeckill(user,goodsVo);
        model.addAttribute("orderInfo", orderInfo);
        model.addAttribute("goodsVo", goodsVo);

         return "order_detail";
    }

    @RequestMapping("/seckillGoods/list")
    public String toGoodsList(Model model,
//                              @CookieValue(value = SeckillUserService.COOKIE_NAME_TOKEN, required=false) String cookieToken,
//                              @RequestParam(value = SeckillUserService.COOKIE_NAME_TOKEN,required = false) String paramToken,
                              SeckillUser seckillUser) {
        if (null == seckillUser) {
            return "login";
        }
//        model.addAttribute("seckillUser", seckillUser);

        List<GoodsVo> goodsList = seckillGoodsService.listSeckillGoods();
        model.addAttribute("goodsList",goodsList);
        return "goods_list";
    }


}
