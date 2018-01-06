package com.openretails.web.interceptor;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.openretails.common.exception.OpenRetailsValidationException;

@Component
@Aspect
@Order(3)
public class LoggingAspect {
	private static final Logger LOGGER = LoggerFactory.getLogger(LoggingAspect.class);

	@After("applyOnServices()")
	public void afterServiceAdvice(JoinPoint joinPoint) {
		LOGGER.debug("class name -> {} ,method name -> {} and method arguments -> {} ends",
				joinPoint.getTarget().getClass(), joinPoint.getSignature().getName(),
				Arrays.asList(joinPoint.getArgs()));
	}

	@AfterThrowing(pointcut = "applyOnServices()", throwing = "cause")
	public void afterThrowingServiceAdvice(JoinPoint joinPoint, Throwable cause) {
		LOGGER.debug("class name -> {} ,method name -> {} and method arguments -> {} throwing exception -> {}",
				joinPoint.getTarget().getClass(), joinPoint.getSignature().getName(),
				Arrays.asList(joinPoint.getArgs()), cause.getMessage());

		if (!(cause instanceof OpenRetailsValidationException)) {
			final StringWriter errorMsg = new StringWriter();
			cause.printStackTrace(new PrintWriter(errorMsg));

			LOGGER.error("class name -> {} ,method name -> {} and method arguments -> {} throwing exception -> {}",
					joinPoint.getTarget().getClass(), joinPoint.getSignature().getName(),
					Arrays.asList(joinPoint.getArgs()), errorMsg);
		}
	}

	@Pointcut("execution(* com.openretails.web.api..*(..))")
	private void applyOnServices() {
		// Do nothing, its dummy pointcut method
	}

	@Before("applyOnServices()")
	public void beforeServiceAdvice(JoinPoint joinPoint) {
		LOGGER.debug("class name -> {} ,method name -> {} and method arguments -> {} starts",
				joinPoint.getTarget().getClass(), joinPoint.getSignature().getName(),
				Arrays.asList(joinPoint.getArgs()));
	}

}
