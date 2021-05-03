package software;

import hardware.memory.Word;

import java.util.ArrayList;

public class ProcessManager {
    public MemoryManager mm;
    private ArrayList<PCB> pcbList;
    private static int process_id = 0;

    public ProcessManager() {
        this.mm = new MemoryManager();
        this.pcbList = new ArrayList<>();
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
