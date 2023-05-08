import java.util.LinkedList;

public interface INodoTrie {

    int buscar(String s); // devuelve la cantidad de comparaciones!

    void imprimir();

    void insertar(String unaPalabra);

    public LinkedList predecir(String unPrefijo);

    public void preOrden(String s, LinkedList acumulador, TNodoTrie nodo);

}
