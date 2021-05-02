package tasks;

import hardware.memory.Memory;
import hardware.memory.Word;
import software.Programs;

import util.Auxiliar;
import util.Console;

import virtualmachine.VM;

public class TrapInTask implements Task {
    public void run() {                             Console.debug(" > TrapInTask.run()");
        Auxiliar aux = new Auxiliar();
        Word[] p = new Programs().trapIn;
        aux.cargaProgramaParaMemoria(p, Memory.get().data);
        VM.get().cpu.setContext(0);
        Console.log("\n---------------------------------- programa TRAP IN ");
        aux.dumpMemoria(Memory.get().data, 4, 5);
        VM.get().cpu.run();
        Console.log("\n---------------------------------- após execucao ");
        aux.dumpMemoria(Memory.get().data, 4, 5);
        
    }
}
