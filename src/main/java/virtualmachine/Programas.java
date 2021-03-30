package virtualmachine;

// -------------------------------------------- programas aa disposicao para copiar na memoria (vide aux.carga)
public class Programas {

    public static Word[] progFibonacci10 = new Word[]{
            new Word(Opcode.LDI, 0, -1, 0),
            new Word(Opcode.LDI, 1, -1, 1),
            new Word(Opcode.LDI, 2, -1, 18), // posicao para escrita
            new Word(Opcode.LDI, 3, -1, 10), // reg para n de fibo
            new Word(Opcode.LDI, 7, -1, 16), // 4  //reg para stop

            new Word(Opcode.STX, 2, 0, -1), //inicio loop
            new Word(Opcode.ADDI, 2, -1, 1),
            new Word(Opcode.SUBI, 3, -1, 1),
            new Word(Opcode.JMPIE, 7, 3, -1),
            new Word(Opcode.STX, 2, 1, -1),
            new Word(Opcode.ADDI, 2, -1, 1),
            new Word(Opcode.SUBI, 3, -1, 1),
            new Word(Opcode.JMPIE, 7, 3, -1),
            new Word(Opcode.ADD, 0, 1, -1),
            new Word(Opcode.ADD, 1, 0, -1),
            new Word(Opcode.JMP, -1, -1, 5), //volta pro loop

            new Word(Opcode.STOP, -1, -1, -1)
    };

//    PRONTO
//    P2, um programa que le um valor de uma determinada posição (carregada no inicio),
//        se o número for menor que zero coloca -1 no início da posição de memória para saída
//        se for maior que zero este é o número de valores da sequencia de fibonacci a
//        serem escritos em sequencia a partir de uma posição de memoria
//
//        1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233, 377, 610, 987, 1597, 2584, 4181, 6765

    public static Word[] progFibonacci = new Word[]{

//          Parametro P recebe o numero do fatorial
            new Word(Opcode.LDI, 0, -1, 13), //armazena no registrador 0 o valor do parametro que dirá o máximo da sequencia de fibonacci
            new Word(Opcode.STD, 0, -1, 25),// armazena o valor do registrador 0 na posição 25 da memória 
            new Word(Opcode.LDD, 1, -1, 25),// guarda no registrador 1 o valor da posição 25 da memória
            new Word(Opcode.LDI, 7, -1, 8), // armazena no registrador 7 o valor 8 que indica o inicio da lógica do programa
            new Word(Opcode.JMPIG, 7, 1, -1),// verifica o valor do registrador 1 é positivo, se sim vai para a linha 8
            new Word(Opcode.LDI, 3, -1, -1),// caso o valor do registrador 1 seja negativo ou zero, armazena -1 no registrador 3 e cai nas duas linhas abaixo
            new Word(Opcode.STD, 3, -1, 26), // imprime na posição 26 da memória o valor -1 que está no registrador 3
            new Word(Opcode.STOP, -1, -1, -1), // linha 7 - para o programa

            new Word(Opcode.LDI, 0, -1, 0),//linha 8 - armazena no registrador 0 o valor 0 que indica o primeiro número de fibonacci
            new Word(Opcode.LDI, 1, -1, 1),// armazena no registrador 1 o valor 1 que indica o segundo número de fibonacci
            new Word(Opcode.LDI, 2, -1, 27), // posicao para escrita do início da saída d memória
            new Word(Opcode.LDD, 3, -1, 25), // armazena no registrador 3 o valor da posição 25 da memória
            new Word(Opcode.LDI, 7, -1, 7), // linha 12 -   armazena no registrador 7 a posição do stop

            new Word(Opcode.STX, 2, 0, -1), //guarda na posição da memória indicada pelo registrador 2 o valor que está no registrador 0
            new Word(Opcode.ADDI, 2, -1, 1),// guarda no registrador 2 o valor do r1++
            new Word(Opcode.SUBI, 3, -1, 1),// guarda no registrador 3 o valor de r1-1
            new Word(Opcode.JMPIE, 7, 3, -1),// verifica se o registrador 3 é zero, se sim pula para o valor que está no registrador 7 que é onde diz onde está o stop
            new Word(Opcode.STX, 2, 1, -1),// guarda na posição da memória indicada o valor
            new Word(Opcode.ADDI, 2, -1, 1), //guarda no registrador 2 o valor do r1++
            new Word(Opcode.SUBI, 3, -1, 1),// guarda no registrador 3 o valor de r1-1
            new Word(Opcode.JMPIE, 7, 3, -1),// verifica se o registrador 3 é zero
            new Word(Opcode.ADD, 0, 1, -1), // guarda no registrador 0 o valor do r0+r1
            new Word(Opcode.ADD, 1, 0, -1),// guarda no registrador 1 o valor de r1+r0
            new Word(Opcode.JMP, -1, -1, 13)// linha 23  volta pro loop
    };
//    AJUSTAR NEGATIVO
//    P3: dado um inteiro em alguma posição de memória,
//        se for negativo armazena -1 na saída;
//        se for positivo responde o fatorial do número na saída
//
//    0! = 1
//    1! = 1
//    2! = 2 . 1 = 2
//    3! = 3 . 2 . 1 = 6
//    4! = 4. 3 . 2 . 1 = 24
//    5! = 5 . 4 . 3 . 2 . 1 = 120
//    6! = 6 . 5 . 4 . 3 . 2 . 1 = 720
//    7! = 7 . 6 . 5 . 4. 3 . 2 . 1 = 5040
    public Word[] progFatorial = new Word[]{

            new Word(Opcode.LDI, 0, -1, 7), //armazena no registrador 0 o valor do parametro
            new Word(Opcode.STD, 0, -1, 30), // armazena na primeira posição de saída da memoria o valor do registrador 0
            new Word(Opcode.LDI, 7, -1, 9), // registrador com o valor de inicio do programa
            new Word(Opcode.LDI, 6, -1, 17), // registrador com o valor de inicio do programa caso seja zero
            new Word(Opcode.JMPIG, 7, 0, -1), // verifica se o valor do parametro é maior que zero, se sim, pula para a linha 9 (inicio do loop)
            new Word(Opcode.JMPIE, 6, 0, -1),// verifica se o valor do parametro é zero, se sim pula para a linha 18
            new Word(Opcode.LDI, 3, -1, -1), // cai aqui caso nao cai nos ifs de cima, ou seja, caso o valor seja negativo
            new Word(Opcode.STD, 3, -1, 31),
            new Word(Opcode.STOP, -1, -1, -1),

            new Word(Opcode.LDI, 4, -1, 14), // valor com o inicio do loop em memória
            new Word(Opcode.LDD, 0, -1, 30), // carrega o valor do fatorial no registrador 0 que está na posição 30 da memória
            new Word(Opcode.LDD, 1, -1, 30), // carrega o valor do fatorial
            new Word(Opcode.SUBI, 1, -1, 1), // diminui 1 de r1
            new Word(Opcode.JMPIE, 6, 1, -1),// valida se r1 é zero, se sim, significa que o parametro tinha o valor 1 e cai na linha 17

            new Word(Opcode.MULT, 0, 1, -1), // linha 14 - loop de multiplicação do fatorial
            new Word(Opcode.SUBI, 1, -1, 1),
            new Word(Opcode.JMPIG, 4, 1, -1),
            new Word(Opcode.STD, 0, -1, 31),// linha 17
            new Word(Opcode.STOP, -1, -1, -1),
    };

//    AJUSTAR
//    P4:
//    Para um N definido (5 por exemplo)
//        o programa ordena um vetor de N números em alguma posição de memória;
//        ordena usando bubble sort
//            loop ate que não swap nada
//                passando pelos N valores
//                faz swap de vizinhos se da esquerda maior que da direita

