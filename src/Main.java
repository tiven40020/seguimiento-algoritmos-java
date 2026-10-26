import java.io.IOException;
import java.util.List;

public class Main {

    private static final int NUMERO_EJECUCIONES = 1;

    public static void main(String[] args) throws IOException {

        // 1. Preprocesar los artículos
        List<TerminoFrecuencia> terminos =
                Preprocesamiento.procesar();

        // 2. Medir Counting Sort
        double tiempoCounting =
                medirCountingSort(terminos);

        // 3. Medir Radix Sort
        double tiempoRadix =
                medirRadixSort(terminos);

        // 4. Medir Bucket Sort
        double tiempoBucket =
                medirBucketSort(terminos);

        // 5. Ejecutar una vez cada algoritmo
        // para mostrar y validar el Top 15
        List<TerminoFrecuencia> resultadoCounting =
                CountingSort.ordenar(terminos);

        List<TerminoFrecuencia> resultadoRadix =
                RadixSort.ordenar(terminos);

        List<TerminoFrecuencia> resultadoBucket =
                BucketSort.ordenar(terminos);

        System.out.println("\nRESULTADO COUNTING SORT");
        mostrarTop15(resultadoCounting);

        System.out.println("\nRESULTADO RADIX SORT");
        mostrarTop15(resultadoRadix);

        System.out.println("\nRESULTADO BUCKET SORT");
        mostrarTop15(resultadoBucket);

        // 6. Mostrar tiempos promedio
        System.out.println("\nTIEMPOS PROMEDIO DE EJECUCIÓN");

        System.out.printf(
                "Counting Sort: %.6f ms%n",
                tiempoCounting
        );

        System.out.printf(
                "Radix Sort: %.6f ms%n",
                tiempoRadix
        );

        System.out.printf(
                "Bucket Sort: %.6f ms%n",
                tiempoBucket
        );
    }


    private static double medirCountingSort(
            List<TerminoFrecuencia> terminos) {

        long tiempoTotal = 0;

        for (int i = 0; i < NUMERO_EJECUCIONES; i++) {

            long inicio = System.nanoTime();

            CountingSort.ordenar(terminos);

            long fin = System.nanoTime();

            tiempoTotal += fin - inicio;
        }

        return convertirAPromedioMilisegundos(
                tiempoTotal
        );
    }


    private static double medirRadixSort(
            List<TerminoFrecuencia> terminos) {

        long tiempoTotal = 0;

        for (int i = 0; i < NUMERO_EJECUCIONES; i++) {

            long inicio = System.nanoTime();

            RadixSort.ordenar(terminos);

            long fin = System.nanoTime();

            tiempoTotal += fin - inicio;
        }

        return convertirAPromedioMilisegundos(
                tiempoTotal
        );
    }


    private static double medirBucketSort(
            List<TerminoFrecuencia> terminos) {

        long tiempoTotal = 0;

        for (int i = 0; i < NUMERO_EJECUCIONES; i++) {

            long inicio = System.nanoTime();

            BucketSort.ordenar(terminos);

            long fin = System.nanoTime();

            tiempoTotal += fin - inicio;
        }

        return convertirAPromedioMilisegundos(
                tiempoTotal
        );
    }


    private static double convertirAPromedioMilisegundos(
            long tiempoTotalNanosegundos) {

        double promedioNanosegundos =
                (double) tiempoTotalNanosegundos
                / NUMERO_EJECUCIONES;

        return promedioNanosegundos
                / 1_000_000.0;
    }


    private static void mostrarTop15(
            List<TerminoFrecuencia> terminos) {

        System.out.println(
                "\n15 términos más frecuentes:\n"
        );

        int limite = Math.min(15, terminos.size());

        for (int i = 0; i < limite; i++) {

            TerminoFrecuencia elemento =
                    terminos.get(i);

            System.out.println(
                    (i + 1)
                    + ". "
                    + elemento.getTermino()
                    + " - "
                    + elemento.getFrecuencia()
            );
        }
    }
}