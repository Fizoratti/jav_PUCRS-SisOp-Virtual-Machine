package software;

import virtualmachine.Interrupt;

import java.util.ArrayList;

public class PCB {
    public int id;
    public ArrayList<Integer> allocatedPages;

    public int pc;
    public StatusPCB status;
    public int[] reg;
    public int nVezesCPU;
    public Interrupt irpt;
    public int valorEscritaLeitura;
    public int posicaoEscritaLeitura;

//    public PCB(int id, ArrayList<Integer> allocatedPages) {
//        this.id = id;
//        this.allocatedPages = allocatedPages;
//    }

    public PCB(int id, ArrayList<Integer> allocatedPages) {
        this.allocatedPages = allocatedPages;
        this.id = id;
        this.irpt = Interrupt.NONE;
        this.pc = 0;
        this.status = StatusPCB.PRONTO;
        this.nVezesCPU = 0;
        this.valorEscritaLeitura = 0;
        this.posicaoEscritaLeitura = 0;
        reg = new int[10];
    }

    public ArrayList<Integer> getAllocatedPages() {
        return this.allocatedPages;
    }

    public int getId() {
        return this.id;
    }

    //retorna a lista de paginas de um processo
    public ArrayList<Integer> getLista(){
        return this.allocatedPages;
    }
}

enum StatusPCB {
    PRONTO, BLOQUEADO, EXECUTANDO
}
