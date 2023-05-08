import java.io.Serializable;
import java.util.Arrays;

public class HashNumToNum implements IHash, Serializable {
  private int[] tabla;

  public static int inserccion_comp;
  public static int busqueda_fallida_comp;
  public static int busqueda_exitosa_comp;

  public HashNumToNum(int tamaño) {
    this.tabla = new int[tamaño];
    Arrays.fill(this.tabla, -1);
  }

  @Override
  public int buscar(int unaClave) {
    int i = 0;
    int j = funcionHashing(unaClave);
    int comparaciones = 0;

    do {
      if (j == this.tabla.length) {  j = 0; }

      comparaciones++;
      if (this.tabla[j] == unaClave) {
        busqueda_exitosa_comp += comparaciones;
        return unaClave;
      } else {
        i += 1;
        j += 1;
      }
    } while (i != j);

    busqueda_fallida_comp += comparaciones;

    return -1;
  }

  @Override
  public int insertar(int unaClave) {
    int i = 0;
    int j = funcionHashing(unaClave);

    do {
      if (j == this.tabla.length) {  j = 0; }

      inserccion_comp++;
      if (this.tabla[j] == -1) {
        tabla[j] = unaClave;
        return unaClave;
      } else {
        i += 1;
        j += 1;
      }
    } while (i != j);

    return -1; // Tabla llena
  }

  @Override
  public int funcionHashing(int unaClave) {
    String numeros = String.valueOf(unaClave);

    int res = 0;

    for (char numero : numeros.toCharArray()) {
      res = res + (int) numero;
    }

    return res % this.tabla.length;
  }
}
