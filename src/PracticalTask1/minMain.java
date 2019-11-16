package PracticalTask1;
import java.util.ArrayList;

public class minMain {
    public static void main(String[] args) {
        ArrayList<Process> processes= new ArrayList<>();

     /*
        //test3
        processes.add(new Process(1, 0, 4));
        processes.add(new Process(2, 2, 3));
        processes.add(new Process(3, 1, 1));
        processes.add(new Process(4, 3, 2));
        processes.add(new Process(5, 4, 5));

        //test 6
        processes.add(new Process(1, 0, 18));
        processes.add(new Process(2, 2, 5));
        processes.add(new Process(3, 4, 7));

        //test1
        processes.add(new Process(1, 0, 2));
        processes.add(new Process(2, 3, 1));
        processes.add(new Process(3, 5, 6));

        //text 2
        processes.add(new Process(1, 0, 4));
        processes.add(new Process(2, 1, 3));
        processes.add(new Process(3, 2, 1));
        processes.add(new Process(4, 3, 2));
        processes.add(new Process(5, 4, 5));

        //test4
        processes.add(new Process(1, 0, 18));
        processes.add(new Process(2, 0, 5));
        processes.add(new Process(3, 0, 7));

    */


        //test5
        processes.add(new Process(1, 0, 18));
        processes.add(new Process(2, 3, 2));
        processes.add(new Process(3, 25, 5));
        processes.add(new Process(4, 29, 2));
        processes.add(new Process(5, 33, 7));



    /*
    Call the other class First Come First Serve to enable the execution print out the
    printTable and printGanttChart().
    */
        FCFS temp = new FCFS(processes);
        temp.run();
        temp.printTable();
        temp.printGanttChart();


    }
}
