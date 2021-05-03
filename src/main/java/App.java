import hardware.memory.Word;
import software.Programs;
import virtualmachine.VM;

import tasks.Tasks;

import util.Console;
import util.Menu;

public class App {
    public static void main(String[] args) {
        Console.info("Iniciando Máquina Virtual... "); Console.wait(1200);
        
        VM.init();

        Word[] fibonacci10 = Programs.fibonacci10;

        VM.get().pm.criaProcesso(fibonacci10);


//         Menu.get().showMenu();

//        Tasks.get().mainTask.run();

//        Tasks.get().fibonacci10.run();
//        Tasks.get().fibonacci.run();
//        Tasks.get().factorial.run();
//        Tasks.get().bubbleSort.run();
        // Tasks.get().trapIn.run();
        // Tasks.get().trapOut.run();
    }
}

