
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class TGrafoDirigido implements IGrafoDirigido {

    private final Map<Comparable, TVertice> vertices; //lista de vertices del grafo.-
    private Collection<TArista> aristas = new LinkedList<>();
    

    public TGrafoDirigido(Collection<TVertice> vertices, Collection<TArista> aristas) {
        this.vertices = new HashMap<>();
        for (TVertice vertice : vertices) {
            insertarVertice(vertice.getEtiqueta());
        }
        for (TArista arista : aristas) {
            insertarArista(arista);
        }
    }


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


    @Override
    public boolean eliminarVertice(Comparable nombreVertice) {
        if (nombreVertice != null) {
            getVertices().remove(nombreVertice);
            return getVertices().containsKey(nombreVertice);
        }
        return false;
    }


    @Override
    public boolean existeArista(Comparable etiquetaOrigen, Comparable etiquetaDestino) {
        TVertice vertOrigen = buscarVertice(etiquetaOrigen);
        TVertice vertDestino = buscarVertice(etiquetaDestino);
        if ((vertOrigen != null) && (vertDestino != null)) {
            return vertOrigen.buscarAdyacencia(vertDestino) != null;
        }
        return false;
    }


    @Override
    public boolean existeVertice(Comparable unaEtiqueta) {
        return getVertices().get(unaEtiqueta) != null;
    }


    protected TVertice buscarVertice(Comparable unaEtiqueta) {
        return getVertices().get(unaEtiqueta);
    }


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


    @Override
    public Map<Comparable, TVertice> getVertices() {
        return vertices;
    }

    public TGrafoDirigido invertirGrafo(){
        LinkedList<TArista> aristasInversas = new LinkedList<>();                       //O(1)
        for (TArista arista : aristas) {                                                //O(A)
            aristasInversas.add(arista.aristaInversa());
        }
        LinkedList<TVertice> vertices = new LinkedList<>();                     //O(V)
        getVertices().keySet().forEach((v -> vertices.add(new TVertice((v))))); // Copia de los vértices para eliminar adyacencias anteriores.
        // Parte de cargar el grafo copia.
        return new TGrafoDirigido(vertices, aristasInversas);  //O(V+A)
    }


    public Collection<TVertice> bpf(TVertice vertice) {
        Collection<TVertice> salida = new LinkedList<>();
        if (vertice != null) {
            desvisitarVertices();
            vertice.bpf(salida);
        }
        return salida;
    }



    public Collection<TVertice> bpf() {
        Collection<TVertice> salida = new LinkedList<>();
        if (!vertices.isEmpty()) {
            desvisitarVertices();
            for (TVertice vertice : vertices.values()) {
                if (!vertice.getVisitado()) {
                    vertice.bpf(salida);
                }
            }
        }
        return salida;
    }

    public Collection<TVertice> bpf(Comparable etiquetaOrigen) {
        Collection<TVertice> salida = new LinkedList<>();
        TVertice origen = vertices.get(etiquetaOrigen);
        if (origen != null) {
            desvisitarVertices();
            origen.bpf(salida);
        }
        return salida;
    }


    public Comparable centroDelGrafo() {
        desvisitarVertices();
        Double excentricidad = 0.0;
        double costo = Double.MAX_VALUE;
        TVertice centro = null;
        for (TVertice vertice : vertices.values()) {
            excentricidad = obtenerExcentricidad(vertice.getEtiqueta());
            if (excentricidad < costo) {
                costo = excentricidad;
                centro = vertice;
            }
        }
        return centro.getEtiqueta();
    }


    public Double[][] floyd() {
        int cantidad = vertices.size();
        Double[][] A = UtilGrafos.obtenerMatrizCostos(vertices);
        for (int k = 0; k < cantidad; k++) {
            for (int i = 0; i < cantidad; i++) {
                if (k != i) {
                    for (int j = 0; j < cantidad; j++) {
                        if (A[i][j] > A[i][k] + A[k][j]) {
                            A[i][j] = A[i][k] + A[k][j];
                        }
                    }
                }
            }
        }
        return A;
    }

    public boolean conectadoCon(Comparable etiquetaOrigen, Comparable etiquetaDestino){
        TVertice origen = vertices.get(etiquetaOrigen);
        TVertice destino = vertices.get(etiquetaDestino);
        this.desvisitarVertices();
        if (origen.conectadoCon(destino)) {
            return true;
        }
        return false;
    }


    public Double obtenerExcentricidad(Comparable etiquetaVertice) {
        Comparable[][] costos = floyd();
        Set<Comparable> etiquetas = vertices.keySet();
        int indice = 0;
        for (Comparable vertice : etiquetas) {
            if (etiquetaVertice.compareTo(vertice) == 0) {
                break;
            }
            indice++;
        }

        Double costoMax = 0d;
        for (int i = 0; i < costos[0].length; i++) {
            if ((Double) costos[i][indice] > costoMax) {
                costoMax = (Double) costos[i][indice];
            }
        }

        return costoMax == Double.MAX_VALUE ? -1 : costoMax;
    }


    public boolean[][] warshall() {
        int cantidad = vertices.size();
        Double[][] C = UtilGrafos.obtenerMatrizCostos(vertices);
        boolean[][] A = new boolean[cantidad][cantidad];
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


    public TCaminos todosLosCaminos(Comparable etiquetaOrigen, Comparable etiquetaDestino) {
        this.desvisitarVertices();
        TCaminos caminos = new TCaminos();
        TVertice origen = this.vertices.get(etiquetaOrigen);
        TVertice destino = this.vertices.get(etiquetaDestino);
        if(origen!= null && destino!= null){
            TCamino camino = new TCamino(origen);
            origen.todosLosCaminos(etiquetaDestino, camino, caminos);
        }
        return caminos;
    }


    public boolean tieneCiclo(Comparable etiquetaOrigen) {
        this.desvisitarVertices();
        LinkedList camino = new LinkedList();
        TVertice origen = this.vertices.get(etiquetaOrigen);
        if(origen!= null){
            return origen.tieneCiclo(camino);
        }
        return false;
        
    }

    
    public boolean tieneCiclo() {
        for (TVertice v : vertices.values()) {
            LinkedList<Comparable> lista = new LinkedList();
            if (v.tieneCiclo(lista)) {
                for (Comparable comparable : lista) {
                    //System.out.println(comparable.toString());
                }
                return true;
            }
        }
        return false;
        
    }
   
    public boolean tieneCiclo(TCamino camino) {
        this.desvisitarVertices();
        /* 
        for(TVertice vertice: this.vertices.values()){
            if(vertice.tieneCiclo(camino)){
                return true;
            }
        }
        */
        return false;
    }

    public Collection<TVertice> bea() {
        Collection<TVertice> salida = new LinkedList<>();
        if (!vertices.isEmpty()) {
            desvisitarVertices();
            for (TVertice vertice : vertices.values()) {
                if (!vertice.getVisitado()) {
                    vertice.bea(salida);
                }
            }
        }
        return salida;
    }

    public LinkedList<TVertice> clasificacionTopologica(){
        this.desvisitarVertices();
        LinkedList<TVertice> lista = new LinkedList<>();
        for (TVertice vertice : vertices.values()) {
            if (!vertice.getVisitado()) {
                vertice.clasificacionTopologica(lista);
            }
        }
        /*
        for (TVertice vertice : lista) {
            System.out.println(vertice.getEtiqueta());
        }
         */
        return lista;
    }

    //Devuelve el camino con el costo más bajo entre los 2 vertices
    public TCamino menosCosto(Comparable etiquetaOrigen, Comparable etiquetaDestino){
        this.desvisitarVertices();
        Map<TCamino, Integer> mapaCaminos =  new HashMap<>();
        TCaminos caminos = new TCaminos();
        TVertice origen = this.vertices.get(etiquetaOrigen);
        TVertice destino = this.vertices.get(etiquetaDestino);
        if(origen!= null && destino!= null){
            TCamino camino = new TCamino(origen);
            origen.menosCosto(etiquetaDestino, camino, caminos);
        }
        double costo = Double.MAX_VALUE;
        TCamino caminoMasCorto = null;
        for (TCamino camino : caminos.getCaminos()) {
            //mapaCaminos.put(camino , camino.getCostoTotal().intValue());
            if (camino.getCostoTotal()<costo) {
                costo = camino.getCostoTotal();
                caminoMasCorto = camino;
            }
        }
        return caminoMasCorto;
       
    }

    public LinkedList<Comparable> verticesNoConectadosConElResto(){
        LinkedList<Comparable> toReturn = new LinkedList<Comparable>();
        for (TVertice verticeInicio : vertices.values()) {
            for (TVertice verticeDestino : vertices.values()) {
                if (!verticeInicio.conectadoCon(verticeDestino)) {
                    toReturn.add(verticeInicio.getEtiqueta());
                }
            }
        }
        return toReturn;
    }
    
    //Devuelve lista con los vertice que marcan la ruta con menos saltos
    public LinkedList<TVertice> rutaMenosSaltos(Comparable origen, Comparable destino){
        TVertice vOrigen = getVertices().get(origen);
        TVertice vDestino = getVertices().get(destino);
        LinkedList<TVertice> listaCaminoMenosSaltos = new LinkedList<>();
        if (vOrigen == null || vDestino == null) {
            return listaCaminoMenosSaltos;
        }
        desvisitarVertices();
        vOrigen.beaCustom(origen,destino);
        listaCaminoMenosSaltos.add(vDestino);
        TVertice aux = vDestino;
        while (aux.getPredecesor()!=null) {
            listaCaminoMenosSaltos.addFirst(aux);
        }
        return listaCaminoMenosSaltos;
    }

   
}