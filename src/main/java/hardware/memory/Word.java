package hardware.memory;

import hardware.processor.Opcode;

// -------------------------------------------------------------------------------------------------------
// --------------------- M E M O R I A - definicoes de opcode e palavra de memoria -----------------------

public class Word { // cada posicao da memoria tem uma instrucao (ou um dado)
    public Opcode opc; //
    public int r1; // indice do primeiro registrador da operacao (Rs ou Rd cfe opcode na tabela)
    public int r2; // indice do segundo registrador da operacao (Rc ou Rs cfe operacao)
    public int p; // parametro para instrucao (k ou A cfe operacao), ou o dado, se opcode = DADO

    
	public static final Word BLANK = new Word(Opcode.___,-1,-1,-1);

	public Word(Opcode _opc, int _r1, int _r2, int _p) {  
		opc = _opc;   r1 = _r1;    r2 = _r2;	p = _p;
	}

	public static boolean isEmpty(Word _word) {
		boolean isEmpty = true;

		if(_word == null) return isEmpty;

		boolean isOpcodeEmpty 		= ((_word.opc == Opcode.___) ? true : false);
		boolean isRegister1Empty 	= ((_word.r1  == -1) 		 ? true : false);
		boolean isRegister2Empty 	= ((_word.r2  == -1) 		 ? true : false);
		boolean isParameterEmpty 	= ((_word.p   == -1) 		 ? true : false);
		
		if(
			!isOpcodeEmpty			||			
			!isRegister1Empty		||
			!isRegister2Empty		||
			!isParameterEmpty		== true		// se qualquer um tiver algum conteúdo
		) {
			isEmpty = false; 					// então a Word não está vazia
		}

		return isEmpty;
	}



	public String toString() {
		return "[ " + opc + ", " + r1 + ", " + r2 + ", " + p + "  ] ";
	}



	public static Word copy(Word _word) {
		Word word = new Word(_word.opc, _word.r1, _word.r2, _word.p);
		return word;
	}


	
	public static boolean equals(Word _word1, Word _word2) {

		boolean isOpcodeEquals		= (_word1.opc == _word2.opc);
		boolean isRegister1Equals 	= (_word1.r1 == _word2.r1);
		boolean isRegister2Equals 	= (_word1.r2 == _word2.r2);
		boolean isParameterEquals 	= (_word1.p == _word2.p);

		boolean isEqual = (
			isOpcodeEquals &&
			isRegister1Equals &&
			isRegister2Equals &&
			isParameterEquals
		);
		
		return isEqual;
	}
}
