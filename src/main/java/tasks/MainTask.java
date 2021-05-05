package tasks;

import hardware.memory.Word;
import software.PCB;
import software.Programs;

import util.Console;

import virtualmachine.VM;

public class MainTask implements Task {

    public void run() {                             Console.debug(" > MainTask.run()");

        Word[] fibonacci10 = Programs.fibonacci10;

        PCB process = VM.get().pm.create(fibonacci10);

        VM.get().escalonador.run();

        VM.get().pm.finish(process); // Esta linha não tá rodando

    }

}
