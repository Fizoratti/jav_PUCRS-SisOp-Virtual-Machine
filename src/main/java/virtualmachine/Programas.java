package virtualmachine;

// -------------------------------------------- programas aa disposicao para copiar na memoria (vide aux.carga)
public class Programas {

    public Word[] progFibonacci = new Word[]{
//            P2, um programa que le um valor de uma determinada posição (carregada no inicio),
//                se o número for menor que zero coloca -1 no início da posição de memória para saída
//                se for maior que zero este é o número de valores da sequencia de fibonacci a
//                serem escritos em sequencia a partir de uma posição de memoria
//
//                1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233, 377, 610, 987, 1597, 2584, 4181, 6765

            // Insere nos registradores os dois primeiros termos (0 e 1)
            new Word(Opcode.LDI, 1, -1, 0),
            new Word(Opcode.LDI, 2, -1, 1),

            // Insere no registrador o de números da sequencia de fibonacci
            new Word(Opcode.LDI, 5, -1, 19),

            // Insere o valor 50 no R4 (ponteiro para a primeira posição que iremos salvar o primeiro número da sequência
            new Word(Opcode.LDI, 4, -1, 50),

            //Salvando 0 e 1 nas posições 50 e 51 da memória --> STX [Rd],Rs
            new Word(Opcode.STX, 4, 1, -1),
            new Word(Opcode.ADDI, 4, -1, 1),
            new Word(Opcode.STX, 4, 2, -1),

            //Posição da memória que começa a repetição no R6 (posição 8)
            new Word(Opcode.LDI, 6, -1, 8),

            //Repetição:
            //Apontamos para o valor anterior na memória
            new Word(Opcode.SUBI, 4, -1, 1),

            //Carrega no R3 o valor anterior (carrega direto da memória usando o ponteiro R4 que criamos)
            new Word(Opcode.LDX, 3, 4, 0),

            //Somando termo atual e o anterior
            new Word(Opcode.ADD, 2, 3, -1),

            //Retorna o ponteiro R4 para a próxima posição a salvar
            new Word(Opcode.ADDI, 4, -1, 2),

            //Salvamos o novo número na memória
            new Word(Opcode.STX, 4, 2, -1),

            //Testa se deve continuar a repetição ou parar
            new Word(Opcode.SUBI, 5, -1, 1),
            new Word(Opcode.JMPIG, 6, 5, -1),

            //Fim
            new Word(Opcode.STOP, -1, -1, -1)
    };

    public Word[] progFatorial = new Word[]{
//            P3: dado um inteiro em alguma posição de memória,
//                se for negativo armazena -1 na saída;
//                se for positivo responde o fatorial do número na saída

//            0! = 1
//            1! = 1
//            2! = 2 . 1 = 2
//            3! = 3 . 2 . 1 = 6
//            4! = 4. 3 . 2 . 1 = 24
//            5! = 5 . 4 . 3 . 2 . 1 = 120
//            6! = 6 . 5 . 4 . 3 . 2 . 1 = 720
//            7! = 7 . 6 . 5 . 4. 3 . 2 . 1 = 5040


    };

    public Word[] progMinimo = new Word[]{
            new Word(Opcode.LDI, 0, -1, 999), // carrega o valor 999 no registrador 0
            new Word(Opcode.STD, 0, -1, 10),  // Passando pra memoria na posicao 10 o valor do registrador 0
            new Word(Opcode.STD, 0, -1, 11),
            new Word(Opcode.STD, 0, -1, 12),
            new Word(Opcode.STD, 0, -1, 13),
            new Word(Opcode.STD, 0, -1, 14),
            new Word(Opcode.STOP, -1, -1, -1)
    };
}
