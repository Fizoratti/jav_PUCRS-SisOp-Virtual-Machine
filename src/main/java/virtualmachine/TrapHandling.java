package virtualmachine;

import java.util.Scanner;

public class TrapHandling {

    public void trap(CPU cpu) {
        System.out.println("reg[8] = " + cpu.registers[8]);
        System.out.println("reg[9] = " + cpu.registers[9]);

        switch (cpu.registers[8]) {
            case 1:
                System.out.println("ENTRADA");
                System.out.println("Entre com o valor inteiro:");
                Scanner scanner = new Scanner(System.in);
                int value = scanner.nextInt();
                scanner.close();

                cpu.memory[cpu.registers[9]].opc = Opcode.DATA;
                cpu.memory[cpu.registers[9]].p = value;

                System.out.println("Valor armazenado " + cpu.memory[cpu.registers[9]].p);
                System.out.println("Posicao " + cpu.registers[9]);
                break;

            case 2:
                System.out.println("SAIDA");
                System.out.println("Valor: " + cpu.memory[cpu.registers[9]].p);
                break;
        }
    }
}
