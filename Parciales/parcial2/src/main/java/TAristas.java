
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

import java.util.LinkedList;

public class TAristas extends LinkedList<TArista> {

    private final static String SEPARADOR_ELEMENTOS_IMPRESOS = "-";
    private Comparator<TArista> comparador;
    //private Collection<TArista> aristas  = new LinkedList<TArista>();

    /**
     * Busca dentro de la lista de aristas una arista que conecte a etOrigen con
     * etDestino
     *
     * @param etOrigen
     * @param etDestino
     * @return
     */
    public TArista buscar(Comparable etOrigen, Comparable etDestino) {
        TArista tempArista;
        for (TArista laa : this) {
            if ((laa.getEtiquetaOrigen().equals(etOrigen)) && laa.getEtiquetaDestino().equals(etDestino)) {
                return laa;
            }
        }

        return null;
    }
    
    public TArista buscarMin(Collection<Comparable> VerticesU, Collection<Comparable> VerticesV) {
        TArista minArista = null;
        int minCosto = Integer.MAX_VALUE;
        for (Comparable verticeU : VerticesU) {
            for (Comparable verticeV : VerticesV) {
                TArista arista = buscar(verticeU, verticeV);
                if (arista != null && arista.getCosto() < minCosto) {
                    minArista = arista;
                    minCosto = (int)arista.getCosto();
                }
            }
        }
        return minArista;
    }

    public String imprimirEtiquetas() {
        if (this.isEmpty()) {
            return null;
        }
        StringBuilder salida = new StringBuilder();
        //TODO: Completar codigo que imprime todas las aristas de la lista en el siguiente formato:
        //ORIGEN - DESTINO - COSTO
        return salida.toString();
    }

    void insertarAmbosSentidos(Collection<TArista> aristas) {
        TArista tempArista;
        for (TArista ta : aristas) {
            this.add(ta);
            this.add(ta.aristaInversa());
        }
    }
    
    public void ordenar(){
        Collections.sort(this,comparador);
    }

}
