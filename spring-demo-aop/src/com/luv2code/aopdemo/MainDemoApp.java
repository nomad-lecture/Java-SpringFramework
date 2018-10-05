package com.luv2code.aopdemo;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.luv2code.aopdemo.dao.AccountDAO;
import com.luv2code.aopdemo.dao.MembershipDAO;

public class MainDemoApp {

	public static void main(String[] args) {
		
		//read spring config java class
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DemoConfig.class);
		
		//get the bean from spring container
		AccountDAO theAccountDAO = context.getBean("accountDAO", AccountDAO.class);
		
			//get membership beam from spring container
		MembershipDAO theMembershipDAO = context.getBean("membershipDAO", MembershipDAO.class);
		
		//call the business method
		Account theAccount = new Account();
		theAccountDAO.addAccount(theAccount, true);
		theAccountDAO.doWork();
		
			//call member ship business method
		theMembershipDAO.addSillyMember();
		theMembershipDAO.goToSleep();
		
		//close the context
		context.close();
	}

}
