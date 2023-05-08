/*
 * NO LICENCE 
 * Author: Ing. Nicolás Navarro Gutérrez
 */

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;


/**
 *
 * @author nnavarro
 */
public class TGrafoRedDatos extends TGrafoNoDirigido implements ITGrafoRedDatos
{
    
    public TGrafoRedDatos(Collection<TVertice> vertices, Collection<TArista> aristas)
    {
        super(vertices, aristas);
    }

    @Override
    public LinkedList<LinkedList<TVertice>> componentesConexos()
    {
        LinkedList<TVertice> visitados = new LinkedList<>();
        for (TVertice vertice : getVertices().values()) {
            if (!vertice.getVisitado()) {
                vertice.bpf(visitados);
            }
        }
        TGrafoDirigido gdi = invertirGrafo();
        LinkedList componentes = new LinkedList();
        for (Iterator<TVertice> it = visitados.descendingIterator(); it.hasNext();) {
            TVertice origen = gdi.getVertices().get(it.next().getEtiqueta());
            if (!origen.getVisitado()) {
                LinkedList<TVertice> abarcador = new LinkedList<>();
                origen.bpf(abarcador);
                componentes.add(new LinkedList<>(abarcador));
            }
        }
        return componentes;
    }

    @Override
    public LinkedList<String> saltosDesde(Comparable origen)
    {
        return null;
    }
    

    public Collection<TVertice> bea(Comparable etiquetaOrigen) {
        Collection<TVertice> salida = new LinkedList<>();
        TVertice verticeOrigen = this.getVertices().get(etiquetaOrigen);
        if (verticeOrigen != null) {
            desvisitarVertices();
            //verticeOrigen.bea(salida);
        }
        return salida;
    }
}