package software;

import hardware.memory.Memory;
import hardware.memory.Word;
import hardware.processor.Opcode;

import java.util.ArrayList;

public class MemoryManager {
    public int MEMORY_SIZE = 1024;
    public int pageSize;
    public int frameSize;
    public int frameCount;
    public boolean[] availableFrames;

    public MemoryManager() {
        this.frameSize = 16;
        this.pageSize = 16;
        this.frameCount = this.MEMORY_SIZE / this.pageSize;

        // pageSize = frameSize = 16
        // frameCount(64) || nroPaginas(64) = tamMemoria(1024) / pageSize(16)
        availableFrames = iniciarFrames(Memory.get().size, frameSize);
    }

    // Inicializa o array de frames com valor TRUE
    private boolean[] iniciarFrames(int tamMem, int pageSizei) {
        availableFrames = new boolean[(tamMem / pageSizei)];

        for (int i = 0; i < availableFrames.length; i++) {
            availableFrames[i] = true;
        }

        return availableFrames;
    }

    // Dada uma demanda em número de palavras, o gerente deve responder se a alocação é possível
    public boolean temEspacoParaAlocar(int numeroPalavras) {
        int quantidadeDeFramesQueVaiOcupar = 0;

        // Se for exatamente o tamanho da Pagina
        if (numeroPalavras % frameSize == 0) {
            quantidadeDeFramesQueVaiOcupar = ((numeroPalavras / frameSize));
        }
        // Se for quebrado o tamanho da pagina
        else {
            quantidadeDeFramesQueVaiOcupar = ((numeroPalavras / frameSize) + 1);
        }

        int quantidadeDeFramesDisponiveis = 0;
        for (int i = 0; i < availableFrames.length; i++) {
            if (availableFrames[i]) {
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
        if (p.length % frameSize == 0) {
            quantidadeDeFramesQueVaiOcupar = ((p.length / frameSize));
        }
        // Se for quebrado o tamanho da pagina
        else {
            quantidadeDeFramesQueVaiOcupar = ((p.length / frameSize) + 1);
        }

        int quantidadeNovosFramesOcupados = 0;
        int posProg = 0;

        ArrayList<Integer> paginas = new ArrayList<>();

        for (int f = 0; f < availableFrames.length; f++) {
            if (availableFrames[f] == true) {
                availableFrames[f] = false;
                quantidadeNovosFramesOcupados++;
                paginas.add(f);
            }

            for (int j = (f * frameSize); j < (f + 1) * frameSize; j++) {
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
            for (int i = 0; i < availableFrames.length; i++) {
                if (pagina == i) {
                    // Libera o frame
                    availableFrames[i] = true;
                    // Libera a memoria
                    for (int k = (i * frameSize); k < (i + 1) * frameSize; k++) {
                        Memory.get().data[k] = new Word(Opcode.___, -1, -1, -1);
                    }
                }
            }
        }
    }

}

