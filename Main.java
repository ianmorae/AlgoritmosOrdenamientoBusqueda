import java.text.Collator;
import java.util.Locale;
import java.util.Scanner;

public class Main {

    static Scanner entrada = new Scanner(System.in);

    public static void main(String[] args) {
        //Se solicita el tamaño del arreglo, que debe ser mayor que cero.
        System.out.println("Por favor digite la cantidad de palabras que quiere almacenar: ");
        int tamano = leerEntero();

        while (tamano <= 0) {
            System.out.println("El tamaño debe ser mayor que cero. Intente de nuevo: ");
            tamano = leerEntero();
        }

        //Se crea el arreglo estático y se llena con las palabras del usuario.
        String[] palabras = new String[tamano];
        llenarArreglo(palabras);

        int opcion;

        do {
            mostrarMenu();
            opcion = leerEntero();

            if (opcion >= 1 && opcion <= 5) {
                //Se ordena una copia para conservar el arreglo original.
                String[] copia = copiarArreglo(palabras);

                switch (opcion) {
                    case 1:
                        ordenamientoSeleccion(copia);
                        System.out.println("\nArreglo ordenado por selección:");
                        break;
                    case 2:
                        ordenamientoInsercion(copia);
                        System.out.println("\nArreglo ordenado por inserción:");
                        break;
                    case 3:
                        ordenamientoBurbuja(copia);
                        System.out.println("\nArreglo ordenado por burbuja:");
                        break;
                    case 4:
                        ordenamientoMezcla(copia, 0, copia.length - 1);
                        System.out.println("\nArreglo ordenado por mezcla:");
                        break;
                    case 5:
                        ordenamientoRapido(copia, 0, copia.length - 1);
                        System.out.println("\nArreglo ordenado por ordenamiento rápido:");
                        break;
                }

                imprimirArreglo(copia);
                menuBusqueda(copia);

            } else if (opcion == 0) {
                System.out.println("Programa finalizado.");
            } else {
                System.out.println("Opción inválida. Intente de nuevo.");
            }

        } while (opcion != 0);
    }

