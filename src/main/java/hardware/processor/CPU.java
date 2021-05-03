package hardware.processor;

import hardware.Hardware;
import hardware.memory.Memory;
import hardware.memory.Word;
import software.MemoryManager;
import util.Console;
import util.Emoji;
import virtualmachine.InterruptHandling;
import virtualmachine.Interrupt;
import virtualmachine.TrapHandling;

import java.util.ArrayList;
import java.util.List;

public class CPU extends Thread implements Hardware {
    // característica do processador: contexto da CPU ...
    public int programCounter; // ... composto de program counter,
    public Word instructionRegister; // instruction register,
    public int[] registers; // registradores da CPU
    public Memory memory; // CPU acessa MEMORIA, guarda referencia 'm' a ela. memoria nao muda. ee sempre a mesma.
    public Interrupt interrupt;

    public final int CLOCK = 100; // Tempo em milissegundos para a execução de cada instrução

    private List<Integer> pagiProg; // tem a os frames de um programa
    private int id; //salva o id do processo em execucao
    private MemoryManager memoryManager = new MemoryManager();

    public CPU(Memory memory) { // ref a MEMORIA e interrupt handler passada na criacao da CPU
        this.memory = memory; // usa o atributo 'memory' para apontar para o atributo 'memory' da VM.
        registers = new int[10]; // aloca o espaço dos registradores
    }

    //traduz o endereço logico para o endereco de memoria do programa
    //verifica a validade do endereco a cada traducao, caso seja invalido, interrompe o programa
    private int traduz(int pc){
        boolean valido = true;
        try {
            pagiProg.get(pc/memoryManager.tamPag);
        } catch (Exception e) {
            interrupt = Interrupt.INVALID_ADDRESS;
            valido = false;
        }
        if(valido){
            return (pagiProg.get(pc / memoryManager.tamPag) * memoryManager.tamPag)+(pc % memoryManager.tamPag);
        }
        else{
            return 0;
        }
    }

    public void setContext(ArrayList<Integer> paginas, int pc, int id, int[] reg) {
        this.programCounter = pc;
        this.pagiProg = paginas;
        this.id = id;
        this.registers = reg;
        interrupt = Interrupt.NONE; // reset da interrupcao registrada
    }

