// PUCRS - Escola Politécnica - Sistemas Operacionais
// Prof. Fernando Dotti
// Código fornecido como parte da solução do projeto de Sistemas Operacionais
//
// Fase 1 - máquina virtual (vide enunciado correspondente)
//

package virtualmachine;

public class Sistema {

    // -------------------------------------------------------------------------------------------------------
    // --------------------- H A R D W A R E - definicoes de HW ----------------------------------------------

    // -------------------------------------------------------------------------------------------------------
    // --------------------- M E M O R I A - definicoes de opcode e palavra de memoria ----------------------

    public class Word { // cada posicao da memoria tem uma instrucao (ou um dado)
        public Opcode opc; //
        public int r1; // indice do primeiro registrador da operacao (Rs ou Rd cfe opcode na tabela)
        public int r2; // indice do segundo registrador da operacao (Rc ou Rs cfe operacao)
        public int p; // parametro para instrucao (k ou A cfe operacao), ou o dado, se opcode = DADO

        public Word(Opcode _opc, int _r1, int _r2, int _p) {
            opc = _opc;
            r1 = _r1;
            r2 = _r2;
            p = _p;
        }
    }
    // -------------------------------------------------------------------------------------------------------

    // -------------------------------------------------------------------------------------------------------
    // --------------------- C P U - definicoes da CPU -----------------------------------------------------

    public enum Opcode {  // "operation code"
        DATA, ___, // se memoria nesta posicao tem um dado, usa DATA, se nao usada é NULO ___
        JMP, JMPI, JMPIG, JMPIL, JMPIE, JMPIM, JMPIGM, JMPILM, JMPIEM, STOP, // desvios e parada
        ADDI, SUBI, ADD, SUB, MULT, // matematicos
        LDI, LDD, STD, LDX, STX, SWAP; // movimentacao
    }

    public class CPU {
        // característica do processador: contexto da CPU ...
        private int pc; // ... composto de program counter,
        private Word ir; // instruction register,
        private int[] reg; // registradores da CPU

        private Word[] m; // CPU acessa MEMORIA, guarda referencia 'm' a ela. memoria nao muda. ee sempre
        // a mesma.

        public CPU(Word[] _m) { // ref a MEMORIA e interrupt handler passada na criacao da CPU
            m = _m; // usa o atributo 'm' para acessar a memoria.
            reg = new int[8]; // aloca o espaço dos registradores
        }

        public void setContext(int _pc) { // no futuro esta funcao vai ter que ser
            pc = _pc; // limite e pc (deve ser zero nesta versao)
        }

