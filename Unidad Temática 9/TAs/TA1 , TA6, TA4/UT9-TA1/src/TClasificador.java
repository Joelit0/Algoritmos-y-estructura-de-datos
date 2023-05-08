
public class TClasificador {

    public static final int METODO_CLASIFICACION_INSERCION = 1;
    public static final int METODO_CLASIFICACION_SHELL = 2;
    public static final int METODO_CLASIFICACION_BURBUJA = 3;
    public static final int METODO_CLASIFICACION_QUICKSORT = 4;
    public static final int METODO_CLASIFICACION_HEAPSORT = 5;
    public static final int METODO_CLASIFICACION_DIR = 6;

    /**
     * Punto de entrada al clasificador
     *
     * @param datosParaClasificar
     * @param metodoClasificacion
     * @return Un vector del tam. solicitado, ordenado por el algoritmo
     * solicitado
     */
    public int[] clasificar(int[] datosParaClasificar, int metodoClasificacion, boolean cascara, int[] comparaciones) {
        switch (metodoClasificacion) {
            case METODO_CLASIFICACION_INSERCION -> {
                if (cascara) {
                    ordenarPorInsercionCascara(datosParaClasificar);
                }
                return ordenarPorInsercion(datosParaClasificar);
            }
            case METODO_CLASIFICACION_SHELL -> {
                if (cascara) {
                    return ordenarPorShellCascara(datosParaClasificar);
                }
                return ordenarPorShell(datosParaClasificar);
            }
            case METODO_CLASIFICACION_BURBUJA -> {
                if (cascara) {
                    return ordenarPorBurbujaCascara(datosParaClasificar);
                }
                return ordenarPorBurbuja(datosParaClasificar);
            }
            case METODO_CLASIFICACION_QUICKSORT -> {
                if (cascara) {
                    return ordenarPorQuickSortCascara(datosParaClasificar);
                }
                return ordenarPorQuickSort(datosParaClasificar, comparaciones);
            }
            case METODO_CLASIFICACION_HEAPSORT -> {
                return ordenarPorHeapSort(datosParaClasificar);
            }
            case METODO_CLASIFICACION_DIR -> {
                return ordenarPorSeleccionDir(datosParaClasificar);
            }
            default -> System.err.println("Este codigo no deberia haberse ejecutado");
        }
        return datosParaClasificar;
    }

    private void intercambiar(int[] vector, int pos1, int pos2) {
        int temp = vector[pos2];
        vector[pos2] = vector[pos1];
        vector[pos1] = temp;
    }

    /**
     * @param datosParaClasificar
     * @return
     */
    private int[] ordenarPorShell(int[] datosParaClasificar) {
        int j, inc;
        int[] incrementos = new int[]{3223, 358, 51, 10, 3, 1};

        for (int posIncrementoActual = 0; posIncrementoActual < incrementos.length; posIncrementoActual++) {
            inc = incrementos[posIncrementoActual];
            if (inc < (datosParaClasificar.length / 2)) {
                for (int i = inc; i < datosParaClasificar.length; i++) {
                    int aux = datosParaClasificar[i];
                    j = i - inc;
                    while (j > -1 && aux < datosParaClasificar[j]) {
                        datosParaClasificar[j + inc] = datosParaClasificar[j];
                        j = j - inc;
                        /*
			if (datosParaClasificar[j] > datosParaClasificar[j + inc]) {
                            intercambiar(datosParaClasificar, j, j + inc);
                            j = j--;
			} 
                         */
                    }
                    datosParaClasificar[j + inc] = aux;
                }
            }
        }
        return datosParaClasificar;
    }

    protected int[] ordenarPorShellCascara(int[] datosParaClasificar) {
        if (datosParaClasificar != null) {
            return datosParaClasificar;
        }
        return null;
    }

    /**
     * @param datosParaClasificar
     * @return
     */
    protected int[] ordenarPorInsercion(int[] datosParaClasificar) {
        if (datosParaClasificar != null) {
            for (int i = 1; i < datosParaClasificar.length; i++) {
                int aux = datosParaClasificar[i];
                int j = i - 1;
                while ((j > -1) && (aux < datosParaClasificar[j])) {
                    datosParaClasificar[j + 1] = datosParaClasificar[j];
                    j--;
                }
                datosParaClasificar[j + 1] = aux;
            }
            return datosParaClasificar;
        }
        return null;
    }

    protected int[] ordenarPorInsercionCascara(int[] datosParaClasificar) {
        return datosParaClasificar;
    }

    private int[] ordenarPorBurbuja(int[] datosParaClasificar) {
        int n = datosParaClasificar.length - 1;
        for (int i = 0; i <= n; i++) {
            for (int j = n; j >= (i + 1); j--) {
                if (datosParaClasificar[j] < datosParaClasificar[j - 1]) {
                    intercambiar(datosParaClasificar, j - 1, j);
                }
            }
        }
        return datosParaClasificar;
    }

    protected int[] ordenarPorBurbujaCascara(int[] datosParaClasificar) {
        if (datosParaClasificar != null) {
            return datosParaClasificar;
        }
        return null;
    }

