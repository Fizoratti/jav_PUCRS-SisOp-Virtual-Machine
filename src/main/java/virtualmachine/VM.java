package virtualmachine;

import hardware.memory.Word;
import hardware.processor.CPU;
import hardware.processor.Opcode;

// ------------------- V M - constituida de CPU e MEMORIA ------------------------------------------------
// -------------------------- atributos e construcao da VM -----------------------------------------------

public class VM {
    private static VM INSTANCE;

    public int memorySize;
    public Word[] memory;
    public CPU cpu;

    public VM() {
        memorySize = 1024;
        memory = new Word[memorySize];
        cleanMemory();
        cpu = new CPU(memory);
    }

    /**
     * Cria uma instância única para a classe VM.
     */
    public static void init() {
        if (INSTANCE == null) INSTANCE = new VM();
    }

    /**
     * @return instância única da VM.
     */
    public static VM get() {
        return INSTANCE;
    }

    private void bootstrap() {
        // kernel, OS e drivers
    }

    public void cleanMemory() {
        for (int i = 0; i < memorySize; i++) {
            memory[i] = new Word(Opcode.___, -1, -1, -1);
        }
    }

}
