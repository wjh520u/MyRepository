package com.wjh.start.controller.aop;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Component
public class TestResponseAop {
	
	
	@AfterReturning(pointcut = "execution(* com.wjh.start.controller.controller..*.*(..) )")
	public void name(JoinPoint joinPoint) {
		ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();

		HttpServletResponse response = servletRequestAttributes.getResponse();
		System.err.println(99);
		response.setContentType("oooo");
	}

}
