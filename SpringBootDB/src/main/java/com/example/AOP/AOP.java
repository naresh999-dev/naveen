package com.example.AOP;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import com.example.Dto.User;
@Aspect
@Component
public class AOP {
	
	
	
	  @AfterReturning( pointcut =
	  "execution(* com.example.Service.UserService.getUser(..))", returning=
	  "status") public void afterReturningAdvice(JoinPoint jp, Object status){ User
	  user=(User)status; 
	  System.out.println("Money deposit status is= "+user.getUname());
	  user.setUname("naresh999"); }
	 
	
	/*
	 * @After( pointcut =
	 * "execution(* com.example.Service.UserService.getUser(..))", returning=
	 * "status") public void afterReturningAdvice(JoinPoint jp, Object status){ User
	 * user=(User)status; System.out.println("Money deposit status is= "
	 * +user.getUname()); user.setUname("nari"); }
	 */
	 
	  
		/*
		 * @Before("selectAll()") public void before(){
		 * System.out.println("before adding do some operation");
		 * 
		 * }
		 * 
		 * @Pointcut("execution(* com.example.Service.UserService.addUser(..))") private
		 * void selectAll(){ System.out.println("Going to setup student profile."); }
		 */
	 
	
	/*
	 * @Around("execution(* com.example.Service.UserService.getUser(..))") public
	 * User logAroundAllMethods(ProceedingJoinPoint joinPoint) throws Throwable {
	 * User result = (User)joinPoint.proceed();
	 * System.out.println("AOP"+result.getUname()); result.setUname("naviiii");
	 * return result;
	 * 
	 * 
	 * }
	 */
	 
	  
	 

}
