package hardware.processor;

public enum Opcode {  // "operation code"
    DATA, ___, // se memoria nesta posicao tem um dado, usa DATA, se nao usada é NULO ___
    JMP, JMPI, JMPIG, JMPIL, JMPIE, JMPIM, JMPIGM, JMPILM, JMPIEM, STOP, // desvios e parada
    ADDI, SUBI, ADD, SUB, MULT, // matematicos
    LDI, LDD, STD, LDX, STX, SWAP, TRAP; // movimentacao
}
