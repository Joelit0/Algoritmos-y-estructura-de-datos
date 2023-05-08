package Ejercicio5;

public class Contador {
  private static int MAX_COUNT = 50;
  private int incremento;
  private int contador;

  public Contador() {
    incremento = 1;
    contador = 1;
  }

  public void reset() {
    contador = 1;
  }

  public void mostrarContador() {
    while (contador <= MAX_COUNT) {
      System.out.println(contador);
      contador += incremento;
    }
  }

  public void mostrarContadorDoWhile() {
    do {
      System.out.println(contador);
      contador += incremento;
    } while (contador <= MAX_COUNT);
  }

  public void mostrarContadorFor() {
    for (; contador <= MAX_COUNT; contador += incremento) {
      System.out.println(contador);
    }
  }
}
