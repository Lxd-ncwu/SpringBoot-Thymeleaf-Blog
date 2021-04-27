package com.lxd.hander;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * @author : Lxd
 * @date : 17:09 2021/4/5
 * 拦截异常处理 自定义
 */

@ControllerAdvice //拦截所有带Controller注解的控制器
public class ControllerExceptionHandler {
    //将异常记录到日志
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler(Exception.class)  //表明此方法是异常处理方法
    public ModelAndView exceptionHandler(HttpServletRequest request , Exception e)throws Exception{

        //记录异常的信息：请求的地址URL,异常信息
        logger.error("request URL : {}, Exception :{}",request.getRequestURL(),e);

        //如果标识了异常的状态码就不拦截异常
        if (AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class)!=null){
            throw e;
        }

        //将记录的异常信息返回到error页面
        ModelAndView mv = new ModelAndView();
        mv.addObject("url",request.getRequestURL());
        mv.addObject("exception",e);
        mv.setViewName("error/error");
        return mv;
    }
}