        public void run() {
            while (true) {                // ciclo de instrucoes. acaba cfe instrucao, veja cada caso.
                ir = m[pc];                // FETCH - busca posicao da memoria apontada por pc, guarda em ir
                switch (ir.opc) {        // EXECUTA INSTRUCAO NO ir - para cada opcode, sua execução

                    case JMP: // PC ← k
                        pc = ir.p;
                        break;

                    case JMPI: // PC ← Rs
                        pc = reg[ir.r1];
                        break;

                    case JMPIG: // If Rc > 0 Then PC ← Rs Else PC ← PC +1 | if(R2>0){PC=R1}else{PC=PC++}
                        if (reg[ir.r2] > 0) {
                            pc = reg[ir.r1];
                        } else {
                            pc++;
                        }
                        break;

                    case JMPIL: // if Rc < 0 then PC ← Rs Else PC ← PC +1
                        if (reg[ir.r2] < 0) {
                            pc = reg[ir.r1];
                        } else {
                            pc++;
                        }
                        break;

                    case JMPIE: // if Rc = 0 then PC ← Rs Else PC ← PC +1
                        if (reg[ir.r2] == 0) {
                            pc = reg[ir.r1];
                        } else {
                            pc++;
                        }
                        break;

                    case JMPIM: // PC <- [A]
                        pc = m[ir.p].p;
                        pc++;
                        break;

                    case ADD: // Rd ← Rd + Rs
                        reg[ir.r1] = reg[ir.r1] + reg[ir.r2];
                        pc++;
                        break;

                    case ADDI: // Rd ← Rd + k
                        reg[ir.r1] = reg[ir.r1] + ir.p;
                        pc++;
                        break;

                    case SUB: // Rd ← Rd - Rs
                        reg[ir.r1] = reg[ir.r1] - reg[ir.r2];
                        pc++;
                        break;

                    case SUBI: // Rd ← Rd – k
                        reg[ir.r1] = reg[ir.r1] - ir.p;
                        pc++;
                        break;

                    case MULT: // Rd ← Rd * Rs
                        reg[ir.r1] = reg[ir.r1] * reg[ir.r2];
                        pc++;
                        break;

                    case LDI: // Rd ← k
                        reg[ir.r1] = ir.p;
                        pc++;
                        break;

                    case STD: // [A] ← Rs
                        m[ir.p].opc = Opcode.DATA;
                        m[ir.p].p = reg[ir.r1];
                        pc++;
                        break;

                    case STX: // [Rd] ←Rs
                        m[reg[ir.r1]].opc = Opcode.DATA;
                        m[reg[ir.r1]].p = reg[ir.r2];
                        pc++;
                        break;

                    case LDD: // Rd ← [A]
                        reg[ir.r1] = m[ir.p].p;
                        pc++;
                        break;

                    case LDX: // Rd ← [Rs]
                        reg[ir.r1] = m[reg[ir.r2]].p;
                        pc++;
                        break;

                    case SWAP: // T <- Ra | Ra <- Rb | Rb <- T
                        int aux;
                        aux = reg[ir.r1];
                        reg[ir.r1] = reg[ir.r2];
                        reg[ir.r2] = aux;
                        pc++;
                        break;

                    case STOP: // por enquanto, para execucao
                        break;

                    case DATA:
                        break;

                    default:
                        break;
                }

                // VERIFICA INTERRUPÇÃO !!! - TERCEIRA FASE DO CICLO DE INSTRUÇÕES
                if (ir.opc == Opcode.STOP) {
                    break; // break sai do loop da cpu
                }
            }
        }
    }
    // ------------------ C P U - fim ------------------------------------------------------------------------
    // -------------------------------------------------------------------------------------------------------

    // ------------------- V M - constituida de CPU e MEMORIA -----------------------------------------------
    // -------------------------- atributos e construcao da VM -----------------------------------------------
    public class VM {
        public int tamMem;
        public Word[] m;
        public CPU cpu;

        public VM() { // vm deve ser configurada com endereço de tratamento de interrupcoes
            // memória
            tamMem = 1024;
            m = new Word[tamMem]; // m é a memoria
            for (int i = 0; i < tamMem; i++) {
                m[i] = new Word(Opcode.___, -1, -1, -1);
            }
            ;
            // cpu
            cpu = new CPU(m);
        }
    }
    // ------------------- V M - fim ------------------------------------------------------------------------
    // -------------------------------------------------------------------------------------------------------

    // --------------------H A R D W A R E - fim -------------------------------------------------------------
    // -------------------------------------------------------------------------------------------------------

    // -------------------------------------------------------------------------------------------------------
    // -------------------------------------------------------------------------------------------------------
    // ------------------- S O F T W A R E - inicio ----------------------------------------------------------

    // ------------------- VAZIO

    // -------------------------------------------------------------------------------------------------------
    // ------------------- S I S T E M A --------------------------------------------------------------------

    public VM vm;

    public Sistema() { // a VM com tratamento de interrupções
        vm = new VM();
    }

    // ------------------- S I S T E M A - fim --------------------------------------------------------------
    // -------------------------------------------------------------------------------------------------------

