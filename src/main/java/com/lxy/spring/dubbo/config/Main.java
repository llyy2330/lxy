package com.lxy.spring.dubbo.config;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
	public static void main(String[] args) {
		ClassPathXmlApplicationContext context;
		context = new ClassPathXmlApplicationContext("spring-config.xml");
/*		registerBeanDefinitionParser("service", new DubboBeanDefinitionParser(
				ServiceBean.class, true));*/
		context.start();
		System.out.println("start success");
	}
}
