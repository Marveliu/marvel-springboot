package com.marveliu.web.aop;

import com.marveliu.web.annotation.SLog;
import com.marveliu.web.utils.AsyncUtil;
import com.marveliu.web.utils.SLogUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.expression.BeanFactoryResolver;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Marveliu
 * @Date 2018/9/17 下午10:39
 **/

@Component
@Aspect
public class SLogAspect implements BeanFactoryAware {

    private static final Logger logger = LoggerFactory.getLogger(SLogAspect.class);


    @Autowired
    private SLogUtil sLogUtil;

    private BeanFactory beanFactory;

    ExpressionParser parser = new SpelExpressionParser();
    LocalVariableTableParameterNameDiscoverer discoverer = new LocalVariableTableParameterNameDiscoverer();


    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }


    @Pointcut("execution(@cn.hyperchain.web.annotaion.SLog * *(..))")
    public void SLogAspect() {
    }


    @Around("SLogAspect()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        SLog sLog = ((MethodSignature) joinPoint.getSignature()).getMethod().getAnnotation(SLog.class);
        EvaluationContext ctx = new StandardEvaluationContext();
        ((StandardEvaluationContext) ctx).setBeanResolver(new BeanFactoryResolver(beanFactory));
        ctx.setVariable("args", joinPoint.getArgs());
        ctx.setVariable("return", joinPoint.proceed());
        ctx.setVariable("req", ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest());
        ctx.setVariable("resp", ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse());
        // 对Log进行处理 el表达式
        Expression expression = parser.parseExpression(sLog.msg());
        String elLog = expression.getValue().toString();
        // 异步进行日志记录
        sLogUtil.makeLogs(sLog, elLog, joinPoint.getSignature().getClass().getName() + "." + ((MethodSignature) joinPoint.getSignature()).getMethod().getName());
        return joinPoint.proceed();
    }

    /**
     * 异常通知
     *
     * @param joinPoint
     * @param e
     */
    @AfterThrowing(pointcut = "SLogAspect()", throwing = "e")
    public void doAfterThrowing(JoinPoint joinPoint, Throwable e) {
        // todo:异常记录
    }

}
