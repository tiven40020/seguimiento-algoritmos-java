import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Preprocesamiento {

    private static final Path CARPETA_PROYECTO = Paths.get("").toAbsolutePath();
    private static final Path ARCHIVO_ARTICULOS = CARPETA_PROYECTO.resolve("datos/articulos.csv");
    private static final Path ARCHIVO_PALABRAS_VACIAS = CARPETA_PROYECTO.resolve("datos/palabras_vacias.txt");
    private static final Path ARCHIVO_FRECUENCIAS = CARPETA_PROYECTO.resolve("datos/frecuencias_terminos.csv");

    private static final Pattern PATRON_PALABRA = Pattern.compile("[a-z]+");

    public static List<TerminoFrecuencia> procesar() throws IOException {
        Set<String> palabrasVacias = cargarPalabrasVacias();
        Map<String, Integer> frecuencias = new HashMap<>();

        try (BufferedReader lector = Files.newBufferedReader(ARCHIVO_ARTICULOS, StandardCharsets.UTF_8)) {
            String linea;
            boolean primeraLinea = true;

            while ((linea = lector.readLine()) != null) {
                if (primeraLinea) {
                    primeraLinea = false;
                    continue;
                }

                List<String> campos = separarCsv(linea);
                if (campos.size() < 3) {
                    continue;
                }

                String resumen = campos.get(2).toLowerCase();
                Matcher matcher = PATRON_PALABRA.matcher(resumen);

                while (matcher.find()) {
                    String palabra = matcher.group();

                    if (!palabrasVacias.contains(palabra)) {
                        frecuencias.put(
                            palabra,
                            frecuencias.getOrDefault(palabra, 0) + 1
                        );
                    }
                }
            }
        }

        List<TerminoFrecuencia> resultado = new ArrayList<>();
        for (Map.Entry<String, Integer> entrada : frecuencias.entrySet()) {
            resultado.add(new TerminoFrecuencia(entrada.getKey(), entrada.getValue()));
        }

        guardarFrecuencias(resultado);
        return resultado;
    }

    private static Set<String> cargarPalabrasVacias() throws IOException {
        Set<String> palabrasVacias = new HashSet<>();

        try (BufferedReader lector = Files.newBufferedReader(ARCHIVO_PALABRAS_VACIAS, StandardCharsets.UTF_8)) {
            String linea;
            while ((linea = lector.readLine()) != null) {
                String palabra = linea.trim().toLowerCase();
                if (!palabra.isEmpty()) {
                    palabrasVacias.add(palabra);
                }
            }
        }

        return palabrasVacias;
    }

    private static List<String> separarCsv(String linea) {
        List<String> campos = new ArrayList<>();
        StringBuilder campo = new StringBuilder();
        boolean entreComillas = false;

        for (int i = 0; i < linea.length(); i++) {
            char caracter = linea.charAt(i);

            if (caracter == '"') {
                if (entreComillas && i + 1 < linea.length() && linea.charAt(i + 1) == '"') {
                    campo.append('"');
                    i++;
                } else {
                    entreComillas = !entreComillas;
                }
            } else if (caracter == ',' && !entreComillas) {
                campos.add(campo.toString());
                campo.setLength(0);
            } else {
                campo.append(caracter);
            }
        }

        campos.add(campo.toString());
        return campos;
    }

    private static void guardarFrecuencias(List<TerminoFrecuencia> terminos) throws IOException {
        try (BufferedWriter escritor = Files.newBufferedWriter(ARCHIVO_FRECUENCIAS, StandardCharsets.UTF_8)) {
            escritor.write("termino,frecuencia");
            escritor.newLine();

            for (TerminoFrecuencia elemento : terminos) {
                escritor.write(elemento.getTermino() + "," + elemento.getFrecuencia());
                escritor.newLine();
            }
        }
    }
}
