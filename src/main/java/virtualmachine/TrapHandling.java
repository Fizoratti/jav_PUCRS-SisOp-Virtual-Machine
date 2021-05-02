package virtualmachine;

import util.Console;

import hardware.processor.CPU;
import hardware.processor.Opcode;

public class TrapHandling {

    public void trap(CPU cpu) {
        Console.log("reg[8] = " + cpu.registers[8]);
        Console.log("reg[9] = " + cpu.registers[9]);

        switch (cpu.registers[8]) {
            case 1:
                Console.log("ENTRADA");
                Console.print("\n > Digite um valor inteiro: ");
                String input = Console.read();

                // Converte o input para um valor inteiro
                int value = Integer.parseInt(input);

                cpu.memory[cpu.registers[9]].opc = Opcode.DATA;
                cpu.memory[cpu.registers[9]].p = value;

                Console.log("Valor armazenado " + cpu.memory[cpu.registers[9]].p);
                Console.log("Posição " + cpu.registers[9]);
                break;

            case 2:
                Console.log("SAÍDA");
                Console.log("Valor: " + cpu.memory[cpu.registers[9]].p);
                break;
        }
    }
}
