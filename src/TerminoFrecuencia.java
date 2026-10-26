
public class TerminoFrecuencia {
    private final String termino;
    private final int frecuencia;

    public TerminoFrecuencia(String termino, int frecuencia) {
        this.termino = termino;
        this.frecuencia = frecuencia;
    }

    public String getTermino() {
        return termino;
    }

    public int getFrecuencia() {
        return frecuencia;
    }

    @Override
    public String toString() {
        return termino + " - " + frecuencia;
    }
}
