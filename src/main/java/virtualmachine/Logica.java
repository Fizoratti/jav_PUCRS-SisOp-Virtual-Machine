package virtualmachine;

public class Logica {

    public void fibonacci(int quantidadeNumerosFibonacci) {
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


    public void fatorial(int n) {
        int fatorial = 1;

        for (int i = 1; i <= n; i++) {
            fatorial = fatorial * i;
        }

        System.out.println(fatorial);
    }

    public void bubbleSort() {
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

    private int[] p4_logica_bubblesort(int[] _array) {
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
}
