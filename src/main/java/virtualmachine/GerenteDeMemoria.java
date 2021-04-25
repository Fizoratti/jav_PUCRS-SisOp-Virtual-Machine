package virtualmachine;

import java.util.ArrayList;

public class GerenteDeMemoria {

    public static boolean[] frameLivre;

    public GerenteDeMemoria() {
        limparMemoria();

        // tamPag = tamFrame = 16
        // nroFrames(64) || nroPaginas(64) = tamMemoria(1024) / tamPag(16)
        frameLivre = iniciarFrames(VM.tamMemoria, VM.tamFrame);
    }

    // Limpa toda a memoria
    private void limparMemoria() {
        for (int i = 0; i < VM.memoria.length; i++) {
            VM.memoria[i] = new Word(Opcode.___, -1, -1, -1);
        }
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
    public boolean verificaEspaco(int numeroPalavras) {
        int quantidadeDeFramesQueVaiOcupar = 0;

        // Se for exatamente o tamanho da Pagina
        if (numeroPalavras % VM.tamFrame == 0) {
            quantidadeDeFramesQueVaiOcupar = ((numeroPalavras / VM.tamFrame));
        }
        // Se for quebrado o tamanho da pagina
        else {
            quantidadeDeFramesQueVaiOcupar = ((numeroPalavras / VM.tamFrame) + 1);
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
    public ArrayList<Integer> aloca(Word[] p, int numeroPalavras) {
        int quantidadeDeFramesQueVaiOcupar = 0;

        // Se for exatamente o tamanho da Pagina
        if (numeroPalavras % VM.tamFrame == 0) {
            quantidadeDeFramesQueVaiOcupar = ((numeroPalavras / VM.tamFrame));
        }
        // Se for quebrado o tamanho da pagina
        else {
            quantidadeDeFramesQueVaiOcupar = ((numeroPalavras / VM.tamFrame) + 1);
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
            for (int j = (f * VM.tamFrame); j < (f + 1) * VM.tamFrame; j++) {
                if (posProg < p.length) {
                    VM.memoria[j].opc = p[posProg].opc;
                    VM.memoria[j].r1 = p[posProg].r1;
                    VM.memoria[j].r2 = p[posProg].r2;
                    VM.memoria[j].p = p[posProg].p;
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
    public void desaloca(ArrayList<Integer> paginasAlocadas) {
        for (Integer pagina : paginasAlocadas) {
            for (int i = 0; i < frameLivre.length; i++) {
                if (pagina == i) {
                    // Libera o frame
                    frameLivre[i] = true;
                    // Libera a memoria
                    for (int k = (i * VM.tamFrame); k < (i + 1) * VM.tamFrame; k++) {
                        VM.memoria[k] = new Word(Opcode.___, -1, -1, -1);
                    }
                }
            }
        }
    }

}
