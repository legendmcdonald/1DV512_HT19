package demo10;

import java.util.concurrent.Semaphore;
import java.util.Random;

public class Connection {

	private static Connection instance = new Connection(); //create the connection instance

	private Semaphore sem = new Semaphore(10, true); // we want 10 connections at a time.
						                            // The second parameter "true" guarantees a fair semaphore.
                                                    // It means that whichever thread called acquire first,
                                                    // will be the first one to get a permit when a permit becomes available
                                                    // If you dont have true, you may leave some threads in the background while other threads are overloaded with work
	private int connections = 0; //number of maximum allowed connections at any time

	private Connection() {

	}

	public static Connection getInstance() {
		return instance;
	} //retrieve the connection instance

    public void connect() {
		try {
			sem.acquire(); // it will acquire the semaphore first
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}

		try{
			doConnect(); // do something
		}
		finally {
			sem.release(); // and release the permit
		}
	}
	public void doConnect() {
		Random random = new Random();

		synchronized (this) {
			connections++;
			System.out.println("Current connections: "+connections);
		}

		try {
			Thread.sleep(random.nextInt(2000)); // simulate some work
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		synchronized (this) {
			connections--; //decrease the number of connections
			System.out.println("Current connections: "+connections);
		}

	}
}
