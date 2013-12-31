package com.lxy.dynamic.proxy;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Proxy;

public class Test {
	public static void main(String[] args) {
		TimeInvocationHandler timeHandler = new TimeInvocationHandler(new TestUserServiceImpl() );
		Object obj = Proxy.newProxyInstance(
				UserServiceImpl.class.getClassLoader(),
				UserServiceImpl.class.getInterfaces(), timeHandler);
	
		
		
		System.out.println("aa="+obj.getClass().getName());
		Test.printClassDefinition(obj.getClass());
		UserService u = (UserService) obj;
		u.update(2);

		u.save();

	}

	public static void printClassDefinition(Class clz) {

		String clzModifier = Modifier.toString(clz.getModifiers()) + " ";
		String superClz = clz.getSuperclass().getName();
		if (superClz != null && !superClz.equals("")) {
			superClz = "extends " + superClz;
		}
		Class[] interfaces = clz.getInterfaces();
		String inters = "";
		for (int i = 0; i < interfaces.length; i++) {
			if (i == 0) {
				inters += "implements ";
			}
			inters += interfaces[i].getName();
		}

		System.out.println(clzModifier + clz.getName() + " " + superClz + " "
				+ inters);
		System.out.println("{");

		System.out.println("//Fields:");
		Field[] fields = clz.getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			String modifier = Modifier.toString(fields[i].getModifiers()) + " ";
			String fieldName = fields[i].getName();
			String fieldType = fields[i].getType().getName();
			System.out.println("    " + modifier + fieldType + " " + fieldName
					+ ";");
		}

		System.out.println("//Constructs:");
		Constructor[] constructs = clz.getConstructors();
		for (int i = 0; i < constructs.length; i++) {
			Constructor construct = constructs[i];
			System.out.println("    " + construct.toString() + ";");
		}

		System.out.println("//Methods:");
		Method[] methods = clz.getDeclaredMethods();
		for (int i = 0; i < methods.length; i++) {
			Method method = methods[i];
			String modifier = Modifier.toString(method.getModifiers()) + " ";
			String methodName = method.getName();
			Class returnClz = method.getReturnType();
			String retrunType = returnClz.getName();

			Class[] clzs = method.getParameterTypes();
			String paraList = "(";
			for (int j = 0; j < clzs.length; j++) {
				paraList += clzs[j].getName();
				if (j != clzs.length - 1) {
					paraList += ", ";
				}
			}
			paraList += ")";

			clzs = method.getExceptionTypes();
			String exceptions = "";
			for (int j = 0; j < clzs.length; j++) {
				if (j == 0) {
					exceptions += "throws ";
				}
				exceptions += clzs[j].getName();
				if (j != clzs.length - 1) {
					exceptions += ", ";
				}
			}
			exceptions += ";";
			String methodPrototype = modifier + retrunType + " " + methodName
					+ paraList + exceptions;
			System.out.println("    " + methodPrototype);
		}
		System.out.println("}");
	}
}