public interface IArbolGenerico<T> {
  INodoGenerico<T> getRaiz();

  boolean insertar(Comparable unaEtiqueta, Comparable<String> etiquetaPadre);

  INodoGenerico<T> buscar(Comparable unaEtiqueta);

  String listarIndentado();

  int tamaño();
}
