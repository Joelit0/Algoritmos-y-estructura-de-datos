
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.TreeMap;

public class TGrafoDirigido implements IGrafoDirigido {

    private final Map<Comparable, TVertice> vertices; //lista de vertices del grafo.-
    

    public TGrafoDirigido(Collection<TVertice> vertices, Collection<TArista> aristas) {
        this.vertices = new HashMap<>();
        for (TVertice vertice : vertices) {
            insertarVertice(vertice.getEtiqueta());
        }
        for (TArista arista : aristas) {
            insertarArista(arista);
        }
    }

    /**
     * Metodo encargado de eliminar una arista dada por un origen y destino. En
     * caso de no existir la adyacencia, retorna falso. En caso de que las
     * etiquetas sean invalidas, retorna falso.
     *
     * @param nomVerticeOrigen
     * @param nomVerticeDestino
     * @return
     */
    @Override
    public boolean eliminarArista(Comparable nomVerticeOrigen, Comparable nomVerticeDestino) {
        if ((nomVerticeOrigen != null) && (nomVerticeDestino != null)) {
            TVertice vertOrigen = buscarVertice(nomVerticeOrigen);
            if (vertOrigen != null) {
                return vertOrigen.eliminarAdyacencia(nomVerticeDestino);
            }
        }
        return false;
    }

    /**
     * Metodo encargado de eliminar un vertice en el grafo. En caso de no
     * existir el v�rtice, retorna falso. En caso de que la etiqueta sea
     * inv�lida, retorna false.
     *
     * @param nombreVertice
     * @return
     */
    @Override
    public boolean eliminarVertice(Comparable nombreVertice) {
        if (nombreVertice != null) {
            getVertices().remove(nombreVertice);
            return getVertices().containsKey(nombreVertice);
        }
        return false;
    }

    /**
     * Metodo encargado de verificar la existencia de una arista. Las etiquetas
     * pasadas por par�metro deben ser v�lidas.
     *
     * @param etiquetaOrigen
     * @param etiquetaDestino
     * @return True si existe la adyacencia, false en caso contrario
     */
    @Override
    public boolean existeArista(Comparable etiquetaOrigen, Comparable etiquetaDestino) {
        TVertice vertOrigen = buscarVertice(etiquetaOrigen);
        TVertice vertDestino = buscarVertice(etiquetaDestino);
        if ((vertOrigen != null) && (vertDestino != null)) {
            return vertOrigen.buscarAdyacencia(vertDestino) != null;
        }
        return false;
    }

    /**
     * Metodo encargado de verificar la existencia de un vertice dentro del
     * grafo.-
     *
     * La etiqueta especificada como par�metro debe ser v�lida.
     *
     * @param unaEtiqueta Etiqueta del v�rtice a buscar.-
     * @return True si existe el vertice con la etiqueta indicada, false en caso
     * contrario
     */
    @Override
    public boolean existeVertice(Comparable unaEtiqueta) {
        return getVertices().get(unaEtiqueta) != null;
    }

    /**
     * Metodo encargado de verificar buscar un vertice dentro del grafo.-
     *
     * La etiqueta especificada como parametro debe ser valida.
     *
     * @param unaEtiqueta Etiqueta del v�rtice a buscar.-
     * @return El vertice encontrado. En caso de no existir, retorna nulo.
     */
    private TVertice buscarVertice(Comparable unaEtiqueta) {
        return getVertices().get(unaEtiqueta);
    }

    /**
     * Matodo encargado de insertar una arista en el grafo (con un cierto
     * costo), dado su vertice origen y destino.- Para que la arista sea valida,
     * se deben cumplir los siguientes casos: 1) Las etiquetas pasadas por
     * parametros son v�lidas.- 2) Los vertices (origen y destino) existen
     * dentro del grafo.- 3) No es posible ingresar una arista ya existente
     * (miso origen y mismo destino, aunque el costo sea diferente).- 4) El
     * costo debe ser mayor que 0.
     *
     * @param arista
     * @return True si se pudo insertar la adyacencia, false en caso contrario
     */
    @Override
    public boolean insertarArista(TArista arista) {
        boolean tempbool = false;
        if ((arista.getEtiquetaOrigen() != null) && (arista.getEtiquetaDestino() != null)) {
            TVertice vertOrigen = buscarVertice(arista.getEtiquetaOrigen());
            TVertice vertDestino = buscarVertice(arista.getEtiquetaDestino());
            tempbool = (vertOrigen != null) && (vertDestino != null);
            if (tempbool) {
                //getLasAristas().add(arista);
                return vertOrigen.insertarAdyacencia(arista.getCosto(), vertDestino);
            }

        }
        return false;
    }

    /**
     * Metodo encargado de insertar un vertice en el grafo.
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
            getVertices().put(unaEtiqueta, vert);
            return getVertices().containsKey(unaEtiqueta);
        }
        return false;
    }

    @Override
    public boolean insertarVertice(TVertice vertice) {
        Comparable unaEtiqueta = vertice.getEtiqueta();
        if ((unaEtiqueta != null) && (!existeVertice(unaEtiqueta))) {
            getVertices().put(unaEtiqueta, vertice);
            return getVertices().containsKey(unaEtiqueta);
        }
        return false;
    }


    public Object[] getEtiquetasOrdenado() {
        TreeMap<Comparable, TVertice> mapOrdenado = new TreeMap<>(this.getVertices());
        return mapOrdenado.keySet().toArray();
    }

    @Override
    public void desvisitarVertices() {
        for (TVertice vertice : this.vertices.values()) {
            vertice.setVisitado(false);
        }
    }

    /**
     * @return the vertices
     */
    @Override
    public Map<Comparable, TVertice> getVertices() {
        return vertices;
    }

