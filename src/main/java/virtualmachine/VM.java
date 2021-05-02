package virtualmachine;

import devices.Device;
import devices.Display;
import hardware.memory.Memory;
import hardware.memory.Word;
import hardware.processor.CPU;
import hardware.processor.Opcode;

// ------------------- V M - constituida de CPU e MEMORIA ------------------------------------------------
// -------------------------- atributos e construcao da VM -----------------------------------------------

public class VM {
    private static VM INSTANCE;

    public int memorySize;
//    public Word[] memory;
    public Memory memory;
    public CPU cpu;

    public Device monitor;

    public VM() {
        memorySize = 1024;
//        memory = new Word[memorySize];
        Memory.init(memorySize);
        memory = Memory.get();
        cleanMemory();
        cpu = new CPU(memory);

        monitor = new Display();
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
            memory.data[i] = new Word(Opcode.___, -1, -1, -1);
        }
    }

}
