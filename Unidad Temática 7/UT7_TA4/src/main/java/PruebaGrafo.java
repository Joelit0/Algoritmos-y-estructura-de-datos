
import java.util.Collection;

public class PruebaGrafo {

    public static void main(String[] args) {
        TGrafoDirigido gd = (TGrafoDirigido) UtilGrafos.cargarGrafo(
          "src/main/java/aeropuertos_1.txt",
          "src/main/java/conexiones_1.txt",
          false,
          TGrafoDirigido.class
        );

//        Object[] etiquetasarray = gd.getEtiquetasOrdenado();

//        Double[][] matriz = UtilGrafos.obtenerMatrizCostos(gd.getVertices());
//        UtilGrafos.imprimirMatrizMejorado(matriz, gd.getVertices(), "Matriz");

//        Double[][] mfloyd = gd.floyd();
//        UtilGrafos.imprimirMatrizMejorado(mfloyd, gd.getVertices(), "Matriz luego de FLOYD");

//        for (int i = 0; i < etiquetasarray.length; i++) {
//            System.out.println("excentricidad de " + etiquetasarray[i] + " : " + gd.obtenerExcentricidad((Comparable) etiquetasarray[i]));
//        }

//        System.out.println();
//        System.out.println("Centro del grafo: " + gd.centroDelGrafo());
       
       Collection<TVertice> recorrido = gd.bpf();

       for (TVertice vertice : recorrido) {
         System.out.println(vertice.getEtiqueta());
       }

      // imprimir etiquetas del bpf de todo el grafo....
//       Collection recorrido_Asuncion = gd.bpf("Asuncion");

       // imprimir etiquetas del bpf desde Asunción....

    }
}
