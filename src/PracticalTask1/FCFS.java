/*
 * File:	Process.java
 * Course: 	Operating Systems
 * Code: 	1DV512
 * Author: 	Suejb Memeti
 * Date: 	November, 2018
 */
package PracticalTask1;
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
		if (tat.getCompletedTime() < 0)  {
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

	//A class to compare the processes arriving time to enable arrival time in ascending order.
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
		//loop through the all the processes
		for (int i=0; i<processes.size(); i++){
			Process temp = processes.get(i);

            //if any the process is equal to 0 then do the print outs.
			if(i==0){
				slash+="|";//print out
				cpt += "0";//print out

			/*
			This loop is to print "≠" when the CPU is idle and waiting for a process.
			 */
			}else {
				if (processes.get(i - 1).completedTime < temp.arrivalTime) {
					int help = temp.getArrivalTime() - processes.get(i-1).completedTime;
					for (int j = 0; j < help; j++) {
                        slash = slash + "≠";
                        cpt = cpt + ' ';
					}
					//print for the arrivals time with "||".
                    slash = slash + "||";
                    cpt = cpt + temp.arrivalTime;
				}

			}

            /*
			This loop is to print the all process ID's.
			 */
			for (int j=0; j<temp.getBurstTime()*2; j++) {//multiplied to make the chart long and nice.
				if (j == temp.getBurstTime()){
					slash = slash + (" P" + temp.processId);
					cpt = cpt + "   ";
					j++;
				}
				//prints these if there is no process ID to be printed.
                slash = slash + " ";
                cpt = cpt + " ";
			}

			 /*
			 if the Completed time is a single integer then print empty string after it.
			 */
					if(temp.completedTime<10)  {
                        cpt = cpt + (temp.completedTime + " ");
					}
				/*	if(i == processes.size()-1) {
				    slash = slash +"|";
			        }
				 */
				//print all the Completed time and the string below
					else{
                        cpt = cpt + (temp.completedTime);
					}
					slash = slash + "||";
			}
		/*
		This loop is to print "=" as long as the slash "||" are printed.
		 */
		for(int i=0;i<slash.length();i++)
		{
            Equ = Equ + "=";
		}

        /*
		Print out the chart as follows so it prints it perfectly.
		 */
		System.out.println(Equ);
		System.out.println(slash);
		System.out.println(Equ);
		System.out.println(cpt);
		System.out.println("\n'≠' indicates the CPU is idle and waiting for processes");
	}
}