import java.util.ArrayList;
import java.util.List;

public class BucketSort {

    public static List<TerminoFrecuencia> ordenar(List<TerminoFrecuencia> terminos) {
        if (terminos.isEmpty()) {
            return new ArrayList<>();
        }

        int frecuenciaMaxima = 0;
        for (TerminoFrecuencia elemento : terminos) {
            if (elemento.getFrecuencia() > frecuenciaMaxima) {
                frecuenciaMaxima = elemento.getFrecuencia();
            }
        }

        int cantidadCubos = 10;
        int tamanoCubo = (frecuenciaMaxima / cantidadCubos) + 1;

        List<List<TerminoFrecuencia>> cubos = new ArrayList<>();
        for (int i = 0; i < cantidadCubos; i++) {
            cubos.add(new ArrayList<>());
        }

        for (TerminoFrecuencia elemento : terminos) {
            int indiceCubo = elemento.getFrecuencia() / tamanoCubo;

            if (indiceCubo >= cantidadCubos) {
                indiceCubo = cantidadCubos - 1;
            }

            cubos.get(indiceCubo).add(elemento);
        }

        List<TerminoFrecuencia> resultado = new ArrayList<>();

        for (int i = cantidadCubos - 1; i >= 0; i--) {
            List<TerminoFrecuencia> cuboOrdenado = ordenarCuboSinComparaciones(cubos.get(i));
            resultado.addAll(cuboOrdenado);
        }

        return resultado;
    }

    /*
     * Dentro de cada cubo usamos nuevamente la frecuencia como índice,
     * evitando Collections.sort() o comparaciones entre pares de elementos.
     */
    private static List<TerminoFrecuencia> ordenarCuboSinComparaciones(
        List<TerminoFrecuencia> cubo
    ) {
        if (cubo.isEmpty()) {
            return new ArrayList<>();
        }

        int minimo = Integer.MAX_VALUE;
        int maximo = Integer.MIN_VALUE;

        for (TerminoFrecuencia elemento : cubo) {
            int frecuencia = elemento.getFrecuencia();
            if (frecuencia < minimo) {
                minimo = frecuencia;
            }
            if (frecuencia > maximo) {
                maximo = frecuencia;
            }
        }

        List<List<TerminoFrecuencia>> posiciones = new ArrayList<>();
        for (int i = minimo; i <= maximo; i++) {
            posiciones.add(new ArrayList<>());
        }

        for (TerminoFrecuencia elemento : cubo) {
            posiciones.get(elemento.getFrecuencia() - minimo).add(elemento);
        }

        List<TerminoFrecuencia> resultado = new ArrayList<>();
        for (int i = posiciones.size() - 1; i >= 0; i--) {
            resultado.addAll(posiciones.get(i));
        }

        return resultado;
    }
}
