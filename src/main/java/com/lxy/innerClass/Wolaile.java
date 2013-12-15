package com.lxy.innerClass;

public class Wolaile {

	public Wolaile(Test handler) {

		System.out.println("lxy test HeaderExchangeHandler  handler="
				+ handler.getClass().getName());

	}

	public static void main(String[] args) {
		Wolaile  aa=new Wolaile(new NewTest());
	}
}
