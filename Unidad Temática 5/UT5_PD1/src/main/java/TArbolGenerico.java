public class TArbolGenerico<T> implements IArbolGenerico {
  private INodoGenerico raiz;

  @Override
  public int tamaño() {
    if (this.raiz == null) {
      return 0;
    } else {
      return this.raiz.tamaño();
    }
  }

  @Override
  public INodoGenerico getRaiz() {
    return this.raiz;
  }

  @Override
  public boolean insertar(Comparable unaEtiqueta, Comparable etiquetaPadre) {
    if (this.raiz == null) {
      this.raiz = new TNodoGenerico<>(unaEtiqueta);
      return true;
    }

    if (etiquetaPadre.compareTo("") == 0) {
      TNodoGenerico<String> nuevaRaiz = new TNodoGenerico<>(unaEtiqueta);
      nuevaRaiz.setPrimerHijo(this.raiz.getPrimerHijo());
      nuevaRaiz.setHermanoDerecho(this.raiz.getHermanoDerecho());
      this.raiz = nuevaRaiz;

      return true;
    }

    return raiz.insertar(unaEtiqueta, etiquetaPadre);
  }

  @Override
  public INodoGenerico buscar(Comparable unaEtiqueta) {
    if (this.raiz == null) {
      return null;
    } else {
      return this.raiz.buscar(unaEtiqueta);
    }
  }

  @Override
  public String listarIndentado() {
    if (this.raiz == null) {
      return "";
    } else {
      return this.raiz.listarIndentado(0);
    }
  }
}
