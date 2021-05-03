package virtualmachine;

import devices.Device;
import devices.Display;
import hardware.memory.Memory;
import hardware.memory.Word;
import hardware.processor.CPU;
import hardware.processor.Opcode;
import software.Escalonador;
import software.ProcessManager;

// ------------------- V M - constituida de CPU e MEMORIA ------------------------------------------------
// -------------------------- atributos e construcao da VM -----------------------------------------------

public class VM {
    private static VM INSTANCE;

    public int memorySize;
    public CPU cpu;
    public ProcessManager pm;
    public Escalonador escalonador;
    public Device monitor;

    public VM() {
        memorySize = 1024;
        Memory.init(memorySize);

        cpu = new CPU(Memory.get());
        cpu.programCounter = 0; // APAGAR

        pm = new ProcessManager();

        escalonador = new Escalonador();



//        monitor = new Display();
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

}
