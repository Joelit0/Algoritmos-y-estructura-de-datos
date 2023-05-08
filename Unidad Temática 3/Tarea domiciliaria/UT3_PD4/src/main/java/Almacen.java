public class Almacen implements  IAlmacen{
  private String direccion;
  private String telefono;
  private String nombre;
  private Lista<IProducto> productos = new Lista<>();

  public Almacen(String direccion, String telefono, String nombre) {
    this.direccion = direccion;
    this.telefono = telefono;
    this.nombre = nombre;
  }

  @Override
  public String getDireccion() {
    return this.direccion;
  }

  @Override
  public void setDireccion(String direccion) {
    this.direccion = direccion;
  }

  @Override
  public String getTelefono() {
    return this.telefono;
  }

  @Override
  public void setTelefono(String telefono) {
    this.telefono = telefono;
  }

  @Override
  public String getNombre() {
    return this.nombre;
  }

  @Override
  public Lista getListaProductos() {
    return this.productos;
  }

  @Override
  public void insertarProducto(Producto unProducto) {
    this.productos.insertar(new Nodo<>(unProducto.getEtiqueta(), unProducto));
  }

  @Override
  public boolean eliminar(Comparable clave) {
    return this.productos.eliminar(clave);
  }

  @Override
  public String imprimirProductos() {
    return this.productos.imprimir();
  }

  @Override
  public String imprimirSeparador(String separador) {
    return null;
  }

  @Override
  public Boolean agregarStock(Comparable clave, Integer cantidad) {
    INodo<IProducto> nodo = this.productos.buscar(clave);

    if (nodo != null) {
      IProducto producto = nodo.getDato();
      producto.setStock(producto.getStock() + cantidad);

      return true;
    } else {
      return false;
    }
  }

  @Override
  public Integer restarStock(Comparable clave, Integer cantidad) {
    INodo<IProducto> nodo = this.productos.buscar(clave);

    if (nodo != null) {
      IProducto producto = nodo.getDato();
      producto.setStock(producto.getStock() - cantidad);

      return producto.getStock();
    }

    return 0;
  }

  @Override
  public IProducto buscarPorCodigo(Comparable clave) {
    INodo<IProducto> producto = this.productos.buscar(clave);

    if (producto == null) { return null; }

    return producto.getDato();
  }

  public boolean eliminarProducto(Comparable clave) {
    return this.productos.eliminar(clave);
  }

  public Integer getExistencias(Comparable clave) {
    INodo<IProducto> producto = this.productos.buscar(clave);

    if (producto == null) { return null; }

    return producto.getDato().getStock();
  }

  @Override
  public void listarOrdenadoPorNombre() {
  }

  @Override
  public Producto buscarPorDescripcion(String descripcion) {
    return null;
  }

  @Override
  public int cantidadProductos() {
    return this.productos.cantElementos();
  }

  @Override
  public int getTotalStock() {
    INodo<IProducto> temp = this.productos.getPrimero();
    int totalStock = 0;

    while (temp != null) {
      totalStock += temp.getDato().getStock() * temp.getDato().getPrecio();
      temp = temp.getSiguiente();
    }

    return totalStock;
  }
}
