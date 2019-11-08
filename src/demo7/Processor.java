package demo7;

import java.util.Scanner;

public class Processor {

	public void produce() throws InterruptedException {
		synchronized (this) {
			System.out.println("Producer thread runing...");
			wait(); //this will hand over the lock to the other thread
			System.out.println("Resumed.");
		}
		
	}

	public void consume() throws InterruptedException {
		Scanner scanner = new Scanner(System.in);
		Thread.sleep(2000);  // makes sure the other thread kicks of first
		
		synchronized (this) { // locking in the same object (intrinsic lock)
			System.out.println("Waiting for return key");
			scanner.nextLine();
			System.out.println("Key pressed");
			notify(); // will notify the other thread waiting in the same lock
			Thread.sleep(5000);
		}
	}

	public static void main(String[] args) throws InterruptedException {
		final Processor processor = new Processor();

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
