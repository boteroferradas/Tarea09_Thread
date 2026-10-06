import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GestorDescargas {
    static void main(String[] args) {
        List<Descarga> descargas = new ArrayList<>();
        List<Thread> hilos = new ArrayList<>();
        List<String> nombres = new ArrayList<>();

        Scanner teclado = new Scanner(System.in);
        long mainTiempoEjecucion;
        long suma = 0;


        for (int i = 1; i <= 4; i+= 1) {
            System.out.println("Introduce nombre de la descarga " + i + ":");
            String entrada = teclado.nextLine();
            if (!entrada.isBlank()) {
                nombres.add(entrada);
            }
        }

        if (nombres.size() != 4) {
            nombres = List.of("cuarzos.png", "meditacion.mp4", "mantras.mp3", "horoscopo.pdf");
        }
        for (String nombre : nombres) {
            Descarga descarga = new Descarga(nombre);
            Thread hilo = new Thread(descarga);

            descargas.add(descarga);
            hilos.add(hilo);
        }

        long inicio = System.currentTimeMillis();

        for (Thread hilo : hilos) {
            hilo.start();
        }

        for (Thread hilo : hilos)
            try {
                hilo.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

        long fin = System.currentTimeMillis();
        mainTiempoEjecucion = fin - inicio;

        System.out.println("...");
        for (Descarga descarga : descargas) {
            System.out.println("[" + descarga.getNombreArchivo() + "] completada en " + descarga.getTiempoEjecucionMs() + " ms");
            suma += descarga.getTiempoEjecucionMs();
        }
        System.out.println();
        System.out.println("Todas las descargas han terminado");
        System.out.println("Tiempo real: " + mainTiempoEjecucion + " ms");
        System.out.println("Si se hubieran descargado una detras de otra: " + suma + " ms");

    }
}
