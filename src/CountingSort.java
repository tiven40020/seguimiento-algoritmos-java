import java.util.ArrayList;
import java.util.List;

public class CountingSort {

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

        List<List<TerminoFrecuencia>> grupos = new ArrayList<>();
        for (int i = 0; i <= frecuenciaMaxima; i++) {
            grupos.add(new ArrayList<>());
        }

        for (TerminoFrecuencia elemento : terminos) {
            grupos.get(elemento.getFrecuencia()).add(elemento);
        }

        List<TerminoFrecuencia> resultado = new ArrayList<>();

        for (int frecuencia = frecuenciaMaxima; frecuencia >= 0; frecuencia--) {
            resultado.addAll(grupos.get(frecuencia));
        }

        return resultado;
    }
}
