package hardware.memory;

import hardware.Hardware;
import hardware.processor.Opcode;
import util.Console;

public class Memory implements Hardware {
    
    private static Memory INSTANCE;
    
    public int size;
    public Word[] data;

    private Memory(int _size) {
        this.size = _size;
        data = new Word[size];
    }

    public void write(Word _word, int _position) {
        Word.copy(data[_position] = _word);
    }

    // Método do Memory Manager
    public void load(Word[] _words, int _start) {
        if(_words[0] == null) return;
        int i = _start;
        for (Word word : _words) {
            write(word, i);
            i++;
        }
    }

    public Word read(int _position) {
        return Word.copy(data[_position]);
    }


    public void delete(int _position) {
        data[_position] = Word.BLANK;
    }

    

    // ### Métodos dump são do Memory Manager


    // Mostra uma (1) word
    public void dump(Word _word) {
        Console.log(_word.toString());
    }

    // Exibe todo o conteúdo da memória
    public void dump(Word[] _memory) {
        if(_memory[0] == null) return;
        int index = 0;
        for (Word word : _memory) {
            if(Word.isEmpty(word)) {                               // caso a Word tenha conteúdo
                Console.log(index + ":  ");  dump(word);       // exibe o conteúdo da Word
            }
            index++;
        }
    }

    // Exibe um trecho da memória
    public void dump(Word[] m, int ini, int end) {
        for (int i = ini; i < end; i++) {
            Console.print(i); Console.print(":  ");  dump(m[i]);
        }
    }

    public void cleanMemory() {
        for (int i = 0; i < size; i++) {
            Memory.get().data[i] = new Word(Opcode.___, -1, -1, -1);
        }
    }

    public static void init(int _size) {
        if(INSTANCE == null)
            INSTANCE = new Memory(_size);

        INSTANCE.cleanMemory();
    }

    public static Memory get() {
        return INSTANCE;
    }

}
