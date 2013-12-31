package com.lxy.dynamic.proxy;

public class UserServiceImpl implements UserService {

	public int save() {
		System.out.println("user save....");
		return 1;
	}

	public void update(int id) {
		System.out.println("update a user " + id);
	}

}