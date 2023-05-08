import java.io.Serializable;
import java.util.LinkedList;

public class TNodoTrie implements INodoTrie, Serializable {

    private static final int CANT_CHR_ABECEDARIO = 26;
    private TNodoTrie[] hijos;
    private boolean esPalabra;
    private Comparable etiqueta;

    public TNodoTrie(Comparable etiqueta) {
        hijos = new TNodoTrie[CANT_CHR_ABECEDARIO];
        esPalabra = false;
        this.etiqueta = etiqueta;
    }

    public Comparable getEtiqueta() {
        return etiqueta;
    }

    @Override
    public void insertar(String unaPalabra) {
        TNodoTrie nodo = this;
        for (int c = 0; c < unaPalabra.length(); c++) {
            int indice = unaPalabra.charAt(c) - 'a';

            if (nodo.hijos[indice] == null) {
                nodo.hijos[indice] = new TNodoTrie(unaPalabra.charAt(c));
            }
            nodo = nodo.hijos[indice];
        }
        nodo.esPalabra = true;
    }

    private void imprimir(String s, TNodoTrie nodo) {
        if (nodo != null) {
            if (nodo.esPalabra) {
                System.out.println(s);

            }
            for (int c = 0; c < CANT_CHR_ABECEDARIO; c++) {
                if (nodo.hijos[c] != null) {
                    imprimir(s + (char) (c + 'a'), nodo.hijos[c]);
                }
            }
        }
    }

    @Override
    public void imprimir() {
        imprimir("", this);
    }

    private TNodoTrie buscarNodoTrie(String s) {
        TNodoTrie nodo = this;
        for (int c = 0; c < s.length(); c++) {
            int indice = s.charAt(c) - 'a';
            if (nodo.hijos[indice] == null) {
                return null;
            }
            nodo = nodo.hijos[indice];
        }
        return nodo;
    }

    @Override
    public LinkedList predecir(String unPrefijo) {
        TNodoTrie nodo = this;
        LinkedList toReturn = new LinkedList();

        for (int c = 0; c < unPrefijo.length(); c++) {
            int indice = unPrefijo.charAt(c) - 'a';
            if (nodo.hijos[indice] == null) {
                return toReturn;
            }
            nodo = nodo.hijos[indice];
        }

        this.preOrden(unPrefijo, toReturn, nodo);

        return toReturn;
    }

    @Override
    public int buscar(String palabra) {
        int comparaciones = 0;
        TNodoTrie nodoActual = this;

        for (char letra : palabra.toCharArray()) {
            TNodoTrie unHijo = null;

            for (TNodoTrie hijo : nodoActual.hijos) {
                comparaciones++;

                if (hijo != null && hijo.getEtiqueta().compareTo(letra) == 0) {
                    unHijo = hijo;
                    break;
                }
            }

             if (unHijo == null) {
                return comparaciones;
            } else {
                nodoActual = unHijo;
            }
        }

        return comparaciones;
    }

    @Override
    public void preOrden(String s, LinkedList acumulador, TNodoTrie nodo) {
        if (nodo != null) {
            if (nodo.esPalabra) {
                acumulador.add(s);
            }
            for (int c = 0; c < CANT_CHR_ABECEDARIO; c++) {
                if (nodo.hijos[c] != null) {
                    preOrden(s + (char) (c + 'a'), acumulador, nodo.hijos[c]);
                }
            }
        }
    }
}
