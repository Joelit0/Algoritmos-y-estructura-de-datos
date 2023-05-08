import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BuscarTests {
  @Test
  void BuscarNodoExistente() {
    TArbolGenerico<String> arbol = new TArbolGenerico<>();
    arbol.insertar("A", "");
    arbol.insertar("B", "A");
    arbol.insertar("C", "A");
    arbol.insertar("D", "C");

    Assertions.assertEquals("D", arbol.buscar("D").getEtiqueta());
  }

  @Test
  void BuscarNodoNoExistente() {
    TArbolGenerico<String> arbol = new TArbolGenerico<>();
    arbol.insertar("A", "");
    arbol.insertar("B", "A");
    arbol.insertar("C", "A");
    arbol.insertar("D", "C");

    Assertions.assertEquals(null, arbol.buscar("H"));
  }

  @Test
  void BuscarNodoEnArbolVacio() {
    TArbolGenerico<String> arbol = new TArbolGenerico<>();

    Assertions.assertEquals(null, arbol.buscar("A"));
  }

}
