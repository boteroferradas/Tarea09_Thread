import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GestorDescargas {
    static void main(String[] args) {
        List<Descarga> descargas = new ArrayList<>();
        List<Thread> hilos = new ArrayList<>();
        Scanner teclado = new Scanner(System.in);
        long mainTiempoEjecucion = 0;
        long suma = 0;


        for (int i = 1; i <= 4; i+= 1) {
            System.out.println("Introduce nombre de la descarga " + i + ":");
            String entrada = teclado.nextLine();

            Descarga descarga = new Descarga(entrada);
            Thread hilo = new Thread(descarga);

            descargas.add(descarga);
            hilos.add(hilo);
        }

        long inicio = System.currentTimeMillis();

        for (Thread hilo : hilos) {
            hilo.start();
        }

//        Descarga descarga1 = new Descarga("cuarzos.png");
//        Thread hilo1 = new Thread(descarga1);
//        descargas.add(descarga1);
//        hilos.add(hilo1);
//
//        Descarga descarga2 = new Descarga("meditacion.mp4");
//        Thread hilo2 = new Thread(descarga2);
//        descargas.add(descarga2);
//        hilos.add(hilo2);
//
//        Descarga descarga3 = new Descarga("mantras.mp3");
//        Thread hilo3 = new Thread(descarga3);
//        descargas.add(descarga3);
//        hilos.add(hilo3);
//
//        Descarga descarga4 = new Descarga("horoscopo.pdf");
//        Thread hilo4 = new Thread(descarga4);
//        descargas.add(descarga4);
//        hilos.add(hilo4);

//        hilo1.start();
//        hilo2.start();
//        hilo3.start();
//        hilo4.start();

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
