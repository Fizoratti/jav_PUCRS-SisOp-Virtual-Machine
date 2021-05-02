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
}