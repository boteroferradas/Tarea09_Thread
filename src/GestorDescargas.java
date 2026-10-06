import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GestorDescargas {
    static void main(String[] args) {
        //Creación de ArrayLists para guardar los objetos descargas, los hilos y
        // los nombres pasados por teclado
        List<Descarga> descargas = new ArrayList<>();
        List<Thread> hilos = new ArrayList<>();
        List<String> nombres = new ArrayList<>();
        //----------------------------------------------------------------------


        Scanner teclado = new Scanner(System.in);
        long mainTiempoEjecucion;

        //Bucle que pide nombres para los archivos por teclado, hasta 4. Si alguno no se introduce
        // pulsando 'ENTER', se usaran por defecto los del nivel 1
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
        //----------------------------------------------------------------------

        //Bucle que asigna los nombres introducidos con un objeto Descarga, y este se asigna a un objeto Thread.
        //Despues se añaden a sus ArrayLists respectivos para poder operar con ellos
        for (String nombre : nombres) {
            Descarga descarga = new Descarga(nombre);
            Thread hilo = new Thread(descarga);

            descargas.add(descarga);
            hilos.add(hilo);
        }
        //----------------------------------------------------------------------

        //Se inicializa el tiempo de ejecución del programa antes de iniciar los hilos (cronometro global)
        long inicio = System.currentTimeMillis();

        //Arranque de los hilos
        for (Thread hilo : hilos) {
            hilo.start();
        }

        //Bucle que hace que todos los hilos esperen al terminar
        for (Thread hilo : hilos)
            try {
                hilo.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

        //Fin del tiempo de ejecucion del programa
        long fin = System.currentTimeMillis();
        mainTiempoEjecucion = fin - inicio;

        System.out.println("...");
        long suma = 0;
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
