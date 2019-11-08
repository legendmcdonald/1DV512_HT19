//Starting threads by implementing the Runnable interface
package demo2;

class Runner implements Runnable {

	public void run() {
		for (int i = 0; i < 10; i++) {
			System.out.println("Hello " + i+ "; Thread: "+Thread.currentThread().getName());

			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}

public class ImplementRunnable {

	public static void main(String[] args) {
		Thread t1 = new Thread(new Runner());
		t1.setName("Thread 1");

		Thread t2 = new Thread(new Runner());
		t2.setName("Thread 2");

		t1.start();
		t2.start();
	}

}
