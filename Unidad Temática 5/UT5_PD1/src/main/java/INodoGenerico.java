public interface INodoGenerico<T> {
  int tamaño();

  boolean insertar(Comparable unaEtiqueta, Comparable etiquetaPadre);

  INodoGenerico<T> buscar(Comparable unaEtiqueta);

  String listarIndentado(int indent);

  Comparable getEtiqueta();

  void setEtiqueta(Comparable nuevaEtiqueta);

  INodoGenerico<T> getPrimerHijo();

  void setPrimerHijo(INodoGenerico<T> nuevoHijo);

  INodoGenerico<T> getHermanoDerecho();

  void setHermanoDerecho(INodoGenerico<T> nuevoHermano);
}
