import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.LinkedList;

public class TGrafoDirigidoTest {
  TGrafoDirigido gd;

  @BeforeEach
  public void setUp() throws Exception {
    Collection<TVertice> vertices = new LinkedList<TVertice>();
    Collection<TArista> aristas = new LinkedList<TArista>();

    TVertice v1 = new TVertice("A");
    TVertice v2 = new TVertice("B");
    TVertice v3 = new TVertice("C");
    TVertice v4 = new TVertice("D");

    vertices.add(v1);
    vertices.add(v2);
    vertices.add(v3);
    vertices.add(v4);

    TArista a1 = new TArista("A", "B", 1.0);
    TArista a2 = new TArista("B", "C", 1.0);
    TArista a3 = new TArista("C", "D", 1.0);

    aristas.add(a1);
    aristas.add(a2);
    aristas.add(a3);

    gd = new TGrafoDirigido(vertices, aristas);
  }

  @Test
  void esPosibleLlegarATodosTest1() {
    // A es capaz de llegar a todos los otros vertices
    Assertions.assertEquals(true, gd.esPosibleLlegarATodos("A"));
  }

  @Test
  void esPosibleLlegarATodosTest2() {
    // Solo A puede llegar a todos, el resto no
    Assertions.assertEquals(false, gd.esPosibleLlegarATodos("D"));
    Assertions.assertEquals(false, gd.esPosibleLlegarATodos("C"));
    Assertions.assertEquals(false, gd.esPosibleLlegarATodos("B"));
  }

  @Test
  void esPosibleLlegarATodosTest3() {
    // Paso un vertice no existente y el programa me devuelve false
    Assertions.assertEquals(false, gd.esPosibleLlegarATodos("NKDJFHE"));
  }
}
