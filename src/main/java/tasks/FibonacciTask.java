package tasks;

import hardware.memory.Word;
import software.Programs;

import util.Auxiliar;
import util.Console;

import virtualmachine.VM;

public class FibonacciTask implements Task {
    public void run() {                             Console.debug(" > FibonacciTask.run()");
        Auxiliar aux = new Auxiliar();
        Word[] p = new Programs().fibonacci;
        aux.cargaProgramaParaMemoria(p, VM.get().memory);
        VM.get().cpu.setContext(0);
        Console.log("\n---------------------------------- programa fibonacci carregado ");
        aux.dumpMemoria(VM.get().memory, 0, 16);
        VM.get().cpu.run();
        Console.log("\n---------------------------------- após execucao ");
        aux.dumpMemoria(VM.get().memory, 27, 40);
    }
}
