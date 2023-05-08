public class TArbolBB<T> implements IArbolBB<T> {

  private TElementoAB<T> raiz;

  /**
   * Separador utilizado entre elemento y elemento al imprimir la lista
   */
  public static final String SEPARADOR_ELEMENTOS_IMPRESOS = "-";

  public TArbolBB() {
    raiz = null;
  }

  @Override
  public boolean insertar(TElementoAB<T> unElemento) {
    if (this.raiz == null) {
      this.raiz = unElemento;
      return true;
    } else {
      return this.raiz.insertar(unElemento);
    }
  }

  @Override
  public TElementoAB buscar(Comparable unaEtiqueta) {
    if (raiz == null){
      return null;
    }
    else{
      return raiz.buscar(unaEtiqueta);
    }
  }

  @Override
  public String preOrden() {
    if (raiz == null) {
      return null;
    } else {
      return raiz.preOrden();
    }
  }

  @Override
  public String inOrden() {
    if (raiz == null) {
      return null;
    } else {
      return raiz.inOrden();
    }
  }

  @Override
  public String postOrden() {
    if (raiz == null) {
      return null;
    } else {
      return raiz.postOrden();
    }
  }

  @Override
  public void eliminar(Comparable unaEtiqueta) {
    throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
  }
}