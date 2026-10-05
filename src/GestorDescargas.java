import java.util.ArrayList;
import java.util.List;

public class GestorDescargas {
    static void main(String[] args) {
        List<Descarga> descargas = new ArrayList<>();
        List<Thread> hilos = new ArrayList<>();
        long inicio = System.currentTimeMillis();
        long mainTiempoEjecucion = 0;
        long suma = 0;

        Descarga descarga1 = new Descarga("Descarga1");
        Thread hilo = new Thread(descarga1);
        descargas.add(descarga1);
        hilos.add(hilo);

        Descarga descarga2 = new Descarga("Descarga2");
        Thread hilo2 = new Thread(descarga2);
        descargas.add(descarga2);
        hilos.add(hilo2);

        Descarga descarga3 = new Descarga("Descarga3");
        Thread hilo3 = new Thread(descarga3);
        descargas.add(descarga3);
        hilos.add(hilo3);

        Descarga descarga4 = new Descarga("Descarga4");
        Thread hilo4 = new Thread(descarga4);
        descargas.add(descarga4);
        hilos.add(hilo4);

        hilo.start();
        hilo2.start();
        hilo3.start();
        hilo4.start();

        for (Thread descarga : hilos)
            try {
                descarga.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                long fin = System.currentTimeMillis();
                mainTiempoEjecucion = fin - inicio;
            }

        System.out.println("...");
        for (Descarga descarga : descargas) {
            System.out.println("[" + descarga.getNombreArchivo() + "] completada en " + descarga.getTiempoEjecucionMs() + " ms");
            suma += descarga.getTiempoEjecucionMs();
        }

        System.out.println("Todas las descargas han terminado");
        System.out.println("Tiempo real: " + mainTiempoEjecucion + " ms");
        System.out.println("Si se hubieran descargado una detras de otra: " + suma + " ms");

    }
}