    public static boolean estaOrdenado(int[] datosClasificados) {
        if (datosClasificados.length > 1) {
            int contador = 0;
            while (contador < datosClasificados.length - 1) {
                if (datosClasificados[contador] > datosClasificados[contador + 1]) {
                    return false;
                }
                contador++;
            }
        }
        return true;
    }

    public static boolean estaOrdeando(int[] vector_entrada, boolean esAscendente) {
        for (int i = 0; i < vector_entrada.length - 1; i++) {
            if (esAscendente) {
                if (vector_entrada[i] > vector_entrada[i + 1]) {
                    return false;
                }
            } else {
                if (vector_entrada[i] < vector_entrada[i + 1]) {
                    return false;
                }
            }
        }
        return true;
    }

    protected int[] ordenarPorQuickSort(int[] datosParaClasificar, int[] comparaciones) {
        quicksort(datosParaClasificar, 0, datosParaClasificar.length - 1, comparaciones);
        return datosParaClasificar;
    }

    protected int[] ordenarPorQuickSortCascara(int[] datosParaClasificar) {
        if (datosParaClasificar != null) {
            return datosParaClasificar;
        }
        return null;
    }

    private void quicksort(int[] entrada, int i, int j, int[] comparaciones) {
        int izquierda = i;
        int derecha = j;

        int posicionPivote = encuentraPivote(izquierda, derecha, entrada);
        if (posicionPivote >= 0) {
            int pivote = posicionPivote;
            while (izquierda <= derecha) {
                comparaciones[0]++; // Para el while de arriba.

                comparaciones[0]++;
                while ((entrada[izquierda] < pivote) && (izquierda < j)) {
                    izquierda++;
                }

                comparaciones[0]++;
                while ((pivote < entrada[derecha]) && (derecha > i)) {
                    derecha--;
                }

                comparaciones[0]++;
                if (izquierda <= derecha) {
                    intercambiar(entrada, derecha, izquierda);
                    izquierda++;
                    derecha--;
                }
            }
            // Suponer que k -> izquierda
            if (i < derecha) {
                quicksort(entrada, i, izquierda - 1, comparaciones);
            }
            if (izquierda < j) {
                quicksort(entrada, izquierda, j, comparaciones);
            }
        }
    }

    public int encuentraPivote(int izquierda, int derecha, int[] entrada) {
        return entrada[(izquierda + derecha) / 2];
    }

    protected int[] ordenarPorHeapSortCascara(int[] datosParaClasificar) {
        if (datosParaClasificar != null) {
            return datosParaClasificar;
        }
        return null;
    }

    protected int[] ordenarPorHeapSort(int[] datosParaClasificar) {
        for (int i = (datosParaClasificar.length / 2) - 1; i >= 0; i--) {
            armaHeap(datosParaClasificar, i, datosParaClasificar.length - 1);
        }

        for (int i = datosParaClasificar.length - 1; i >= 0; i--) {
            intercambiar(datosParaClasificar, 0, i);
            armaHeap(datosParaClasificar, 0, i - 1);
        }
        return datosParaClasificar;
    }

    private void armaHeap(int[] datosParaClasificar, int primero, int ultimo) {
        if (primero < ultimo) {
            int r = primero;
            while (r <= ultimo / 2) {
                if (ultimo == 2 * r) { //r tiene un hijo solo
                    if (datosParaClasificar[r] < datosParaClasificar[r * 2]) {
                        intercambiar(datosParaClasificar, r, 2 * r);
                        r = 2 * r;
                    } else {
                        r = ultimo;
                    }
                } else { //r tiene 2 hijos
                    int posicionIntercambio = 0;
                    if (datosParaClasificar[2 * r] > datosParaClasificar[2 * r + 1]) {
                        posicionIntercambio = 2 * r;
                    } else {
                        posicionIntercambio = 2 * r + 1;
                    }
                    if (datosParaClasificar[r] < datosParaClasificar[posicionIntercambio]) {
                        intercambiar(datosParaClasificar, r, posicionIntercambio);
                        r = posicionIntercambio;
                    } else {
                        r = ultimo;
                    }
                }
            }
        }
    }

    private long calcularTiempo(int[] vectorOriginal, int metodo, long tiemporesolucion) {
        long t1 = System.nanoTime();
        long total = 0;
        int cantLlamadas = 0;
        while (total < tiemporesolucion) {
            cantLlamadas++;
            int[] datosCopia = vectorOriginal.clone();
            //clasificar(datosCopia, metodo, false);
            long t2 = System.nanoTime();
            total = t2 - t1;
        }

        long tiempoAlgBase = total / cantLlamadas;

        t1 = System.nanoTime();
        total = 0;
        cantLlamadas = 0;
        while (total < tiemporesolucion) {
            cantLlamadas++;
            int[] datosCopia = vectorOriginal.clone();
            //clasificar(datosCopia, metodo, true);
            long t2 = System.nanoTime();
            total = t2 - t1;
        }

        long tiempoCascara = total / cantLlamadas;

        return tiempoAlgBase - tiempoCascara;
    }

