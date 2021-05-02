package tasks;

import hardware.memory.Word;
import software.Programs;

import util.Auxiliar;
import util.Console;

import virtualmachine.VM;

public class FactorialTask implements Task {
    public void run() {                             Console.debug(" > FactorialTask.run()");
        Auxiliar aux = new Auxiliar();
        Word[] p = new Programs().factorial;
        aux.cargaProgramaParaMemoria(p, VM.get().memory);
        VM.get().cpu.setContext(0);
        Console.log("\n---------------------------------- programa fatorial carregado ");
        aux.dumpMemoria(VM.get().memory, 0, 27);
        VM.get().cpu.run();
        Console.log("\n---------------------------------- após execucao ");
        aux.dumpMemoria(VM.get().memory, 30, 32);
    }
}