    // -------------------------------------------------------------------------------------------------------
    // ------------------- instancia e testa sistema
    public static void main(String args[]) {
        Sistema s = new Sistema();

        // s.p2_logica_fibonacci(5);
        // s.p3_logica_fatorial(6);
        // s.test_p4_logica_bubblesort();

        // s.test();
        // s.test1();
        s.test2();
        // s.test3();
        // s.test4();
    }
    // -------------------------------------------------------------------------------------------------------
    // --------------- TUDO ABAIXO DE MAIN É AUXILIAR PARA FUNCIONAMENTO DO SISTEMA - nao faz parte

    // -------------------------------------------- teste do sistema , veja classe de programas

    public void p2_logica_fibonacci(int quantidadeNumerosFibonacci) {
        int r1;
        int r2;
        int p;
        r1 = 0;
        r2 = 1;

        // 1 - 1 - 2 - 3 - 5 - 8

        for (int i = 0; i < quantidadeNumerosFibonacci; i++) {  // linha 10 a 20
            p = r1 + r2;
            r1 = r2;
            r2 = p;

            System.out.println(r2);
        }
    }

    public void p3_logica_fatorial(int n) {
        int fatorial = 1;

        for (int i = 1; i <= n; i++) {
            fatorial = fatorial * i;
        }

        System.out.println(fatorial);
    }

    public int[] p4_logica_bubblesort(int[] _array) {
        int[] array = _array;
        int temp;
        int length = array.length;
        int swapped;

        for (int j = 0; j < length - 1; j++) {
            swapped = 0;                            // false

            for (int i = 0; i < length - j - 1; i++) {
                if (array[i] > array[i + 1]) {
                    temp = array[i];
                    array[i] = array[i + 1];
                    array[i + 1] = temp;
                    swapped = 1;                    // true
                }
            }

            if (swapped == 0) {
                break;
            }
        }

        return array;
    }

    public void test_p4_logica_bubblesort() {
        int[] array = {3, 6, 6, 2, 3, 7, 4, 5, 0, 2, -1, 9};

        System.out.print("Before: ");
        for (int n : array) {
            System.out.print(n + ", ");
        }
        System.out.println();

        int[] sortedArray = p4_logica_bubblesort(array);

        System.out.print("After: ");
        for (int n : sortedArray) {
            System.out.print(n + ", ");
        }
        System.out.println();
    }

    public void test() {
        Aux aux = new Aux();
        Word[] p = new Programas().progMinimo;
        aux.carga(p, vm.m);
        vm.cpu.setContext(0);
        System.out.println("---------------------------------- programa carregado ");
        aux.dump(vm.m, 0, 15);
        System.out.println("---------------------------------- após execucao ");
        vm.cpu.run();
        aux.dump(vm.m, 0, 15);
    }

    public void test1() {
        Aux aux = new Aux();
        Word[] p = new Programas().programa1;
        aux.carga(p, vm.m);
        vm.cpu.setContext(0);
        System.out.println("---------------------------------- programa carregado ");
        aux.dump(vm.m, 0, 33);
        System.out.println("---------------------------------- após execucao ");
        vm.cpu.run();
        aux.dump(vm.m, 0, 33);
    }

    public void test2() {
        Aux aux = new Aux();
        Word[] p = new Programas().programa2;
        aux.carga(p, vm.m);
        vm.cpu.setContext(0);
        System.out.println("---------------------------------- programa carregado ");
        aux.dump(vm.m, 0, 60);
        System.out.println("---------------------------------- após execucao ");
        vm.cpu.run();
        aux.dump(vm.m, 0, 60);
    }

    public void test3() {
        Aux aux = new Aux();
        Word[] p = new Programas().programa3;
        aux.carga(p, vm.m);
        vm.cpu.setContext(0);
        System.out.println("---------------------------------- programa carregado ");
        aux.dump(vm.m, 0, 60);
        System.out.println("---------------------------------- após execucao ");
        vm.cpu.run();
        aux.dump(vm.m, 0, 60);
    }

