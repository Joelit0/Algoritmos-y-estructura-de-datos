
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;

public class TGrafoNoDirigido extends TGrafoDirigido implements IGrafoNoDirigido, IGrafoKevinBacon {
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
        if (!this.esConexo()){
            U.add(verts.getFirst());
            while (!verts.isEmpty()){
                TArista ar = this.getLasAristas().buscarMin(U, verts);
                aris.add(ar);
                U.add(ar.etiquetaDestino);
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
	 
    public LinkedList<TVertice> puntosArticulacion(Comparable etOrigen) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public boolean esConexo(){
        Collection<TVertice> grafo = this.bea(lasAristas.pollFirst().etiquetaOrigen);

        for (TVertice v: grafo){
            if (!this.getVertices().values().contains(v)){
                return false;
            }
        }

        return true;
    }

    @Override
    public boolean conectados(TVertice origen, TVertice destino) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int numBacon(Comparable actor) {
        for (TVertice vertice : getVertices().values()) {
            vertice.setVisitado(false);
            vertice.setBacon(0);
        }

        TVertice kevin = this.vertices.get("Kevin_Bacon");

        return kevin.beacon(actor);
    }
}
