//thread pools
package demo6;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class Processor implements Runnable {
	private Random random = new Random();
	private int id;

	public Processor(int id) {
        this.id = id;
	}

	public void run() {
		System.out.println("Starting: "+id);
		try {
			// Thread.sleep(1000); //simulated some useful work
			Thread.sleep(random.nextInt(1000));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Completed: "+id);
	}

}

public class ThreadPool {

	public static void main(String[] args) {
		ExecutorService executor = Executors.newFixedThreadPool(2); //create a thread loop with two threads

		for (int i = 0; i < 9; i++) { //create 5 tasks and submit them to the queue
			executor.submit(new Processor(i));
		}

        System.out.println("All tasks submited");

		executor.shutdown(); //stop accepting new tasks

		try {
			executor.awaitTermination(1, TimeUnit.DAYS); // waits for 1 day to finish the tasks
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println("All tasks completed");
	}

}
