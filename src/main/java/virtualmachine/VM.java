package virtualmachine;

// ------------------- V M - constituida de CPU e MEMORIA ------------------------------------------------
// -------------------------- atributos e construcao da VM -----------------------------------------------

public class VM {
    public static int tamMemoria;
    public static int tamFrame;
    public static Word[] memoria;
    public CPU cpu;
    public static GerenteDeMemoria gerenteDeMemoria;


    public VM(InterruptHandling interruptHandling, TrapHandling trapHandling) {
        tamFrame = 16;

        tamMemoria = 1024;
        memoria = new Word[tamMemoria];

        cpu = new CPU(memoria, interruptHandling, trapHandling);

        gerenteDeMemoria = new GerenteDeMemoria();
    }

}
