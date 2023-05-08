import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ListarTests {
  @Test
  void ListarArbolConNodos() {
    TArbolGenerico<String> arbol = new TArbolGenerico<>();
    arbol.insertar("A", "");
    arbol.insertar("B", "A");
    arbol.insertar("C", "A");
    arbol.insertar("D", "A");
    arbol.insertar("E", "D");
    arbol.insertar("F", "D");

    Assertions.assertEquals(
      "A\n" +
              "\tB\n" +
              "\tC\n" +
              "\tD\n" +
              "\t\tE\n" +
              "\t\tF\n",
      arbol.listarIndentado()
    );
  }

  @Test
  void ListarArbolSinNodos() {
    TArbolGenerico<String> arbol = new TArbolGenerico<>();

    Assertions.assertEquals(
      "",
      arbol.listarIndentado()
    );
  }

  @Test
  void ListarArbolSoloConRaiz() {
    TArbolGenerico<String> arbol = new TArbolGenerico<>();
    arbol.insertar("A", "");

    Assertions.assertEquals(
      "A\n",
      arbol.listarIndentado()
    );
  }
}
