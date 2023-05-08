import java.util.LinkedList;

public interface INodoTrieHashMap {

    int buscar(String s); // devuelve la cantidad de comparaciones!

    void imprimir();

    void insertar(String unaPalabra);

    LinkedList predecir(String unPrefijo);

    void preOrden(String s, LinkedList acumulador, TNodoTrieHashMap nodo);
}
