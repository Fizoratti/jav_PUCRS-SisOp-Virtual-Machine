package tasks;

import hardware.memory.Memory;
import hardware.memory.Word;
import software.Programs;

import util.Auxiliar;
import util.Console;

import virtualmachine.VM;

public class FactorialTask implements Task {
    public void run() {                             Console.debug(" > FactorialTask.run()");
        Auxiliar aux = new Auxiliar();
        Word[] p = new Programs().factorial;
        aux.cargaProgramaParaMemoria(p, Memory.get().data);
//        VM.get().cpu.setContext(0);
        Console.log("\n---------------------------------- programa fatorial carregado ");
        aux.dumpMemoria(Memory.get().data, 0, 27);
        VM.get().cpu.run();
        Console.log("\n---------------------------------- após execucao ");
        aux.dumpMemoria(Memory.get().data, 30, 32);
    }
}
