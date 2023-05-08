public class Main {
  public static void main(String[] args) {
    Almacen almacen = new Almacen(
      "Calle Falsa",
      "094234425",
      "Almacen de pepe"
    );

    System.out.println("==================================");
    System.out.println("         Agregar productos        ");
    System.out.println("==================================");
    String[] productos = ManejadorArchivosGenerico.leerArchivo("src//main/java//altas.txt");

    for (String producto : productos) {
      String[] data = producto.split(",");

      String etiqueta = data[0];
      String nombre = data[1];
      Integer precio = Integer.parseInt(data[2]);
      Integer stock = Integer.parseInt(data[3]);

      // Inserto nuevo producto
      almacen.insertarProducto(new Producto(etiqueta, precio, stock, nombre));
    }

    // Imprimo el total del stock
    System.out.println(almacen.getTotalStock());

    System.out.println("==================================");
    System.out.println("              Pruebas             ");
    System.out.println("==================================");

    // Agrego stock a un producto
    System.out.println(almacen.agregarStock("1000001", 2));
    System.out.println(almacen.getTotalStock());

    // Resto stock a un producto
    System.out.println(almacen.restarStock("1000001", 2));
    System.out.println(almacen.getTotalStock());

    System.out.println("==================================");
    System.out.println("           Agregar ventas         ");
    System.out.println("==================================");

    String[] ventas = ManejadorArchivosGenerico.leerArchivo("src//main/java//ventas.txt");

    for (String venta : ventas) {
      String[] data = venta.split(",");

      String etiqueta = data[0];
      Integer CantidadVender = Integer.parseInt(data[1]);

      // Disminuyo stock
      almacen.restarStock(etiqueta, CantidadVender);
    }

    // Imprimo el total del stock
    System.out.println(almacen.getTotalStock());
  }
}
