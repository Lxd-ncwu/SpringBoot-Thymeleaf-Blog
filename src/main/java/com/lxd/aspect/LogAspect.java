package com.lxd.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * @author : Lxd
 * @date : 17:41 2021/4/5
 * 日志切面处理 使用AOP
 * 在访问Controller之前，拦截请求的url ip 调用的方法 传递的参数 返回的内容 并记录到日志
 */
@Aspect
@Component
public class LogAspect {

    private static class RequestLog{
        String url;
        String ip;
        String method;
        Object[] args;

        public RequestLog(String url, String ip, String method, Object[] args) {
            this.url = url;
            this.ip = ip;
            this.method = method;
            this.args = args;
        }

        @Override
        public String toString() {
            return "RequestLog{" +
                    "url='" + url + '\'' +
                    ", ip='" + ip + '\'' +
                    ", method='" + method + '\'' +
                    ", args=" + Arrays.toString(args) +
                    '}';
        }
    }

    //获取日志信息
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    //定义切面 声明log是一个切面
    @Pointcut("execution(* com.lxd.controller.*.*(..))")
    public void log(){
    }

    //在切面之前执行
    @Before("log()")
    public void doBefore(JoinPoint joinPoint){
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes != null) {
            HttpServletRequest request = attributes.getRequest();

            //获取请求的URL和ip
            String url = request.getRequestURL().toString();
            String ip = request.getRemoteAddr();

            //获取请求的方法
            String method = joinPoint.getSignature().getDeclaringTypeName()+"."+joinPoint.getSignature().getName();

            //获取请求参数
            Object[] args = joinPoint.getArgs();
            RequestLog requestLog = new RequestLog(url, ip, method, args);
            logger.info("Request : {}",requestLog);
        }
    }

    //返回之后拦截
    @AfterReturning(returning = "result",pointcut = "log()")
    public void doAfterReturning(Object result){
        logger.info("Result : {}",result);
    }
}