    public void test4() {
        Aux aux = new Aux();
        Word[] p = new Programas().programa4;
        aux.carga(p, vm.m);
        vm.cpu.setContext(0);
        System.out.println("---------------------------------- programa carregado ");
        aux.dump(vm.m, 0, 60);
        System.out.println("---------------------------------- após execucao ");
        vm.cpu.run();
        aux.dump(vm.m, 0, 60);
    }

    // ------------------------------------------- classes e funcoes auxiliares
    public class Aux {
        public void dump(Word w) {
            System.out.print("[ " + w.opc + ", " + w.r1 + ", " + w.r2 + ", " + w.p + " ] \n");
        }

        public void dump(Word[] m, int ini, int fim) {
            for (int i = ini; i < fim; i++) {
                System.out.print(i);
                System.out.print(":  ");
                dump(m[i]);
            }
        }

        public void carga(Word[] p, Word[] m) {
            for (int i = 0; i < p.length; i++) {
                m[i].opc = p[i].opc;
                m[i].r1 = p[i].r1;
                m[i].r2 = p[i].r2;
                m[i].p = p[i].p;
            }
        }
    }
    // ------------------------------------------- fim classes e funcoes auxiliares

    // -------------------------------------------- programas aa disposicao para copiar na memoria (vide aux.carga)
    public class Programas {
        public Word[] progMinimo = new Word[]{
                new Word(Opcode.LDI, 0, -1, 999), // carrega o valor 999 no registrador 0
                new Word(Opcode.STD, 0, -1, 10),  // Passando pra memoria na posicao 10 o valor do registrador 0
                new Word(Opcode.STD, 0, -1, 11),
                new Word(Opcode.STD, 0, -1, 12),
                new Word(Opcode.STD, 0, -1, 13),
                new Word(Opcode.STD, 0, -1, 14),
                new Word(Opcode.STOP, -1, -1, -1)};

        /*
            ALGORITMO:
            10 primeiros números da sequência de Fibonacci.
        */
        public Word[] programa1 = new Word[]{
                new Word(Opcode.LDI, 0, -1, 0),        // cpu.r[0]      = 0   	 | 										*R0:0  r1:-  r2:-  r3:-  r4:-  r5:-  r6:-  r7:-
                new Word(Opcode.STD, 0, -1, 20),    // memory[20]    = 0 	 | memory.write(Address: 20, DATA: 0)
                new Word(Opcode.LDI, 1, -1, 1),        // cpu.r[1]      = 1   	 | 										 r0:0 *R1:1  r2:-  r3:-  r4:-  r5:-  r6:-  r7:-
                new Word(Opcode.STD, 1, -1, 21),    // memory[21]    = 1   	 | memory.write(Address: 21, DATA: 1)
                new Word(Opcode.LDI, 7, -1, 22),    // cpu.r[7]      = 22  	 | 										 r0:0  r1:1  r2:-  r3:-  r4:-  r5:-  r6:- *R7:22
                new Word(Opcode.LDI, 5, -1, 6),        // cpu.r[5]      = 6   	 | 										 r0:0  r1:2  r2:0  r3:-  r4:- *R5:6  r6:-  r7:22
                new Word(Opcode.LDI, 6, -1, 31),    // cpu.r[6]      = 31  	 | 										 r0:0  r1:1  r2:0  r3:-  r4:-  r5:6 *R6:31 r7:22
                new Word(Opcode.LDI, 2, -1, 0),        // cpu.r[2]      = 0   	 | 										 r0:0  r1:1 *R2:0  r3:-  r4:-  r5:6  r6:31 r7:22
                new Word(Opcode.ADD, 2, 0, -1),        // cpu.r[2]	     = 0+0 	 | cpu.r[2] = cpu.r[2] + cpu.r[0]
                new Word(Opcode.LDI, 0, -1, 0),        // cpu.r[0]      = 0     | 										*R0:0  r1:1  r2:0  r3:-  r4:-  r5:6  r6:31 r7:22
                new Word(Opcode.ADD, 0, 1, -1),        // cpu.r[0]      = 0+1   | cpu.r[0] = cpu.r[0] + cpu.r[1]		*R0:1  r1:1  r2:0  r3:-  r4:-  r5:6  r6:31 r7:22
                new Word(Opcode.ADD, 1, 2, -1),        // cpu.r[1]      = 1+1   | cpu.r[1] = cpu.r[1] + cpu.r[2]		 r0:1 *R1:2  r2:0  r3:-  r4:-  r5:6  r6:31 r7:22
                new Word(Opcode.STX, 7, 1, -1),        // memory[r7:22] = 1     | memory.write(Address:22, DATA:1)
                new Word(Opcode.ADDI, 7, -1, 1),    // cpu.r[7]      = 22+1  | cpu.r[7] = cpu.r[7] + 1				 r0:1  r1:2  r2:0  r3:-  r4:-  r5:6  r6:31*R7:23
                new Word(Opcode.SUB, 6, 7, -1),        // cpu.r[6]      = 31-23 | cpu.r[6] = cpu.r[6] - cpu.r[7]		 r0:1  r1:2  r2:0  r3:-  r4:-  r5:6 *R6:8  r7:23
                new Word(Opcode.JMPIG, 5, 6, -1),    // jump(memory[6])       | if(cpu.r[6]>0) jump(memory[cpu.r[5])  r0:1  r1:2  r2:0  r3:-  r4:- *R5:6  r6:8  r7:23
                new Word(Opcode.STOP, -1, -1, -1)
        };

