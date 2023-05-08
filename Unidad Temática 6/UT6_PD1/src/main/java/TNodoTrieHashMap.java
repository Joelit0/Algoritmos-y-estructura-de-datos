import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedList;

public class TNodoTrieHashMap implements INodoTrieHashMap, Serializable {
    private HashMap<String, TNodoTrieHashMap> hijos;
    private boolean esPalabra;

    public TNodoTrieHashMap() {
        this.hijos = new HashMap<>();
        esPalabra = false;
    }

    @Override
    public void insertar(String unaPalabra) {
        TNodoTrieHashMap nodo = this;

        for (char letra : unaPalabra.toCharArray()) {
            String letraToString = String.valueOf(letra);

            if (nodo.hijos.get(letraToString) == null) {
                nodo.hijos.put(letraToString, new TNodoTrieHashMap());
            }

            nodo = nodo.hijos.get(letraToString);
        }

        nodo.esPalabra = true;
    }

    private void imprimir(String s, TNodoTrieHashMap nodo) {
        if (nodo != null) {
            if (nodo.esPalabra) {
                System.out.println(s);
            }

            for (String key : nodo.hijos.keySet()) {
                    imprimir(s + key, nodo.hijos.get(key));
            }
        }
    }

    @Override
    public void imprimir() {

        imprimir("", this);
    }

    @Override
    public LinkedList predecir(String unPrefijo) {
        TNodoTrieHashMap nodo = this;
        LinkedList toReturn = new LinkedList();

        for (char letra : unPrefijo.toCharArray()) {
            String letraToString = String.valueOf(letra);

            if (nodo.hijos.get(letraToString) == null) {
                return toReturn;
            }

            nodo = nodo.hijos.get(letraToString);
        }

        this.preOrden(unPrefijo, toReturn, nodo);

        return toReturn;
    }

    @Override
    public int buscar(String palabra) {
        int comparaciones = 0;
        TNodoTrieHashMap nodoActual = this;

        for (char letra : palabra.toCharArray()) {
            TNodoTrieHashMap unHijo = nodoActual.hijos.get(letra);

            if (unHijo == null) {
                return comparaciones;
            } else {
                nodoActual = unHijo;
            }
        }

        return comparaciones;
    }

    @Override
    public void preOrden(String s, LinkedList acumulador, TNodoTrieHashMap nodo) {
        if (nodo != null) {
            if (nodo.esPalabra) {
                acumulador.add(s);
            }

            for (String key : nodo.hijos.keySet()) {
                preOrden(s + key, acumulador, nodo.hijos.get(key));
            }
        }
    }
}
