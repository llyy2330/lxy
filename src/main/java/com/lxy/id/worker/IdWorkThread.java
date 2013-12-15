package com.lxy.id.worker;

import java.util.Set;

public class IdWorkThread  implements Runnable{
	private Set<Long> set;

		private IdWorker idWorker;

		public IdWorkThread(Set<Long> set, IdWorker idWorker) {

			this.set = set;

			this.idWorker = idWorker;

		}

		public void run() {

			while (true) {

				long id = idWorker.nextId();

				if (!set.add(id)) {

					System.out.println("duplicate:" + id);

				}else{
					System.out.println("add:"+Thread.currentThread().getId()+"=" + id);
				}

			}

		}

	}