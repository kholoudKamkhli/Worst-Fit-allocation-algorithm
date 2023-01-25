package memoryManagement;
public class Process {

    public String name;
    private int size;
    public boolean allocated = false;

    public Process(String name, int size) {
        this.name = name;
        this.size = size;
    }
    public int getSize(){
        return size;
    }
    public void setAllocated(){
        this.allocated = true;
    }
}