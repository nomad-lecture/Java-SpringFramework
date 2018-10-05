package com.luv2code.aopdemo;

import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.luv2code.aopdemo.dao.AccountDAO;
import com.luv2code.aopdemo.dao.MembershipDAO;

public class AfterReturningDemoApp {

	public static void main(String[] args) {
		
		//read spring config java class
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DemoConfig.class);
		
		//get the bean from spring container
		AccountDAO theAccountDAO = context.getBean("accountDAO", AccountDAO.class);
		
		//call method to find the accounts
		List<Account> theAccounts = null;
				
		try {
			//add boolean flag to simulate expections
			boolean tripWire = true;
			theAccounts = theAccountDAO.findAccounts(tripWire);
			
		}catch(Exception exc) {
			System.out.println("\n\nMain Programm ... caught exception: " + exc);
		}
			
		
		//display the accounts
		System.out.println("\n\nMain Programm: AfterReturningDemoApp");
		System.out.println("-----");
		
		System.out.println(theAccounts);
		
		System.out.println("\n");
		
		//close the context
		context.close();
	}

}
