import java.util.Collection;

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

    // Matriz del arbol abarcador de costo minimo
    TGrafoNoDirigido prim = gnd.Prim();

    Double[][] matrizPrim = UtilGrafos.obtenerMatrizCostos(prim.getVertices());
    UtilGrafos.imprimirMatrizMejorado(matrizPrim, prim.getVertices(), "Matriz Prim");

    // Verificar si entre todos los vertices están conectados entre si
    System.out.println("Conectados?: ");

    for (TVertice verticeX : gnd.getVertices().values()) {
      for (TVertice verticeY : gnd.getVertices().values()) {
        System.out.println(verticeX.getEtiqueta() + " - " + verticeY.getEtiqueta() + ": " + gnd.conectados(verticeX, verticeY));
      }
    }
  }
}
