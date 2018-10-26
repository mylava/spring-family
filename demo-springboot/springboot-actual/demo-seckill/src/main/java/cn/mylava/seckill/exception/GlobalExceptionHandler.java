package cn.mylava.seckill.exception;

import cn.mylava.seckill.result.CodeMessage;
import cn.mylava.seckill.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
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
@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public Result<String> exceptionHanlder(HttpServletRequest request, Exception e) {
        if (e instanceof BindException) {
            BindException be = (BindException) e;
            List<ObjectError> errors = be.getAllErrors();
            String[] args = new String[errors.size()];
            for (int i=0;i<errors.size();i++) {
                args[i] = errors.get(i).getDefaultMessage();
            }
            return Result.error(CodeMessage.VALIDATE_ERROR.fillArgs(args));

        } else {
            log.error(e.toString(),e);
            return Result.error(CodeMessage.SERVER_ERROR);
        }

    }
}
