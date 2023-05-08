import java.util.Collection;
import java.util.LinkedList;

public class Main {
  public static void main(String[] args) {
    TGrafoNoDirigido gnd = UtilGrafos.cargarGrafo(
      "src/main/java/vertices.txt",
      "src/main/java/aristas.txt",
      false,
      TGrafoNoDirigido.class
    );

    // Matriz del grafo sin prim
    Double[][] matrizAdyacencia = UtilGrafos.obtenerMatrizCostos(gnd.getVertices());
    UtilGrafos.imprimirMatrizMejorado(matrizAdyacencia, gnd.getVertices(), "Matriz Adyacencia");

    // Matriz del arbol abarcador de costo minimo PRIM
    TGrafoNoDirigido prim = gnd.Prim();

    Double[][] matrizPrim = UtilGrafos.obtenerMatrizCostos(prim.getVertices());
    UtilGrafos.imprimirMatrizMejorado(matrizPrim, prim.getVertices(), "Matriz Prim");

    // Matriz del arbol abarcador de costo minimo Kruskal
    TGrafoNoDirigido kruskal = gnd.Kruskal();

    Double[][] matrizKruskal = UtilGrafos.obtenerMatrizCostos(kruskal.getVertices());
    UtilGrafos.imprimirMatrizMejorado(matrizKruskal, kruskal.getVertices(), "Matriz Kruskal");

    // Efectivamente devuelven el mismo valor y las mismas matrices

    LinkedList<TVertice> puntosArticulacion = gnd.puntosArticulacion("a");

    for (TVertice vertice : puntosArticulacion) {
      System.out.print(vertice.getEtiqueta() + " - ");
    }
  }
}
