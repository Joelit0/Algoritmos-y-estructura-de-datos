import java.util.Collection;

public class Main {
  public static void main(String[] args) {
    TGrafoNoDirigido gnd = UtilGrafos.cargarGrafo(
      "src/main/java/deptos.txt",
      "src/main/java/conexiones.txt",
      false,
      TGrafoNoDirigido.class);

    Double[][] matriz = UtilGrafos.obtenerMatrizCostos(gnd.getVertices());
    UtilGrafos.imprimirMatrizMejorado(matriz, gnd.getVertices(), "Matriz");

    TCaminos caminos = gnd.todosLosCaminos("Montevideo", "Artigas");

    // Imprimir todos los caminos de un origen a destino
    for (TCamino camino : caminos.getCaminos()) {
      System.out.println(camino.imprimirEtiquetas());
    }

    // Busqueda en profundidad
    Collection<TVertice> recorrido = gnd.bpf();

    for (TVertice etVert : recorrido) {
      System.out.print(etVert.getEtiqueta() + " - ");
    }

    System.out.println();

    // Grafo es conexo
    System.out.println(gnd.esConexo());

    // Prim del grafo
    // El grafo usado es el mismo que el del ejemplo de las diapositivas
    TGrafoNoDirigido gnd2 = UtilGrafos.cargarGrafo(
      "src/main/java/Ejercicio2Vertices",
      "src/main/java/Ejercicio2Aristas",
      false,
      TGrafoNoDirigido.class
    );

    // Matriz del grafo sin prim
    Double[][] matrizAdyacencia = UtilGrafos.obtenerMatrizCostos(gnd2.getVertices());
    UtilGrafos.imprimirMatrizMejorado(matrizAdyacencia, gnd2.getVertices(), "Matriz Adyacencia");

    // Matriz del arbol abarcador de costo minimo
    TGrafoNoDirigido prim = gnd2.Prim();

    Double[][] matrizPrim = UtilGrafos.obtenerMatrizCostos(prim.getVertices());
    UtilGrafos.imprimirMatrizMejorado(matrizPrim, prim.getVertices(), "Matriz Prim");

    // Busqueda en Amplitud que comienza por el vertice 1
    for (TVertice vertice : gnd2.bea("1") )
      System.out.printf(vertice.getEtiqueta() + " - ");
  }
}
