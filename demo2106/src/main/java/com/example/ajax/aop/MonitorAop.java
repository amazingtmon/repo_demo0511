package com.example.ajax.aop;

import java.lang.reflect.Method;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

//하나의 문서에 여러개의 Bean을 설정할 때 - @Configuration 을 사용한다.0

@Aspect
@Component//@Bean 클래스 앞에 온다.
public class MonitorAop {
	@Pointcut("execution(* com.example.ajax.controller..*.*(..))")
	private void cut() {
		
	}
	
	@Before("cut()")
	public void before(JoinPoint joinPoint) {
		MethodSignature methodSignature = (MethodSignature)joinPoint.getSignature();
		Method method = methodSignature.getMethod();
		
		Object[] args = joinPoint.getArgs();
		for(Object obj:args) {
			System.out.println("type: "+obj.getClass().getSimpleName());//MemberVO의 타입.
			System.out.println("value: "+obj);//MemberVO에 담긴 정보들.
		}
	}
	
	@AfterReturning(value="cut()", returning="returnObj")
	public void afterReturn(JoinPoint joinPoint, Object returnObj) {
		System.out.println("================[[ 포인터컷한 메소드의 반환주소번지 object ]]============");
		System.out.println(returnObj);
	}
}
