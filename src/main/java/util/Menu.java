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

    private String header = "\n\n\n================== MENU ==================";

    public String[] options = {
        "Digite o valor de uma das opções abaixo:\n",

        "1. Executar Main Task",
        "2. Abrir menu antigo (stale)",

        "\n0. Encerrar"
    };

    private Menu() {}   


    public void showMenu() {
        int input;
        do {
            showOptions(this.options);
    
            // Variável input recebe o valor inserido pelo terminal
            Console.print("\n > Digite a opção: ");
            input = Integer.parseInt(Console.read());

            switch (input) {
                case 0:
                    Console.info("Encerrando...");
                    Console.wait(800);
                    break;
                
                case 1:
                    Tasks.get().mainTask.run();
                    break;

                case 2:
                    showOldMenu();
                    break;
                
                default:
                    Console.error(" Input inválido. Input: "+input);
                    break;
                
            }
        }
        while (input != 0);

    }




    public String[] oldOptions = {
        "Digite o valor de uma das opções abaixo:\n",

        "1. Fibonacci dos 10 primeiros números",
        "2. Fibonacci para o valor N do programa",
        "3. Fatorial para o valor N do programa",
        "4. Ordenação do array do programa",
        "5. Trap IN:",
        "6. Trap OUT",

        "\n0. Voltar"
    };

    public void showOldMenu() {
        int input;
        do {
            showOptions(this.oldOptions);
    
            // Variável input recebe o valor inserido pelo terminal
            Console.print("\n > Digite a opção: ");
            input = Integer.parseInt(Console.read());

            switch (input) {
                case 0:
                Console.info("Voltando...");
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
        while (input != 0);

    }

    
    private void showOptions(String[] options) {
        Console.log(this.header);

        for (int i=0; i<options.length; i++) {
            showOption(options[i]);
        }
        
    }

    private void showOption(String option) {
        Console.log(option);
    }


    /**
     * @return instância única do Menu.
     */
    public static Menu get() {
        return INSTANCE;
    }

}