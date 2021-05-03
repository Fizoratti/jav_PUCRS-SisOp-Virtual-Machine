package util;

import hardware.memory.Memory;
import hardware.memory.Word;
import software.Programs;
import tasks.Tasks;
import virtualmachine.VM;

/**
 * O Menu apenas facilita a execução de tasks
 */
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
        int option;
        do {
            showOptions();
    
            // Variável input recebe o valor inserido pelo terminal
            Console.print("\n > Digite a opção: ");
            option = Integer.parseInt(Console.read());

            switch (option) {
                case 0:
                Console.info("Encerrando...");
                Console.wait(800);
                break;
                
                case 1:
                    Tasks.get().fibonacci10.run();
                    Memory.get().cleanMemory();
                break;
                
                case 2:
                    Tasks.get().fibonacci.run();
                    Memory.get().cleanMemory();
                    break;
                
                case 3:
                    Tasks.get().factorial.run();
                    Memory.get().cleanMemory();
                    break;
                
                case 4:
                    Tasks.get().bubbleSort.run();
                    Memory.get().cleanMemory();
                    break;
                
                case 5:
                    Tasks.get().trapIn.run();
                    break;
                
                case 6:
                    Tasks.get().trapOut.run();
                    break;
                
                default:
                    break;
                
            }
        }
        while (option != 0);

    }
}