    public Word[] bubbleSort = new Word[]{
            new Word(Opcode.LDI, 0, -1, 12),  //carregando valor na memoria
            new Word(Opcode.STD, 0, -1, 40),

            new Word(Opcode.LDI, 0, -1, 20),
            new Word(Opcode.STD, 0, -1, 41),

            new Word(Opcode.LDI, 0, -1, 12),
            new Word(Opcode.STD, 0, -1, 42),

            new Word(Opcode.LDI, 0, -1, 1),
            new Word(Opcode.STD, 0, -1, 43),

            new Word(Opcode.LDI, 0, -1, 29),
            new Word(Opcode.STD, 0, -1, 44),

            new Word(Opcode.LDI, 0, -1, -12),
            new Word(Opcode.STD, 0, -1, 45),

            new Word(Opcode.LDI, 0, -1, 0),
            new Word(Opcode.STD, 0, -1, 46),// valores carregados

            new Word(Opcode.LDI, 3, -1, 6),
            new Word(Opcode.LDI, 4, -1, 6),
            new Word(Opcode.LDI, 5, -1, 20),
            new Word(Opcode.LDI, 6, -1, 33),
            new Word(Opcode.LDI, 7, -1, 38),
            new Word(Opcode.LDI, 0, -1, 40),

            new Word(Opcode.JMPIE, 6, 3, -1), //inicio loop

            new Word(Opcode.SUBI, 3, -1, 1),
            new Word(Opcode.LDX, 1, 0, -1),
            new Word(Opcode.ADDI, 0, -1, 1),
            new Word(Opcode.LDX, 2, 0, -1),
            new Word(Opcode.SUB, 2, 1, -1),
            new Word(Opcode.JMPIG, 5, 2, -1),

            new Word(Opcode.LDX, 2, 0, -1),
            new Word(Opcode.STX, 0, 1, -1),
            new Word(Opcode.SUBI, 0, -1, 1),
            new Word(Opcode.STX, 0, 2, -1),
            new Word(Opcode.ADDI, 0, -1, 1),
            new Word(Opcode.JMPI, 5, 0, -1),

            new Word(Opcode.JMPIE, 7, 4, -1),
            new Word(Opcode.SUBI, 4, -1, 1),
            new Word(Opcode.LDI, 0, -1, 40),
            new Word(Opcode.LDI, 3, -1, 6),
            new Word(Opcode.JMPIG, 5, 0, -1),//fim do loop

            new Word(Opcode.STOP, -1, -1, -1)
    };
}
