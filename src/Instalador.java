import java.util.List;


public class Instalador implements Runnable{
    private final List<Thread> hilos;
    private final String archivo1;
    private final String archivo2;

    public Instalador(List<Thread> hilos, String archivo1, String  archivo2){
        this.hilos = hilos;
        this.archivo1 = archivo1;
        this.archivo2 = archivo2;
    }

    @Override
    public void run() {
        for (Thread h : hilos){
            if (h.getName().equalsIgnoreCase(archivo1) || h.getName().equalsIgnoreCase(archivo2)) {
                try {
                    h.join();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return;
                }
            }
        }

    System.out.println("[Instalador] " + archivo1 + " y " + archivo2 + " listos: instalando...");
    System.out.println("Instalacion terminada");

    }
}
