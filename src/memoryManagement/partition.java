package memoryManagement;
public class partition {

    public int name;
    public String n = "partition";
    public int size;
    private Process p;

    public boolean occupied = false;
    public boolean externalFragment = true;


    public partition(int name, int size) {
        this.name = name;
        this.size = size;
    }

    public void allocateWF(Process p){
        this.p = p;
        this.size = p.getSize();
        p.setAllocated();
        externalFragment = false;
        occupied = true;
    }


    public Process getProcess(){
        return p;
    }
}