package com.lxy.dynamic.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class TimeInvocationHandler implements InvocationHandler {
	private Object o;

	public TimeInvocationHandler(Object o) {
		this.o = o;
	}

	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		System.out.println("proxy="+proxy.getClass().getName());
	
		System.out.println("startTime : " + System.currentTimeMillis());
		Object obj = method.invoke(o, args);
		System.out.println("endTime : " + System.currentTimeMillis());
		return obj;
	}
	private int teest;

	public int getTeest() {
		return teest;
	}

}
