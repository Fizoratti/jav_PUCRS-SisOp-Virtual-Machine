package hardware.processor;

import hardware.Hardware;
import hardware.memory.Word;
import util.Console;
import virtualmachine.InterruptHandling;
import virtualmachine.Interrupts;
import virtualmachine.TrapHandling;

public class CPU implements Hardware {
    // característica do processador: contexto da CPU ...
    public int programCounter; // ... composto de program counter,
    public Word instrucionRegister; // instruction register,
    public int[] registers; // registradores da CPU
    public Word[] memory; // CPU acessa MEMORIA, guarda referencia 'm' a ela. memoria nao muda. ee sempre a mesma.
    public Interrupts interrupt;

    public InterruptHandling interruptHandling;
    public TrapHandling trapHandling;

    public CPU(Word[] _m) { // ref a MEMORIA e interrupt handler passada na criacao da CPU
        memory = _m; // usa o atributo 'm' para acessar a memoria.
        registers = new int[10]; // aloca o espaço dos registradores
        this.interruptHandling = new InterruptHandling();
        this.trapHandling = new TrapHandling();
    }

    public void setContext(int _pc) { // no futuro esta funcao vai ter que ser
        programCounter = _pc; // limite e pc (deve ser zero nesta versao)
        interrupt = Interrupts.NONE;
    }

