package demo8;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class HighLevelSynchTechniques {
	private static BlockingQueue<Integer> queue = new ArrayBlockingQueue<Integer>(10); //thread safe queue, no need for synchronization


	private static void produce() throws InterruptedException {
		Random random = new Random();

		while(true) {
			int val = random.nextInt(100);
			queue.put(val); //if size is 10, put() waits until consume has taken one element out
			System.out.println(" + Added value is: "+val + "; List size now is: " + queue.size());
		}
	}

	private static void consume() throws InterruptedException {
		Random random = new Random();

		while(true) {
			Thread.sleep(100);

			if(random.nextInt(10) == 0) {
				System.out.println(" = Queue size is: "+queue.size());
				Integer value = queue.take(); // if size == 0, take() waits until produce has put one element in
				System.out.println(" - Taken value: "+ value + "; Queue size now is: " + queue.size());
			}

		}
	}

	public static void main(String[] args) {
		Thread t1 = new Thread(new Runnable(){

			public void run() {
				try {
					produce();
				} catch (InterruptedException e) {
				}
			}
		});

		Thread t2 = new Thread(new Runnable(){

			public void run() {
				try {
					consume();
				} catch (InterruptedException e) {
				}
			}
		});
		t1.start();
		t2.start();


	}
}
