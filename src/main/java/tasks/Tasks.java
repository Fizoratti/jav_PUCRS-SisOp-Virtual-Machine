package tasks;

import hardware.memory.Word;
import software.Programs;
import util.Auxiliar;
import virtualmachine.VM;

/**
 * São sequências pré-definidas de interação com a VM.
 */
public class Tasks {
    private static Tasks INSTANCE = new Tasks();

    public Task fibonacci10;
    public Task fibonacci;
    public Task factorial;
    public Task bubbleSort;
    public Task trapIn;
    public Task trapOut;

    private Tasks() {
        this.fibonacci10 = new Fibonacci10Task();
        this.fibonacci = new FibonacciTask();
        this.factorial = new FactorialTask();
        this.bubbleSort = new BubbleSortTask();
        this.trapIn = new TrapInTask();
        this.trapOut = new TrapOutTask();
    }   

    /**
     * @return instância única do Menu.
     */
    public static Tasks get() {
        return INSTANCE;
    }

    public void fibonacci10() {
        Auxiliar aux = new Auxiliar();
        Word[] p = new Programs().fibonacci10;
        aux.cargaProgramaParaMemoria(p, VM.get().memory);
        VM.get().cpu.setContext(0);
        System.out.println("\n---------------------------------- programa fibonacci_10 carregado ");
        aux.dumpMemoria(VM.get().memory, 0, 17);
        System.out.println("---------------------------------- após execucao ");
        VM.get().cpu.run();
        aux.dumpMemoria(VM.get().memory, 18, 27);
    }

    public void fibonacci() {
        Auxiliar aux = new Auxiliar();
        Word[] p = new Programs().fibonacci;
        aux.cargaProgramaParaMemoria(p, VM.get().memory);
        VM.get().cpu.setContext(0);
        System.out.println("\n---------------------------------- programa fibonacci carregado ");
        aux.dumpMemoria(VM.get().memory, 0, 16);
        System.out.println("---------------------------------- após execucao ");
        VM.get().cpu.run();
        aux.dumpMemoria(VM.get().memory, 27, 40);
    }

    public void fatorial() {
        Auxiliar aux = new Auxiliar();
        Word[] p = new Programs().factorial;
        aux.cargaProgramaParaMemoria(p, VM.get().memory);
        VM.get().cpu.setContext(0);
        System.out.println("\n---------------------------------- programa fatorial carregado ");
        aux.dumpMemoria(VM.get().memory, 0, 27);
        System.out.println("---------------------------------- após execucao ");
        VM.get().cpu.run();
        aux.dumpMemoria(VM.get().memory, 30, 32);
    }

    public void bubbleSort() {
        Auxiliar aux = new Auxiliar();
        Word[] p = new Programs().bubbleSort;
        aux.cargaProgramaParaMemoria(p, VM.get().memory);
        VM.get().cpu.setContext(0);
        System.out.println("\n---------------------------------- programa bubbleSort carregado ");
        aux.dumpMemoria(VM.get().memory, 0, 39);
        System.out.println("---------------------------------- após execucao ");
        VM.get().cpu.run();
        aux.dumpMemoria(VM.get().memory, 39, 50);
    }

    public void trapIn() {
        Auxiliar aux = new Auxiliar();
        Word[] p = new Programs().trapIn;
        aux.cargaProgramaParaMemoria(p, VM.get().memory);
        VM.get().cpu.setContext(0);
        System.out.println("\n---------------------------------- programa TRAP IN ");
        aux.dumpMemoria(VM.get().memory, 4, 5);
        System.out.println("---------------------------------- após execucao ");
        VM.get().cpu.run();
        aux.dumpMemoria(VM.get().memory, 4, 5);
    }

    public void trapOut() {
        Auxiliar aux = new Auxiliar();
        Word[] p = new Programs().trapOut;
        aux.cargaProgramaParaMemoria(p, VM.get().memory);
        VM.get().cpu.setContext(0);
        System.out.println("\n---------------------------------- programa TRAP OUT ");
        aux.dumpMemoria(VM.get().memory, 10, 11);
        System.out.println("---------------------------------- após execucao ");
        VM.get().cpu.run();
        aux.dumpMemoria(VM.get().memory, 10, 11);
    }
}