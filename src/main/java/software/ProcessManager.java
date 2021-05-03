package software;

import hardware.memory.Word;

import java.util.LinkedList;
import java.util.Queue;

public class ProcessManager {
    public MemoryManager mm;
    public Queue<PCB> pcbList;
    public int process_id = 0;

    public ProcessManager() {
        this.mm = new MemoryManager();
        this.pcbList = new LinkedList<>();
    }

    public void criaProcesso(Word[] p) {
        if (mm.temEspacoParaAlocar(p.length)) {
            PCB processControlBlock = new PCB(process_id, mm.allocate(p));
            process_id++;

            pcbList.add(processControlBlock);
        } else {
            System.out.println("Sem espaco");
        }
    }

    public void finalizaProcesso(PCB processo) {
        mm.unallocate(processo.getAllocatedPages());
        pcbList.remove(processo);
    }


}
