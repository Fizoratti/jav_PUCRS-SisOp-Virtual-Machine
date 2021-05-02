package tasks;

import hardware.memory.Word;
import software.Programs;

import util.Auxiliar;
import util.Console;

import virtualmachine.VM;

public class TrapOutTask implements Task {
    public void run() {                             Console.debug(" > TrapOutTask.run()");
        Auxiliar aux = new Auxiliar();
        Word[] p = new Programs().trapOut;
        aux.cargaProgramaParaMemoria(p, VM.get().memory.data);
        VM.get().cpu.setContext(0);
        Console.log("\n---------------------------------- programa TRAP OUT ");
        aux.dumpMemoria(VM.get().memory.data, 10, 11);
        VM.get().cpu.run();
        Console.log("\n---------------------------------- após execucao ");
        aux.dumpMemoria(VM.get().memory.data, 10, 11);
        
    }
}
