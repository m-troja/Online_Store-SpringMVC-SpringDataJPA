package com.itbulls.learnit.onlinestore.web.aop;

import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.itbulls.learnit.onlinestore.persistence.aop.LoggingAspectPersistence;

@Aspect
@Component
public class LoggingAspectWeb {

    private static final Logger logger = LoggerFactory.getLogger(LoggingAspectWeb.class);

    // Wskazuje na wszystkie publiczne metody w paczce web.controllers
    @Around("execution(public * com.itbulls.learnit.onlinestore.web.controllers..*(..))")
    public Object logFacadeMethods(ProceedingJoinPoint joinPoint) throws Throwable {
        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getSignature().getDeclaringType().getSimpleName();
        String arguments = Arrays.toString(joinPoint.getArgs());

        logger.info("Entering {}.{} with args {}", className, methodName, arguments);

        try {
            Object result = joinPoint.proceed();
            logger.info("Exiting {}.{} with result: {}", className, methodName, result);
            return result;
        } catch (Throwable ex) {
            logger.error("Exception in {}.{} with args {}: {}", className, methodName, arguments, ex.getMessage(), ex);
            throw ex;
        }
    }
}
