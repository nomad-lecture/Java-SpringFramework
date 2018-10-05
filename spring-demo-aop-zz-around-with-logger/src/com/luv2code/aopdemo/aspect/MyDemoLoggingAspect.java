package com.luv2code.aopdemo.aspect;

import java.util.List;
import java.util.logging.Logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.luv2code.aopdemo.Account;

@Aspect
@Component
@Order(2)
public class MyDemoLoggingAspect {
	
	private Logger logger = Logger.getLogger(MyDemoLoggingAspect.class.getName());
	
	@Around("execution(* com.luv2code.aopdemo.service.*.getFortune(..))")
	public Object aroundGetFortune(
			ProceedingJoinPoint theProceedingJoinPoint) throws Throwable {
				
		//print out method we are advising on
		String method = theProceedingJoinPoint.getSignature().toShortString();
		logger.info("\n=====>>>  Executing @Around on method: " + method);
		
		// get begin timestamp
		long begin = System.currentTimeMillis();
		
		//now, let's execute the method
		Object result = theProceedingJoinPoint.proceed();
		
		//get end timestamp
		long end = System.currentTimeMillis();
		
		//compute duration and display it
		long duration = end - begin;
		logger.info("\n====> Duration : " + duration / 1000.0 + "seconds");
		
		return result;
		
	}
	
	
	
	@After("execution(* com.luv2code.aopdemo.dao.AccountDAO.findAccounts(..))")
	public void afterFinallyFindAccountsAdvice(JoinPoint theJoinPoint) {
		
		//print out which method we are advising on
		String method = theJoinPoint.getSignature().toShortString();
		logger.info("\n=====>>>  Executing @After (finally) on method: " 
							+ method);
		
	}
	
	
	
	@AfterThrowing(
			pointcut = "execution(* com.luv2code.aopdemo.dao.AccountDAO.findAccounts(..))",
			throwing = "theExc"
			)
	public void afterThrowingFindAccountsAdvice( JoinPoint theJoinPoint, Throwable theExc) {
		
		//print out which method we are advising on
		String method = theJoinPoint.getSignature().toShortString();
		logger.info("\n=====>>>  Executing @AfterThrowing on method: " + method);
		
		//log the exception
		logger.info("\n=====>>>  The exception is: " + theExc);
	}
	
	
	
	@AfterReturning(
				pointcut="execution(* com.luv2code.aopdemo.dao.AccountDAO.findAccounts(..))",
				returning = "result")
	public void afterReturningFindACcountsAdvice(JoinPoint theJoinPoint, List<Account> result) {
		
		//print out which method we are advising on
		String method = theJoinPoint.getSignature().toShortString();
		logger.info("\n=====>>>  Executing @After Returnging on method: " + method);
		
		//print out the results of the method call
		logger.info("\n=====>>>  result is: " + result);
		
		//let's post-process the data ... let's modify it :-)
		
		//convert the account names to uppercase
		convertAccountNamesToUpperCase(result);
		
		logger.info("\n=====>>>  result is: " + result);
		
	}
			
	
	
	private void convertAccountNamesToUpperCase(List<Account> result) {
		
		//loop through accounts
		for(Account tempAccount : result) {
			
			//get uppercase version of name
			String theUpperName = tempAccount.getName().toUpperCase();
			
			//update the name on the account
			tempAccount.setName(theUpperName);
		}
		
		
	}



	@Before("com.luv2code.aopdemo.aspect.LuvAopExpressions.forDaoPackageNoGetterSetter()")
	public void beforeAddAccountAdvice(JoinPoint theJoinPoint) {
		
		logger.info("\n=====>>> Executin @Before advice on method");
		
		//display the method signature
		MethodSignature methodSig = (MethodSignature) theJoinPoint.getSignature();
		
		logger.info("Method : " + methodSig);
		
		//display method arguments
		
			//get args
			Object[] args = theJoinPoint.getArgs();
			
			//loopthru args
			for(Object tempArg : args) {
				logger.info(tempArg.toString());
				
				if(tempArg instanceof Account) {
				
					//downcast and print Account specific stuff
					Account theAccount = (Account) tempArg;
					
					logger.info("account name: " + theAccount.getName());
					logger.info("account level: " + theAccount.getLevel());
				}
			}
	}
	
}