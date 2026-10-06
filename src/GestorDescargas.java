import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GestorDescargas {
    public static void main(String[] args) {
        //Creación de ArrayLists para guardar los objetos descargas, los hilos y
        //los nombres pasados por teclado
        List<Descarga> descargas = new ArrayList<>();
        List<Thread> hilos = new ArrayList<>();
        List<String> nombres = new ArrayList<>();


        Scanner teclado = new Scanner(System.in);
        long mainTiempoEjecucion;

        //Bucle que pide nombres para los archivos por teclado, hasta 4. Si alguno no se introduce
        //pulsando 'ENTER', se usaran por defecto los del nivel 1
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

        //Bucle que asigna los nombres introducidos con un objeto Descarga, y este se asigna a un objeto Thread.
        //Despues se añaden a sus ArrayLists respectivos para poder operar con ellos
        for (String nombre : nombres) {
            Descarga descarga = new Descarga(nombre);
            Thread hilo = new Thread(descarga);
            hilo.setName(nombre);

            descargas.add(descarga);
            hilos.add(hilo);
        }

        //Se inicializa el tiempo de ejecución del programa antes de iniciar los hilos (cronometro global)
        long inicio = System.currentTimeMillis();

        //Arranque de los hilos
        for (Thread h : hilos) {
            h.start();
        }
        //Se instancia un hilo de tipo Monitor y se arranca (comprueba cada 500ms)
        Monitor monitor = new Monitor(hilos, 500);
        monitor.start();

        //Se instancia un hilo de tipo Instalador y se arranca
        Instalador instalador = new Instalador(hilos, "meditacion.mp4", "mantras.mp3");
        Thread hiloInstalador = new Thread(instalador);
        hiloInstalador.start();

        //Esto se encarga de buscar entre la lista de hilos por el que se llame "meditacion.mp4"
        //Cuando lo encuentra lo hace esperar 3s y si sigue vivo avisa y continua.
        for (Thread h : hilos){
            if (h.getName().equalsIgnoreCase("meditacion.mp4")) {
                try{
                    h.join(3000);
                    if (h.isAlive()) {
                        System.out.println("[Main] " + h.getName() + " sigue en segundo plano");
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                break;
            }
        }

        //Espera a que la totalidad de los hilos de descarga terminen su trabajo
        for (Thread h : hilos)
            try {
                h.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

        //Espera a que el monitor y el instalador terminen
        try {
            hiloInstalador.join();
            monitor.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        //Fin del tiempo de ejecucion del programa
        long fin = System.currentTimeMillis();
        mainTiempoEjecucion = fin - inicio;

        //Impresion de los resultados
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
