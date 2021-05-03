package tasks;

import hardware.memory.Memory;
import hardware.memory.Word;
import software.Programs;

import util.Auxiliar;
import util.Console;

import virtualmachine.VM;

public class FibonacciTask implements Task {
    public void run() {                             Console.debug(" > FibonacciTask.run()");
        Auxiliar aux = new Auxiliar();
        Word[] p = new Programs().fibonacci;
        aux.cargaProgramaParaMemoria(p, Memory.get().data);
//        VM.get().cpu.setContext(0);
        Console.log("\n---------------------------------- programa fibonacci carregado ");
        aux.dumpMemoria(Memory.get().data, 0, 16);
        VM.get().cpu.run();
        Console.log("\n---------------------------------- após execucao ");
        aux.dumpMemoria(Memory.get().data, 27, 40);
    }
}
