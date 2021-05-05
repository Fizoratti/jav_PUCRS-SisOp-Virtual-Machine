package software;

import hardware.memory.Word;
import util.Console;

import java.util.LinkedList;
import java.util.Queue;

public class ProcessManager {
    public MemoryManager mm;
    public Queue<PCB> pcbList;
    public int processId = 0;

    public ProcessManager() {
        this.mm = new MemoryManager();
        this.pcbList = new LinkedList<>();
    }

    /**
     * 
     * @param p programa. Ex.: bubbleSort
     * @return referência para o novo processo criado 
     */
    public PCB createProcess(Word[] p) {            Console.debug(" > ProcessManager.createProcess()");
        PCB processControlBlock;

        if (mm.temEspacoParaAlocar(p.length)) {
            processControlBlock = new PCB(processId, mm.allocate(p));
            ++processId;

            pcbList.add(processControlBlock);
        } else {
            Console.error("Sem espaço na memória para criar o processo de ID:"+processId);
            processControlBlock = null;
        }

        return processControlBlock;
    }

    public void endProcess(PCB processo) {          Console.debug(" > ProcessManager.endProcess()");
        mm.unallocate(processo.getAllocatedPages());
        pcbList.remove(processo);
    }

    public PCB getProcess(int id) {
        if (pcbList.peek().getId() == id) {
            return pcbList.peek();
        } else {
            Console.error("Não foi possível encontrar o processo de ID:"+processId);
            return null;
        }
    }
}