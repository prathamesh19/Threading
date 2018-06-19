package com.scp.threads;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class ProducerConsumerSingle {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
List<Integer> l1=new ArrayList(10);
Producer producer = new Producer(l1,10);
Consumer consumer = new Consumer(l1,10);

producer.start();
consumer.start();
}
}
class Producer extends Thread {
int MAX_SIZE = 10;
private List<Integer> l;
boolean flag;
public Producer(List<Integer> l,int MAX_SIZE) {
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
					System.out .println("\nList is full....Consume an item !"); 
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

class Consumer extends Thread {
private List<Integer> l;
private int MAX_SIZE;
public Consumer(List<Integer> l, int MAX_SIZE) {
	super();
	this.l = l;
	this.MAX_SIZE=MAX_SIZE;
}

@Override
public void run() {
	while(true) {
		synchronized(l) {
			while(l.size()<=(MAX_SIZE-1)) {
				System.out.println("\nOne item has been consumed....Produce an item !");
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
