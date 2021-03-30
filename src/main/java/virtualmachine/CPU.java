package virtualmachine;

public class CPU {
    // característica do processador: contexto da CPU ...
    private int programCounter; // ... composto de program counter,
    private Word instrucionRegister; // instruction register,
    private int[] registers; // registradores da CPU

    private Word[] memory; // CPU acessa MEMORIA, guarda referencia 'm' a ela. memoria nao muda. ee sempre a mesma.

    public CPU(Word[] _m) { // ref a MEMORIA e interrupt handler passada na criacao da CPU
        memory = _m; // usa o atributo 'm' para acessar a memoria.
        registers = new int[8]; // aloca o espaço dos registradores
    }

    public void setContext(int _pc) { // no futuro esta funcao vai ter que ser
        programCounter = _pc; // limite e pc (deve ser zero nesta versao)
    }

    public void run() {
        while (true) {                // ciclo de instrucoes. acaba cfe instrucao, veja cada caso.
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
                    programCounter = memory[instrucionRegister.p].p;
                    programCounter++;
                    break;

                case JMPIGM: // if Rc > 0 then PC <-  [A] Else PC <-  PC +1
                    if (registers[instrucionRegister.r2] > 0)
                        programCounter = instrucionRegister.p;
                    else
                        programCounter++;
                    break;

                case JMPILM: // if Rc < 0 then PC <-  [A] Else PC <-  PC +1
                    if (registers[instrucionRegister.r2] < 0)
                        programCounter = instrucionRegister.p;
                    else
                        programCounter++;
                    break;

                case JMPIEM: // if Rc = 0 then PC <-  [A] Else PC <-  PC +1
                    if (registers[instrucionRegister.r2] == 0) {
                        programCounter = instrucionRegister.p;
                    } else
                        programCounter++;
                    break;

                case STOP: // Parada do programa
                    break;
//              --------------------------------------------------------------------------------------------------

//              --------------------------------------------------------------------------------------------------
//              Instruções Aritméticas
                case ADDI: // Rd <-  Rd + k
                    registers[instrucionRegister.r1] = registers[instrucionRegister.r1] + instrucionRegister.p;
                    programCounter++;
                    break;

                case SUBI: // Rd <-  Rd – k
                    registers[instrucionRegister.r1] = registers[instrucionRegister.r1] - instrucionRegister.p;
                    programCounter++;
                    break;

                case ADD: // Rd <-  Rd + Rs
                    registers[instrucionRegister.r1] = registers[instrucionRegister.r1] + registers[instrucionRegister.r2];
                    programCounter++;
                    break;

                case SUB: // Rd <-  Rd - Rs
                    registers[instrucionRegister.r1] = registers[instrucionRegister.r1] - registers[instrucionRegister.r2];
                    programCounter++;
                    break;

                case MULT: // Rd <-  Rd * Rs
                    registers[instrucionRegister.r1] = registers[instrucionRegister.r1] * registers[instrucionRegister.r2];
                    programCounter++;
                    break;
//              --------------------------------------------------------------------------------------------------

//              --------------------------------------------------------------------------------------------------
//              Instruções de Movimentação
                case LDI: // Rd <-  k
                    registers[instrucionRegister.r1] = instrucionRegister.p;
                    programCounter++;
                    break;

                case LDD: // Rd <-  [A]
                    registers[instrucionRegister.r1] = memory[instrucionRegister.p].p;
                    programCounter++;
                    break;

                case STD: // [A] <-  Rs
                    memory[instrucionRegister.p].opc = Opcode.DATA;
                    memory[instrucionRegister.p].p = registers[instrucionRegister.r1];
                    programCounter++;
                    break;

                case LDX: // Rd <-  [Rs]
                    registers[instrucionRegister.r1] = memory[registers[instrucionRegister.r2]].p; // OBS
                    programCounter++;
                    break;

                case STX: // [Rd] <- Rs
                    memory[registers[instrucionRegister.r1]].opc = Opcode.DATA;
                    memory[registers[instrucionRegister.r1]].p = registers[instrucionRegister.r2];
                    programCounter++;
                    break;

                case SWAP: // T <- Ra | Ra <- Rb | Rb <- T
                    int aux;
                    aux = registers[instrucionRegister.r1];
                    registers[instrucionRegister.r1] = registers[instrucionRegister.r2];
                    registers[instrucionRegister.r2] = aux;
                    programCounter++;
                    break;
//              --------------------------------------------------------------------------------------------------

                default:
                    break;
            }

            // VERIFICA INTERRUPÇÃO !!! - TERCEIRA FASE DO CICLO DE INSTRUÇÕES
            if (instrucionRegister.opc == Opcode.STOP) {
                break; // break sai do loop da cpu
            }
        }
    }
}
