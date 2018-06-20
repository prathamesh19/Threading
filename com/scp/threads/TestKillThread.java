package com.scp.threads;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class TestKillThread {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
ThreadKill t1 = new ThreadKill("t1");
ThreadKill t2 = new ThreadKill("t2");
t1.start();
t2.start();
t1.threadKill();

	}
}

class ThreadKill extends Thread {
	volatile boolean flag = true;
	volatile int count = 0;

	public void threadKill() {
		flag = false;
		System.out.println("KillingThread --" + Thread.currentThread().getName());
	}

	public ThreadKill(String name) {
		super(name);
	}

	public void run() {
		while (flag) {
			System.out.println("Count value -- " + count);
			try {
				TimeUnit.SECONDS.sleep(2);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			if (Thread.currentThread().getName().equals("t1")) {
				if (count >= 100) {
					threadKill();
				}
			}

			System.out.println(
					Thread.currentThread().getName() + " -- Running Thread --" + ThreadLocalRandom.current().nextInt());
		}
		System.out.println("killing the thread" + Thread.currentThread().getName());
	}
}