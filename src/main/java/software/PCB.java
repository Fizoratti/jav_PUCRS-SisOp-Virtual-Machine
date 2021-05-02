package software;

import java.util.ArrayList;

public class PCB {
    private int id;
    ArrayList<Integer> allocatedPages;
//    private Context context;

    public PCB(int id, ArrayList<Integer> allocatedPages) {
        this.id = id;
        this.allocatedPages = allocatedPages;
//        this.context = new Context(0,1024,allocatedPages,new int[8], 0, new Word(Opcode.___,-1,-1,-1));
    }

    public ArrayList<Integer> getAllocatedPages() {
        return this.allocatedPages;
    }

    public int getId() {
        return this.id;
    }

//    public Context getContext() {
//        return context;
//    }

//    public void saveContext(Context context){
//        this.context = context;
//    }
}
