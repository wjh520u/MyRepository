package com.forezp.finchley.provide.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class EncoderAop {
	
	@Around(value = "execution (* com.forezp.finchley.provide..*.*(..))")
	public Object name(ProceedingJoinPoint joinPoint) throws Throwable {
		Object proceed = joinPoint.proceed();
		return proceed;
	}

}
