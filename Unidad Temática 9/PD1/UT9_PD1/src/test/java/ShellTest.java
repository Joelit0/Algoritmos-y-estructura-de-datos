import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ShellTest {
  @Test
  void vectorAleatorio() {
    TClasificador clasificador = new TClasificador();
    GeneradorDatosGenericos generador = new GeneradorDatosGenericos();

    int[] vectorAleatorio = generador.generarDatosAleatorios();

    int[] resAleatorio = clasificador.clasificar(vectorAleatorio,
      clasificador.METODO_CLASIFICACION_SHELL);

    Assertions.assertEquals(9, vectorAleatorio.length);
    Assertions.assertEquals(true, clasificador.estaOrdenado(resAleatorio));
  }

  @Test
  void vectorAscendente() {
    TClasificador clasificador = new TClasificador();
    GeneradorDatosGenericos generador = new GeneradorDatosGenericos();

    int[] vectorAscendente = generador.generarDatosAscendentes();

    int[] resAscendente = clasificador.clasificar(vectorAscendente,
      clasificador.METODO_CLASIFICACION_SHELL);

    Assertions.assertEquals(9, vectorAscendente.length);
    Assertions.assertEquals(true, clasificador.estaOrdenado(resAscendente));
  }

  @Test
  void vectorDescendente() {
    TClasificador clasificador = new TClasificador();
    GeneradorDatosGenericos generador = new GeneradorDatosGenericos();

    int[] vectorDescendente = generador.generarDatosAleatorios();

    int[] resDescendente = clasificador.clasificar(vectorDescendente,
      clasificador.METODO_CLASIFICACION_SHELL);

    Assertions.assertEquals(9, vectorDescendente.length);
    Assertions.assertEquals(true, clasificador.estaOrdenado(resDescendente));
  }
}
