//The synchronized keyword
package demo4;

public class Synchronized {

	private int count = 0;

	public synchronized void increment() {
		count ++;
	}

	public static void main(String[] args) {
		Synchronized app = new Synchronized();
		app.foo();

	}

	public void foo() {
		Thread t1 = new Thread(new Runnable() {
			public void run() {
				for(int i=0; i<10000; i++) {
					increment();
					// count++; // = count + 1; // read count, add 1 to it, store to count
				}
			}
		});

		Thread t2 = new Thread(new Runnable() {
			public void run() {
				for(int i=0; i<10000; i++) {
					increment();
					// count++;
				}
			}
		});

		t1.start();
		t2.start();


        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(count);
	}

}
