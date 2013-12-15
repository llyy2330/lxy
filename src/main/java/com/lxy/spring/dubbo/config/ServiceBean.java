package com.lxy.spring.dubbo.config;

import java.lang.reflect.Method;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.support.AbstractApplicationContext;

public class ServiceBean<T> implements InitializingBean, DisposableBean,
		ApplicationContextAware, ApplicationListener, BeanNameAware {

	private transient ApplicationContext applicationContext;

	private static transient ApplicationContext SPRING_CONTEXT;

	public void setBeanName(String arg0) {
		// TODO Auto-generated method stub

	}

	public void onApplicationEvent(ApplicationEvent event) {
		System.out.println("event check");
		if (ContextRefreshedEvent.class.getName().equals(
				event.getClass().getName())) {

			System.out.println("hello spring init bean event");
		}
	}

	public void setApplicationContext(ApplicationContext arg0)
			throws BeansException {
		System.out.println("start set applicationContext ");
		this.applicationContext = applicationContext;
		if (applicationContext != null) {
			SPRING_CONTEXT = applicationContext;
			try {
				Method method = applicationContext.getClass().getMethod(
						"addApplicationListener",
						new Class<?>[] { ApplicationListener.class }); // 兼容Spring2.0.1
				method.invoke(applicationContext, new Object[] { this });
			} catch (Throwable t) {
				if (applicationContext instanceof AbstractApplicationContext) {
					try {
						Method method = AbstractApplicationContext.class
								.getDeclaredMethod(
										"addListener",
										new Class<?>[] { ApplicationListener.class }); // 兼容Spring2.0.1
						if (!method.isAccessible()) {
							method.setAccessible(true);
						}
						method.invoke(applicationContext, new Object[] { this });
					} catch (Throwable t2) {
					}
				}
			}
		}

	}

	public void destroy() throws Exception {
		// TODO Auto-generated method stub

	}

	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub

	}

}
