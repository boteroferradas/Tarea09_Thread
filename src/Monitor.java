import java.util.List;

/**
 * Clase Monitor que hereda de Thread, y se encarga de monitorear los hilos que esten
 * activos en el main, imprimiendo su estado cada X ms que le pasemos por parametro a la clase.
 */
public class Monitor extends Thread{
    private final List<Thread> hilosAMonitorear;
    private final int intervaloMs;


    /**
     * Contructor para inicializar el objeto, con una lista de objetos Thread y el
     * intervalo que queramos imprimir en consola el estado de los hilos en ms.
     * @param hilosAMonitorear
     * @param intervaloMs
     */
    public Monitor(List<Thread> hilosAMonitorear, int intervaloMs){
        this.hilosAMonitorear = hilosAMonitorear;
        this.intervaloMs = intervaloMs;
    }

    /**
     * Lógica principal del hilo que se ejecuta al llamar a Thread.start().
     * Guarda en una variable el tamañano de la lista de Thread que le pasemos,
     * comprueba con un bucle "for" que los hilos esten vivos e imprime su
     * situacion por consola. Si la variable se queda a 0, se avisa por consola y
     * el hilo finaliza.
     */
    @Override
    public void run() {
        while (true) {
            int hilosVivos = hilosAMonitorear.size();

            for (Thread h : hilosAMonitorear) {
                if (!h.isAlive()) {
                    hilosVivos--;
                }
            }

            if (hilosVivos == 0) {
                System.out.println("No queda ninguna descarga en curso");
                break;
            }

            System.out.println("[Monitor] Descargas en curso: " + hilosVivos);

            try {
                Thread.sleep(intervaloMs);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
}
