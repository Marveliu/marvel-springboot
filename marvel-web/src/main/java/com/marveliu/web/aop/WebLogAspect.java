package com.marveliu.web.aop;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * @Author Marveliu
 * @Date 2018/7/18 8:22 PM
 **/

@Component
@Aspect
public class WebLogAspect {

    private static final Logger logger = LoggerFactory.getLogger(WebLogAspect.class);

    private static final String searchString = "com.marveliu.web";

    ThreadLocal<StringBuilder> logs = new ThreadLocal<>();

    @Pointcut("execution(public * com.marveliu.web.controller..*(..))")
    public void webLog() {
    }

    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        Object[] arr = joinPoint.getArgs();
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        // 记录下请求内容
        StringBuilder str = new StringBuilder();
        logs.set(str);
        logs.get().append(String.format("请求地址:[%s],HTTP METHOD:[%s],IP:[%s],CLASS_METHOD:[%s],参数:%s,",
                request.getRequestURL().toString(),
                request.getMethod(),
                request.getRemoteAddr(),
                joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName(),
                StringUtils.abbreviate(Arrays.toString(joinPoint.getArgs()), 100)
        ));
    }


    @Around("webLog()")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
        long startTime = System.currentTimeMillis();
        // ob 为方法的返回值
        Object ob = pjp.proceed();
        logs.get().append(String.format("返回值:%s,耗时:%d",
                StringUtils.abbreviate(ob.toString(), 100),
                System.currentTimeMillis() - startTime));
        logger.info(StringUtils.replace(logs.get().toString(), searchString, "*"));
        return ob;
    }


}
