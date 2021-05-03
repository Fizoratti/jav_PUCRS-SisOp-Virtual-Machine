package tasks;

import hardware.memory.Memory;
import hardware.memory.Word;
import software.Programs;

import util.Auxiliar;
import util.Console;

import virtualmachine.VM;

public class MainTask implements Task {

    public void run() {                             Console.debug(" > Fibonacci10Task.run()");
        Auxiliar aux = new Auxiliar();
        Word[] p = new Programs().fibonacci10;
        aux.cargaProgramaParaMemoria(p, Memory.get().data);
        VM.get().cpu.setContext(0);
        Console.log("\n---------------------------------- programa fibonacci_10 carregado ");
        aux.dumpMemoria(Memory.get().data, 0, 17);
        VM.get().cpu.run();
        Console.log("\n---------------------------------- após execucao ");
        aux.dumpMemoria(Memory.get().data, 18, 27);
    }


}
