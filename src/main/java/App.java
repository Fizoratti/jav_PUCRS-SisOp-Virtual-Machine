import hardware.memory.Word;
import software.Programs;
import virtualmachine.VM;

import tasks.Tasks;

import util.Console;
import util.Menu;

public class App {
    public static void main(String[] args) {
        // Console.info("Iniciando Máquina Virtual... "); Console.wait(1200);
        
        // VM.init();

        // Menu.get().showMenu();

        // Tasks.get().mainTask.run();

        // Tasks.get().fibonacci10.run();
        // Tasks.get().fibonacci.run();
        // Tasks.get().factorial.run();
        // Tasks.get().bubbleSort.run();
        // Tasks.get().trapIn.run();
        // Tasks.get().trapOut.run();

        /**
         * Explorando Threads
         */
        Processo p1 = new Processo("fibonacci (p1)", 20);
        Processo p2 = new Processo("fatorial (p2)", 5);
        Processo p3 = new Processo("bubbleSort (p3)", 10);

        p1.start();
        p2.start();
        p3.start();
        
    }
}

class Processo extends Thread {
    private Thread t;
    private String nome;
    private int ciclos;

    Processo(String nome, int ciclos) {
        this.nome = nome;
        this.ciclos = ciclos;
    }

    public void run() {
        try {

            for (int i = 0; i < ciclos; i++) {
                Console.log("Processo "+this.nome+", "+i);
                Thread.sleep(400);
            }

        } 
        catch (InterruptedException e) {
            Console.error("Processo "+this.nome+" foi interrompido");
        }

        Console.info("Processo "+this.nome+" terminou");
    }

    public void start() {
        Console.info("Iniciando processo "+this.nome);
        if (t == null) {
            t = new Thread(this, this.nome);
            t.start();
        }
    }
}

