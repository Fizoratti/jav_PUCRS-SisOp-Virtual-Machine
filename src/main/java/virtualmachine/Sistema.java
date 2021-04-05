// PUCRS - Escola Politécnica - Sistemas Operacionais
// Prof. Fernando Dotti
// Código fornecido como parte da solução do projeto de Sistemas Operacionais
//
// Fase 1 - máquina virtual (vide enunciado correspondente)
//

package virtualmachine;

public class Sistema {
    public InterruptHandling interruptHandling;
    public TrapHandling trapHandling;
    public VM vm;

    public Sistema() { // a VM com tratamento de interrupções
        interruptHandling = new InterruptHandling();
        trapHandling = new TrapHandling();
        vm = new VM(interruptHandling, trapHandling);
    }

    public static void main() {
        Sistema s = new Sistema();

        Exercicios exercicio = Exercicios.TRAP_OUT;

        switch (exercicio) {
            case FIBONACCI_10:
                s.fibonacci10();
                break;

            case FIBONACCI_N:
                s.fibonacci();
                break;

            case FATORIAL:
                s.fatorial();
                break;

            case BUBBLE_SORT:
                s.bubbleSort();
                break;

            case TRAP_IN:
                s.trapIn();
                break;

            case TRAP_OUT:
                s.trapOut();
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
        aux.dumpMemoria(vm.memoria, 30, 32);
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

    public void trapIn() {
        Auxiliar aux = new Auxiliar();
        Word[] p = new Programas().trapIn;
        aux.cargaProgramaParaMemoria(p, vm.memoria);
        vm.cpu.setContext(0);
        System.out.println("---------------------------------- programa TRAP IN ");
        aux.dumpMemoria(vm.memoria, 4, 5);
        System.out.println("---------------------------------- após execucao ");
        vm.cpu.run();
        aux.dumpMemoria(vm.memoria, 4, 5);
    }

    public void trapOut() {
        Auxiliar aux = new Auxiliar();
        Word[] p = new Programas().trapOut;
        aux.cargaProgramaParaMemoria(p, vm.memoria);
        vm.cpu.setContext(0);
        System.out.println("---------------------------------- programa TRAP OUT ");
        aux.dumpMemoria(vm.memoria, 10, 11);
        System.out.println("---------------------------------- após execucao ");
        vm.cpu.run();
        aux.dumpMemoria(vm.memoria, 10, 11);
    }

}


