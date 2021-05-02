package software;

import hardware.memory.Word;

import java.util.ArrayList;
import java.util.LinkedList;

public class ProcessManager {
    public MemoryManager mm;
    private LinkedList<PCB> pcbList;
    private static int process_id = 0;

    public void criaProcesso(Word[] p) {
        ArrayList<Integer> allocatedPages = mm.allocate(p);
        PCB processo = new PCB(process_id, allocatedPages);
        process_id++;
        pcbList.add(processo);
    }

    public void finalizaProcesso (PCB processo){
        mm.unallocate(processo.getAllocatedPages());
        pcbList.remove(processo);
    }

}