    public void run() {

        while (interrupt == Interrupts.NONE) {                // ciclo de instrucoes. acaba cfe instrucao, veja cada caso.

            instrucionRegister = memory[programCounter];                // FETCH - busca posicao da memoria apontada por pc, guarda em ir

            switch (instrucionRegister.opc) {        // EXECUTA INSTRUCAO NO ir - para cada opcode, sua execução

//              --------------------------------------------------------------------------------------------------
//              Instruções JUMP
                case JMP: // PC <-   k
                    programCounter = instrucionRegister.p;
                    break;

                case JMPI: // PC <- Rs
                    programCounter = registers[instrucionRegister.r1];
                    break;

                case JMPIG: // If Rc > 0 Then PC <-  Rs Else PC <-  PC +1 | if(R2>0){PC=R1}else{PC=PC++}
                    if (registers[instrucionRegister.r2] > 0)
                        programCounter = registers[instrucionRegister.r1];
                    else
                        programCounter++;
                    break;

                case JMPIL: // if Rc < 0 then PC <- Rs Else PC <-  PC +1
                    if (registers[instrucionRegister.r2] < 0)
                        programCounter = registers[instrucionRegister.r1];
                    else
                        programCounter++;
                    break;

                case JMPIE: // if Rc = 0 then PC <-  Rs Else PC <-  PC +1
                    if (registers[instrucionRegister.r2] == 0)
                        programCounter = registers[instrucionRegister.r1];
                    else
                        programCounter++;
                    break;

                case JMPIM: // PC <- [A]
                    int value = instrucionRegister.p;
                    if (interruptHandling.checkAddressLimits(value)) {
                        programCounter = memory[value].p;
                        programCounter++;
                    } else {
                        interrupt = Interrupts.INVALID_ADDRESS;
                    }
                    break;

                case JMPIGM: // if Rc > 0 then PC <-  [A] Else PC <-  PC +1
                    value = instrucionRegister.p;
                    if (interruptHandling.checkAddressLimits(value)) {
                        if (registers[instrucionRegister.r2] > 0)
                            programCounter = memory[value].p;
                        else
                            programCounter++;
                    } else {
                        interrupt = Interrupts.INVALID_ADDRESS;
                    }
                    break;

                case JMPILM: // if Rc < 0 then PC <-  [A] Else PC <-  PC +1
                    value = instrucionRegister.p;
                    if (interruptHandling.checkAddressLimits(value)) {
                        if (registers[instrucionRegister.r2] < 0)
                            programCounter = memory[value].p;
                        else
                            programCounter++;
                    } else {
                        interrupt = Interrupts.INVALID_ADDRESS;
                    }
                    break;

                case JMPIEM: // if Rc = 0 then PC <-  [A] Else PC <-  PC +1
                    value = instrucionRegister.p;
                    if (interruptHandling.checkAddressLimits(value)) {
                        if (registers[instrucionRegister.r2] == 0)
                            programCounter = memory[value].p;
                        else
                            programCounter++;
                    } else {
                        interrupt = Interrupts.INVALID_ADDRESS;
                    }
                    break;

                case STOP: // Parada do programa
                    interrupt = Interrupts.STOP;
                    break;
//              --------------------------------------------------------------------------------------------------

//              --------------------------------------------------------------------------------------------------
//              Instruções Aritméticas
                case ADDI: // Rd <-  Rd + k
                    value = registers[instrucionRegister.r1] + instrucionRegister.p;
                    if (interruptHandling.checkOverflowMathOperation(value)) {
                        registers[instrucionRegister.r1] = value;
                        programCounter++;
                    } else {
                        interrupt = Interrupts.OVERFLOW;
                    }
                    break;

                case SUBI: // Rd <-  Rd – k
                    value = registers[instrucionRegister.r1] - instrucionRegister.p;
                    if (interruptHandling.checkOverflowMathOperation(value)) {
                        registers[instrucionRegister.r1] = value;
                        programCounter++;
                    } else {
                        interrupt = Interrupts.OVERFLOW;
                    }
                    break;

                case ADD: // Rd <-  Rd + Rs
                    value = registers[instrucionRegister.r1] + registers[instrucionRegister.r2];
                    if (interruptHandling.checkOverflowMathOperation(value)) {
                        registers[instrucionRegister.r1] = value;
                        programCounter++;
                    } else {
                        interrupt = Interrupts.OVERFLOW;
                    }
                    break;

                case SUB: // Rd <-  Rd - Rs
                    value = registers[instrucionRegister.r1] - registers[instrucionRegister.r2];
                    if (interruptHandling.checkOverflowMathOperation(value)) {
                        registers[instrucionRegister.r1] = value;
                        programCounter++;
                    } else {
                        interrupt = Interrupts.OVERFLOW;
                    }
                    break;

                case MULT: // Rd <-  Rd * Rs
                    value = registers[instrucionRegister.r1] * registers[instrucionRegister.r2];
                    if (interruptHandling.checkOverflowMathOperation(value)) {
                        registers[instrucionRegister.r1] = value;
                        programCounter++;
                    } else {
                        interrupt = Interrupts.OVERFLOW;
                    }
                    break;
//              --------------------------------------------------------------------------------------------------

//              --------------------------------------------------------------------------------------------------
//              Instruções de Movimentação
                case LDI: // Rd <-  k
                    registers[instrucionRegister.r1] = instrucionRegister.p;
                    programCounter++;
                    break;

                case LDD: // Rd <-  [A]
                    value = instrucionRegister.p;
                    if (interruptHandling.checkAddressLimits(value)) {
                        registers[instrucionRegister.r1] = memory[value].p;
                        programCounter++;
                    } else {
                        interrupt = Interrupts.INVALID_ADDRESS;
                    }
                    break;

                case STD: // [A] <-  Rs
                    value = instrucionRegister.p;
                    if (interruptHandling.checkAddressLimits(value)) {
                        memory[value].opc = Opcode.DATA;
                        memory[value].p = registers[instrucionRegister.r1];
                        programCounter++;
                    } else {
                        interrupt = Interrupts.INVALID_ADDRESS;
                    }
                    break;

                case LDX: // Rd <-  [Rs]
                    value = registers[instrucionRegister.r2];
                    if (interruptHandling.checkAddressLimits(value)) {
                        registers[instrucionRegister.r1] = memory[value].p; // OBS
                        programCounter++;
                    } else {
                        interrupt = Interrupts.INVALID_ADDRESS;
                    }
                    break;

                case STX: // [Rd] <- Rs
                    value = registers[instrucionRegister.r1];
                    if (interruptHandling.checkAddressLimits(value)) {
                        memory[value].opc = Opcode.DATA;
                        memory[value].p = registers[instrucionRegister.r2];
                        programCounter++;
                    } else {
                        interrupt = Interrupts.INVALID_ADDRESS;
                    }
                    break;

                case SWAP: // T <- Ra | Ra <- Rb | Rb <- T
                    int aux;
                    aux = registers[instrucionRegister.r1];
                    registers[instrucionRegister.r1] = registers[instrucionRegister.r2];
                    registers[instrucionRegister.r2] = aux;
                    programCounter++;
                    break;

                case TRAP:
                    interrupt = Interrupts.TRAP;
                    programCounter++;
                    break;
//              --------------------------------------------------------------------------------------------------

                default:
                    interrupt = Interrupts.INVALID_INSTRUCTION;
                    break;
            }

            switch (interrupt) {
                case STOP:
                    Console.warn("STOP");
                    break;
                case INVALID_ADDRESS:
                    Console.warn("INVALID_ADDRESS");
                    break;
                case INVALID_INSTRUCTION:
                    Console.warn("INVALID_INSTRUCTION");
                    break;
                case OVERFLOW:
                    Console.warn("OVERFLOW");
                    break;
                case TRAP:
                    trapHandling.trap(this);
                    interrupt = Interrupts.NONE;
                    break;
                default:
                    break;
            }
        }
    }
}
