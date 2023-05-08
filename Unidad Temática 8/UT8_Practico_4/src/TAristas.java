
import java.util.Collection;
import java.util.Iterator;

import java.util.LinkedList;

public class TAristas extends LinkedList<TArista> {

    private final static String SEPARADOR_ELEMENTOS_IMPRESOS = "-";
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
        for(TArista aris : this){
            if (aris.getEtiquetaDestino().compareTo(etDestino) == 0){
                if (aris.getEtiquetaOrigen().compareTo(etOrigen) == 0){
                    //System.out.println(aris.getEtiquetaDestino());
                   // System.out.println(aris.getEtiquetaOrigen());
                    return aris;
                }
            }
        }
        return null;
    }

    /**
     * Busca la arista de menor costo que conecte a cualquier nodo de VerticesU
     * con cualquier otro de VerticesV y cuyo costo sea el minimo
     *
     * @param VerticesU - Lista de vertices U
     * @param VerticesV - Lista de vertices V
     * @return
     */
    public TArista buscarMin(Collection<Comparable> VerticesU, Collection<Comparable> VerticesV) {
       double costoMin = Double.MAX_VALUE;
       TArista tAMin = null;
       for (Comparable u : VerticesU){
           for(Comparable v : VerticesV){
               TArista tA = buscar(u, v);
               if (tA != null && tA.costo < costoMin){
                   tAMin = tA;
                   costoMin = tA.costo;
               }
           }
       }
       return tAMin;
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
        if (aristas == null) return;
        TArista tempArista;
        for (TArista ta : aristas) {
            this.add(ta);
            this.add(ta.aristaInversa());
        }
    }

}
