import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class InsertarTests {
  @Test
  void InsertarEnArbolConNodos() {
    TArbolGenerico<String> arbol = new TArbolGenerico<>();
    arbol.insertar("A", "");
    arbol.insertar("B", "A");
    arbol.insertar("C", "A");
    arbol.insertar("D", "A");

    Assertions.assertEquals(true, arbol.insertar("B1", "B"));
    Assertions.assertEquals(5, arbol.tamaño());
  }

  @Test
  void InsertarEnArbolVacio() {
    TArbolGenerico<String> arbol = new TArbolGenerico<>();

    Assertions.assertEquals(true, arbol.insertar("B", "A"));
    Assertions.assertEquals("B", arbol.getRaiz().getEtiqueta());
    Assertions.assertEquals(1, arbol.tamaño());
  }

  @Test
  void InsertarEnArbolComoRaiz() {
    TArbolGenerico<String> arbol = new TArbolGenerico<>();
    arbol.insertar("A", "");
    arbol.insertar("B", "A");
    arbol.insertar("C", "A");
    arbol.insertar("D", "A");

    Assertions.assertEquals(true, arbol.insertar("Z", ""));
    Assertions.assertEquals("Z", arbol.getRaiz().getEtiqueta());
    Assertions.assertEquals(4, arbol.tamaño());
  }
}
