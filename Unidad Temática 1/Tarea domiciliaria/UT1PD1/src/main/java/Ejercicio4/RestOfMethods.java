package Ejercicio4;

class RestOfMethods {
  public static int recorrer(String cadena) {
    int res = 0;

    for (int i = 0; i < cadena.length(); i++) {
      if (cadena.charAt(i) != ' ') {
        res++;
      }
    }

    return res;
  }

  public static int getValor() {
    int vector[] = {6, 16, 26, 36, 46, 56, 66, 76};
    int idx = 7;
    return vector[idx];
  }

  public static char getPrimerCaracter(String palabra) {
    return (palabra.charAt(0));
  }

  public static String paraAString(int a) {
    return String.valueOf(a);
  }
}

