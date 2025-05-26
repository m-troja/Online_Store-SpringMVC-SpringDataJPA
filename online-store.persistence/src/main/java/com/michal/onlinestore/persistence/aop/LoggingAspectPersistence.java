package com.michal.onlinestore.persistence.aop;

import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspectPersistence {

    private static final Logger logger = LoggerFactory.getLogger(LoggingAspectPersistence.class);

    // Wskazuje na wszystkie publiczne metody w paczce persistence.repo
    @Around("execution(public * com.michal.onlinestore.persistence.repo..*(..))")
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

