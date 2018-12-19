package com.dinry.clouddisk.aop;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: 吴佳杰
 * @Date: 2018/12/19 9:29
 * @Description:
 */
@Slf4j
@Aspect
@Component
public class LogAop {

    @Pointcut("execution(* com.dinry.clouddisk.api.*.*(..))")
    public void pointCut() {
    }

    @Before(value = "pointCut()")
    public void before(JoinPoint joinPoint) throws JsonProcessingException {
        log.info("<=======================");
        log.info("<<<方法  " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        if (joinPoint.getArgs().length == 0) {
            log.info("<<<参数  null ");
        } else {
            log.info("<<<参数  " + new ObjectMapper().setSerializationInclusion(JsonInclude.Include.NON_NULL)
                    .writeValueAsString(joinPoint.getArgs()));
        }
    }

    @AfterReturning(pointcut = "pointCut()", returning = "object")
    public void after(Object object) {
        log.info(">>>返回 " + object.toString());
        log.info("=======================>");
    }
}
