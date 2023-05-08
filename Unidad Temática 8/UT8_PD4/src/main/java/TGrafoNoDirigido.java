
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
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
        boolean tempbool = false;
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
        LinkedList<Comparable> U = new LinkedList<>();
        TAristas aris = new TAristas();

        for (TVertice vr : this.getVertices().values()) {
            verts.add(vr.getEtiqueta());
        }

        if (this.esConexo()){
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        
    }

    @Override
    public Collection<TVertice> bea(Comparable etiquetaOrigen) {
        LinkedList<TVertice> visitados = new LinkedList<>();

        desvisitarVertices();

        for (TVertice vertice : getVertices().values()) {
            if (vertice.getEtiqueta().compareTo(etiquetaOrigen) == 0) {
                vertice.bea(visitados);
            }
        }

        return visitados;
    }
	 
    @Override
    public LinkedList<TVertice> puntosArticulacion(Comparable etOrigen) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean esConexo(){
        Collection<TVertice> grafo = this.bea(this.vertices.values().iterator().next().getEtiqueta());

        for (TVertice vertice : this.getVertices().values()) {
            System.out.println(vertice.getEtiqueta());
        }

        for (TVertice vertice: this.getVertices().values()){
            if (!grafo.contains(vertice)){
              return false;
            }
        }

        return true;
    }

    public boolean conectados(TVertice v,  TVertice w) {
        LinkedList<TVertice> visitados = new LinkedList<>();
        desvisitarVertices();
        v.bea(visitados);

        return visitados.contains(w);
    }
}
