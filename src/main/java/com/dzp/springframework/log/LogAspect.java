package com.dzp.springframework.log;


import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.dzp.springframework.log.model.OperLog;
import com.dzp.springframework.log.model.OperLogPO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

/**
 * 功能描述: 请求日志切面类.
 *
 * @version: V1.0
 * @classname: com.sunshine.buddhistlife.component.log.LogAspect.java
 * @copyright: Powered By sunshine
 * @author: somnus
 * @date: 2020-11-25 16:02:59
 */
@Slf4j
@Aspect
@Component
public class LogAspect {

    /**
     * 切点(controller包及其子包所有类 和 以Controller结尾的类).
     */
    @Pointcut("@annotation(com.dzp.springframework.log.model.OperLog)")
    public void requestServer() {
    }

    /**
     * 环绕通知.
     * @return 日志输出
     */
    @Around("requestServer()")
    public Object doAround(ProceedingJoinPoint point) throws Throwable {
        StopWatch watch = new StopWatch();
        watch.start();

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = Objects.requireNonNull(attributes).getRequest();
        Object result = point.proceed();
        watch.stop();

        final OperLogPO operLog = assembleField(request, point);
        operLog.setOperRespParam(JSON.toJSONString(result));
        operLog.setOperCost(watch.getTotalTimeMillis());
        operLog.setOperSuccess(ReqStatus.SUCCESS.code);

        log.info("【Request Info】：{}", JSON.toJSONString(operLog));
        return result;
    }

    /**
     * 异常通知.
     * @param point 连接点
     * @param ex 异常信息
     */
    @AfterThrowing(pointcut = "requestServer()", throwing = "ex")
    public void doAfterThrow(JoinPoint point, RuntimeException ex) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = Objects.requireNonNull(attributes).getRequest();

        final OperLogPO operLog = assembleField(request, point);
        operLog.setOperRespParam(Throwables.getStackTraceAsString(ex));
        operLog.setOperSuccess(ReqStatus.ERROR.code);

        log.error("【Error Info】：{}", JSON.toJSONString(operLog));
    }

    private Map<String, String> getRequestHeaders(HttpServletRequest request) {
        //获取所有请求头键
        Enumeration<String> headerNames = request.getHeaderNames();
        Map<String, String> headerMap = new HashMap<>(8);
        while (headerNames.hasMoreElements()) {
            String name = headerNames.nextElement();
            String value = request.getHeader(name);
            headerMap.put(name, value);
        }
        return headerMap;
    }

    private Map<String, Object> getRequestParams(JoinPoint point) {
        String[] paramNames = ((MethodSignature) point.getSignature()).getParameterNames();
        Object[] paramValues = point.getArgs();
        return buildParams(paramNames, paramValues);
    }

    private Map<String, Object> buildParams(String[] paramNames, Object[] paramValues) {
        Map<String, Object> requestParams = new HashMap<>(paramNames.length);
        for (var i = 0; i < paramNames.length; i++) {
            Object value = paramValues[i];
            //文件对象
            if (value instanceof MultipartFile) {
                MultipartFile multipartFile = (MultipartFile) value;
                value = multipartFile.getOriginalFilename();
            }
            requestParams.put(paramNames[i], value);
        }
        //过滤HttpServletRequest、HttpServletResponse
        return requestParams.entrySet().stream()
                .filter((e) -> (!(e.getValue() instanceof HttpServletRequest)
                        && !(e.getValue() instanceof HttpServletResponse)))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }



    private OperLogPO assembleField(HttpServletRequest request, JoinPoint point) {
        final Method method = ((MethodSignature) point.getSignature()).getMethod();
        OperLog annotation = method.getAnnotation(OperLog.class);

        final OperLogPO operLog = new OperLogPO();
        operLog.setOperModule(annotation.operModule());
        operLog.setOperSubModule(annotation.operSubModule());
        operLog.setOperAction(annotation.operAction());
        operLog.setOperDesc(annotation.operDesc());

        operLog.setOperReqId(MDC.get("API_REQUEST_ID"));
        operLog.setOperReqHeader(JSON.toJSONString(getRequestHeaders(request)));
        operLog.setOperReqParam(JSON.toJSONString(getRequestParams(point)));
        operLog.setOperMethod(String.format("%s.%s",
                point.getSignature().getDeclaringTypeName(), point.getSignature().getName()));
        operLog.setOperUri(request.getRequestURL().toString());
        operLog.setOperIp(request.getRemoteAddr());
        //TODO
        operLog.setOperUserId("");
        operLog.setOperUserName("");
        operLog.setOperVersion(request.getHeader("API_VERSION_HEADER"));
        operLog.setOperTime(LocalDateTime.now());
        operLog.setCreater(operLog.getOperUserName());
        operLog.setUpdater(operLog.getOperUserName());

        return operLog;
    }

    /**
     * 请求状态枚举.
     *
     * @classname: com.sunshine.buddhistlife.component.log.LogAspect.java
     * @copyright: Powered By Sunshine
     * @author: Somnus
     * @date: 2022-06-30 10:43:31
     * @version: V1.0
     */
    @Getter
    @AllArgsConstructor
    enum ReqStatus {
        /** SUCCESS. */
        SUCCESS(0, "请求成功"),
        /** ERROR. */
        ERROR(1, "请求异常")
        ;

        private final Integer code;
        private final String desc;
    }
}
