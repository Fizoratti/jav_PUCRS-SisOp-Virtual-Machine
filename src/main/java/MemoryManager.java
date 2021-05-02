import java.util.*;

public class MemoryManager {
    public final int MEMORY_SIZE;
    public int tamPag;
    public int tamFrame;
    public int nroFrames;
    public boolean[] frameLivre;

    public Map<Integer, ArrayList<Integer>> tabPaginas;

    public MemoryManager(int _tamMemoria) {
        this.MEMORY_SIZE = _tamMemoria;
        this.tamFrame = 16;
        this.tamPag = 16;
        this.nroFrames = this.MEMORY_SIZE / this.tamPag;
        this.frameLivre = new boolean[this.nroFrames];

        this.tabPaginas = new HashMap<Integer, ArrayList<Integer>>();

        Arrays.fill(frameLivre, true);
    }

    public boolean allocate() {
        return true;

        // popular tabPaginas e frameLivre com o programa (int, int[] (processID, posicao do frame livre) / false (de acordo com a posicao do frame))
    }

    public void unallocate(int[] pass) {

    }



}