    public void run() {                             Console.debug(" > CPU.run() "); Console.print(Emoji.BUILDING_CONSTRUCTION + " > ");

        while (interrupt == Interrupt.NONE) {                // ciclo de instrucoes. acaba cfe instrucao, veja cada caso.

            instructionRegister = memory.data[traduz(programCounter)];                // FETCH - busca posicao da memoria apontada por pc, guarda em ir

            switch (instructionRegister.opc) {        // EXECUTA INSTRUCAO NO ir - para cada opcode, sua execução

//              --------------------------------------------------------------------------------------------------
//              Instruções JUMP
                case JMP: // PC <-   k
                    programCounter = instructionRegister.p;
                    break;

                case JMPI: // PC <- Rs
                    programCounter = registers[instructionRegister.r1];
                    break;

                case JMPIG: // If Rc > 0 Then PC <-  Rs Else PC <-  PC +1 | if(R2>0){PC=R1}else{PC=PC++}
                    if (registers[instructionRegister.r2] > 0)
                        programCounter = registers[instructionRegister.r1];
                    else
                        programCounter++;
                    break;

                case JMPIL: // if Rc < 0 then PC <- Rs Else PC <-  PC +1
                    if (registers[instructionRegister.r2] < 0)
                        programCounter = registers[instructionRegister.r1];
                    else
                        programCounter++;
                    break;

                case JMPIE: // if Rc = 0 then PC <-  Rs Else PC <-  PC +1
                    if (registers[instructionRegister.r2] == 0)
                        programCounter = registers[instructionRegister.r1];
                    else
                        programCounter++;
                    break;

                case JMPIM: // PC <- [A]
                    int value = instructionRegister.p;
                    if (InterruptHandling.checkAddressLimits(value)) {
                        programCounter = memory.data[traduz(value)].p;
                        programCounter++;
                    } else {
                        interrupt = Interrupt.INVALID_ADDRESS;
                    }
                    break;

                case JMPIGM: // if Rc > 0 then PC <-  [A] Else PC <-  PC +1
                    value = instructionRegister.p;
                    if (InterruptHandling.checkAddressLimits(value)) {
                        if (registers[instructionRegister.r2] > 0)
                            programCounter = memory.data[traduz(value)].p;
                        else
                            programCounter++;
                    } else {
                        interrupt = Interrupt.INVALID_ADDRESS;
                    }
                    break;

                case JMPILM: // if Rc < 0 then PC <-  [A] Else PC <-  PC +1
                    value = instructionRegister.p;
                    if (InterruptHandling.checkAddressLimits(value)) {
                        if (registers[instructionRegister.r2] < 0)
                            programCounter = memory.data[traduz(value)].p;
                        else
                            programCounter++;
                    } else {
                        interrupt = Interrupt.INVALID_ADDRESS;
                    }
                    break;

                case JMPIEM: // if Rc = 0 then PC <-  [A] Else PC <-  PC +1
                    value = instructionRegister.p;
                    if (InterruptHandling.checkAddressLimits(value)) {
                        if (registers[instructionRegister.r2] == 0)
                            programCounter = memory.data[traduz(value)].p;
                        else
                            programCounter++;
                    } else {
                        interrupt = Interrupt.INVALID_ADDRESS;
                    }
                    break;

                case STOP: // Parada do programa
                    interrupt = Interrupt.STOP;
                    break;
//              --------------------------------------------------------------------------------------------------

//              --------------------------------------------------------------------------------------------------
//              Instruções Aritméticas
                case ADDI: // Rd <-  Rd + k
                    value = registers[instructionRegister.r1] + instructionRegister.p;
                    if (InterruptHandling.checkOverflowMathOperation(value)) {
                        registers[instructionRegister.r1] = value;
                        programCounter++;
                    } else {
                        interrupt = Interrupt.OVERFLOW;
                    }
                    break;

                case SUBI: // Rd <-  Rd – k
                    value = registers[instructionRegister.r1] - instructionRegister.p;
                    if (InterruptHandling.checkOverflowMathOperation(value)) {
                        registers[instructionRegister.r1] = value;
                        programCounter++;
                    } else {
                        interrupt = Interrupt.OVERFLOW;
                    }
                    break;

                case ADD: // Rd <-  Rd + Rs
                    value = registers[instructionRegister.r1] + registers[instructionRegister.r2];
                    if (InterruptHandling.checkOverflowMathOperation(value)) {
                        registers[instructionRegister.r1] = value;
                        programCounter++;
                    } else {
                        interrupt = Interrupt.OVERFLOW;
                    }
                    break;

                case SUB: // Rd <-  Rd - Rs
                    value = registers[instructionRegister.r1] - registers[instructionRegister.r2];
                    if (InterruptHandling.checkOverflowMathOperation(value)) {
                        registers[instructionRegister.r1] = value;
                        programCounter++;
                    } else {
                        interrupt = Interrupt.OVERFLOW;
                    }
                    break;

                case MULT: // Rd <-  Rd * Rs
                    value = registers[instructionRegister.r1] * registers[instructionRegister.r2];
                    if (InterruptHandling.checkOverflowMathOperation(value)) {
                        registers[instructionRegister.r1] = value;
                        programCounter++;
                    } else {
                        interrupt = Interrupt.OVERFLOW;
                    }
                    break;
//              --------------------------------------------------------------------------------------------------

//              --------------------------------------------------------------------------------------------------
//              Instruções de Movimentação
                case LDI: // Rd <-  k
                    registers[instructionRegister.r1] = instructionRegister.p;
                    programCounter++;
                    break;

                case LDD: // Rd <-  [A]
                    value = instructionRegister.p;
                    if (InterruptHandling.checkAddressLimits(value)) {
                        registers[instructionRegister.r1] = memory.data[traduz(value)].p;
                        programCounter++;
                    } else {
                        interrupt = Interrupt.INVALID_ADDRESS;
                    }
                    break;

                case STD: // [A] <-  Rs
                    value = instructionRegister.p;
                    if (InterruptHandling.checkAddressLimits(value)) {
                        memory.data[traduz(value)].opc = Opcode.DATA;
                        memory.data[traduz(value)].p = registers[instructionRegister.r1];
                        programCounter++;
                    } else {
                        interrupt = Interrupt.INVALID_ADDRESS;
                    }
                    break;

                case LDX: // Rd <-  [Rs]
                    value = registers[instructionRegister.r2];
                    if (InterruptHandling.checkAddressLimits(value)) {
                        registers[instructionRegister.r1] = memory.data[traduz(value)].p; // OBS
                        programCounter++;
                    } else {
                        interrupt = Interrupt.INVALID_ADDRESS;
                    }
                    break;

                case STX: // [Rd] <- Rs
                    value = registers[instructionRegister.r1];
                    if (InterruptHandling.checkAddressLimits(value)) {
                        memory.data[traduz(value)].opc = Opcode.DATA;
                        memory.data[traduz(value)].p = registers[instructionRegister.r2];
                        programCounter++;
                    } else {
                        interrupt = Interrupt.INVALID_ADDRESS;
                    }
                    break;

                case SWAP: // T <- Ra | Ra <- Rb | Rb <- T
                    int aux;
                    aux = registers[instructionRegister.r1];
                    registers[instructionRegister.r1] = registers[instructionRegister.r2];
                    registers[instructionRegister.r2] = aux;
                    programCounter++;
                    break;

                case TRAP:
                    interrupt = Interrupt.TRAP;
                    programCounter++;
                    break;
//              --------------------------------------------------------------------------------------------------

                default:
                interrupt = Interrupt.INVALID_INSTRUCTION;
                break;
            }

            Console.print(".");  // Simula no terminal o loading da CPU. 
            
            switch (interrupt) {
                case STOP:
                    Console.print("\n"); Console.warn(" > Interrupt.STOP");
                    break;

                case INVALID_ADDRESS:
                    Console.print("\n"); Console.warn(" > Interrupt.INVALID_ADDRESS");
                    break;

                case INVALID_INSTRUCTION:
                    Console.print("\n"); Console.warn(" > Interrupt.INVALID_INSTRUCTION");
                    break;

                case OVERFLOW:
                    Console.print("\n"); Console.warn(" > Interrupt.OVERFLOW");
                    break;

                case TRAP:
                    Console.print("\n");
                    TrapHandling.trap(this);
                    interrupt = Interrupt.NONE;
                    break;
            }
        
            try{ 
                Thread.sleep(this.CLOCK); 
            } catch(Exception e) {
                Thread.currentThread().interrupt();
            }

        }
    }
}
