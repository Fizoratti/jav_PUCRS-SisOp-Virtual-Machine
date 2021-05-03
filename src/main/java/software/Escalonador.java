package software;

import virtualmachine.VM;

import java.util.concurrent.Semaphore;

public class Escalonador extends Thread {
    public static Semaphore useESC;
    private volatile int cont;

    //construtor do escalonador
    public Escalonador() {
        useESC = new Semaphore(1);
    }

    //run da thread escalonador, espera alguem mandar ela executar, 
    //apos alguem mandar executar, 1 processo por vez,
    //espera que a rotina de tratamento libere o uso da cpu para voltar a escalonar
    public void run() {
        while (true) {
            try {
//                VM.semESC.acquire();
            } catch (Exception e) {
            }
            cont = 0;
            try {
                useESC.acquire();
            } catch (Exception e) {
            }

            //apos liberado, fica testando se a fila é > 0, caso seja executa 1 vez e espera a liberacao
            //e caso seja 0, continua esperando para escalonar
            while (cont == 0) {
                //pega o primeiro da fila de prontos, muda status para executando, seta contexto na cpu, retira da fila de prontos, libera cpu.
                if (VM.get().pm.pcbList.size() > 0) {
                    VM.get().pm.pcbList.peek().status = StatusPCB.EXECUTANDO;
                    VM.get().cpu.setContext(VM.get().pm.pcbList.peek().allocatedPages, VM.get().pm.pcbList.peek().pc, VM.get().pm.pcbList.peek().id, VM.get().pm.pcbList.peek().reg);
                    VM.get().pm.pcbList.remove(VM.get().pm.pcbList.peek());
                    VM.get().cpu.run();
//                    VM.semCPU.release();
                    cont++;
                }
            }
        }
    }
}
