package cn.mylava.seckill.controller;

import cn.mylava.seckill.bean.SeckillUser;
import cn.mylava.seckill.redis.RedisService;
import cn.mylava.seckill.service.GoodsService;
import cn.mylava.seckill.service.SeckillUserService;
import cn.mylava.seckill.vo.GoodsVo;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
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
public class GoodsController {
    @Autowired
    private SeckillUserService seckillUserService;

    @Autowired
    private RedisService redisService;

    @Autowired
    private GoodsService goodsService;


    @RequestMapping("/goods/to_detail/{goodsId}")
    public String detail(HttpServletResponse response, Model model,
                         SeckillUser user, @PathVariable("goodsId") Long goodsId) {
        if (null == user) {
            return "login";
        }
        model.addAttribute("user", user);

        GoodsVo goodsVo = goodsService.getGoodsVoById(goodsId);
        model.addAttribute("goodsVo",goodsVo);
        log.info(goodsVo.toString());
        log.info(goodsVo.getStartDate().toString());
        long startAt = goodsVo.getStartDate().getTime();
        long endAt = goodsVo.getEndDate().getTime();
        long now = System.currentTimeMillis();

        int state = 0; //秒杀状态
        int remainSeconds = 0; //距秒杀开始还有多少秒
//        int remainSeconds = 0;
        //秒杀尚未开始
        if (now<startAt) {
            state = 0;
            remainSeconds = (int) ((startAt-now)/1000);
        } else if (now>endAt) {//秒杀结束
            state = 2;
            remainSeconds = -1;
        } else {//秒杀进行中
            state = 1;
            remainSeconds = 0;
        }

        model.addAttribute("state", state);
        model.addAttribute("remainSeconds", remainSeconds);
        return "goods_detail";
    }

}
