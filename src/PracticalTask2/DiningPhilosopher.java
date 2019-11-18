/*
 * File:	DiningPhilosopher.java
 * Course: 	Operating Systems
 * Code: 	1DV512
 * Author: 	Suejb Memeti (modified by Kostiantyn Kucher)
 * Date: 	November 2019
 */
package PracticalTask2;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


public class DiningPhilosopher {

	/*
	 * Controls whether logs should be shown on the console or not.
	 * Logs should print events such as: state of the philosopher, and state of the chopstick
	 * 		for example: philosopher # is eating;
	 * 		philosopher # picked up the left chopstick (chopstick #)
	 */
	public boolean DEBUG = false;
	
	private final int NUMBER_OF_PHILOSOPHERS = 5;
	private int SIMULATION_TIME = 10000;
	private int SEED = 0;

	ExecutorService executorService = null;
	ArrayList<Philosopher> philosophers = null;
	ArrayList<Chopstick> chopsticks = null;

	public void start() throws InterruptedException {
		try {
			/*
			 * First we start two non-adjacent threads, which are T1 and T3
			 */
			for (int i = 1; i < NUMBER_OF_PHILOSOPHERS; i+=2) {
				executorService.execute(philosophers.get(i));
				Thread.sleep(50); //makes sure that this thread kicks in before the next one
			}

			/*
			 * Now we start the rest of the threads, which are T0, T2, and T4.
			 */
			for (int i = 0; i < NUMBER_OF_PHILOSOPHERS; i+=2) {
				executorService.execute(philosophers.get(i));
				Thread.sleep(50); //makes sure that this thread kicks in before the next one
			}

			// Main thread sleeps till time of simulation
			Thread.sleep(SIMULATION_TIME);

			if (DEBUG) {
				System.out.println("\n>>> Asking all philosophers to stop\n");
			}
			
			/*	TODO
			 *  Stop all philosophers.
			 *  Make sure all of the philosopher threads actually terminate!!!  
			 *  
			 *  Add comprehensive comments to explain your implementation.
			 */

			//in order to stop all philosophers they all should have been eating and be full
			for (int i=0; i<NUMBER_OF_PHILOSOPHERS; i++){
				philosophers.get(i).status(true);
			}


		} finally {
			executorService.shutdown();
			executorService.awaitTermination(10, TimeUnit.MILLISECONDS);
		}
	}
	/*to help get the ids
	public Chopstick getChopstickByID(int id) {
		for(int i=0; i<chopsticks.size(); i++){
			if (id == i){
				return chopsticks.get(i);
			}
		}
		return null;
	}

	 */

	public void initialize(int simulationTime, int randomSeed) {
		SIMULATION_TIME = simulationTime;
		SEED = randomSeed;

		philosophers = new ArrayList<Philosopher>(NUMBER_OF_PHILOSOPHERS);
		chopsticks = new ArrayList<Chopstick>(NUMBER_OF_PHILOSOPHERS);

		//create the executor service
		executorService = Executors.newFixedThreadPool(NUMBER_OF_PHILOSOPHERS);

		/* TODO
		 * Add chopsticks,
		 * Add philosophers, and
		 * Assign the corresponding chopsticks.
		 * Add comprehensive comments to explain your implementation.
		 */

		//add new chopstick
		Chopstick c1 = new Chopstick(1);
		chopsticks.add(c1);
		Chopstick c2 = new Chopstick(2);
		chopsticks.add(c2);
		Chopstick c3 = new Chopstick(3);
		chopsticks.add(c3);
		Chopstick c4 = new Chopstick(4);
		chopsticks.add(c4);
		Chopstick c5 = new Chopstick(5);
		chopsticks.add(c5);

		//**add new philosopher
		Philosopher p1= new Philosopher(0,c2,c1,100,true);
        philosophers.add(p1);
		Philosopher p2= new Philosopher(1,c3,c2,101,true);
		philosophers.add(p2);
		Philosopher p3= new Philosopher(2,c4,c3,102,true);
		philosophers.add(p3);
		Philosopher p4= new Philosopher(3,c5,c4,103,true);
		philosophers.add(p4);
		Philosopher p5= new Philosopher(4,c1,c5,104,true);
		philosophers.add(p5);
		}



	public ArrayList<Philosopher> getPhilosophers() {
		return philosophers;
	}

	/*
	 * The following code prints a table where each row corresponds to one of the Philosophers,
	 * Columns correspond to the Philosopher ID (PID), average thinking time (ATT), average eating time (AET), average hungry time (AHT), number of thinking turns(#TT), number of eating turns(#ET), and number of hungry turns(#HT).
	 * This table should be printed regardless of the DEBUG value
	 */
	public void printTable() {
		DecimalFormat df2 = new DecimalFormat(".##");
		System.out.println("\n---------------------------------------------------");
		System.out.println("PID \tATT \tAET \tAHT \t#TT \t#ET \t#HT");

		for (Philosopher p : philosophers) {
			System.out.println(" "+p.getId() + "\t"
					+df2.format(p.getAverageThinkingTime()) + "\t"
					+df2.format(p.getAverageEatingTime()) + "\t"
					+df2.format(p.getAverageHungryTime()) + "\t"
					+p.getNumberOfThinkingTurns() + "\t"
					+p.getNumberOfEatingTurns() + "\t"
					+p.getNumberOfHungryTurns() + "\t");
		}

		System.out.println("---------------------------------------------------\n");
	}

}
