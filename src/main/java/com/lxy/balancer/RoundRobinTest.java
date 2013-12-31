package com.lxy.balancer;

/**
 * 轮询普通算法
 * @author lixiaoyong
 *
 */
public class RoundRobinTest {
	private static int[] server = new int[10];// 机器序号:ip
	private static int j;
	private static int i = -1;

	public static int next() {
		j = i;
		do {
			j = (j + 1) % server.length;

			i = j;
			return i;

		} while (j != i);
	}

	public static void main(String[] args) {
		int req = 100;
		while (req >= 0) {
			System.out.println(RoundRobinTest.next() + ",");
			req--;
		}
	}
}
