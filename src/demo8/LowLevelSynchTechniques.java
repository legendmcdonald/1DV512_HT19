package demo8;

import java.util.LinkedList;
import java.util.Random;

public class LowLevelSynchTechniques {

	private LinkedList<Integer> list = new LinkedList<Integer>();
	private final int LIMIT = 10; //the list elements
	private Object lock = new Object();

	//this method adds items to the list when the list size is less than LIMIT
	public void produce() throws InterruptedException {

        Random random = new Random();

		while(true) {
			synchronized (lock) {
				while(list.size() == LIMIT) {
					lock.wait(); //hands over the lock to the consume thread
				}
				int val = random.nextInt(100);
				list.add(val);
				System.out.println(" + Added value is: "+val + "; List size now is: " + list.size());
				lock.notify(); // notifies the consume
			}
		}
	}

    //this method consumes elements from the list when the list has at least one item
	public void consume() throws InterruptedException {
		Random random = new Random();
		while(true) {
			synchronized (lock) {
				while(list.size() == 0) {
					lock.wait(); //hands over the lock to the produce thread
				}
				System.out.println(" = List size is: "+list.size());
				int value = list.removeFirst();
				System.out.println(" - Taken value is: "+value + "; List size now is: " + list.size());
				lock.notify(); // notifies the produce
			}
			Thread.sleep(random.nextInt(1000));
		}
	}

	public static void main(String[] args) throws InterruptedException {
		final LowLevelSynchTechniques processor = new LowLevelSynchTechniques();

		Thread t1 = new Thread(new Runnable() {

			public void run() {
				try {
					processor.produce();
				} catch (InterruptedException e) {
				}
			}
		});

		Thread t2 = new Thread(new Runnable() {

			public void run() {
				try {
					processor.consume();
				} catch (InterruptedException e) {
				}
			}
		});
		t1.start();
		t2.start();

		t1.join();
		t2.join();

	}

}
