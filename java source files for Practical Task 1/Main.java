import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<Process> processes= new ArrayList<>();



        processes.add(new Process(1, 0, 4));
        processes.add(new Process(2, 1, 3));
        processes.add(new Process(3, 2, 1));
        processes.add(new Process(4, 3, 2));
        processes.add(new Process(5, 4, 5));

        FCFS help = new FCFS(processes);
        help.run();
        help.printTable();
        help.printGanttChart();


    }
}
