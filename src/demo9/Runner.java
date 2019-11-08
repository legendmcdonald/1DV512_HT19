//deadlock
package demo9;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Runner {

	private Account acc1 = new Account();
	private Account acc2 = new Account();

	private Lock lock1 = new ReentrantLock(); //you can lock reentrant locks multiple times
	private Lock lock2 = new ReentrantLock();

	private void acquireLocks(Lock firstLock, Lock secondLock) throws InterruptedException { //allows you to aquire the locks in any order
		while(true) {
			//Acquire the locks
			boolean gotFirstLock = false, gotSecondLock = false;
			try{
				gotFirstLock = firstLock.tryLock();
				gotSecondLock = secondLock.tryLock();
			}
			finally {
				if(gotFirstLock && gotSecondLock) {
					return;
				}

				if(gotFirstLock) {
					firstLock.unlock();
				}

				if(gotSecondLock) {
					secondLock.unlock();
				}

			}
			//Locks not acquired
			Thread.sleep(1);
		}
	}

	public void firstThread() throws InterruptedException { //transfers money from acc1 to acc2
		Random random = new Random();

		for(int i = 0; i<1000; i ++ ){

			// lock1.lock();
			// lock2.lock();
			acquireLocks(lock1, lock2);
//			Account.transfer(acc1, acc2, random.nextInt(100));

			try{
				Account.transfer(acc1, acc2, random.nextInt(100));
			}
			finally {
				lock1.unlock();
				lock2.unlock();
			}
		}

	}

	public void secondThread() throws InterruptedException { //transfers money from acc2 to acc1
		Random random = new Random();

		for(int i = 0; i<1000; i ++ ){

			// lock1.lock();
			// lock2.lock(); //lock ordering is not the same, a deadlock will happen

			acquireLocks(lock2, lock1);

//			Account.transfer(acc2, acc1, random.nextInt(100));
//
			try{
				Account.transfer(acc2, acc1, random.nextInt(100));
			}
			finally{
				lock2.unlock();
				lock1.unlock();
			}
		}

	}

	public void finished() {
		System.out.println("Account 1 balance: "+acc1.getBalance());
		System.out.println("Account 2 balance: "+acc2.getBalance());

		System.out.println("Total balance: "+ (acc1.getBalance() + acc2.getBalance()));
	}

}
