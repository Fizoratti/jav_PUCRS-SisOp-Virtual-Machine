package tasks;

import hardware.memory.Word;
import software.Programs;

import util.Auxiliar;
import util.Console;

import virtualmachine.VM;

public class Fibonacci10Task implements Task {
    public void run() {                             Console.debug(" > Fibonacci10Task.run()");
        Auxiliar aux = new Auxiliar();
        Word[] p = new Programs().fibonacci10;
        aux.cargaProgramaParaMemoria(p, VM.get().memory);
        VM.get().cpu.setContext(0);
        Console.log("\n---------------------------------- programa fibonacci_10 carregado ");
        aux.dumpMemoria(VM.get().memory, 0, 17);
        VM.get().cpu.run();
        Console.log("\n---------------------------------- após execucao ");
        aux.dumpMemoria(VM.get().memory, 18, 27);
    }
}
