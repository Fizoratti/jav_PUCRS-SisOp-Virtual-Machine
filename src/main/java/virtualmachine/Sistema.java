// PUCRS - Escola Politécnica - Sistemas Operacionais
// Prof. Fernando Dotti
// Código fornecido como parte da solução do projeto de Sistemas Operacionais
//
// Fase 1 - máquina virtual (vide enunciado correspondente)
//

package virtualmachine;

public class Sistema {

    // -------------------------------------------------------------------------------------------------------
    // --------------------- H A R D W A R E - definicoes de HW ----------------------------------------------

    // -------------------------------------------------------------------------------------------------------
    // --------------------- M E M O R I A - definicoes de opcode e palavra de memoria ----------------------

    // -------------------------------------------------------------------------------------------------------

    // -------------------------------------------------------------------------------------------------------
    // --------------------- C P U - definicoes da CPU -----------------------------------------------------

    // ------------------ C P U - fim ------------------------------------------------------------------------
    // -------------------------------------------------------------------------------------------------------

    // ------------------- V M - fim ------------------------------------------------------------------------
    // -------------------------------------------------------------------------------------------------------

    // --------------------H A R D W A R E - fim -------------------------------------------------------------
    // -------------------------------------------------------------------------------------------------------

    // -------------------------------------------------------------------------------------------------------
    // -------------------------------------------------------------------------------------------------------
    // ------------------- S O F T W A R E - inicio ----------------------------------------------------------

    // ------------------- VAZIO

    // -------------------------------------------------------------------------------------------------------
    // ------------------- S I S T E M A --------------------------------------------------------------------

    public VM vm;

    public Sistema() { // a VM com tratamento de interrupções
        vm = new VM(this);
    }

    // ------------------- S I S T E M A - fim --------------------------------------------------------------
    // -------------------------------------------------------------------------------------------------------

    // -------------------------------------------------------------------------------------------------------
    // ------------------- instancia e testa sistema
    public static void main(String args[]) {
        Sistema s = new Sistema();
        Logica logica = new Logica();

//        s.progMinimo();

//        logica.fibonacci(5);
        s.fibonacci();
//
//        logica.fatorial(6);
//        s.fatorial();
//
//        logica.bubbleSort();
//        s.bubbleSort();
    }
    // -------------------------------------------------------------------------------------------------------
    // --------------- TUDO ABAIXO DE MAIN É AUXILIAR PARA FUNCIONAMENTO DO SISTEMA - nao faz parte

    // -------------------------------------------- teste do sistema , veja classe de programas

    public void progMinimo() {
        Aux aux = new Aux();
        Word[] p = new Programas().progMinimo;
        aux.carga(p, vm.m);
        vm.cpu.setContext(0);
        System.out.println("---------------------------------- programa progMinimo carregado ");
        aux.dump(vm.m, 0, 15);
        System.out.println("---------------------------------- após execucao ");
        vm.cpu.run();
        aux.dump(vm.m, 0, 15);
    }

    public void fibonacci() {
        Aux aux = new Aux();
        Word[] p = new Programas().progFibonacci;
        aux.carga(p, vm.m);
        vm.cpu.setContext(0);
        System.out.println("---------------------------------- programa fibonacci carregado ");
        aux.dump(vm.m, 0, 16);
        System.out.println("---------------------------------- após execucao ");
        vm.cpu.run();
        aux.dump(vm.m, 50, 80);
    }

    public void fatorial() {
        Aux aux = new Aux();
        Word[] p = new Programas().progFatorial;
        aux.carga(p, vm.m);
        vm.cpu.setContext(0);
        System.out.println("---------------------------------- programa fatorial carregado ");
        aux.dump(vm.m, 0, 60);
        System.out.println("---------------------------------- após execucao ");
        vm.cpu.run();
        aux.dump(vm.m, 0, 60);
    }

    // ------------------------------------------- fim classes e funcoes auxiliares

}


