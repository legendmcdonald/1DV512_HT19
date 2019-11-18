/*
 * File:	Philosopher.java
 * Course: 	Operating Systems
 * Code: 	1DV512
 * Author: 	Suejb Memeti (modified by Kostiantyn Kucher)
 * Date: 	November 2019
 */
package PracticalTask2;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Philosopher implements Runnable {
	
	/*
	 * Controls whether logs should be shown on the console or not.
	 * Logs should print events such as: state of the philosopher, and state of the chopstick
	 * 		for example: philosopher # is eating;
	 * 		philosopher # picked up the left chopstick (chopstick #)
	 */
	public boolean DEBUG = false;


	private int id;
	
	private final Chopstick leftChopstick;
	private final Chopstick rightChopstick;
	
	private Random randomGenerator = new Random();
	
	private int numberOfEatingTurns = 0;
	private int numberOfThinkingTurns = 0;
	private int numberOfHungryTurns = 0;

	private double thinkingTime = 0;
	private double eatingTime = 0;
	private double hungryTime = 0;

	private volatile boolean isFull=false;
	int activity;
	public Philosopher(int id, Chopstick leftChopstick, Chopstick rightChopstick, int seed, boolean debug) {
		this.id = id;
		this.leftChopstick = leftChopstick;
		this.rightChopstick = rightChopstick;
		
		this.DEBUG = debug;
		
		/*
		 * set the seed for this philosopher. To differentiate the seed from the other philosophers, we add the philosopher id to the seed.
		 * the seed makes sure that the random numbers are the same every time the application is executed
		 * the random number is not the same between multiple calls within the same program execution 
		 
		 * NOTE
		 * In order to get the same average values use the seed 100, and set the id of the philosopher starting from 0 to 4 (0,1,2,3,4). 
		 * Each philosopher sets the seed to the random number generator as seed+id. 
		 * The seed for each philosopher is as follows:
		 * 	 	P0.seed = 100 + P0.id = 100 + 0 = 100
		 * 		P1.seed = 100 + P1.id = 100 + 1 = 101
		 * 		P2.seed = 100 + P2.id = 100 + 2 = 102
		 * 		P3.seed = 100 + P3.id = 100 + 3 = 103
		 * 		P4.seed = 100 + P4.id = 100 + 4 = 104
		 * Therefore, if the ids of the philosophers are not 0,1,2,3,4 then different random numbers will be generated.
		 */
		
		randomGenerator.setSeed(id+seed);
	}
	public int getId() {
		return id;
	}

	public double getAverageThinkingTime() {
		/* TODO
		 * Return the average thinking time
		 * Add comprehensive comments to explain your implementation
		 */
		if(thinkingTime==0)
			return 0;
		return getTotalThinkingTime()/numberOfThinkingTurns;
	}

	public double getAverageEatingTime() {
		/* TODO
		 * Return the average eating time
		 * Add comprehensive comments to explain your implementation
		 */
		if(eatingTime==0)
			return 0;
		return getTotalEatingTime()/numberOfEatingTurns;
	}

	public double getAverageHungryTime() {
		/* TODO
		 * Return the average hungry time
		 * Add comprehensive comments to explain your implementation
		 */
		if(hungryTime==0)
			return 0;
		return getTotalHungryTime()/numberOfHungryTurns;

	}
	
	public int getNumberOfThinkingTurns() {
		return numberOfThinkingTurns;
	}
	
	public int getNumberOfEatingTurns() {
		return numberOfEatingTurns;
	}
	
	public int getNumberOfHungryTurns() {
		return numberOfHungryTurns;
	}

	public double getTotalThinkingTime() {
		return thinkingTime;
	}

	public double getTotalEatingTime() {
		return eatingTime;
	}

	public double getTotalHungryTime() {
		return hungryTime;
	}

	public void status(boolean isFull){
		this.isFull=isFull;
	}


	@Override
	public void run() {
		/* TODO
		 * (Initialize some additional variables, if necessary)
		 *
		 * Think,
		 * Get hungry,
		 * Pick up the left and then the right chopstick,
		 * Eat,
		 * Release the chopsticks.
		 * ^^^ Repeat until the thread is interrupted
		 *
		 * Increment the thinking/hungry/eating turns counter *when each turn starts*..
		 *
		 * Update the duration of each turn *after the turn is completely finished*.
		 * Keep track of total hungry turn durations by getting the current timestamp with System.currentTimeMillis()
		 * when the turn starts, then another System.currentTimeMillis() after the turn has finished, and subtracting these.
		 * For thinking and eating turns, use the duration generated with randomGenerator.nextInt(1000).
		 *
		 * If DEBUG is true, print the log messages for each event.
		 * Additionally, you might want to print a message such as "philosopher X has finished" when the thread terminates
		 * (for debugging purposes).
		 *
		 *
		 * Add comprehensive comments to explain your implementation, including deadlock prevention/detection.
		 * You should start with a straightforward implementation, but you will eventually have to make it more sophisticated
		 * w.r.t the order (and conditions) of the actions and the threads synchronization in order to pass the tests with the expected results!

		public boolean pickedUp(){

		}


		public void putDown(){

		}
 */

		try {
		while(DEBUG) {
			long start = System.currentTimeMillis();

			//philosopher thinking
			think();
			activity = 1;
			printMsg();
			TimeUnit.SECONDS.sleep(2);
			//philosopher hungry
			Hungry();
			activity = 2;
			printMsg();
			//picks up left chopstick if available
			//System.exit(0);

			if(leftChopstick.getLock().tryLock(10, TimeUnit.MILLISECONDS)) {
				activity = 4;
				printMsg();
				//picks up right chopstick if available
				if (rightChopstick.getLock().tryLock(10, TimeUnit.MILLISECONDS)) {
					activity = 5;
					printMsg();
					long end = System.currentTimeMillis();
					hungryTime += start - end;
					eat();
					activity = 3;
					printMsg();

					//releases right chopstick if available
					rightChopstick.getLock().unlock();
					activity = 7;
					printMsg();
				}

				//releases left chopstick if available
				leftChopstick.getLock().unlock();
				activity = 6;
				printMsg();
			}
		}

			} catch (InterruptedException e) {
				e.printStackTrace();
			}
        //System.out.println("P"+getId()+" Something" );
	}

		private void think() throws InterruptedException {
			int ran =randomGenerator.nextInt(1000);
			// random number 0-999 thinking time.
			thinkingTime += ran;
			//increase number of thinking turns.
		    numberOfThinkingTurns++;
		    Thread.sleep(ran);
		    activity=1;
		}

		private void Hungry(){
		//increase number of hungary turns.
			numberOfHungryTurns++;
			activity=2;
		}

		private void eat() throws InterruptedException {
			int ran =randomGenerator.nextInt(1000);
			// random number 0-999 eating time.
			eatingTime+=ran;
			//increase number of eating turns.
			eatingTime++;
			Thread.sleep(ran);
			activity=3;
		}


		public void printMsg(){
		if(  DEBUG== true){
			if(activity == 1){
				System.out.println("Philosopher "+this.getId()+" is THINKING ");
			}
			if(activity == 2){
				System.out.println("Philosopher "+this.getId()+" is HUNGRY ");
			}
			if(activity == 3){
				System.out.println("Philosopher "+this.getId()+" is EATING ");
			}
			if(activity == 4){
				System.out.println("Philosopher "+this.getId()+" picked-up "+leftChopstick.getId()+ "- Left Chopstick");
			}
			if(activity == 5){
				System.out.println("Philosopher "+this.getId()+" picked-up "+rightChopstick.getId()+ "- Right Chopstick");
			}
			if(activity == 6){
				System.out.println("Philosopher "+this.getId()+" released "+leftChopstick.getId()+ "- Left Chopstick");
			}
			if(activity == 7){
				System.out.println("Philosopher "+this.getId()+" released "+rightChopstick.getId()+ "- Right Chopstick");
			}

		}


		}




}
