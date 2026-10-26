import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

public class InterfazPrincipal extends JFrame {

    private List<TerminoFrecuencia> terminos;

    private final JLabel etiquetaArticulos;
    private final JLabel etiquetaPalabrasUtiles;
    private final JLabel etiquetaTerminosUnicos;
    private final JLabel etiquetaFrecuenciaMaxima;

    private final JLabel etiquetaAlgoritmo;
    private final JLabel etiquetaTiempo;
    private final JLabel etiquetaTabla;

    private final JTextArea areaProcesamiento;
    private final JTextArea areaComparacion;

    private final DefaultTableModel modeloTabla;


    public InterfazPrincipal() {

        setTitle(
                "Análisis Bibliométrico - Algoritmos No Comparativos"
        );

        setDefaultCloseOperation(
                JFrame.EXIT_ON_CLOSE
        );

        setSize(1000, 760);

        setMinimumSize(
                new Dimension(900, 650)
        );

        setLocationRelativeTo(null);

        setLayout(
                new BorderLayout(10, 10)
        );


        // ==============================
        // TÍTULO
        // ==============================

        JLabel titulo = new JLabel(
                "ANÁLISIS BIBLIOMÉTRICO CON ALGORITMOS NO COMPARATIVOS",
                SwingConstants.CENTER
        );

        titulo.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        20
                )
        );

        titulo.setBorder(
                BorderFactory.createEmptyBorder(
                        15,
                        10,
                        5,
                        10
                )
        );

        add(
                titulo,
                BorderLayout.NORTH
        );


        // ==============================
        // PANEL PRINCIPAL
        // ==============================

        JPanel panelPrincipal =
                new JPanel(
                        new BorderLayout(10, 10)
                );

        panelPrincipal.setBorder(
                BorderFactory.createEmptyBorder(
                        0,
                        15,
                        15,
                        15
                )
        );

        add(
                panelPrincipal,
                BorderLayout.CENTER
        );


        // ==============================
        // PARTE SUPERIOR
        // ==============================

        JPanel panelSuperior =
                new JPanel(
                        new BorderLayout(10, 10)
                );

        panelPrincipal.add(
                panelSuperior,
                BorderLayout.NORTH
        );


        // ==============================
        // DATOS DEL CORPUS
        // ==============================

        JPanel panelEstadisticas =
                new JPanel(
                        new GridLayout(
                                4,
                                2,
                                10,
                                7
                        )
                );

        panelEstadisticas.setBorder(
                BorderFactory.createTitledBorder(
                        "Datos del corpus"
                )
        );


        panelEstadisticas.add(
                new JLabel(
                        "Artículos procesados:"
                )
        );

        etiquetaArticulos =
                new JLabel("Sin procesar");

        panelEstadisticas.add(
                etiquetaArticulos
        );


        panelEstadisticas.add(
                new JLabel(
                        "Palabras útiles:"
                )
        );

        etiquetaPalabrasUtiles =
                new JLabel("Sin procesar");

        panelEstadisticas.add(
                etiquetaPalabrasUtiles
        );


        panelEstadisticas.add(
                new JLabel(
                        "Términos únicos:"
                )
        );

        etiquetaTerminosUnicos =
                new JLabel("Sin procesar");

        panelEstadisticas.add(
                etiquetaTerminosUnicos
        );


        panelEstadisticas.add(
                new JLabel(
                        "Frecuencia máxima:"
                )
        );

        etiquetaFrecuenciaMaxima =
                new JLabel("Sin procesar");

        panelEstadisticas.add(
                etiquetaFrecuenciaMaxima
        );


        panelSuperior.add(
                panelEstadisticas,
                BorderLayout.WEST
        );


        // ==============================
        // BOTONES
        // ==============================

        JPanel panelBotones =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.CENTER,
                                8,
                                8
                        )
                );

        panelBotones.setBorder(
                BorderFactory.createTitledBorder(
                        "Opciones de ejecución"
                )
        );


        JButton botonProcesar =
                new JButton(
                        "Procesar artículos"
                );

        JButton botonCounting =
                new JButton(
                        "Counting Sort"
                );

        JButton botonRadix =
                new JButton(
                        "Radix Sort"
                );

        JButton botonBucket =
                new JButton(
                        "Bucket Sort"
                );

        JButton botonComparar =
                new JButton(
                        "Comparar los tres"
                );


        panelBotones.add(
                botonProcesar
        );

        panelBotones.add(
                botonCounting
        );

        panelBotones.add(
                botonRadix
        );

        panelBotones.add(
                botonBucket
        );

        panelBotones.add(
                botonComparar
        );


        panelSuperior.add(
                panelBotones,
                BorderLayout.CENTER
        );


        // ==============================
        // PROCESAMIENTO
        // ==============================

        areaProcesamiento =
                new JTextArea(
                        7,
                        30
                );

        areaProcesamiento.setEditable(
                false
        );

        areaProcesamiento.setFont(
                new Font(
                        "Monospaced",
                        Font.PLAIN,
                        12
                )
        );

        areaProcesamiento.setText(
                "Presione 'Procesar artículos' "
                + "para iniciar el análisis."
        );


        JScrollPane scrollProcesamiento =
                new JScrollPane(
                        areaProcesamiento
                );

        scrollProcesamiento.setBorder(
                BorderFactory.createTitledBorder(
                        "Proceso realizado"
                )
        );


        panelSuperior.add(
                scrollProcesamiento,
                BorderLayout.SOUTH
        );


        // ==============================
        // TABLA
        // ==============================

        JPanel panelTabla =
                new JPanel(
                        new BorderLayout(
                                5,
                                5
                        )
                );


        etiquetaTabla =
                new JLabel(
                        "Datos pendientes de procesamiento",
                        SwingConstants.CENTER
                );

        etiquetaTabla.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        14
                )
        );


        panelTabla.add(
                etiquetaTabla,
                BorderLayout.NORTH
        );


        modeloTabla =
                new DefaultTableModel(
                        new Object[]{
                                "Posición",
                                "Término",
                                "Frecuencia"
                        },
                        0
                ) {

                    @Override
                    public boolean isCellEditable(
                            int fila,
                            int columna
                    ) {

                        return false;
                    }
                };


        JTable tabla =
                new JTable(
                        modeloTabla
                );

        tabla.setRowHeight(25);

        tabla.getTableHeader()
                .setReorderingAllowed(false);


        JScrollPane scrollTabla =
                new JScrollPane(
                        tabla
                );


        panelTabla.add(
                scrollTabla,
                BorderLayout.CENTER
        );


        panelPrincipal.add(
                panelTabla,
                BorderLayout.CENTER
        );


        // ==============================
        // PARTE INFERIOR
        // ==============================

        JPanel panelInferior =
                new JPanel(
                        new BorderLayout(
                                10,
                                10
                        )
                );


        JPanel panelResultado =
                new JPanel(
                        new GridLayout(
                                2,
                                2,
                                10,
                                5
                        )
                );

        panelResultado.setBorder(
                BorderFactory.createTitledBorder(
                        "Resultado de ejecución"
                )
        );


        panelResultado.add(
                new JLabel(
                        "Algoritmo:"
                )
        );

        etiquetaAlgoritmo =
                new JLabel("Ninguno");

        panelResultado.add(
                etiquetaAlgoritmo
        );


        panelResultado.add(
                new JLabel(
                        "Tiempo:"
                )
        );

        etiquetaTiempo =
                new JLabel("-- ms");

        panelResultado.add(
                etiquetaTiempo
        );


        panelInferior.add(
                panelResultado,
                BorderLayout.NORTH
        );


        // ==============================
        // COMPARACIÓN
        // ==============================

        areaComparacion =
                new JTextArea(
                        7,
                        40
                );

        areaComparacion.setEditable(
                false
        );

        areaComparacion.setFont(
                new Font(
                        "Monospaced",
                        Font.PLAIN,
                        13
                )
        );

        areaComparacion.setText(
                "Todavía no se ha realizado "
                + "una comparación."
        );


        JScrollPane scrollComparacion =
                new JScrollPane(
                        areaComparacion
                );

        scrollComparacion.setBorder(
                BorderFactory.createTitledBorder(
                        "Comparación experimental"
                )
        );


        panelInferior.add(
                scrollComparacion,
                BorderLayout.CENTER
        );


        panelPrincipal.add(
                panelInferior,
                BorderLayout.SOUTH
        );


        // ==============================
        // EVENTOS DE LOS BOTONES
        // ==============================

        botonProcesar.addActionListener(
                e -> procesarArticulos()
        );

        botonCounting.addActionListener(
                e -> ejecutarCountingSort()
        );

        botonRadix.addActionListener(
                e -> ejecutarRadixSort()
        );

        botonBucket.addActionListener(
                e -> ejecutarBucketSort()
        );

        botonComparar.addActionListener(
                e -> solicitarComparacion()
        );
    }


    // ==========================================================
    // PROCESAMIENTO DE LOS ARTÍCULOS
    // ==========================================================

    private void procesarArticulos() {

        try {

            terminos =
                    Preprocesamiento.procesar();


            int articulos =
                    contarArticulos();


            int totalPalabras = 0;

            int frecuenciaMaxima = 0;


            for (
                TerminoFrecuencia elemento :
                terminos
            ) {

                totalPalabras +=
                        elemento.getFrecuencia();


                if (
                    elemento.getFrecuencia()
                    > frecuenciaMaxima
                ) {

                    frecuenciaMaxima =
                            elemento.getFrecuencia();
                }
            }


            etiquetaArticulos.setText(
                    String.valueOf(
                            articulos
                    )
            );

            etiquetaPalabrasUtiles.setText(
                    String.valueOf(
                            totalPalabras
                    )
            );

            etiquetaTerminosUnicos.setText(
                    String.valueOf(
                            terminos.size()
                    )
            );

            etiquetaFrecuenciaMaxima.setText(
                    String.valueOf(
                            frecuenciaMaxima
                    )
            );


            areaProcesamiento.setText(
                    "PROCESAMIENTO COMPLETADO\n\n"

                    + "1. Lectura de los resúmenes "
                    + "(abstracts).\n"

                    + "   Artículos procesados: "
                    + articulos
                    + "\n\n"

                    + "2. Conversión del texto "
                    + "a minúsculas.\n\n"

                    + "3. Extracción de palabras "
                    + "mediante el patrón [a-z]+.\n"

                    + "   Se conservaron únicamente "
                    + "términos formados por letras.\n\n"

                    + "4. Eliminación de palabras "
                    + "vacías (stopwords).\n"

                    + "   Ejemplos: the, and, of, "
                    + "in, to...\n\n"

                    + "5. Conteo global de frecuencias "
                    + "mediante HashMap.\n\n"

                    + "RESULTADO DEL PREPROCESAMIENTO\n"

                    + "Palabras útiles: "
                    + totalPalabras
                    + "\n"

                    + "Términos únicos: "
                    + terminos.size()
                    + "\n"

                    + "Frecuencia máxima: "
                    + frecuenciaMaxima
                    + "\n\n"

                    + "En este punto todavía NO se ha "
                    + "aplicado ningún algoritmo "
                    + "de ordenamiento."
            );


            etiquetaAlgoritmo.setText(
                    "Preprocesamiento"
            );

            etiquetaTiempo.setText(
                    "No aplica"
            );


            mostrarDatosSinOrdenar(
                    terminos
            );


            areaComparacion.setText(
                    "Los datos están preparados.\n"
                    + "Puede ejecutar individualmente "
                    + "Counting Sort, Radix Sort o "
                    + "Bucket Sort,\n"
                    + "o realizar una comparación "
                    + "experimental de los tres."
            );


        } catch (IOException ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "No fue posible procesar "
                    + "los archivos:\n"
                    + ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }


    // ==========================================================
    // CONTAR ARTÍCULOS DEL CSV
    // ==========================================================

    private int contarArticulos()
            throws IOException {

        Path archivo =
                Paths.get(
                        "datos/articulos.csv"
                );


        try (
            Stream<String> lineas =
                    Files.lines(
                            archivo,
                            StandardCharsets.UTF_8
                    )
        ) {

            return (int)
                    lineas
                    .skip(1)
                    .filter(
                            linea ->
                            !linea.isBlank()
                    )
                    .count();
        }
    }


    // ==========================================================
    // VERIFICAR DATOS
    // ==========================================================

    private boolean verificarDatos() {

        if (
            terminos == null
            || terminos.isEmpty()
        ) {

            JOptionPane.showMessageDialog(
                    this,
                    "Primero debe procesar "
                    + "los artículos.",
                    "Datos no procesados",
                    JOptionPane.WARNING_MESSAGE
            );

            return false;
        }

        return true;
    }


    // ==========================================================
    // COUNTING SORT - UNA SOLA EJECUCIÓN
    // ==========================================================

    private void ejecutarCountingSort() {

        if (!verificarDatos()) {
            return;
        }


        long inicio =
                System.nanoTime();


        List<TerminoFrecuencia> resultado =
                CountingSort.ordenar(
                        terminos
                );


        long fin =
                System.nanoTime();


        double tiempo =
                convertirAMilisegundos(
                        fin - inicio
                );


        mostrarResultadoIndividual(
                "Counting Sort",
                tiempo,
                resultado
        );
    }


    // ==========================================================
    // RADIX SORT - UNA SOLA EJECUCIÓN
    // ==========================================================

    private void ejecutarRadixSort() {

        if (!verificarDatos()) {
            return;
        }


        long inicio =
                System.nanoTime();


        List<TerminoFrecuencia> resultado =
                RadixSort.ordenar(
                        terminos
                );


        long fin =
                System.nanoTime();


        double tiempo =
                convertirAMilisegundos(
                        fin - inicio
                );


        mostrarResultadoIndividual(
                "Radix Sort",
                tiempo,
                resultado
        );
    }


    // ==========================================================
    // BUCKET SORT - UNA SOLA EJECUCIÓN
    // ==========================================================

    private void ejecutarBucketSort() {

        if (!verificarDatos()) {
            return;
        }


        long inicio =
                System.nanoTime();


        List<TerminoFrecuencia> resultado =
                BucketSort.ordenar(
                        terminos
                );


        long fin =
                System.nanoTime();


        double tiempo =
                convertirAMilisegundos(
                        fin - inicio
                );


        mostrarResultadoIndividual(
                "Bucket Sort",
                tiempo,
                resultado
        );
    }


    // ==========================================================
    // MOSTRAR RESULTADO INDIVIDUAL
    // ==========================================================

    private void mostrarResultadoIndividual(
            String algoritmo,
            double tiempo,
            List<TerminoFrecuencia> resultado
    ) {

        etiquetaAlgoritmo.setText(
                algoritmo
        );


        etiquetaTiempo.setText(
                String.format(
                        "%.6f ms",
                        tiempo
                )
        );


        etiquetaTabla.setText(
                "Top 15 - " + algoritmo
        );


        mostrarTop15(
                resultado
        );


        areaComparacion.setText(
                algoritmo
                + " ejecutado una sola vez.\n\n"

                + "Tiempo de esta ejecución: "
                + String.format(
                        "%.6f ms",
                        tiempo
                )

                + "\n\n"
                + "Este valor corresponde únicamente "
                + "a la ejecución realizada "
                + "al presionar el botón."
        );
    }


    // ==========================================================
    // SOLICITAR NÚMERO DE EJECUCIONES
    // ==========================================================

    private void solicitarComparacion() {

        if (!verificarDatos()) {
            return;
        }


        JSpinner selectorEjecuciones =
                new JSpinner(
                        new SpinnerNumberModel(
                                20,
                                1,
                                10000,
                                1
                        )
                );


        JPanel panel =
                new JPanel(
                        new GridLayout(
                                2,
                                1,
                                5,
                                5
                        )
                );


        panel.add(
                new JLabel(
                        "Número de ejecuciones "
                        + "por algoritmo:"
                )
        );

        panel.add(
                selectorEjecuciones
        );


        int opcion =
                JOptionPane.showConfirmDialog(
                        this,
                        panel,
                        "Comparación experimental",
                        JOptionPane.OK_CANCEL_OPTION,
                        JOptionPane.PLAIN_MESSAGE
                );


        if (
            opcion
            != JOptionPane.OK_OPTION
        ) {

            return;
        }


        int numeroEjecuciones =
                (Integer)
                selectorEjecuciones.getValue();


        compararAlgoritmos(
                numeroEjecuciones
        );
    }


    // ==========================================================
    // COMPARAR LOS TRES
    // ==========================================================

    private void compararAlgoritmos(
            int numeroEjecuciones
    ) {

        double tiempoCounting =
                medirCountingSort(
                        numeroEjecuciones
                );


        double tiempoRadix =
                medirRadixSort(
                        numeroEjecuciones
                );


        double tiempoBucket =
                medirBucketSort(
                        numeroEjecuciones
                );


        List<TerminoFrecuencia>
                resultadoCounting =
                CountingSort.ordenar(
                        terminos
                );


        mostrarTop15(
                resultadoCounting
        );


        etiquetaTabla.setText(
                "Top 15 - Validación de resultados"
        );


        etiquetaAlgoritmo.setText(
                "Comparación de los tres"
        );


        etiquetaTiempo.setText(
                numeroEjecuciones
                + " ejecuciones por algoritmo"
        );


        String menorTiempo =
                obtenerMenorTiempo(
                        tiempoCounting,
                        tiempoRadix,
                        tiempoBucket
                );


        areaComparacion.setText(
                "COMPARACIÓN EXPERIMENTAL\n\n"

                + "Ejecuciones seleccionadas: "
                + numeroEjecuciones
                + "\n\n"

                + "Counting Sort\n"
                + "  Ejecuciones medidas: "
                + numeroEjecuciones
                + "\n"
                + "  Tiempo promedio: "
                + String.format(
                        "%.6f ms",
                        tiempoCounting
                )
                + "\n\n"

                + "Radix Sort\n"
                + "  Ejecuciones medidas: "
                + numeroEjecuciones
                + "\n"
                + "  Tiempo promedio: "
                + String.format(
                        "%.6f ms",
                        tiempoRadix
                )
                + "\n\n"

                + "Bucket Sort\n"
                + "  Ejecuciones medidas: "
                + numeroEjecuciones
                + "\n"
                + "  Tiempo promedio: "
                + String.format(
                        "%.6f ms",
                        tiempoBucket
                )
                + "\n\n"

                + "Menor tiempo promedio "
                + "en esta medición:\n"
                + menorTiempo
                + "\n\n"
        );
    }


    // ==========================================================
    // MEDICIÓN COUNTING SORT
    // ==========================================================

    private double medirCountingSort(
            int numeroEjecuciones
    ) {

        long tiempoTotal = 0;


        for (
            int i = 0;
            i < numeroEjecuciones;
            i++
        ) {

            long inicio =
                    System.nanoTime();


            CountingSort.ordenar(
                    terminos
            );


            long fin =
                    System.nanoTime();


            tiempoTotal +=
                    fin - inicio;
        }


        return calcularPromedio(
                tiempoTotal,
                numeroEjecuciones
        );
    }


    // ==========================================================
    // MEDICIÓN RADIX SORT
    // ==========================================================

    private double medirRadixSort(
            int numeroEjecuciones
    ) {

        long tiempoTotal = 0;


        for (
            int i = 0;
            i < numeroEjecuciones;
            i++
        ) {

            long inicio =
                    System.nanoTime();


            RadixSort.ordenar(
                    terminos
            );


            long fin =
                    System.nanoTime();


            tiempoTotal +=
                    fin - inicio;
        }


        return calcularPromedio(
                tiempoTotal,
                numeroEjecuciones
        );
    }


    // ==========================================================
    // MEDICIÓN BUCKET SORT
    // ==========================================================

    private double medirBucketSort(
            int numeroEjecuciones
    ) {

        long tiempoTotal = 0;


        for (
            int i = 0;
            i < numeroEjecuciones;
            i++
        ) {

            long inicio =
                    System.nanoTime();


            BucketSort.ordenar(
                    terminos
            );


            long fin =
                    System.nanoTime();


            tiempoTotal +=
                    fin - inicio;
        }


        return calcularPromedio(
                tiempoTotal,
                numeroEjecuciones
        );
    }


    // ==========================================================
    // CALCULAR PROMEDIO
    // ==========================================================

    private double calcularPromedio(
            long tiempoTotalNanosegundos,
            int numeroEjecuciones
    ) {

        double promedioNanosegundos =
                (double)
                tiempoTotalNanosegundos
                / numeroEjecuciones;


        return promedioNanosegundos
                / 1_000_000.0;
    }


    // ==========================================================
    // CONVERTIR UNA EJECUCIÓN A MILISEGUNDOS
    // ==========================================================

    private double convertirAMilisegundos(
            long nanosegundos
    ) {

        return nanosegundos
                / 1_000_000.0;
    }


    // ==========================================================
    // MENOR TIEMPO
    // ==========================================================

    private String obtenerMenorTiempo(
            double counting,
            double radix,
            double bucket
    ) {

        if (
            counting <= radix
            && counting <= bucket
        ) {

            return "Counting Sort - "
                    + String.format(
                            "%.6f ms",
                            counting
                    );
        }


        if (
            radix <= counting
            && radix <= bucket
        ) {

            return "Radix Sort - "
                    + String.format(
                            "%.6f ms",
                            radix
                    );
        }


        return "Bucket Sort - "
                + String.format(
                        "%.6f ms",
                        bucket
                );
    }


    // ==========================================================
    // MOSTRAR DATOS ANTES DE ORDENAR
    // ==========================================================

    private void mostrarDatosSinOrdenar(
            List<TerminoFrecuencia> datos
    ) {

        modeloTabla.setRowCount(0);


        etiquetaTabla.setText(
                "Muestra de datos después del "
                + "preprocesamiento - Sin ordenar"
        );


        int limite =
                Math.min(
                        15,
                        datos.size()
                );


        for (
            int i = 0;
            i < limite;
            i++
        ) {

            TerminoFrecuencia elemento =
                    datos.get(i);


            modeloTabla.addRow(
                    new Object[]{
                            i + 1,
                            elemento.getTermino(),
                            elemento.getFrecuencia()
                    }
            );
        }
    }


    // ==========================================================
    // MOSTRAR TOP 15
    // ==========================================================

    private void mostrarTop15(
            List<TerminoFrecuencia> resultado
    ) {

        modeloTabla.setRowCount(0);


        int limite =
                Math.min(
                        15,
                        resultado.size()
                );


        for (
            int i = 0;
            i < limite;
            i++
        ) {

            TerminoFrecuencia elemento =
                    resultado.get(i);


            modeloTabla.addRow(
                    new Object[]{
                            i + 1,
                            elemento.getTermino(),
                            elemento.getFrecuencia()
                    }
            );
        }
    }
}