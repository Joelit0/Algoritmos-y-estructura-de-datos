public class Main {
  public static void main(String[] args) {
    // ==================================
    // Suc1 - Ejercicio Parte 1 y 2
    // ==================================

    String[] sucursalesEnArchivo = ManejadorArchivosGenerico.leerArchivo("src//main//java//suc1.txt");
    Lista<Sucursal> sucursales = new Lista<>();

    for (String sucursal : sucursalesEnArchivo) {
      sucursales.insertar(new Nodo<>(sucursal, new Sucursal(sucursal)));
    }

    // Imprimir las sucursales
    System.out.println(sucursales.imprimir());

    // Imprimir cantidad de sucursales
    System.out.println(sucursales.cantElementos()); // 107 elementos

    // Eliminar Chicago
    sucursales.eliminar("Chicago");
    System.out.println(sucursales.imprimir()); // La que le sigue a Hong Kong es Shenzhen

    // ==================================
    // Suc2 - Ejercicio Parte c
    // ==================================

    sucursalesEnArchivo = ManejadorArchivosGenerico.leerArchivo("src//main//java//suc2.txt");
    sucursales = new Lista<>();

    for (String sucursal : sucursalesEnArchivo) {
      sucursales.insertar(new Nodo<>(sucursal, new Sucursal(sucursal)));
    }

    System.out.println(sucursales.imprimir());

    // Eliminar Shenzen y Tokio
    sucursales.eliminar("Tokio");
    sucursales.eliminar("Shenzen"); // No pasa nada ya que mi método eliminar cubre este caso
    // Por ende, no queda ninguna ciudad en la lista

    System.out.println(sucursales.imprimir());

    // ==================================
    // Suc3 - Ejercicio Parte d
    // ==================================
    sucursalesEnArchivo = ManejadorArchivosGenerico.leerArchivo("src//main//java//suc3.txt");
    sucursales = new Lista<>();

    for (String sucursal : sucursalesEnArchivo) {
      sucursales.insertar(new Nodo<>(sucursal, new Sucursal(sucursal)));
    }

    System.out.println(sucursales.imprimir());
  }
}
