import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.LinkedList;

public class TVerticeTest {
  TGrafoDirigido gd;
  TVertice v1;
  TVertice v2;
  TVertice v3;
  TVertice v4;

  @BeforeEach
  public void setUp() throws Exception {
    Collection<TVertice> vertices = new LinkedList<TVertice>();
    Collection<TArista> aristas = new LinkedList<TArista>();

    v1 = new TVertice("A");
    v2 = new TVertice("B");
    v3 = new TVertice("C");
    v4 = new TVertice("D");

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
  void bpfTest1() {
    LinkedList<TVertice> lista = new LinkedList<>();
    gd.desvisitarVertices();

    v1.bpf(lista);

    Assertions.assertEquals(4, lista.size());
  }

  @Test
  void bpfTest2() {
    LinkedList<TVertice> lista = new LinkedList<>();

    v4.bpf(lista);

    Assertions.assertEquals(1, lista.size());
    Assertions.assertEquals(false, lista.contains("A"));
    Assertions.assertEquals(false, lista.contains("B"));
    Assertions.assertEquals(false, lista.contains("C"));
  }
}
