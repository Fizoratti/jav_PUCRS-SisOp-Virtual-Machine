package virtualmachine;

public class CPU {
    // característica do processador: contexto da CPU ...
    private int programCounter; // ... composto de program counter,
    private Word instrucionRegister; // instruction register,
    public int[] registers; // registradores da CPU
    public Word[] memory; // CPU acessa MEMORIA, guarda referencia 'm' a ela. memoria nao muda. ee sempre a mesma.
    private Interrupts interrupt;

    public InterruptHandling interruptHandling;
    public TrapHandling trapHandling;

    public CPU(Word[] _m, InterruptHandling interruptHandling, TrapHandling trapHandling) { // ref a MEMORIA e interrupt handler passada na criacao da CPU
        memory = _m; // usa o atributo 'm' para acessar a memoria.
        registers = new int[10]; // aloca o espaço dos registradores
        this.interruptHandling = interruptHandling;
        this.trapHandling = trapHandling;
    }

    public void setContext(int _pc) { // no futuro esta funcao vai ter que ser
        programCounter = _pc; // limite e pc (deve ser zero nesta versao)
        interrupt = Interrupts.NO_INTERRUPT;
    }

    public void run() {

        while (interrupt == Interrupts.NO_INTERRUPT) {                // ciclo de instrucoes. acaba cfe instrucao, veja cada caso.

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
                        interrupt = Interrupts.ADDRESS_INVALID;
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
                        interrupt = Interrupts.ADDRESS_INVALID;
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
                        interrupt = Interrupts.ADDRESS_INVALID;
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
                        interrupt = Interrupts.ADDRESS_INVALID;
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
                        interrupt = Interrupts.ADDRESS_INVALID;
                    }
                    break;

                case STD: // [A] <-  Rs
                    value = instrucionRegister.p;
                    if (interruptHandling.checkAddressLimits(value)) {
                        memory[value].opc = Opcode.DATA;
                        memory[value].p = registers[instrucionRegister.r1];
                        programCounter++;
                    } else {
                        interrupt = Interrupts.ADDRESS_INVALID;
                    }
                    break;

                case LDX: // Rd <-  [Rs]
                    value = registers[instrucionRegister.r2];
                    if (interruptHandling.checkAddressLimits(value)) {
                        registers[instrucionRegister.r1] = memory[value].p; // OBS
                        programCounter++;
                    } else {
                        interrupt = Interrupts.ADDRESS_INVALID;
                    }
                    break;

                case STX: // [Rd] <- Rs
                    value = registers[instrucionRegister.r1];
                    if (interruptHandling.checkAddressLimits(value)) {
                        memory[value].opc = Opcode.DATA;
                        memory[value].p = registers[instrucionRegister.r2];
                        programCounter++;
                    } else {
                        interrupt = Interrupts.ADDRESS_INVALID;
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
                    interrupt = Interrupts.INSTRUCTION_INVALID;
                    break;
            }

            switch (interrupt) {
                case STOP:
                    System.out.println("STOP");
                    break;
                case ADDRESS_INVALID:
                    System.out.println("ADDRESS_INVALID");
                    break;
                case INSTRUCTION_INVALID:
                    System.out.println("INSTRUCTION_INVALID");
                    break;
                case OVERFLOW:
                    System.out.println("OVERFLOW");
                    break;
                case TRAP:
                    trapHandling.trap(this);
                    interrupt = Interrupts.NO_INTERRUPT;
                    break;
                default:
                    break;
            }
        }
    }
}
