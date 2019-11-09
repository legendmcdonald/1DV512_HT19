/*
 * File:	Process.java
 * Course: 	Operating Systems
 * Code: 	1DV512
 * Author: 	Suejb Memeti
 * Date: 	November, 2018
 */

import java.util.ArrayList;

public class FCFS{

	private int FCT;
	// The list of processes to be scheduled
	public ArrayList<Process> processes;


	// Class constructor
	public FCFS(ArrayList<Process> processes) {
		this.processes = processes;
	}



	public void run() {
		// TODO Implement the FCFS algorithm here
        /*loop through the process and the set as follows.
		-Completion Time
	    -Turn Around Time
		-Waiting Time
         */
		for(int i=0; i<processes.size(); i++){
			Process temp=processes.get(i);

			//set the the time for a process to be completed
			temp.setCompletedTime(findCompTime(temp));
			//set the turn around time
			temp.setTurnaroundTime(findTurnAroundTime(temp));
			//waiting time for the next process
			temp.setWaitingTime(findWaitingTime(temp));

		}



	}

	//function to find the waiting time for all processes
	private int findWaitingTime(Process wt) {
		int FWT =wt.getTurnaroundTime()-wt.getBurstTime();
		return FWT;
	}


	//function to calculate turn around time
	private int findTurnAroundTime (Process tat){
		int FTAT= tat.getCompletedTime()-tat.getArrivalTime();
		return FTAT;
	}

	/*To calculate the completed time for processes
	we check to conditions below.
	* */
	private int findCompTime( Process ct){

		if (FCT < ct.getArrivalTime()) {
			FCT=ct.getArrivalTime()+ct.getBurstTime();
		}
			else{

			FCT = FCT + ct.getBurstTime();
			}

		return FCT;
	}

	private int sort(Process sort) {

		return 0;
	}

	public void printTable() {
		// TODO Print the list of processes in form of a table here

	}

	public void printGanttChart() {
		// TODO Print the demonstration of the scheduling algorithm using Gantt Chart

	}

}
