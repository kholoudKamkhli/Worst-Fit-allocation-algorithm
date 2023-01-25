package memoryManagement;

import java.util.ArrayList;
import java.util.Scanner;

public class WorstFit {
    Scanner scanner = new Scanner(System.in);
    static ArrayList<Process> unallocatedProcesses = new ArrayList<Process>();
    private ArrayList<Process> processes = new ArrayList<Process>();
    private ArrayList<partition> partitions = new ArrayList<partition>();
    public int partitionNum;

    WorstFit(ArrayList<Process> processes, ArrayList<partition> partitions ){
        this.processes = processes;
        this.partitions = partitions;

        allocateProcesses(this.partitions, this.processes, partitions.size());
        System.out.println("Do you want to campact? 1.yes 2.no");
        int compact = scanner.nextInt();
        if(compact == 1){

            if(unallocatedProcesses.size()>0){
                compact(unallocatedProcesses);
            }

        }
    }


    public void allocateProcesses(ArrayList<partition> par, ArrayList<Process> proc, int sz) {
        partitionNum = sz;
        for(int i=0; i<proc.size(); i++){
            partition p = getMax(par);
            boolean flag = false;

            if(p != null){
                if(p.size > proc.get(i).getSize()){
                    int Index = par.indexOf(p) + 1;
                    int partitionSize = (p.size - proc.get(i).getSize());
                    partition newPartition = new partition(partitionNum, partitionSize);
                    par.add(Index, newPartition);
                    partitionNum += 1;
                    p.allocateWF(proc.get(i));
                    flag = true;
                }
                else if(p.size == proc.get(i).getSize()){
                    p.allocateWF(proc.get(i));
                    flag = true;
                }
            }
            if (!flag) {
                unallocatedProcesses.add(proc.get(i));
            }
        }
        print(par, proc);

//		System.out.println("Do you want to campact? 1.yes 2.no");
//    	int compact = scanner.nextInt();
//    	if(compact == 1){
//
//    		if(unallocatedProcesses.size()>0){
//    			compact(unallocatedProcesses);
//    		}
//
//    	}
    }

    public void compact(ArrayList<Process> processes){
        int name = partitions.size();
        int sz=0;
        ArrayList<partition> newPartition = new ArrayList<partition>();
        ArrayList<Process> process = new ArrayList<>(processes);

        for (int i = 0; i < partitions.size(); i++) {

            if (partitions.get(i).externalFragment) {
                sz += partitions.get(i).size;

            }
            else
                newPartition.add(partitions.get(i));
        }
        partition merged = new partition(name, sz);
        newPartition.add(merged);
        unallocatedProcesses.clear();
        allocateProcesses(newPartition, process, name+1);

    }


    private partition getMax(ArrayList<partition> partitions) {
        partition maxPartition = partitions.get(0);
        for(int i=1; i<partitions.size(); i++){
            if(partitions.get(i).size > maxPartition.size && !(partitions.get(i).occupied)){
                maxPartition = partitions.get(i);
            }
        }
        if(!(maxPartition.occupied))
            return maxPartition;
        else
            return null;
    }

    public void print(ArrayList<partition> par, ArrayList<Process> proc){
        for(int i=0; i<par.size(); i++){
            if(par.get(i).externalFragment){

                System.out.println("partition " +par.get(i).name + "(" +par.get(i).size+" KB) => External fragment");
            }

            if(!(par.get(i).externalFragment)){
                System.out.println("partition "+par.get(i).name + "(" + par.get(i).size+" KB) => "+par.get(i).getProcess().name);
            }
        }

        for(int i=0; i<proc.size(); i++){
            if(!(proc.get(i).allocated))
                System.out.println("process "+(i+1)+" can not be allocated.");
        }
    }



}
