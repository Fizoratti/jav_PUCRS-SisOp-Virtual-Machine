package hardware.processor;

import util.Console;
import util.Emoji;

import hardware.Hardware;
import hardware.memory.Memory;
import hardware.memory.Word;
import software.MemoryManager;

import virtualmachine.InterruptHandling;
import virtualmachine.Interrupt;
import virtualmachine.TrapHandling;

import java.util.ArrayList;
import java.util.List;

public class CPU extends Thread implements Hardware {
    
    public int programCounter; 
    public Word instructionRegister; 
    public int[] registers; 
    public Memory memory; // CPU acessa MEMORIA, guarda referencia 'm' a ela. memoria nao muda. ee sempre a mesma.
    public Interrupt interrupt;

    public final int CLOCK = 50; // Tempo em milissegundos para a execução de cada instrução

    private List<Integer> programPages; 
    private int id;
    private MemoryManager memoryManager = new MemoryManager();


    public CPU() {
        this.memory = Memory.get();     // usa o atributo 'memory' para apontar para o atributo 'memory' da VM.
        this.registers = new int[10]; 
    }


    public void setContext(ArrayList<Integer> paginas, int pc, int id, int[] reg) {
        interrupt = Interrupt.NONE;
        
        this.programPages = paginas;
        this.programCounter = pc;
        this.id = id;
        this.registers = reg;
    }

    public void run() {                                    Console.debug(" > CPU.run() "); Console.print(Emoji.BUILDING_CONSTRUCTION + " > ");

        while (interrupt == Interrupt.NONE) {                // ciclo de instrucoes. acaba cfe instrucao, veja cada caso.

            instructionRegister = memory.data[translate(programCounter)];                // FETCH - busca posicao da memoria apontada por pc, guarda em ir

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
                        programCounter = memory.data[translate(value)].p;
                        programCounter++;
                    } else {
                        interrupt = Interrupt.INVALID_ADDRESS;
                    }
                    break;

                case JMPIGM: // if Rc > 0 then PC <-  [A] Else PC <-  PC +1
                    value = instructionRegister.p;
                    if (InterruptHandling.checkAddressLimits(value)) {
                        if (registers[instructionRegister.r2] > 0)
                            programCounter = memory.data[translate(value)].p;
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
                            programCounter = memory.data[translate(value)].p;
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
                            programCounter = memory.data[translate(value)].p;
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
                        registers[instructionRegister.r1] = memory.data[translate(value)].p;
                        programCounter++;
                    } else {
                        interrupt = Interrupt.INVALID_ADDRESS;
                    }
                    break;

                case STD: // [A] <-  Rs
                    value = instructionRegister.p;
                    if (InterruptHandling.checkAddressLimits(value)) {
                        memory.data[translate(value)].opc = Opcode.DATA;
                        memory.data[translate(value)].p = registers[instructionRegister.r1];
                        programCounter++;
                    } else {
                        interrupt = Interrupt.INVALID_ADDRESS;
                    }
                    break;

                case LDX: // Rd <-  [Rs]
                    value = registers[instructionRegister.r2];
                    if (InterruptHandling.checkAddressLimits(value)) {
                        registers[instructionRegister.r1] = memory.data[translate(value)].p; // OBS
                        programCounter++;
                    } else {
                        interrupt = Interrupt.INVALID_ADDRESS;
                    }
                    break;

                case STX: // [Rd] <- Rs
                    value = registers[instructionRegister.r1];
                    if (InterruptHandling.checkAddressLimits(value)) {
                        memory.data[translate(value)].opc = Opcode.DATA;
                        memory.data[translate(value)].p = registers[instructionRegister.r2];
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

    /**
     * Converte de um endereço lógico em um endereço físico.
     */
    private int translate(int pc){

        boolean isValid = true;
        
        int pageSize = memoryManager.pageSize;
        int index = pc / pageSize;
        int res = 0;
        
        try {
            programPages.get(index);
        } 
        catch (Exception e) {
            interrupt = Interrupt.INVALID_ADDRESS;
            isValid = false;
        }

        if(isValid){
            res = (programPages.get(index) * pageSize) + (pc % pageSize);
        }

        return res;
    }

    public static CPU init() {                             Console.debug(" > CPU.init() ");
        return new CPU();
    }
    
}
