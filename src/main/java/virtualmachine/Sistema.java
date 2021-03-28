// PUCRS - Escola Politécnica - Sistemas Operacionais
// Prof. Fernando Dotti
// Código fornecido como parte da solução do projeto de Sistemas Operacionais
//
// Fase 1 - máquina virtual (vide enunciado correspondente)
//

package virtualmachine;

public class Sistema {

    public VM vm;

    public Sistema() { // a VM com tratamento de interrupções
        vm = new VM();
    }

    public static void main() {
        Sistema s = new Sistema();

        int programaExecutado = 0;

        switch (programaExecutado) {
            case 0:
                s.fibonacci10();
                break;

            case 1:
                s.fibonacci();
                break;

            case 2:
                s.fatorial();
                break;

            case 3:
                s.bubbleSort();
                break;
        }
    }

    public void fibonacci10() {
        Auxiliar aux = new Auxiliar();
        Word[] p = new Programas().progFibonacci10;
        aux.cargaProgramaParaMemoria(p, vm.memoria);
        vm.cpu.setContext(0);
        System.out.println("---------------------------------- programa fibonacci carregado ");
        aux.dumpMemoria(vm.memoria, 0, 17);
        System.out.println("---------------------------------- após execucao ");
        vm.cpu.run();
        aux.dumpMemoria(vm.memoria, 18, 27);
    }

    public void fibonacci() {
        Auxiliar aux = new Auxiliar();
        Word[] p = new Programas().progFibonacci;
        aux.cargaProgramaParaMemoria(p, vm.memoria);
        vm.cpu.setContext(0);
        System.out.println("---------------------------------- programa fibonacci carregado ");
        aux.dumpMemoria(vm.memoria, 0, 16);
        System.out.println("---------------------------------- após execucao ");
        vm.cpu.run();
        aux.dumpMemoria(vm.memoria, 27, 40);
    }

    public void fatorial() {
        Auxiliar aux = new Auxiliar();
        Word[] p = new Programas().progFatorial;
        aux.cargaProgramaParaMemoria(p, vm.memoria);
        vm.cpu.setContext(0);
        System.out.println("---------------------------------- programa fatorial carregado ");
        aux.dumpMemoria(vm.memoria, 0, 27);
        System.out.println("---------------------------------- após execucao ");
        vm.cpu.run();
        aux.dumpMemoria(vm.memoria, 29, 33);
    }

    public void bubbleSort() {
        Auxiliar aux = new Auxiliar();
        Word[] p = new Programas().bubbleSort;
        aux.cargaProgramaParaMemoria(p, vm.memoria);
        vm.cpu.setContext(0);
        System.out.println("---------------------------------- programa fatorial carregado ");
        aux.dumpMemoria(vm.memoria, 0, 39);
        System.out.println("---------------------------------- após execucao ");
        vm.cpu.run();
        aux.dumpMemoria(vm.memoria, 39, 50);
    }

}