        /*
            ALGORITMO:
            Escrever N números da sequência de Fibonacci a partir de um valor na memória.
            Escreve números de maneira crescente.
        */
        public Word[] programa2 = new Word[]{
                new Word(Opcode.LDI, 0, -1, -1),    // cpu.r[0]      = ?   	 | cpu.r[0] = -1 (null)					*R0:?  r1:-  r2:-  r3:-  r4:-  r5:-  r6:-  r7:-
                new Word(Opcode.LDI, 1, -1, 0),        // cpu.r[1]      = 0   	 | 										 r0:? *R1:0  r2:-  r3:-  r4:-  r5:-  r6:-  r7:-
                new Word(Opcode.LDI, 2, -1, 1),        // cpu.r[2]      = 1   	 | 										 r0:?  r1:0 *R2:1  r3:-  r4:-  r5:-  r6:-  r7:-
                new Word(Opcode.LDI, 3, -1, 50),    // cpu.r[3]      = 50    | 										 r0:?  r1:0  r2:1 *R3:50 r4:-  r5:-  r6:-  r7:-
                new Word(Opcode.LDX, 4, 3, -1),        // cpu.r[4]      = ?     | cpu.r[4] = memory.read(Address:50) 	 r0:?  r1:0  r2:1  r3:50*R4:?  r5:-  r6:-  r7:-
                new Word(Opcode.LDI, 5, -1, 18),    // cpu.r[5]      = 18    | 									 	 r0:?  r1:0  r2:1  r3:50 r4:? *R5:18 r6:-  r7:-
                new Word(Opcode.LDI, 6, -1, 32),    // cpu.r[6]      = 32    | 									 	 r0:?  r1:0  r2:1  r3:50 r4:?  r5:18*R6:32 r7:-
                new Word(Opcode.JMPIL, 5, 4, -1),
                new Word(Opcode.JMPIE, 6, 4, -1),
                new Word(Opcode.STX, 3, 1, -1),        // memory[50] = 0 		 | memory.write(Address:50, DATA:0)
                new Word(Opcode.ADDI, 3, -1, 1),
                new Word(Opcode.SUBI, 4, -1, 1),
                new Word(Opcode.JMPIE, 6, 4, -1),
                new Word(Opcode.JMP, -1, -1, 20),
                new Word(Opcode.STX, 3, 0, -1),
                new Word(Opcode.JMP, -1, -1, 32),
                new Word(Opcode.LDI, 0, -1, 0),
                new Word(Opcode.LDI, 1, -1, 1),
                new Word(Opcode.LDI, 5, -1, 23),
                new Word(Opcode.LDI, 2, -1, 0),
                new Word(Opcode.ADD, 2, 1, -1),
                new Word(Opcode.LDI, 0, -1, 0),
                new Word(Opcode.ADD, 0, 1, -1),
                new Word(Opcode.ADD, 1, 2, -1),
                new Word(Opcode.STX, 3, 1, -1),
                new Word(Opcode.ADDI, 3, -1, 1),
                new Word(Opcode.SUBI, 4, -1, 1),
                new Word(Opcode.JMPIG, 5, 4, -1),
                new Word(Opcode.STOP, -1, -1, -1)
        };

