package com.example.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Configuration
@Aspect
public class AspectConfig {

	Logger logger = LoggerFactory.getLogger(getClass());

	@Before(value = "execution(* com.example.controller.*.*(..))")
	public void beforeAdvice(JoinPoint joinPoint) {
		logger.info("Inside Before Advice");
	}

	@AfterReturning(value = "execution(* com.example.controller.*.*(..)) and args(object)", returning = "returningObject")
	public void afterReturningAdvice(JoinPoint joinPoint, Object object, Object returningObject) {
		logger.info("Response: " + returningObject);
	}

	@Around(value = "execution(* com.example.controller.*.*(..)) and args(object)")
	public void aroundAdvice(ProceedingJoinPoint proceedingJoinPointd, Object object) {

		Object returningObject = null;
		try {
			returningObject = proceedingJoinPointd.proceed();
		} catch (Throwable e) {
			e.printStackTrace();
		}

		logger.info("Response: " + returningObject);
	}
}
