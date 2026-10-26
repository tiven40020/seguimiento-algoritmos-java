import java.util.ArrayList;
import java.util.List;

public class RadixSort {

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

        List<TerminoFrecuencia> resultado = new ArrayList<>(terminos);

        for (int posicion = 1; frecuenciaMaxima / posicion > 0; posicion *= 10) {
            resultado = countingSortDigito(resultado, posicion);
        }

        return convertirADescendenteConEmpatesEstables(resultado);
    }

    private static List<TerminoFrecuencia> countingSortDigito(
        List<TerminoFrecuencia> terminos,
        int posicion
    ) {
        int cantidad = terminos.size();
        TerminoFrecuencia[] resultado = new TerminoFrecuencia[cantidad];
        int[] conteo = new int[10];

        for (TerminoFrecuencia elemento : terminos) {
            int digito = (elemento.getFrecuencia() / posicion) % 10;
            conteo[digito]++;
        }

        for (int i = 1; i < 10; i++) {
            conteo[i] += conteo[i - 1];
        }

        for (int i = cantidad - 1; i >= 0; i--) {
            TerminoFrecuencia elemento = terminos.get(i);
            int digito = (elemento.getFrecuencia() / posicion) % 10;
            resultado[conteo[digito] - 1] = elemento;
            conteo[digito]--;
        }

        List<TerminoFrecuencia> listaResultado = new ArrayList<>();
        for (TerminoFrecuencia elemento : resultado) {
            listaResultado.add(elemento);
        }

        return listaResultado;
    }

    private static List<TerminoFrecuencia> convertirADescendenteConEmpatesEstables(
        List<TerminoFrecuencia> ascendente
    ) {
        List<TerminoFrecuencia> descendente = new ArrayList<>();
        int i = ascendente.size() - 1;

        while (i >= 0) {
            int frecuenciaActual = ascendente.get(i).getFrecuencia();
            int inicio = i;

            while (
                inicio >= 0 &&
                ascendente.get(inicio).getFrecuencia() == frecuenciaActual
            ) {
                inicio--;
            }

            for (int j = inicio + 1; j <= i; j++) {
                descendente.add(ascendente.get(j));
            }

            i = inicio;
        }

        return descendente;
    }
}