        /*
            ALGORITMO:
            Escrever o fatorial de um valor pré determinado na memória.
        */
        public Word[] programa3 = new Word[]{
                new Word(Opcode.DATA, 50, -1, 10),
                new Word(Opcode.LDI, 0, -1, -1),
                new Word(Opcode.LDI, 1, -1, 1),
                new Word(Opcode.LDI, 2, -1, 50),
                new Word(Opcode.LDX, 3, 2, -1),
                new Word(Opcode.LDI, 4, -1, 11),
                new Word(Opcode.LDI, 5, -1, 13),
                new Word(Opcode.LDI, 6, -1, 25),
                new Word(Opcode.JMPIL, 4, 3, -1),
                new Word(Opcode.JMPIE, 5, 3, -1),
                new Word(Opcode.JMP, -1, -1, 15),
                new Word(Opcode.STX, 2, 0, -1),
                new Word(Opcode.JMP, -1, -1, 24),
                new Word(Opcode.STX, 2, 1, -1),
                new Word(Opcode.JMP, -1, -1, 24),
                new Word(Opcode.LDI, 4, -1, 0),
                new Word(Opcode.ADD, 4, 3, -1),
                new Word(Opcode.SUBI, 3, -1, 1),
                new Word(Opcode.STX, 2, 1, -1),
                new Word(Opcode.JMPIE, 6, 3, -1),
                new Word(Opcode.LDI, 0, -1, 21),
                new Word(Opcode.MULT, 4, 3, -1),
                new Word(Opcode.STX, 2, 4, -1),
                new Word(Opcode.SUB, 3, -1, 1),
                new Word(Opcode.JMPIG, 0, 3, -1),
                new Word(Opcode.STOP, -1, -1, -1)
        };

        /*
            ALGORITMO:
            Bubble sort.
        */
        public Word[] programa4 = new Word[]{
                new Word(Opcode.DATA, 0, 0, 5),
                new Word(Opcode.DATA, 1, 0, 3),
                new Word(Opcode.DATA, 2, 0, 4),
                new Word(Opcode.DATA, 3, 0, 1),
                new Word(Opcode.DATA, 4, 0, 2),

                new Word(Opcode.LDI, 3, -1, 6),
                new Word(Opcode.LDI, 2, -1, 5),
                new Word(Opcode.LDI, 0, -1, 1),
                new Word(Opcode.LDI, 1, -1, 1),
                new Word(Opcode.JMPIL, 4, 2, -1),
                new Word(Opcode.STOP, -1, -1, -1),
                new Word(Opcode.LDX, 3, 0, -1),
                new Word(Opcode.LDX, 4, 1, -1),
                new Word(Opcode.SUB, 3, 4, -1),
                new Word(Opcode.LDI, 4, -1, 12),
                new Word(Opcode.JMPIG, 4, 3, -1),
                new Word(Opcode.JMP, -1, -1, 4),
                new Word(Opcode.LDX, 3, 1, -1),
                new Word(Opcode.SWAP, 3, 4, -1),
                new Word(Opcode.STX, 0, 3, -1),
                new Word(Opcode.STX, 1, 4, -1),
                new Word(Opcode.ADDI, 0, -1, 1),
                new Word(Opcode.ADDI, 1, -1, 1),
                new Word(Opcode.JMP, -1, -1, 4)
        };

    }
}