    public int[] ordenarPorSeleccionDir(int[] datosParaClasificar) {
        for (int i = 0; i < datosParaClasificar.length; i++) {
            int indiceDelMenor = i;
            int claveMenor = datosParaClasificar[i];

            for (int j = i + 1; j != datosParaClasificar.length; j++) {
                if (datosParaClasificar[j] < claveMenor) {
                    indiceDelMenor = j;
                    claveMenor = datosParaClasificar[j];
                }
            }

            intercambiar(datosParaClasificar, i, indiceDelMenor);
        }

        return datosParaClasificar;
    }

    public static void main(String args[]) {
        GeneradorDatosGenericos gdg = new GeneradorDatosGenericos();
        TClasificador clasif = new TClasificador();
        int[] vectorAleatorio;
        int[] vectorAscendente;
        int[] vectorDescendente;
        int[] comparaciones = {0};
        int[] tamaños = {300, 3000, 30000};
        for (int i = 0; i <= tamaños.length - 1; i++) {
            vectorAleatorio = gdg.generarDatosAleatorios(tamaños[i]);
            vectorAscendente = gdg.generarDatosAscendentes(tamaños[i]);
            vectorDescendente = gdg.generarDatosDescendentes(tamaños[i]);

            for (int k = 1; k <= 6; k++) {
                if (k == 1) {
                    System.out.println("====================ORDENACIÓN POR INSERCIÓN - PARA : " + tamaños[i] + " ====================");
                } else if (k == 2) {
                    System.out.println("====================ORDENACIÓN POR SHELL - PARA : " + tamaños[i] + " ====================");
                } else if (k == 3) {
                    System.out.println("====================ORDENACIÓN POR BURBUJA - PARA : " + tamaños[i] + " ====================");
                } else if (k == 4) {
                    System.out.println("====================ORDENACIÓN POR QUICKSORT - PARA : " + tamaños[i] + " ====================");
                } else if (k == 5) {
                    System.out.println("====================ORDENACIÓN POR HEAPSORT - PARA : " + tamaños[i] + " ====================");
                } else {
                    System.out.println("====================ORDENACIÓN POR SELECCION DIR - PARA : " + tamaños[i] + " ====================");
                }

                comparaciones[0] = 0;
                double start = System.nanoTime();
                int[] resAleatorio = clasif.clasificar(vectorAleatorio.clone(), k, false, comparaciones);
                double tiempo = System.nanoTime() - start;
                System.out.println("Array ordenado? " + estaOrdeando(resAleatorio, k != 5) + ". Cantidad de comparaciones realizadas: " + comparaciones[0]);
                System.out.println("Tiempo transucrrido clasificación aleatoria en milisegundos: " + tiempo / 1000);

                comparaciones[0] = 0;
                start = System.nanoTime();
                int[] resAscendente = clasif.clasificar(vectorAscendente.clone(), k, false, comparaciones);
                tiempo = System.nanoTime() - start;
                System.out.println("Array ordenado? " + estaOrdeando(resAscendente, k != 5) + ". Cantidad de comparaciones realizadas: " + comparaciones[0]);
                System.out.println("Tiempo transucrrido clasificación ascendente en milisegundos: " + tiempo / 1000);

                comparaciones[0] = 0;
                start = System.nanoTime();
                int[] resDescendente = clasif.clasificar(vectorDescendente.clone(), k, false, comparaciones);
                tiempo = System.nanoTime() - start;
                System.out.println("Array ordenado? " + estaOrdeando(resDescendente, k != 5) + ". Cantidad de comparaciones realizadas: " + comparaciones[0]);
                System.out.println("Tiempo transucrrido clasificación descendente en milisegundos: " + tiempo / 1000);

                System.out.println("");
            }
        }

//        int[] resAleatorio = clasif.clasificar(vectorAleatorio,
//                METODO_CLASIFICACION_QUICKSORT);
//        for (int i = 0; i < resAleatorio.length; i++) {
//            System.out.print(resAleatorio[i] + " ");
//        }
//        System.out.println("");
//        System.out.println("esta Ordenado = " + estaOrdenado(resAleatorio));
//        int[] resAscendente = clasif.clasificar(vectorAscendente,
//                METODO_CLASIFICACION_QUICKSORT);
//        for (int i = 0; i < resAscendente.length; i++) {
//            System.out.print(resAscendente[i] + " ");
//        }
//        System.out.println("");
//        System.out.println("esta Ordenado = " + estaOrdenado(resAscendente));
//        int[] resDescendente = clasif.clasificar(vectorDescendente,
//                METODO_CLASIFICACION_QUICKSORT);
//        for (int i = 0; i < resDescendente.length; i++) {
//            //System.out.print(resDescendente[i] + " ");
//        }
//        //resDescendente[4] = 7;    //para romper el esta ordenado
//        System.out.println("");
//        System.out.println("esta Ordenado = " + estaOrdenado(resDescendente));
/*
        System.out.println("TA6");
        int[] resTA6 = clasif.clasificar(vectorTA6,
                METODO_SELECCION_HEAPSORT);
        for (int i = 0; i < resTA6.length; i++) {
            System.out.print(resTA6[i] + " ");
        }
         */
    }
}
