public class PruebaGrafo {

    public static void main(String[] args) {
        TGrafoDirigido gd = (TGrafoDirigido) UtilGrafos.cargarGrafo(
          "src/main/java/deptos.txt",
          "src/main/java/conexiones.txt",
          false,
          TGrafoDirigido.class);

        Object[] etiquetasarray = gd.getEtiquetas();

        int[][] matriz = UtilGrafos.obtenerMatrizCostos(gd.getVertices());
        UtilGrafos.imprimirMatrizMejorado(matriz, gd.getVertices(), "Matriz");

        int[][] mfloyd = gd.floyd();
        UtilGrafos.imprimirMatrizMejorado(mfloyd, gd.getVertices(), "Matriz luego de FLOYD");

        for (int i = 0; i < etiquetasarray.length; i++) {
            System.out.println(
              "Excentricidad de " + etiquetasarray[i] + " : " +  gd.obtenerExcentricidad((Comparable) etiquetasarray[i], i, mfloyd)
            );
        }

        System.out.println();
        System.out.println("Centro del grafo: " + gd.centroDelGrafo(mfloyd));
    }
}
