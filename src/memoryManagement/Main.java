package memoryManagement;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        ArrayList<partition> blocks = new ArrayList<partition>();
        ArrayList<Process> processes = new ArrayList<Process>();

        System.out.println("Enter number of partitions: ");
        Scanner scanner = new Scanner(System.in);

        int size = scanner.nextInt();

        for (int i = 0; i < size; i++) {

            System.out.println("Enter number of each partition: ");
            int size_parition = scanner.nextInt();

            partition p = new partition(i, size_parition);
            blocks.add(p);
        }

        System.out.println("Enter number of processes: ");
        int size2 = scanner.nextInt();

        for (int i = 0; i < size2; i++) {
            System.out.println("Enter name of process: ");
            String name_process = scanner.next();

            System.out.println("Enter size of process: ");
            int size_process = scanner.nextInt();

            Process p = new Process(name_process, size_process);
            processes.add(p);
        }


                WorstFit wf = new WorstFit(processes, blocks);

        }
    }

