public class Producto implements IProducto {
  private Comparable etiqueta;
  private Integer precio;
  private Integer stock;
  private String nombre;

  public Producto(Comparable etiqueta, Integer precio, Integer stock, String nombre) {
    this.etiqueta = etiqueta;
    this.precio = precio;
    this.stock = stock;
    this.nombre = nombre;
  }

  @Override
  public Comparable getEtiqueta() {
    return this.etiqueta;
  }

  @Override
  public Integer getPrecio() {
    return this.precio;
  }

  @Override
  public void setPrecio(Integer precio) {
    this.precio = precio;
  }

  @Override
  public Integer getStock() {
    return this.stock;
  }

  @Override
  public void setStock(Integer stock) {
    this.stock = stock;
  }

  @Override
  public String getNombre() {
    return this.nombre;
  }

  @Override
  public void setNombre(String nombre) {
    this.nombre = nombre;
  }
}
