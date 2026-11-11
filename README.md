# Algoritmos de ordenamiento y búsqueda

**SOFT-10 Estructuras de Datos** · Universidad CENFOTEC
Docente: Romario Salas Cerdas
Estudiante: Ian Aarón Mora Espinoza

## Descripción

Programa en Java que permite al usuario:

1. Definir el tamaño de un arreglo estático de palabras (`String[]`) y llenarlo.
2. Elegir en un menú el algoritmo de ordenamiento: selección, inserción, burbuja, mezcla o rápido.
3. Ver el arreglo ordenado y buscar palabras en él con búsqueda binaria.

Cada algoritmo está programado como subrutina del programa principal, con sus pasos comentados y su complejidad en notación O grande. El orden es ascendente y alfabético en español, sin distinguir mayúsculas ni tildes.

## Cómo ejecutarlo

```bash
javac Main.java
java Main
```

## Complejidad de tiempo

| Algoritmo | Complejidad |
|---|---|
| Selección | O(n²) |
| Inserción | O(n²) |
| Burbuja | O(n²) |
| Mezcla | O(n log n) |
| Rápido | O(n log n) esperado, O(n²) peor caso |
| Búsqueda binaria | O(log n) |
