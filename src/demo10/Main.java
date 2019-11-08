package demo10;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {

	public static void main(String[] args) throws Exception {

		ExecutorService executor = Executors.newCachedThreadPool(); // a thread pool that tries to reuse existing threads

		for (int i = 0; i < 50; i++) { //creating 50 threads, calling connect from each of them
			executor.submit(new Runnable() {
				public void run() {
					Connection.getInstance().connect();
				}
			});
		}

			executor.shutdown();

			executor.awaitTermination(1, TimeUnit.DAYS);

	}

}
