package cn.mylava.seckill.controller;

import cn.mylava.seckill.result.CodeMessage;
import cn.mylava.seckill.result.Result;
import cn.mylava.seckill.service.SeckillUserService;
import cn.mylava.seckill.vo.LoginVo;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

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
public class LoginController {

    @Autowired
    private SeckillUserService seckillUserService;

    @RequestMapping("/login/toLogin")
    public String toLogin(){
        return "login";
    }

    @RequestMapping("/login/doLogin")
    @ResponseBody
    public Result<Boolean> doLogin(HttpServletResponse response, @Valid LoginVo vo){

        log.info(vo.toString());
        /*
        String mobile = vo.getMobile();
        String password = vo.getPassword();
        if (StringUtils.isEmpty(mobile)||StringUtils.isEmpty(password)) {
            return Result.error(CodeMessage.LOGIN_FIELD_EMPT_ERROR);
        }
        if (!ValidateUtil.isMobile(mobile)) {
            return Result.error(CodeMessage.LOGIN_MOBILE_FORMAT_ERROR);
        }*/

        CodeMessage cm = seckillUserService.login(response, vo);
        if (cm.getCode()==0) {
            return Result.success(true);
        } else {
            return Result.error(cm);
        }
    }
}
