
import java.util.Collection;
import java.util.LinkedList;

public class TGrafoNoDirigido extends TGrafoDirigido implements IGrafoNoDirigido {

    protected TAristas lasAristas = new TAristas();

    /**
     *
     * @param vertices
     * @param aristas
     */
    public TGrafoNoDirigido(Collection<TVertice> vertices, Collection<TArista> aristas) {
        super(vertices, aristas);
        lasAristas.insertarAmbosSentidos(aristas);

    }

    @Override
    public boolean insertarArista(TArista arista) {
        boolean tempbool;
        TArista arInv = new TArista(arista.getEtiquetaDestino(), arista.getEtiquetaOrigen(), arista.getCosto());
        tempbool = (super.insertarArista(arista) && super.insertarArista(arInv));
        return tempbool;
    }

    public TAristas getLasAristas() {
        return lasAristas;
    }

    @Override
    public TGrafoNoDirigido Prim() {
        LinkedList<Comparable> verts = new LinkedList<>();
        for (TVertice vr : this.getVertices().values()){
            verts.add(vr.getEtiqueta());
        }
        LinkedList<Comparable> U = new LinkedList<>();
        TAristas aris = new TAristas();
        if (this.esConexo() == true){
            U.add(verts.getFirst());
            verts.removeFirst();
            while (!verts.isEmpty()){
                TArista ar = this.getLasAristas().buscarMin(U, verts);
                aris.add(ar);
                U.add(ar.etiquetaDestino);
                verts.remove(ar.getEtiquetaDestino());
            }
        }
        LinkedList<TVertice> UVertices = new LinkedList<>();
        for (Comparable u : U){
            TVertice vert = new TVertice(u);
            UVertices.add(vert);
        }
        TGrafoNoDirigido prim = new TGrafoNoDirigido(UVertices, aris);
        return prim;
    }

    @Override
    public TGrafoNoDirigido Kruskal() {
        TAristas ars = new TAristas();
        TAristas aristascopia = this.getLasAristas();
        LinkedList<Comparable> U = new LinkedList<>();
        do {
            double costoMin = Double.MAX_VALUE;
            TArista tA = null;
            for (TArista ar : aristascopia){
                if (ar.costo < costoMin){
                    costoMin = ar.costo;
                    tA.etiquetaDestino = ar.etiquetaDestino;
                    tA.etiquetaOrigen = ar.etiquetaOrigen;
                }
            }
            if (tA != null){
                boolean destino = false;
                boolean origen = true;
                if (!U.contains(tA.etiquetaDestino)){
                    U.add(tA.etiquetaDestino);
                    destino = true;
                }
                if (!U.contains(tA.etiquetaOrigen)){
                    U.add(tA.etiquetaOrigen);
                    origen = true;
                }
                if (origen || destino){
                    ars.add(tA);
                }
            }
        }
        while (U.size() == this.getVertices().values().size());
        LinkedList<TVertice> UVertices = new LinkedList<>();
        for (Comparable u : U){
            TVertice vert = new TVertice(u);
            UVertices.add(vert);
        }
        TGrafoNoDirigido kruskal = new TGrafoNoDirigido(UVertices, ars);
        return kruskal;
    }

    @Override
    public Collection<TVertice> bea(Comparable etiqueta) {
        LinkedList<TVertice> visitados = new LinkedList<>();

        for (TVertice vertice : getVertices().values()) {
            vertice.setVisitado(false);
        }

        for (TVertice vertice : getVertices().values()) {
            if (vertice.getEtiqueta().compareTo(etiqueta) == 0) {
                vertice.bea(visitados);
            }
        }

        return visitados;
    }
	
    @Override
    public LinkedList<TVertice> puntosArticulacion(Comparable etOrigen) {
        LinkedList<TVertice> articulacion = new LinkedList<>();
        for (TVertice vertice : getVertices().values()) {
            vertice.setVisitado(false);
        }
        if (this.esConexo() && getVertices().containsKey(etOrigen)) {
            TVertice vertice = getVertices().get(etOrigen);
            int temp = 1;
            vertice.puntosArticulacion(articulacion, temp, null);
        }
        return articulacion;
    }
    
        
	@Override
	public boolean esConexo(){
            Collection<TVertice> grafo = this.bea(lasAristas.pollFirst().etiquetaOrigen);
            for (TVertice v: grafo){
                if (this.getVertices().values().contains(v) == false){
                    return false;
                }
            }
            return true;
    }
        
        public boolean connectados(TVertice v, TVertice w) {
        LinkedList<TVertice> visitados = new LinkedList<>();

        for (TVertice vertice : getVertices().values()) {
            vertice.setVisitado(false);
        }

        for (TVertice vertice : getVertices().values()) {
            if (vertice == v) {
                vertice.bea(visitados);
            }
        }

        return visitados.contains(w);
    }
}
