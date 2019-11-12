import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<Process> processes= new ArrayList<>();



        /*
        processes.add(new Process(1, 0, 18));
        processes.add(new Process(2, 2, 5));
        processes.add(new Process(3, 4, 7));
        processes.add(new Process(1, 0, 2));
        processes.add(new Process(2, 3, 1));
        processes.add(new Process(3, 5, 6));

        //test1
        processes.add(new Process(1, 0, 2));
        processes.add(new Process(2, 3, 1));
        processes.add(new Process(3, 5, 6));
*/

        //test5
        processes.add(new Process(1, 0, 18));
        processes.add(new Process(2, 3, 2));
        processes.add(new Process(3, 25, 5));
        processes.add(new Process(4, 29, 2));
        processes.add(new Process(5, 33, 7));






        FCFS temp = new FCFS(processes);
        temp.run();
        temp.printTable();
        temp.printGanttChart();


    }
}
