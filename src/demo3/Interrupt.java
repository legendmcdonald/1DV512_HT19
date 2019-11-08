//interrupt method
package demo3;

import java.util.Random;

public class Interrupt {

	public static void main(String[] args) {
		System.out.println("Starting ...");

		Thread t = new Thread(new Runnable(){

			@Override
			public void run() {
				Random random = new Random();
				for (int i = 0; i < 1E8; i++) {
					//check if the flag is set to true
					if(Thread.currentThread().isInterrupted()) {
						System.out.println("Interrupted");
						break;
					}

					Math.sin(random.nextDouble());
				}
			}

		});

		t.start();

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		t.interrupt(); // sets the thread isInterrupted flag to true

		try {
			t.join(); //wait for the threads to finish
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println("Finished.");

	}

}
