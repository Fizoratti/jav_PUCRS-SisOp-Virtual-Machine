package tasks;

import hardware.memory.Word;
import software.Programs;

import util.Auxiliar;
import util.Console;

import virtualmachine.VM;

public class TrapInTask implements Task {
    public void run() {                             Console.debug(" > TrapInTask.run()");
        Auxiliar aux = new Auxiliar();
        Word[] p = new Programs().trapIn;
        aux.cargaProgramaParaMemoria(p, VM.get().memory);
        VM.get().cpu.setContext(0);
        Console.log("\n---------------------------------- programa TRAP IN ");
        aux.dumpMemoria(VM.get().memory, 4, 5);
        VM.get().cpu.run();
        Console.log("\n---------------------------------- após execucao ");
        aux.dumpMemoria(VM.get().memory, 4, 5);
        
    }
}
