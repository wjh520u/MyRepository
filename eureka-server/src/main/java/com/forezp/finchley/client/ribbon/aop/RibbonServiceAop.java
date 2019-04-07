package com.forezp.finchley.client.ribbon.aop;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class RibbonServiceAop {
	
	@Around(value = "execution (* com.forezp.finchley.client.ribbon.service..*.*(..))")
	public Object name(ProceedingJoinPoint joinPoint) throws Throwable {
		System.err.println("开始");
		Object proceed = joinPoint.proceed();
		return proceed;
	}

}