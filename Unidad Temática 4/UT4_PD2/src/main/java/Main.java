public class Main {
  public static void main(String[] args) {
    TArbolBB arbol = new TArbolBB();

    String[] claves = ManejadorArchivosGenerico.leerArchivo("src//main//java//clavesPrueba.txt");

    for (String clave : claves) {
      TElementoAB<Integer> elemento = new TElementoAB<Integer>(Integer.parseInt(clave), null) {};
      arbol.insertar(elemento);
    }

    ManejadorArchivosGenerico.escribirArchivo(
      "src//main//java//recorridos.txt",
      new String[]{
        "Pre orden: " + arbol.preOrden(),
        "In orden: " + arbol.inOrden(),
        "Post orden: " + arbol.postOrden()
      }
    );

    TArbolBB arbol2 = new TArbolBB();

    String[] claves2 = ManejadorArchivosGenerico.leerArchivo("src//main//java//claves1.txt");

    for (String clave : claves2) {
      TElementoAB<Integer> elemento = new TElementoAB<Integer>(Integer.parseInt(clave), null) {};
      arbol2.insertar(elemento);
    }

    // Ninguna de estas claves está en el arbol
    System.out.println(arbol2.buscar(10636));
    System.out.println(arbol2.buscar(4567));
    System.out.println(arbol2.buscar(12));
    System.out.println(arbol2.buscar(8978));

    // A continuación buscaré un elemento que sí esté en el arbol
    System.out.println(arbol2.buscar(406)); // Me devuelve el elemento que busqué

    // Imprimo el pre orden para que se vea la décima clave y responder a la pregunta D de esta sección
    // La clave es la 797
    System.out.println(arbol2.preOrden());
  }
}
