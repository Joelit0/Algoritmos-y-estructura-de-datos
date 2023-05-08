public abstract class TElementoAB<T> implements IElementoAB<T> {

  private Comparable etiqueta;
  private TElementoAB hijoIzq;
  private TElementoAB hijoDer;
  private T datos;
  private int contador = 0;

  @SuppressWarnings("unchecked")
  public TElementoAB(Comparable unaEtiqueta, T unosDatos) {
    etiqueta = unaEtiqueta;
    datos = unosDatos;
  }

  @Override
  public Comparable getEtiqueta() {
    return this.etiqueta;
  }

  @Override
  public TElementoAB<T> getHijoIzq() {
    return this.hijoIzq;
  }

  @Override
  public TElementoAB<T> getHijoDer() {
    return this.hijoDer;
  }

  @Override
  public void setHijoIzq(TElementoAB<T> elemento) {
    this.hijoIzq = elemento;
  }

  @Override
  public void setHijoDer(TElementoAB<T> elemento) {
    this.hijoDer = elemento;
  }

  @Override
  public TElementoAB<T> buscar(Comparable unaEtiqueta) {
    TElementoAB<T> result = null;

    if (this.getEtiqueta().compareTo(unaEtiqueta) == 0) {
      result = this;
    } else {
      if (this.etiqueta.compareTo(unaEtiqueta) > 0) {
        result = (this.hijoIzq != null) ? this.hijoIzq.buscar(unaEtiqueta) : (null);
      } else {
        result = (this.hijoDer != null) ? this.hijoDer.buscar(unaEtiqueta) : (null);
      }
    }

    return result;
  }

  @Override
  public String preOrden() {
    String resultado = "- " + this.getEtiqueta();
    if (getHijoIzq() != null) {
      resultado = resultado + getHijoIzq().preOrden();
    }
    if (getHijoDer() != null) {
      resultado = resultado + getHijoDer().preOrden();
    }
    return resultado;
  }

  @Override
  public String inOrden() {
    String resultado = "";

    if (getHijoIzq() != null) {
      resultado = resultado + getHijoIzq().inOrden();
    }

    resultado = resultado + "- " + this.getEtiqueta();

    if (getHijoDer() != null) {
      resultado = resultado + getHijoDer().inOrden();
    }

    return resultado;
  }

  @Override
  public String postOrden() {
    String resultado = "";

    if (getHijoIzq() != null) {
      resultado = resultado + getHijoIzq().postOrden();
    }
    if (getHijoDer() != null) {
      resultado = resultado + getHijoDer().postOrden();
    }
    return resultado + "- " + this.getEtiqueta();
  }

  @Override
  public T getDatos() {
    throw new java.lang.UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
  }

  @Override
  public TElementoAB eliminar(Comparable unaEtiqueta) {
    throw new java.lang.UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
  }

  public int getContador() {
    return this.contador;
  }

  @Override
  public boolean insertar(TElementoAB<T> elemento) {
    this.contador += 1;

    if (this.etiqueta == elemento.etiqueta) {
      return true;
    }

    if (elemento.etiqueta.compareTo(this.etiqueta) < 0) {
      if (this.hijoIzq == null) {
        this.hijoIzq = elemento;
        return true;
      } else {
        hijoIzq.insertar(elemento);
        return true;
      }
    } else {
      if (this.hijoDer == null) {
        this.hijoDer = elemento;
        return true;
      } else {
        hijoDer.insertar(elemento);
        return true;
      }
    }
  }
}