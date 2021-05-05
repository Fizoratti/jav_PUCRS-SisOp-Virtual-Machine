package virtualmachine;

import hardware.memory.Memory;
import hardware.processor.CPU;
import software.Escalonador;
import software.ProcessManager;
import util.Console;

// ------------------- V M - constituida de CPU e MEMORIA ------------------------------------------------
// -------------------------- atributos e construcao da VM -----------------------------------------------

public class VM {
    private static VM INSTANCE;

    public int memorySize;
    public CPU cpu;
    public ProcessManager pm;
    public Escalonador escalonador;

    public VM() {
        // Inicializa Memória e CPU (Hardware)

        memorySize = 1024;
        Memory.init(memorySize);

        cpu = CPU.init();
        cpu.programCounter = 0;         // Alterar

        bootstrap();
    }


    private void bootstrap() {              Console.debug(" > VM.bootstrap() ");
        // Inicializa kernel, OS e drivers (Software)

        ProcessManager.init();
        Escalonador.init();

        pm = ProcessManager.get();
        escalonador = Escalonador.get();

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

}
