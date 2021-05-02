package tasks;

import hardware.memory.Word;
import software.Programs;

import util.Auxiliar;
import util.Console;

import virtualmachine.VM;

public class BubbleSortTask implements Task {
    public void run() {                             Console.debug(" > BubbleSortTask.run()");
        Auxiliar aux = new Auxiliar();
        Word[] p = new Programs().bubbleSort;
        aux.cargaProgramaParaMemoria(p, VM.get().memory.data);
        VM.get().cpu.setContext(0);
        Console.log("\n---------------------------------- programa bubbleSort carregado ");
        aux.dumpMemoria(VM.get().memory.data, 0, 39);
        VM.get().cpu.run();
        Console.log("\n---------------------------------- após execucao ");
        aux.dumpMemoria(VM.get().memory.data, 39, 50);
    }
}
