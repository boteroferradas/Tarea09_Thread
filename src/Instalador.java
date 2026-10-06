import java.util.List;

/**
 * Clase Instalador con la interfaz Runnable, encargada de coordinar la instalación de dos archivos
 * pasados por parametros a modo de String por su nombre una vez su descarga haya finalizado
 */
public class Instalador implements Runnable{
    private final List<Thread> hilos;
    private final String archivo1;
    private final String archivo2;

    /**
     * Constructor de la clase Instalador
     * @param hilos    Lista de hilos entre los que se buscaran los archivos requeridos
     * @param archivo1 Nombre del primer archivo.
     * @param archivo2 Nombre del segundo archivo.
     */
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
