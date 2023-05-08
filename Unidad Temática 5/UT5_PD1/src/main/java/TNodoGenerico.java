public class TNodoGenerico<T> implements INodoGenerico {
  private Comparable etiqueta;
  private INodoGenerico primerHijo;
  private INodoGenerico hermanoDerecho;

  public TNodoGenerico(Comparable unaEtiqueta) {
    this.etiqueta = unaEtiqueta;
  }

  @Override
  public int tamaño() {
    int cantNodos = 1;

    if (this.primerHijo != null) {
      cantNodos += this.primerHijo.tamaño();
    }

    if (this.hermanoDerecho != null) {
      cantNodos += this.hermanoDerecho.tamaño();
    }

    return cantNodos;
  }

  @Override
  public Comparable getEtiqueta() {
    return this.etiqueta;
  }

  public void setEtiqueta(Comparable nuevaEtiqueta) {
    this.etiqueta = nuevaEtiqueta;
  }

  @Override
  public INodoGenerico getPrimerHijo() {
    return this.primerHijo;
  }

  @Override
  public void setPrimerHijo(INodoGenerico nuevoHijo) {
    this.primerHijo = nuevoHijo;
  }

  @Override
  public INodoGenerico getHermanoDerecho() {
    return this.hermanoDerecho;
  }

  @Override
  public void setHermanoDerecho(INodoGenerico nuevoHermano) {
    this.hermanoDerecho = nuevoHermano;
  }

  @Override
  public boolean insertar(Comparable unaEtiqueta, Comparable etiquetaPadre) {
    if (etiquetaPadre.compareTo(this.etiqueta) == 0) {
      if (this.primerHijo != null) {
        INodoGenerico<String> temp = this.primerHijo;

        while (temp.getHermanoDerecho() != null) {
          temp = temp.getHermanoDerecho();
        }
        
        temp.setHermanoDerecho(new TNodoGenerico<>(unaEtiqueta));
      } else {
        this.primerHijo = new TNodoGenerico<>(unaEtiqueta);
      }

      return true;
    }

    boolean encontrado = false;

    if (this.hermanoDerecho != null) {
      encontrado = this.hermanoDerecho.insertar(unaEtiqueta, etiquetaPadre);
    }

    if (this.primerHijo != null && !encontrado) {
      encontrado = this.primerHijo.insertar(unaEtiqueta, etiquetaPadre);
    }

    return encontrado;
  }

  @Override
  public INodoGenerico buscar(Comparable unaEtiqueta) {
    if (this.etiqueta.compareTo(unaEtiqueta) == 0) {
      return this;
    } else {
      INodoGenerico<T> encontrado = null;

      if (this.primerHijo != null) {
        encontrado = this.primerHijo.buscar(unaEtiqueta);
      }

      if (this.hermanoDerecho != null && encontrado == null) {
        encontrado = this.hermanoDerecho.buscar(unaEtiqueta);
      }

      return encontrado;
    }
  }

  @Override
  public String listarIndentado(int tabs) {
    StringBuilder resultado = new StringBuilder("");

    resultado.append(repeat("\t", tabs) + this.getEtiqueta() + "\n");

    if (this.primerHijo != null) {
      resultado.append(this.primerHijo.listarIndentado(tabs + 1));
    }

    if (this.hermanoDerecho != null) {
      resultado.append(this.hermanoDerecho.listarIndentado(tabs));
    }

    return resultado.toString();
  }

  private String repeat(String string, int cant) {
    if (cant == 0) {
      return "";
    } else {
      String resultado = "";

      for (int i = 1; i <= cant; i++) {
        resultado += string;
      }

      System.out.println(resultado);
      return resultado;
    }
  }
}
