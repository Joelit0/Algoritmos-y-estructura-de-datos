import java.lang.reflect.Array;
import java.util.ArrayList;

public class Main {
  public static void main(String[] args) {

    // Comparando esta solución con la del PD6, concluyo en que ambas
    // utilizan aproximadamente la misma cantidad de recursos en cuanto a
    // tiempo y memoria. Esta solución me pareciió mucho más fácil de
    // implementar y mucho más legible

    // En cuanto al consumo de memoria, la otra solución requería más
    // ya que una lista encadenada ocupa más que un ArrayList(si los objetos
    // que tiene como elementos son los mismos) tal y como hablamos en los
    // anteriores PD's. Considero que esta solución
    // es más recomendable en cuanto a memoria nos referimos.

    ArraySucursales sucursales = new ArraySucursales();

    String[] sucursalesALeer = ManejadorArchivosGenerico.leerArchivo("src//main//java//sucursales.txt");

    for (String sucursalLeida : sucursalesALeer) {
      sucursales.AgregarSucursal(sucursalLeida);
    }

    // Cantidad sucursales
    System.out.println(sucursales.CantSucursales());

    // Buscar sucursal
    System.out.println(sucursales.BuscarSucursal("Montevideo"));

    // Eliminar sucursal
    System.out.println(sucursales.QuitarSucursal("Montevideo"));

    // Listar sucursalesx
    sucursales.ListarSucursales();

    // Verificar si está vacío
    System.out.println(sucursales.isEmpty());
  }
}
