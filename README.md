# Proyecto de Análisis de Algoritmos - Java

Proyecto académico para procesar los resúmenes de 20 artículos científicos y
ordenar la frecuencia de sus términos mediante algoritmos no comparativos.

## Algoritmos

- Counting Sort
- Radix Sort
- Bucket Sort

## Estructura

```text
proyecto_algoritmos_java/
├── datos/
│   ├── articulos.csv
│   ├── palabras_vacias.txt
│   └── frecuencias_terminos.csv   # se genera al ejecutar
├── src/
│   ├── Main.java
│   ├── Preprocesamiento.java
│   ├── TerminoFrecuencia.java
│   ├── CountingSort.java
│   ├── RadixSort.java
│   └── BucketSort.java
├── README.md
└── .gitignore
```

## Compilar

Desde la carpeta raíz del proyecto:

### PowerShell (Windows)

```powershell
javac -encoding UTF-8 -d out src/*.java
```

## Ejecutar

```powershell
java -cp out Main
```

No se utilizan librerías externas.
