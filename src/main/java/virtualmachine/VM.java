package virtualmachine;

// ------------------- V M - constituida de CPU e MEMORIA ------------------------------------------------
// -------------------------- atributos e construcao da VM -----------------------------------------------

public class VM {
    public int tamMem;
    public Word[] memoria;
    public CPU cpu;

    public VM() {
        tamMem = 1024;
        memoria = new Word[tamMem];
        limparMemoria();
        cpu = new CPU(memoria);
    }

    private void limparMemoria() {
        for (int i = 0; i < tamMem; i++) {
            memoria[i] = new Word(Opcode.___, -1, -1, -1);
        }
    }

}
