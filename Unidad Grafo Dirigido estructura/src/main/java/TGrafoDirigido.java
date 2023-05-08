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
    public Comparable obtenerExcentricidad(Comparable etiquetaVertice) {
        Double[][] a = this.floyd();
        int cont = 0;
        double caminoMasLargo = 0;

        if (vertices.containsKey(etiquetaVertice)) {
            for (Comparable v : vertices.keySet()) {
                if (v.equals(etiquetaVertice)) {
                    break;
                }
                cont++;
            }

            for (int i = 0; i < this.vertices.size(); i++) {
                if (a[i][cont] > caminoMasLargo && a[i][cont]!= Double.MAX_VALUE) {
                    caminoMasLargo = a[i][cont];
                }
            }

            return caminoMasLargo;
        }

        return -1; //NO EXISTE EL VÉRTICE
    }

    @Override
    public Comparable centroDelGrafo() {
        Comparable menorExcentricidad = null;
        TVertice centro = null;

        for(TVertice v: this.vertices.values()){
            Comparable excentricidadDeV = this.obtenerExcentricidad(v.getEtiqueta());

            if((menorExcentricidad == null || excentricidadDeV.compareTo(menorExcentricidad) < 0) && !(excentricidadDeV.compareTo(0.0) == 0)){
                menorExcentricidad = excentricidadDeV;
                centro = v;
            }
        }

        if (centro != null) {
            return centro.getEtiqueta();
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
    public boolean[][] warshall() {
        final int cantidad = vertices.size();
        final Double[][] C = UtilGrafos.obtenerMatrizCostos(vertices);
        final boolean[][] A = new boolean[cantidad][cantidad];
        for (int i = 0; i < cantidad; i++) {
            for (int j = 0; j < cantidad; j++) {
                A[i][j] = C[i][j] < Double.MAX_VALUE;
            }
        }
        for (int k = 0; k < cantidad; k++) {
            for (int i = 0; i < cantidad; i++) {
                for (int j = 0; j < cantidad; j++) {
                    if (A[i][j] == false) {
                        A[i][j] = A[i][k] && A[k][j];
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

    public void desvisitarVertices() {
        for (TVertice vertice : this.getVertices().values()) {
            vertice.setVisitado(false);
        }
    }

    private int obtenerPosicionVertice(Comparable etiqueta){
        if (existeVertice(etiqueta)){
            int i=0;
            for (Comparable etiq: getVertices().keySet()){
                if(etiq.equals(etiqueta)){
                    break;
                }
                i++;
            }
            return i;
        }else{
            return -1;
        }
    }

    public Double[] dijkstra(Comparable etiquetaOrigen){
        LinkedList<Comparable> verticesAux = new LinkedList<>(this.getVertices().keySet());
        verticesAux.remove(etiquetaOrigen);

        Double[][] costos = UtilGrafos.obtenerMatrizCostos(vertices);
        Double[] D = new Double[this.vertices.size()];

        LinkedList<Comparable> S = new LinkedList<>();
        S.add(etiquetaOrigen);


        int posOrigen = this.obtenerPosicionVertice(etiquetaOrigen);
        for (int i = 0; i < this.vertices.size(); i++) {
            D[i] = costos[posOrigen][i];
        }

        double costoMin;
        Comparable posMin;
        int posicion;


        while (S.size() < this.vertices.size()) {
            costoMin = Double.POSITIVE_INFINITY;
            posMin = null;
            for (Comparable i : verticesAux) { // Elegir w
                posicion = this.obtenerPosicionVertice(i);
                if (D[posicion] < costoMin) {
                    costoMin = D[posicion];
                    posMin = i;
                }
            }

            S.add(posMin); // Agregar w a S

            verticesAux.remove(posMin); // Quitar w de V, para que sea V-S

            for (Comparable v : verticesAux){
                posicion = this.obtenerPosicionVertice(v);
                int w = this.obtenerPosicionVertice(posMin);
                D[posicion] = Math.min(D[posicion], D[w] + costos[w][posicion]);

            }
        }
        return D;
    }

    public LinkedList<TVertice> clasToplogica(Comparable etiquetaVertice) {
        LinkedList<TVertice> lista = new LinkedList<>();

        desvisitarVertices();

        this.buscarVertice(etiquetaVertice).clasTopologica(lista);

        return lista;
    }

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


//    i, j, k : integer
//    ;
//    COM
//    for i:= 1 to n do
//          for j:= 1 to n do
//        A[i,j]:= C[i,j]; P[
//        i, j] := 0 ;
//    for i:= 1 to n do A[i,i]:= 0;
//    for k:= 1 to n do
//          for i:= 1 to n do
//          for j:= 1 to n do
//          if (A[i,k]+A[k,j]) < A[i,j
//    ]
//        then A[i,j]:= A[i,k]+A[k,j]; P[
//        i, j] := k ;

//    k :=
//    P
//[
//    i,
//    j];
//    if
//        k = 0 then salir
//          ;
//        camino
//          (
//            i,
//            k);
//        imprimir
//          (
//            k);
//        camino
//          (
//            k,
//            j)
//        end; { camino }
}
