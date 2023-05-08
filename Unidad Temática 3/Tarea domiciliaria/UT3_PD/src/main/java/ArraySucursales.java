import java.util.ArrayList;

public class ArraySucursales {
  ArrayList<String> sucursales = new ArrayList();

  public void AgregarSucursal(String sucursal) { // TIEMPO EJECUCIÓN: O(1)
    this.sucursales.add(sucursal);
  }

  public String BuscarSucursal(String nombreSucursal) { // TIEMPO EJECUCIÓN: O(N)
    for (String sucursal : this.sucursales) {
      if (sucursal.equals(nombreSucursal)) { return sucursal; }
    }

    return "";
  }

  public boolean QuitarSucursal(String nombreSucursal) { // TIEMPO EJECUCIÓN: O(N)
    if (BuscarSucursal(nombreSucursal).equals(nombreSucursal)) {
      this.sucursales.remove(nombreSucursal);
      return true;
    } else {
      return false;
    }
  }

  public void ListarSucursales() { // TIEMPO EJECUCIÓN: O(N)
    for (String sucursal : this.sucursales) {
      System.out.println(sucursal);
    }
  }

  public int CantSucursales() { // TIEMPO EJECUCIÓN: O(N)
    int cantidadElementos = 0;

    for (String sucursal : this.sucursales) { cantidadElementos++; }

    return cantidadElementos;
  }

  public boolean isEmpty() { // TIEMPO EJECUCIÓN: O(N)
    return CantSucursales() == 0;
  }
}
