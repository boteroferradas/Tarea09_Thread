import java.util.random.RandomGenerator;

/**
 * Clase Descarga que implementa Runnable para simular el proceso de descarga
 * de un archivo en un hilo independiente, registrando su tiempo de ejecución.
 */
public class Descarga implements Runnable{
    private String nombreArchivo;
    private RandomGenerator random = RandomGenerator.getDefault();
    private long tiempoEjecucionMs;


    /**
     * Constructor para inicializar el objeto con el nombre del archivo.
     * @param nombreArchivo Nombre asignado al hilo/descarga
     */
    public Descarga(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }


    /**
     * Lógica principal del hilo que se ejecuta al llamar a Thread.start().
     */
    @Override
    public void run() {
        int espera = random.nextInt(100, 500);
        long inicio = System.currentTimeMillis();
        try {
            for (int i = 10; i <= 100; i+= 10) {
                System.out.println("[" + nombreArchivo + "] " + i + "%");
                Thread.sleep(espera);
            }
        } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
        } finally {
            long fin = System.currentTimeMillis();
            this.tiempoEjecucionMs = fin - inicio;
        }
    }

    /**
     * Obtiene el tiempo total que tardó la descarga en completarse.
     * @return Duración en milisegundos (ms)
     */
    public long getTiempoEjecucionMs() {
        return tiempoEjecucionMs;
    }

    /**
     * Obtiene el nombre del archivo asociado a la descarga.
     * @return Nombre del archivo
     */
    public String getNombreArchivo() {
        return nombreArchivo;
    }
}
