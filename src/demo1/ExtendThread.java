//Starting threads by extending the Thread class 
package demo1;

class Runner extends Thread {
	public void run() {
		for (int i = 0; i < 10; i++) {
			System.out.println("Hello " + i + "; Thread: " + Thread.currentThread().getName());
			
			try {
				Thread.sleep(100); //put thread to sleep for 100 miliseconds
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

public class ExtendThread {

	public static void main(String[] args) {
		//create 2 threads

		Runner r1 = new Runner();
		r1.setName("Runner 1"); // setting a name of the thread
		r1.start(); // executes on a separate thread
//		r1.run(); //executes on the main thread.
		
		Runner r2 = new Runner();
		r2.setName("Runner 2");
		r2.start();
	}
}
