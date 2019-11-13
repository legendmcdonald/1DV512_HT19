/*
 * File:	Process.java
 * Course: 	Operating Systems
 * Code: 	1DV512
 * Author: 	Suejb Memeti
 * Date: 	November, 2018
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import static java.util.Collections.*;

public class FCFS {


	// The list of processes to be scheduled
	public ArrayList<Process> processes;


	// Class constructor
	public FCFS(ArrayList<Process> processes) {
		this.processes = processes;
	}


	public void run() {
		// TODO Implement the FCFS algorithm here
        /*loop through the process, sort and the set;
		-Completion Time
	    -Turn Around Time
		-Waiting Time
*/

		for (int i = 0; i < processes.size(); i++) {
			Process temp = processes.get(i);

			//sorts all the process in ascending order
			sort(processes, new sort());

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
		if (wt.getTurnaroundTime() < 0) {
			System.out.println("Can not calculate Waiting Time!");
		}
		return wt.getTurnaroundTime() - wt.getBurstTime();
	}


	//function to calculate turn around time
	private int findTurnAroundTime(Process tat) {

		//System.out.println(tat.getCompletedTime() -tat.getArrivalTime());
		if (tat.getCompletedTime() < 0) {
			System.out.println("Can not calculate Turn Around Time!");
		}
		return tat.getCompletedTime() - tat.getArrivalTime();

	}


	/*To calculate the completed time for processes
	we check to conditions below.
	* */
	private int FCT;

	private int findCompTime(Process ct) {
		if (FCT <= ct.getArrivalTime()) {
			FCT = ct.getArrivalTime() + ct.getBurstTime();
		} else {
			FCT = FCT + ct.getBurstTime();
		}
		return FCT;
	}

	public class sort implements Comparator<Process> {
		// Used for sorting in ascending order of
		@Override
		public int compare(Process p1, Process p2) {

			return p1.getArrivalTime() - p2.getArrivalTime();
		}


	}

	public void printTable() {
		// TODO Print the list of processes in form of a table here
		System.out.print("-------------------------------------------\n");
		System.out.println("PID     AT      BT       CT     TAT     WT");
		for (int i = 0; i < processes.size(); i++) {
			Process temp = processes.get(i);

			System.out.println(" " + temp.getProcessId() + "\t\t" + temp.getArrivalTime() + "\t\t" + temp.getBurstTime() + "\t\t "
					+ temp.getCompletedTime() + "\t\t" + temp.getTurnaroundTime() + "\t\t" + temp.getWaitingTime());

		}
		System.out.print("-------------------------------------------\n");
	}


	public void printGanttChart() {
		// TODO Print the demonstration of the scheduling algorithm using Gantt Chart
		String Equ="";
		String slash="";
		String cpt="";


		System.out.println("\n\n%%%%%%%%%%% GRANTT CHART %%%%%%%%%%\n");
		//
		for (int i=0; i<processes.size(); i++){
			Process temp = processes.get(i);

			if(i==0){
				slash+="|";
				cpt += "0";



			}else {
				if (processes.get(i - 1).completedTime < temp.arrivalTime) {
					int help = temp.getArrivalTime() - processes.get(i-1).completedTime;
					for (int j = 0; j < help; j++) {
                        slash = slash + "≠";
                        cpt = cpt + ' ';


					}
                    slash = slash + "||";
                    cpt = cpt + temp.arrivalTime;
				}

			}

			for (int j=0; j<temp.getBurstTime()*2; j++) {
				if (j == temp.getBurstTime()-1){
					slash = slash + (" P" + temp.processId);
					cpt = cpt + "   ";
					j++;
				}

                slash = slash + " ";
                cpt = cpt + " ";
			}

					if(temp.completedTime<10)  {
                        cpt = cpt + (temp.completedTime + " ");
					}

				/*	if(i == processes.size()-1) {
				    slash = slash +"|";
			        }

				 */

					else{
                        cpt = cpt + (temp.completedTime);
					}

					slash = slash + "||";

			}

		for(int i=0;i<slash.length();i++){
            Equ = Equ + "=";
				}

		System.out.println(Equ);
		System.out.println(slash);
		System.out.println(Equ);
		System.out.println(cpt);

		System.out.println("\n'≠' indicates the CPU is waiting for processes");


	}
}