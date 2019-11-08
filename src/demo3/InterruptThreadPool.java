//shutdownNow
package demo3;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class InterruptThreadPool {

	public static void main(String[] args) throws InterruptedException {
		System.out.println("Starting ...");

		ExecutorService exec = Executors.newCachedThreadPool();

		exec.submit(new Runnable() {

			@Override
			public void run() {
				Random random = new Random();
				for (int i = 0; i < 1E8; i++) {
					if (Thread.currentThread().isInterrupted()) {
						System.out.println("Interrupted");
						break;
					}
					Math.sin(random.nextDouble());
				}
			}

		});

		exec.shutdown();

		Thread.sleep(2000);

		exec.shutdownNow(); //interrupts the executor

		exec.awaitTermination(1, TimeUnit.DAYS); // equivalent to join()

		System.out.println("Finished.");

	}

}
