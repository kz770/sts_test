package com.example.demo.Advice;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ProfilingAspect {
	
	@Pointcut("execution (public * com.example.demo.dao..*(..))")
	public void myDao() {
		
	}
	
//	@Before("myDao()")
//	public void pro(JoinPoint joinpoint) {
//		String method1=joinpoint.getSignature().toLongString();	// 메서드 이름 반환
//		String method2=joinpoint.getSignature().toShortString();
//		System.out.println(method1);
//		System.out.println(method2);
//		System.out.println("메서드 동작 전");
//	}
	
//	@AfterReturning("myDao()")
//	public void pro(JoinPoint joinpoint) {
//		String methodName=joinpoint.getSignature().toShortString();
//		System.out.println(methodName+"이 성공적으로 동작했음");
//	}
	
//	@After("myDao()")
//	public void pro(JoinPoint joinpoint) {
//		String methodName=joinpoint.getSignature().toShortString();
//		System.out.println(methodName+"이 성공적으로 동작했음");
//	}
	
	@Around("myDao()")
	public Object pro(ProceedingJoinPoint joinpoint) {
		String methodName=joinpoint.getSignature().toShortString();
		Object re = null;
		System.out.println(methodName+"가 동작하기 전입니다.");
		long start=System.currentTimeMillis();
		try {
			re = joinpoint.proceed();
		} catch (Throwable e) {
			e.printStackTrace();
		}
		long end=System.currentTimeMillis();
		System.out.println(methodName+"이 동작한 후입니다.");
		System.out.println("걸린 시간 ==> "+(end-start));
		return re;
	}
	
}
