package com.luv2code.aopdemo;

import java.util.logging.Logger;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.luv2code.aopdemo.service.TrafficFortuneService;


public class AroundHandleExceptionDemoApp {

	private static Logger log = Logger.getLogger(AroundHandleExceptionDemoApp.class.getName());
	
	public static void main(String[] args) {
		
		//read spring config java class
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DemoConfig.class);
		
		//get the bean from spring container
		TrafficFortuneService theFortuneService = context.getBean("trafficFortuneService", TrafficFortuneService.class);
		
		
		log.info("\nMain Program: AroundDemoApp");
		
		log.info("Calling getFortune");
		
		String data = theFortuneService.getFortune();
		
		log.info("\nMy fortune is: " + data);
		
		log.info("Finished");
		
		//close the context
		context.close();
	}

}
