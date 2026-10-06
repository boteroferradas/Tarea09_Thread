import java.util.List;

public class Monitor extends Thread{
    private final List<Thread> hilosAMonitorear;
    private final int intervaloMs;

    public Monitor(List<Thread> hilosAMonitorear, int intervaloMs){
        this.hilosAMonitorear = hilosAMonitorear;
        this.intervaloMs = intervaloMs;
        this.setDaemon(true);
    }

    @Override
    public void run() {
        while (true) {
            int hilosVivos = hilosAMonitorear.size();

            for (Thread h : hilosAMonitorear) {
                if (!h.isAlive()) {
                    hilosVivos--;
                }
            }
            System.out.println("[Monitor] Descargas en curso: " + hilosVivos);

            if (hilosVivos == 0) {
                System.out.println("No queda ninguna descarga en curso");
                break;
            }

            try {
                Thread.sleep(intervaloMs);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
}
