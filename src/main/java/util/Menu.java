package util;

import hardware.memory.Word;
import software.Programs;
import virtualmachine.VM;

public class Menu {
    private static Menu INSTANCE = new Menu();

    private Menu() {}   

    /**
     * @return instância única do Menu.
     */
    public static Menu get() {
        return INSTANCE;
    }

    public void showOptions() {
        Console.log("\n\n\n================== MENU ==================");
        Console.log("Digite o valor de uma das opções abaixo:\n");
        Console.log("1. Fibonacci dos 10 primeiros números");
        Console.log("2. Fibonacci para o valor N do programa");
        Console.log("3. Fatorial para o valor N do programa");
        Console.log("4. Ordenação do array do programa");
        Console.log("5. Trap IN:");
        Console.log("6. Trap OUT");

        Console.log("\n0. Encerrar");
    }

    public void showMenu() {
        showOptions();

        Options menuOption = new Options();

        // Variável input recebe o valor inserido pelo terminal
        Console.print("\n > Digite a opção: ");
        String input = Console.read();

        // Converte o input para um valor inteiro
        int option = Integer.parseInt(input);

        switch (option) {
            case 0:
                Console.info("Encerrando...");
                Console.wait(2000);
                break;

            case 1:
                menuOption.fibonacci10();
                VM.get().cleanMemory();
            
            case 2:
                menuOption.fibonacci();
                VM.get().cleanMemory();
            
            case 3:
                menuOption.fatorial();
                VM.get().cleanMemory();
            
            case 4:
                menuOption.bubbleSort();
                VM.get().cleanMemory();
            
            case 5:
                menuOption.trapIn();
            
            case 6:
                menuOption.trapOut();
            
            default:
                break;
            
        }
    }


    class Options {

        public void fibonacci10() {
            Auxiliar aux = new Auxiliar();
            Word[] p = new Programs().fibonacci10;
            aux.cargaProgramaParaMemoria(p, VM.get().memory);
            VM.get().cpu.setContext(0);
            System.out.println("\n---------------------------------- programa fibonacci_10 carregado ");
            aux.dumpMemoria(VM.get().memory, 0, 17);
            System.out.println("---------------------------------- após execucao ");
            VM.get().cpu.run();
            aux.dumpMemoria(VM.get().memory, 18, 27);
            showMenu();
        }

        public void fibonacci() {
            Auxiliar aux = new Auxiliar();
            Word[] p = new Programs().fibonacci;
            aux.cargaProgramaParaMemoria(p, VM.get().memory);
            VM.get().cpu.setContext(0);
            System.out.println("\n---------------------------------- programa fibonacci carregado ");
            aux.dumpMemoria(VM.get().memory, 0, 16);
            System.out.println("---------------------------------- após execucao ");
            VM.get().cpu.run();
            aux.dumpMemoria(VM.get().memory, 27, 40);
            showMenu();
        }

        public void fatorial() {
            Auxiliar aux = new Auxiliar();
            Word[] p = new Programs().factorial;
            aux.cargaProgramaParaMemoria(p, VM.get().memory);
            VM.get().cpu.setContext(0);
            System.out.println("\n---------------------------------- programa fatorial carregado ");
            aux.dumpMemoria(VM.get().memory, 0, 27);
            System.out.println("---------------------------------- após execucao ");
            VM.get().cpu.run();
            aux.dumpMemoria(VM.get().memory, 30, 32);
            showMenu();
        }

        public void bubbleSort() {
            Auxiliar aux = new Auxiliar();
            Word[] p = new Programs().bubbleSort;
            aux.cargaProgramaParaMemoria(p, VM.get().memory);
            VM.get().cpu.setContext(0);
            System.out.println("\n---------------------------------- programa bubbleSort carregado ");
            aux.dumpMemoria(VM.get().memory, 0, 39);
            System.out.println("---------------------------------- após execucao ");
            VM.get().cpu.run();
            aux.dumpMemoria(VM.get().memory, 39, 50);
            showMenu();
        }

        public void trapIn() {
            Auxiliar aux = new Auxiliar();
            Word[] p = new Programs().trapIn;
            aux.cargaProgramaParaMemoria(p, VM.get().memory);
            VM.get().cpu.setContext(0);
            System.out.println("\n---------------------------------- programa TRAP IN ");
            aux.dumpMemoria(VM.get().memory, 4, 5);
            System.out.println("---------------------------------- após execucao ");
            VM.get().cpu.run();
            aux.dumpMemoria(VM.get().memory, 4, 5);
            showMenu();
        }

        public void trapOut() {
            Auxiliar aux = new Auxiliar();
            Word[] p = new Programs().trapOut;
            aux.cargaProgramaParaMemoria(p, VM.get().memory);
            VM.get().cpu.setContext(0);
            System.out.println("\n---------------------------------- programa TRAP OUT ");
            aux.dumpMemoria(VM.get().memory, 10, 11);
            System.out.println("---------------------------------- após execucao ");
            VM.get().cpu.run();
            aux.dumpMemoria(VM.get().memory, 10, 11);
            showMenu();
        }
    }
}