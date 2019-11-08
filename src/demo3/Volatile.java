//The volatile keyword
package demo3;

import java.util.Scanner;

class Processor extends Thread {
	// private boolean running = true; //The main thread will write to this variable using the shutDown() method, whereas the p1 thread will read this.
                                    // When Java optimizes the code, one single thread does not expect that another thread will modify its own data.
	private volatile boolean running = true; // makes sure the running does not get cached
                                            // this guaranteees that this code will work in every system and every implementation of java

	public void run() {
		Thread tt = Thread.currentThread();
//		while(true) {
		while(running) {
			System.out.println("Hello from: "+ tt.getName());

			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void shutdown() {

		running = false;
	}
}

public class Volatile {

	public static void main(String[] args) {
		Processor p1 = new Processor();
		p1.start();

		System.out.println("Press return to stop");
		Scanner scanner = new Scanner(System.in);
		scanner.nextLine();
//
		p1.shutdown(); //switches the running variable to false

	}

}
