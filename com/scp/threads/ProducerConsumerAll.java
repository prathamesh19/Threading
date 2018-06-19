package com.scp.threads;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class ProducerConsumerAll {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

List<Integer> l1=new ArrayList(10);
Producer1 producer1 = new Producer1(l1,10);
Consumer1 consumer1 = new Consumer1(l1,10);

producer1.start();
consumer1.start();
}
}
class Producer1 extends Thread {
int MAX_SIZE = 10;
private List<Integer> l;
boolean flag;
public Producer1(List<Integer> l,int MAX_SIZE) {
	super();
	this.l = l;
	this.MAX_SIZE=MAX_SIZE;
}

@Override
public void run() {
	while (true) {
		synchronized(l) {
			while(l.size() == MAX_SIZE) {
				try {
					System.out .println("\nList is full....Consume items !"); 
					l.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		
			/*Random random = new Random();
			int i = random.nextInt();*/
			int i=ThreadLocalRandom.current().nextInt(10,30);
			System.out.println("Producing item: " + i);
			l.add(i);
			l.notify();
		}
	}
}
}

class Consumer1 extends Thread {
private List<Integer> l;
private int MAX_SIZE;
public Consumer1(List<Integer> l, int MAX_SIZE) {
	super();
	this.l = l;
	this.MAX_SIZE=MAX_SIZE;
}

@Override
public void run() {
	while(true) {
		synchronized(l) {
			while(l.isEmpty()) {
				System.out.println("\nList is empty....Produce items !");
				try {
					l.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
			System.out.println("Consuming item: " + l.remove(0));
			l.notify();
		}
	}
}
}