    @Override
    public Collection<TVertice> bpf(TVertice vertice) {
        Collection<TVertice> collection = new HashSet<>();
        if (vertice.getVisitado() == false){
        vertice.bpf(collection);
        }
        return collection;
    }

    

    @Override
    public boolean tieneCiclo(TCamino camino) {
        LinkedList<Comparable> verticesCamino;
        verticesCamino = (LinkedList<Comparable>) camino.getOtrosVertices();
        verticesCamino.add(camino.getOrigen().getEtiqueta());
        boolean resultado;
        resultado = camino.getOrigen().tieneCiclo(verticesCamino);
        return resultado;
    }

    @Override
    public Collection<TVertice> bpf() {
        Collection<TVertice> collection = new HashSet<>();

        for (TVertice vertice : this.vertices.values()) {
            if (!vertice.getVisitado()) {
                vertice.bpf(collection);
            }
        }

        return collection;
    }

    @Override
    public Collection<TVertice> bpf(Comparable etiquetaOrigen) {
        Collection<TVertice> collection = new HashSet<>();
        TVertice vertice = buscarVertice(etiquetaOrigen);
        if (vertice.getVisitado() == false){
        vertice.bpf(collection);
        }
        return collection;
    }

    @Override
    public Comparable centroDelGrafo(Double[][] mfloyd) {
        TVertice resultado = null;
        Comparable menor_excentricidad = 100000.0;
        int contador = 0;
        for (final TVertice t : this.vertices.values()) {
            Comparable excentricidad = this.obtenerExcentricidad(t.getEtiqueta(), contador, mfloyd);
            if (excentricidad.compareTo(menor_excentricidad) < 0.0) {
                menor_excentricidad = excentricidad;
                resultado = t;
            }
            contador ++;
        }
        if (resultado != null) {
            return resultado.getEtiqueta();
        }
        return -1;
    }

    @Override
    public Double[][] floyd() {
        final Double[][] C = UtilGrafos.obtenerMatrizCostos(vertices);
        final Double[][] A = new Double[C.length][C.length];
        for (int i = 0; i < C.length; i++) {
            for (int j = 0; j < C.length; j++) {
                A[i][j] = C[i][j];
            }
        }
        for (int i = 0; i < C.length; i++) {
            A[i][i] = 0.0;
        }
        for (int k = 0; k < C.length; k++) {
            for (int i = 0; i < C.length; i++) {
                for (int j = 0; j < C.length; j++) {
                        if (A[i][k] + A[k][j] < A[i][j] && A[i][k] + A[k][j] >= 0) {
                        A[i][j] = A[i][k] + A[k][j];
                    }
                }
            }
        }
        return A;
    }

    @Override
    public Comparable obtenerExcentricidad(final Comparable etiquetaVertice, int numeroVertice, Double[][] mfloyd) {
        final TVertice vertice = this.buscarVertice(etiquetaVertice);
        if (vertice != null) {
            int v = numeroVertice;
            //final LinkedList<TAdyacencia> adyacentes = vertice.getAdyacentes();
            Double mas_lejano = 0.0;
            for (int a = 0 ; a < vertices.size() ; a++) {
                if (mfloyd [v][a] > mas_lejano) {
                    mas_lejano = mfloyd [v][a];
                }
            }
            return mas_lejano;
        }
        return null;
    }

    @Override
    public boolean[][] warshall() {
        final int cantidad = vertices.size();
        final Double[][] C = UtilGrafos.obtenerMatrizCostos(vertices);
        final boolean[][] A = new boolean[cantidad][cantidad];
        for (int i = 0; i < cantidad; i++) {
            for (int j = 0; j < cantidad; j++) {
                if (i != j) {
                    A[i][j] = C[i][j] < Double.MAX_VALUE;
                }
            }
        }
        for (int k = 0; k < cantidad; k++) {
            for (int i = 0; i < cantidad; i++) {
                if (k != i) {
                    for (int j = 0; j < cantidad; j++) {
                        if (i != j && !A[i][j]) {
                            A[i][j] = A[i][k] && A[k][j];
                        }
                    }
                }
            }
        }
        return A;
    }

    @Override
    public TCaminos todosLosCaminos(Comparable etiquetaOrigen, Comparable etiquetaDestino) {
        desvisitarVertices();
        TCaminos todosLosCaminos = new TCaminos();
        TVertice v = buscarVertice(etiquetaOrigen);
        if(v != null){
            TCamino caminoPrevio = new TCamino(v);
            v.todosLosCaminos(etiquetaDestino, caminoPrevio, todosLosCaminos);
            return todosLosCaminos;
        }
        return null;
    }

    @Override
    public boolean tieneCiclo(Comparable etiquetaOrigen) {
        LinkedList<TVertice> verticesCamino;
        verticesCamino = (LinkedList<TVertice>) this.bpf(etiquetaOrigen);
        if (!verticesCamino.isEmpty()){
            TVertice primero = verticesCamino.getFirst();
            return primero.tieneCiclo(verticesCamino);
        }
        return false;
    }

    @Override
    public boolean tieneCiclo() {
        boolean resultado = false;
        for (TVertice vertice : this.vertices.values()) {
            if (!vertice.getVisitado()) {
                resultado = tieneCiclo(vertice.getEtiqueta());
            }
        }
        return resultado;
    }

    @Override
    public Collection<TVertice> bea() {
        Collection<TVertice> collection = new HashSet<>();

        for (TVertice vertice : this.vertices.values()) {
            if (!vertice.getVisitado()) {
                vertice.bea(collection);
            }
        }

        return collection;
    }
    
    
}