    //Lee un número entero y lo vuelve a pedir si lo digitado no es válido.
    public static int leerEntero() {
        while (true) {
            try {
                return Integer.parseInt(entrada.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Debe digitar un número entero. Intente de nuevo: ");
            }
        }
    }

    public static void llenarArreglo(String[] palabras) {
        for (int i = 0; i < palabras.length; i++) {
            System.out.println("Por favor digite la palabra " + (i + 1) + " de " + palabras.length + ": ");
            String palabra = entrada.nextLine().trim();

            while (palabra.isEmpty()) {
                System.out.println("La palabra no puede estar vacía. Intente de nuevo: ");
                palabra = entrada.nextLine().trim();
            }

            palabras[i] = palabra;
        }
    }

    public static void mostrarMenu() {
        System.out.println("\n===== MENÚ DE ORDENAMIENTO =====");
        System.out.println("1. Ordenamiento por selección");
        System.out.println("2. Ordenamiento por inserción");
        System.out.println("3. Ordenamiento por burbuja");
        System.out.println("4. Ordenamiento por mezcla");
        System.out.println("5. Ordenamiento rápido");
        System.out.println("0. Salir");
        System.out.println("Seleccione una opción: ");
    }

    public static String[] copiarArreglo(String[] palabras) {
        String[] copia = new String[palabras.length];
        for (int i = 0; i < palabras.length; i++) {
            copia[i] = palabras[i];
        }
        return copia;
    }

    public static void imprimirArreglo(String[] palabras) {
        for (int i = 0; i < palabras.length; i++) {
            System.out.println("[" + i + "] " + palabras[i]);
        }
    }

    //Permite buscar palabras en el arreglo ordenado las veces que el usuario quiera.
    public static void menuBusqueda(String[] palabrasOrdenadas) {
        System.out.println("\n¿Desea buscar una palabra con búsqueda binaria? (s/n): ");
        String respuesta = entrada.nextLine().trim();

        while (respuesta.equalsIgnoreCase("s")) {
            System.out.println("Por favor digite la palabra que desea buscar: ");
            String palabraBuscada = entrada.nextLine().trim();

            busquedaBinaria(palabrasOrdenadas, palabraBuscada);

            System.out.println("\n¿Desea buscar otra palabra? (s/n): ");
            respuesta = entrada.nextLine().trim();
        }
    }

    //Compara dos palabras en orden alfabético del español, sin distinguir mayúsculas ni tildes.
    //Retorna negativo si palabra1 va antes, cero si son iguales y positivo si va después.
    public static int compararPalabras(String palabra1, String palabra2) {
        Collator comparador = Collator.getInstance(Locale.forLanguageTag("es"));
        comparador.setStrength(Collator.PRIMARY);
        return comparador.compare(palabra1, palabra2);
    }

    //Ordenamiento por selección
    //Complejidad de tiempo: O(n^2)
    public static void ordenamientoSeleccion(String[] palabras) {
        //Se declaran algunas variables de control necesarias.
        int n = palabras.length;
        int menor;
        String temp;

        //El ciclo externo recorre todo el arreglo (n - 1 pasadas).
        for (int i = 0; i < n - 1; i++) {
            menor = i;

            //El ciclo interno busca la palabra mínima en la porción restante.
            for (int j = i + 1; j < n; j++) {
                if (compararPalabras(palabras[j], palabras[menor]) < 0) {
                    menor = j;
                }
            }

            //Si el mínimo está en otra casilla, se intercambian.
            if (menor != i) {
                temp = palabras[i];
                palabras[i] = palabras[menor];
                palabras[menor] = temp;
            }
        }
    }

    //Ordenamiento por inserción
    //Complejidad de tiempo: O(n^2)
    public static void ordenamientoInsercion(String[] palabras) {
        //Se crea un arreglo nuevo del mismo tamaño y las variables de control.
        int n = palabras.length;
        String[] palabrasNuevo = new String[n];
        int posicionInsertar;
        int cantidadInsertados = 0;

        //El ciclo externo recorre todo el arreglo original.
        for (int i = 0; i < n; i++) {
            posicionInsertar = cantidadInsertados;

            //Se busca la casilla del arreglo nuevo donde se debe insertar la palabra.
            for (int j = 0; j < cantidadInsertados; j++) {
                if (compararPalabras(palabras[i], palabrasNuevo[j]) < 0) {
                    posicionInsertar = j;
                    break;
                }
            }

            //Se desplazan hacia la derecha las palabras mayores, de atrás hacia adelante.
            for (int k = cantidadInsertados; k > posicionInsertar; k--) {
                palabrasNuevo[k] = palabrasNuevo[k - 1];
            }

            //Se inserta la palabra.
            palabrasNuevo[posicionInsertar] = palabras[i];
            cantidadInsertados++;
        }

        //Se actualiza el arreglo original con el arreglo nuevo ya ordenado.
        for (int c = 0; c < n; c++) {
            palabras[c] = palabrasNuevo[c];
        }
    }

    //Ordenamiento por burbuja
    //Complejidad de tiempo: O(n^2)
    public static void ordenamientoBurbuja(String[] palabras) {
        //Se declaran algunas variables de control necesarias.
        int n = palabras.length;
        String temp;
        boolean huboIntercambio;

        //En cada pasada, la palabra mayor queda al final de la porción restante.
        for (int i = 0; i < n - 1; i++) {
            huboIntercambio = false;

            //Se comparan las casillas vecinas y se intercambian si están en desorden.
            for (int j = 0; j < n - 1 - i; j++) {
                if (compararPalabras(palabras[j], palabras[j + 1]) > 0) {
                    temp = palabras[j];
                    palabras[j] = palabras[j + 1];
                    palabras[j + 1] = temp;
                    huboIntercambio = true;
                }
            }

            //Si no hubo intercambios, el arreglo ya está ordenado.
            if (!huboIntercambio) {
                break;
            }
        }
    }

    //Ordenamiento por mezcla
    //Complejidad de tiempo: O(n log n)
    public static void ordenamientoMezcla(String[] palabras, int inicio, int fin) {
        //Si la porción tiene una sola casilla, ya está ordenada.
        if (inicio < fin) {
            //Se divide la porción en dos mitades.
            int medio = (inicio + fin) / 2;

            //Se ordena cada mitad de forma recursiva.
            ordenamientoMezcla(palabras, inicio, medio);
            ordenamientoMezcla(palabras, medio + 1, fin);

            //Se mezclan las dos mitades ordenadas.
            mezclar(palabras, inicio, medio, fin);
        }
    }

    //Une dos mitades ordenadas en una sola porción ordenada.
    public static void mezclar(String[] palabras, int inicio, int medio, int fin) {
        //Se crean arreglos auxiliares para cada mitad.
        int tamanoIzquierda = medio - inicio + 1;
        int tamanoDerecha = fin - medio;
        String[] izquierda = new String[tamanoIzquierda];
        String[] derecha = new String[tamanoDerecha];

        //Se copian los datos de cada mitad.
        for (int i = 0; i < tamanoIzquierda; i++) {
            izquierda[i] = palabras[inicio + i];
        }
        for (int j = 0; j < tamanoDerecha; j++) {
            derecha[j] = palabras[medio + 1 + j];
        }

        int i = 0;
        int j = 0;
        int k = inicio;

        //Se compara la primera palabra de cada mitad y se coloca la menor.
        while (i < tamanoIzquierda && j < tamanoDerecha) {
            if (compararPalabras(izquierda[i], derecha[j]) <= 0) {
                palabras[k] = izquierda[i];
                i++;
            } else {
                palabras[k] = derecha[j];
                j++;
            }
            k++;
        }

        //Se copian las palabras que hayan quedado en alguna de las mitades.
        while (i < tamanoIzquierda) {
            palabras[k] = izquierda[i];
            i++;
            k++;
        }

        while (j < tamanoDerecha) {
            palabras[k] = derecha[j];
            j++;
            k++;
        }
    }

    //Ordenamiento rápido
    //Complejidad de tiempo: O(n log n) en el caso esperado y O(n^2) en el peor caso.
    public static void ordenamientoRapido(String[] palabras, int inicio, int fin) {
        //Si la porción tiene una sola casilla, ya está ordenada.
        if (inicio < fin) {
            //Se coloca el pivote en su posición definitiva.
            int posicionPivote = particion(palabras, inicio, fin);

            //Se ordenan recursivamente las partes a la izquierda y a la derecha del pivote.
            ordenamientoRapido(palabras, inicio, posicionPivote - 1);
            ordenamientoRapido(palabras, posicionPivote + 1, fin);
        }
    }

    //Acomoda las palabras menores a la izquierda del pivote y las mayores a la derecha.
    public static int particion(String[] palabras, int inicio, int fin) {
        //Se toma como pivote la última palabra de la porción.
        String pivote = palabras[fin];
        int i = inicio - 1;
        String temp;

        //Cada palabra menor o igual al pivote se pasa a la zona de las menores.
        for (int j = inicio; j < fin; j++) {
            if (compararPalabras(palabras[j], pivote) <= 0) {
                i++;
                temp = palabras[i];
                palabras[i] = palabras[j];
                palabras[j] = temp;
            }
        }

        //Se coloca el pivote justo después de las palabras menores.
        temp = palabras[i + 1];
        palabras[i + 1] = palabras[fin];
        palabras[fin] = temp;

        return i + 1;
    }

    //Búsqueda binaria
    //Complejidad de tiempo: O(log n), porque en cada iteración se descarta la mitad
    //de la porción restante, hasta encontrar la palabra o agotar el arreglo.
    public static void busquedaBinaria(String[] palabras, String palabraBuscada) {
        //Se declaran e inicializan las variables de control necesarias.
        int n = palabras.length;
        int limiteMenor = 0;
        int limiteMayor = n - 1;
        int pivote = (limiteMenor + limiteMayor) / 2;

        //Se reduce el intervalo de búsqueda mientras no se encuentre la palabra.
        while (compararPalabras(palabras[pivote], palabraBuscada) != 0) {
            //Se ajusta uno de los límites según la comparación con el pivote.
            if (compararPalabras(palabras[pivote], palabraBuscada) > 0) {
                limiteMayor = pivote - 1;
            } else {
                limiteMenor = pivote + 1;
            }

            //Si los límites se traslapan, la palabra no está en el arreglo.
            if (limiteMenor > limiteMayor) {
                System.out.println("La palabra \"" + palabraBuscada + "\" no se encontró.");
                return;
            }

            //Se redefine el pivote con los límites actualizados.
            pivote = (limiteMayor + limiteMenor) / 2;
        }

        System.out.println("La palabra \"" + palabraBuscada + "\" se encontró en la casilla: " + pivote);
    }
}