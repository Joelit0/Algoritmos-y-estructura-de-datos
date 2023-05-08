import java.util.*;

public class TGrafoDirigido implements IGrafoDirigido {

    public Map<Comparable, TVertice> vertices; //lista de v�rtices del grafo.-

    public TGrafoDirigido(Collection<TVertice> vertices, Collection<TArista> aristas) {
        this.vertices = new HashMap<>();
        for (TVertice vertice : vertices) {
            insertarVertice(vertice.getEtiqueta());
        }
        for (TArista arista : aristas) {
            insertarAdyacencia(arista.getEtiquetaOrigen(),
                    arista.getEtiquetaDestino(), arista.getCosto());
        }
    }

  

    /**
     * M�todo encargado de verificar la existencia de una adyacencia. Las
     * etiquetas pasadas por par�metro deben ser v�lidas.
     *
     * @return True si existe la adyacencia, false en caso contrario
     */
    public boolean existeAdyacencia(Comparable etiquetaOrigen, Comparable etiquetaDestino) {
        TVertice vertOrigen = buscarVertice(etiquetaOrigen);
        TVertice vertDestino = buscarVertice(etiquetaDestino);
        if ((vertOrigen != null) && (vertDestino != null)) {
            return vertOrigen.buscarAdyacencia(vertDestino) != null;
        }
        return false;
    }

    /**
     * M�todo encargado de verificar la existencia de un v�rtice dentro del
     * grafo.-
     *
     * La etiqueta especificada como par�metro debe ser v�lida.
     *
     * @param unaEtiqueta Etiqueta del v�rtice a buscar.-
     * @return True si existe el vertice con la etiqueta indicada, false en caso
     * contrario
     */
    public boolean existeVertice(Comparable unaEtiqueta) {
        return vertices.get(unaEtiqueta) != null;
    }

    /**
     * M�todo encargado de  buscar un v�rtice dentro del grafo.-
     *
     * La etiqueta especificada como par�metro debe ser v�lida.
     *
     * @param unaEtiqueta Etiqueta del v�rtice a buscar.-
     * @return El vertice encontrado. En caso de no existir, retorna nulo.
     */
    private TVertice buscarVertice(Comparable unaEtiqueta) {
        return vertices.get(unaEtiqueta);
    }

    /**
     * M�todo encargado de insertar una adyacencia en el grafo (con un cierto
     * costo), dado su vertice origen y destino.- Para que la adyacencia sea
     * v�lida, se deben cumplir los siguientes casos: 1) Las etiquetas pasadas
     * por par�metros son v�lidas.- 2) Los v�rtices (origen y destino) existen
     * dentro del grafo.- 3) No es posible ingresar una adyacencia ya existente
     * (miso origen y mismo destino, aunque el costo sea diferente).- 4) El
     * costo debe ser mayor que 0.
     *
     * @return True si se pudo insertar la adyacencia, false en caso contrario
     */
    public boolean insertarAdyacencia(Comparable etiquetaOrigen, Comparable etiquetaDestino, Double costo) {
        if ((etiquetaOrigen != null) && (etiquetaDestino != null) && (costo != null)) {
            TVertice vertOrigen = buscarVertice(etiquetaOrigen);
            TVertice vertDestino = buscarVertice(etiquetaDestino);
            if ((vertOrigen != null) && (vertDestino != null)) {
                return vertOrigen.insertarAdyacencia(costo, vertDestino);
            }
        }
        return false;
    }

    /**
     * M�todo encargado de insertar un v�rtice en el grafo.
     *
     * No pueden ingresarse v�rtices con la misma etiqueta. La etiqueta
     * especificada como par�metro debe ser v�lida.
     *
     * @param unaEtiqueta Etiqueta del v�rtice a ingresar.
     * @return True si se pudo insertar el vertice, false en caso contrario
     */
    public boolean insertarVertice(Comparable unaEtiqueta) {
        if ((unaEtiqueta != null) && (!existeVertice(unaEtiqueta))) {
            TVertice vert = new TVertice(unaEtiqueta);
            vertices.put(unaEtiqueta, vert);
            return vertices.containsKey(unaEtiqueta);
        }
        return false;
    }

    @Override
    public boolean esPosibleLlegarATodos(Comparable aeropuertoOrigen) {
        TVertice origen = this.vertices.get(aeropuertoOrigen);

        desvisitarVertices();
      
        if (origen != null) {
            Collection<TVertice> lista = new LinkedList<>();
            origen.bpf(lista);

            desvisitarVertices();

            for (TVertice vertice: this.vertices.values()){
                if (!lista.contains(vertice)){
                    return false;
                }
            }

            return true;
        }

        return false;
    }

    public void desvisitarVertices() {
        for (TVertice vertice : this.vertices.values()) {
            vertice.setVisitado(false);
        }
    }

    public Object[] getEtiquetasOrdenado() {
        TreeMap<Comparable, TVertice> mapOrdenado = new TreeMap<>(this.vertices);
        return mapOrdenado.keySet().toArray();
    }
}
