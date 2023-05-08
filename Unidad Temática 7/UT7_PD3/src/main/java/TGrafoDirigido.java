
import java.util.*;

public class TGrafoDirigido implements IGrafoDirigido {

    private Map<Comparable, TVertice> vertices; // vertices del grafo.-

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
     */
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
     * Metodo encargado de verificar la existencia de una arista. Las etiquetas
     * pasadas por par�metro deben ser v�lidas.
     *
     * @return True si existe la adyacencia, false en caso contrario
     */
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
     * @param unaEtiqueta Etiqueta del vertice a buscar.-
     * @return True si existe el vertice con la etiqueta indicada, false en caso
     * contrario
     */
    public boolean existeVertice(Comparable unaEtiqueta) {
        return getVertices().get(unaEtiqueta) != null;
    }

    /**
     * Metodo encargado de verificar buscar un vertice dentro del grafo.-
     *
     * La etiqueta especificada como parametro debe ser valida.
     *
     * @param unaEtiqueta Etiqueta del vertice a buscar.-
     * @return El vertice encontrado. En caso de no existir, retorna nulo.
     */
    private TVertice buscarVertice(Comparable unaEtiqueta) {
        return getVertices().get(unaEtiqueta);
    }

    /**
     * Metodo encargado de insertar una arista en el grafo (con un cierto
     * costo), dado su vertice origen y destino.- Para que la arista sea valida,
     * se deben cumplir los siguientes casos: 1) Las etiquetas pasadas por
     * parametros son v�lidas.- 2) Los vertices (origen y destino) existen
     * dentro del grafo.- 3) No es posible ingresar una arista ya existente
     * (miso origen y mismo destino, aunque el costo sea diferente).- 4) El
     * costo debe ser mayor que 0.
     *
     * @return True si se pudo insertar la adyacencia, false en caso contrario
     */
    public boolean insertarArista(TArista arista) {
        if ((arista.getEtiquetaOrigen() != null) && (arista.getEtiquetaDestino() != null)) {
            TVertice vertOrigen = buscarVertice(arista.getEtiquetaOrigen());
            TVertice vertDestino = buscarVertice(arista.getEtiquetaDestino());
            if ((vertOrigen != null) && (vertDestino != null)) {
                return vertOrigen.insertarAdyacencia(arista.getCosto(), vertDestino);
            }
        }
        return false;
    }

    /**
     * Metodo encargado de insertar un vertice en el grafo.
     *
     * No pueden ingresarse vertices con la misma etiqueta. La etiqueta
     * especificada como par�metro debe ser v�lida.
     *
     * @param unaEtiqueta Etiqueta del vertice a ingresar.
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

    public Object[] getEtiquetas() {
        return this.getVertices().keySet().toArray();
    }

    /**
     * @return the vertices
     */
    public Map<Comparable, TVertice> getVertices() {
        return vertices;
    }

    @Override
    public Comparable centroDelGrafo(int[][] mfloyd) {
        TVertice resultado = null;
        Comparable menor_excentricidad = 100000;
        int contador = 0;

        for (final TVertice t : this.vertices.values()) {
            Comparable excentricidad = this.obtenerExcentricidad(t.getEtiqueta(), contador, mfloyd);
            if (excentricidad.compareTo(menor_excentricidad) < 0) {
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
    public int[][] floyd() {
        final int[][] C = UtilGrafos.obtenerMatrizCostos(vertices);
        final int[][] A = new int[C.length][C.length];
        for (int i = 0; i < C.length; i++) {
            for (int j = 0; j < C.length; j++) {
                A[i][j] = C[i][j];
            }
        }
        for (int i = 0; i < C.length; i++) {
            A[i][i] = 0;
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

    public boolean existeConexion(Comparable origen, Comparable destino) {
        TVertice vertOrigen = buscarVertice(origen);
        TVertice vertDestino = buscarVertice(destino);

        if ((vertOrigen != null) && (vertDestino != null)) {
            return vertOrigen.buscarAdyacencia(vertDestino) != null;
        }

        return false;
    }

    @Override
    public Comparable obtenerExcentricidad(Comparable etiquetaVertice, int numeroVertice, int[][] mfloyd) {
        final TVertice vertice = this.buscarVertice(etiquetaVertice);

        if (vertice != null) {
            int v = numeroVertice;
            int mas_lejano = 0;

            for (int a = 0 ; a < this.vertices.size() ; a++) {
                if (mfloyd[v][a] != Integer.MAX_VALUE && mfloyd[v][a] != 0) {
                    if (mfloyd[v][a] > mas_lejano) {
                        mas_lejano = mfloyd[v][a];
                    }
                }
            }

            return mas_lejano;
        }
        return null;
    }

    @Override
    public boolean[][] warshall() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean eliminarVertice(Comparable nombreVertice) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Collection<TVertice> bpf() {
        Collection<TVertice> collection = new HashSet<TVertice>();

        for (TVertice vertice : this.vertices.values()) {
            if (!vertice.getVisitado()) {
                vertice.bpf(collection);
            }
        }

        return collection;
    }
}
