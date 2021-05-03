package software;

import hardware.memory.Memory;
import hardware.memory.Word;
import hardware.processor.Opcode;

import java.util.ArrayList;

public class MemoryManager {
    public int MEMORY_SIZE = 1024;
    public int tamPag;
    public int tamFrame;
    public int nroFrames;
    public boolean[] frameLivre;

    public MemoryManager() {
        this.tamFrame = 16;
        this.tamPag = 16;
        this.nroFrames = this.MEMORY_SIZE / this.tamPag;

        // tamPag = tamFrame = 16
        // nroFrames(64) || nroPaginas(64) = tamMemoria(1024) / tamPag(16)
        frameLivre = iniciarFrames(Memory.get().size, tamFrame);
    }

    // Inicializa o array de frames com valor TRUE
    private boolean[] iniciarFrames(int tamMem, int tamPagi) {
        frameLivre = new boolean[(tamMem / tamPagi)];

        for (int i = 0; i < frameLivre.length; i++) {
            frameLivre[i] = true;
        }

        return frameLivre;
    }

    // Dada uma demanda em número de palavras, o gerente deve responder se a alocação é possível
    public boolean temEspacoParaAlocar(int numeroPalavras) {
        int quantidadeDeFramesQueVaiOcupar = 0;

        // Se for exatamente o tamanho da Pagina
        if (numeroPalavras % tamFrame == 0) {
            quantidadeDeFramesQueVaiOcupar = ((numeroPalavras / tamFrame));
        }
        // Se for quebrado o tamanho da pagina
        else {
            quantidadeDeFramesQueVaiOcupar = ((numeroPalavras / tamFrame) + 1);
        }

        int quantidadeDeFramesDisponiveis = 0;
        for (int i = 0; i < frameLivre.length; i++) {
            if (frameLivre[i]) {
                quantidadeDeFramesDisponiveis++;
            }
        }

        return (quantidadeDeFramesQueVaiOcupar <= quantidadeDeFramesDisponiveis);
    }

    // Retornar o conjunto de frames alocados
    // Retorna um array de inteiros com os índices dos frames.
    public ArrayList<Integer> allocate(Word[] p) {
        int quantidadeDeFramesQueVaiOcupar = 0;

        // Se for exatamente o tamanho da Pagina
        if (p.length % tamFrame == 0) {
            quantidadeDeFramesQueVaiOcupar = ((p.length / tamFrame));
        }
        // Se for quebrado o tamanho da pagina
        else {
            quantidadeDeFramesQueVaiOcupar = ((p.length / tamFrame) + 1);
        }

        int quantidadeNovosFramesOcupados = 0;
        int posProg = 0;

        ArrayList<Integer> paginas = new ArrayList<>();

        for (int f = 0; f < frameLivre.length; f++) {
            if (frameLivre[f] == true) {
                frameLivre[f] = false;
                quantidadeNovosFramesOcupados++;
                paginas.add(f);
            }

            for (int j = (f * tamFrame); j < (f + 1) * tamFrame; j++) {
                if (posProg < p.length) {
                    Memory.get().data[j].opc = p[posProg].opc;
                    Memory.get().data[j].r1 = p[posProg].r1;
                    Memory.get().data[j].r2 = p[posProg].r2;
                    Memory.get().data[j].p = p[posProg].p;
                    posProg++;
                } else {
                    break;
                }
            }
            if (quantidadeNovosFramesOcupados == quantidadeDeFramesQueVaiOcupar) {
                return paginas;
            }
        }
        return null;
    }

    // Dado um array de inteiros com as páginas de um processo,
    // o gerente desloca as páginas.
    public void unallocate(ArrayList<Integer> paginasAlocadas) {
        for (Integer pagina : paginasAlocadas) {
            for (int i = 0; i < frameLivre.length; i++) {
                if (pagina == i) {
                    // Libera o frame
                    frameLivre[i] = true;
                    // Libera a memoria
                    for (int k = (i * tamFrame); k < (i + 1) * tamFrame; k++) {
                        Memory.get().data[k] = new Word(Opcode.___, -1, -1, -1);
                    }
                }
            }
        }
    }

}

