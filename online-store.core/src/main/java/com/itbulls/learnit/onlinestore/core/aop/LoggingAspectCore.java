package com.itbulls.learnit.onlinestore.core.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class LoggingAspectCore {

    private static final Logger logger = LoggerFactory.getLogger(LoggingAspectCore.class);

    // Wskazuje na wszystkie publiczne metody w paczce facades (i jej podpakietach)
    @Around("execution(public * com.itbulls.learnit.onlinestore.core.facades..*(..))")
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

