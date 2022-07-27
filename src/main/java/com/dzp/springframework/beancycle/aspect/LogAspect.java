package com.dzp.springframework.beancycle.aspect;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@EnableAspectJAutoProxy
@Component
public class LogAspect {


    @Pointcut("execution(* com.dzp.springframework.beancycle.service.*.*(..))")
    private void pointCutMethod() {
    }

    /**
     * 环绕通知
     *
     * @param pjp
     * @return
     * @throws Throwable
     */
    @Around("pointCutMethod()")
    public Object logService(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("------------------------------");
        Method method = ((MethodSignature) pjp.getSignature()).getMethod();
        System.out.println("环绕通知进入：" + method.getName());
        Object proceed = pjp.proceed();
        System.out.println("环绕通知退出：" + method.getName());
        return proceed;
    }


    /**
     * 前置通知.
     */
    @Before("pointCutMethod()")
    public void doBefore() {
        System.out.println("前置通知");
    }


    /**
     * 后置通知.
     *
     * @param result return val
     */
    @AfterReturning(pointcut = "pointCutMethod()", returning = "result")
    public void doAfterReturning(String result) {
        System.out.println("后置通知, 返回值: " + result);
    }

    /**
     * 异常通知.
     *
     * @param e exception
     */
    @AfterThrowing(pointcut = "pointCutMethod()", throwing = "e")
    public void doAfterThrowing(Exception e) {
        System.out.println("异常通知, 异常: " + e.getMessage());
    }

    /**
     * 最终通知.
     */
    @After("pointCutMethod()")
    public void doAfter() {
        System.out.println("最终通知");
    }


}
