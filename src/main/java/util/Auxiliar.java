package util;

import hardware.memory.Word;

// ------------------------------------------- classes e funcoes auxiliares
public class Auxiliar {
    private void dump(Word w) {
        System.out.print("[ " + w.opc + ", " + w.r1 + ", " + w.r2 + ", " + w.p + " ] \n");
    }

    public void dumpMemoria(Word[] m, int ini, int fim) {
        for (int i = ini; i < fim; i++) {
            System.out.print(i);
            System.out.print(":  ");
            dump(m[i]);
        }
    }

    public void cargaProgramaParaMemoria(Word[] programa, Word[] memoria) {
        for (int i = 0; i < programa.length; i++) {
            memoria[i].opc = programa[i].opc;
            memoria[i].r1 = programa[i].r1;
            memoria[i].r2 = programa[i].r2;
            memoria[i].p = programa[i].p;
        }
    }
}
