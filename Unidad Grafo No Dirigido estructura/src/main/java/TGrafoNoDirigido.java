
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

public class TGrafoNoDirigido extends TGrafoDirigido implements IGrafoNoDirigido {
    protected TAristas lasAristas = new TAristas();

    public TGrafoNoDirigido(Collection<TVertice> vertices, Collection<TArista> aristas) {
        super(vertices, aristas);
        this.lasAristas.insertarAmbosSentidos(aristas);
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
        LinkedList<Comparable> V = new LinkedList<>();
        LinkedList<Comparable> U = new LinkedList<>();
        TAristas aristas = new TAristas();

        for (TVertice vr : this.getVertices().values()) {
            V.add(vr.getEtiqueta());
        }

        if (this.esConexo()) {
            U.add(V.getFirst());
            V.removeFirst();
            Double valor = 0.0;

            while (!V.isEmpty()){
                TArista ar = this.getLasAristas().buscarMin(U, V);
                valor += ar.getCosto();
                aristas.add(ar);
                U.add(ar.etiquetaDestino);
                V.remove(ar.getEtiquetaDestino());
            }

            System.out.println("PRIM: " + valor);
        }

        LinkedList<TVertice> UVertices = new LinkedList<>();

        for (Comparable u : U){
            TVertice vert = new TVertice(u);
            UVertices.add(vert);
        }

        TGrafoNoDirigido prim = new TGrafoNoDirigido(UVertices, aristas);

        return prim;
    }

    @Override
    public TGrafoNoDirigido Kruskal() {
        TGrafoNoDirigido arbolCostoMinimo = new TGrafoNoDirigido(vertices.values(), new TAristas());
        TAristas aristasOrdenadas = this.lasAristas.copiarTAristasOrdenado();

        int aristasAgregadas = 0;
        Double valor = 0.0;

        while (aristasAgregadas != getVertices().size() - 1) {
            TArista aristaMin = aristasOrdenadas.removeFirst();

            TVertice verticeOrigen = arbolCostoMinimo.buscarVertice(aristaMin.getEtiquetaOrigen());
            TVertice verticeDestino = arbolCostoMinimo.buscarVertice(aristaMin.getEtiquetaDestino());

            if (!arbolCostoMinimo.conectados(verticeOrigen, verticeDestino)) {
                valor += aristaMin.getCosto();
                arbolCostoMinimo.insertarArista(aristaMin);
                arbolCostoMinimo.getLasAristas().add(aristaMin);
                arbolCostoMinimo.getLasAristas().add(aristaMin.aristaInversa());
                aristasAgregadas++;
            }
        }

        System.out.println("Kruskal: " + valor);

        return arbolCostoMinimo;
    }

    @Override
    public Collection<TVertice> bea(Comparable etiquetaOrigen) {
        LinkedList<TVertice> visitados = new LinkedList<>();
        desvisitarVertices();

        TVertice vertice = this.vertices.get(etiquetaOrigen);

        if (vertice != null) {
            vertice.bea(visitados);
        }

        return visitados;
    }
	 
    @Override
    public LinkedList<TVertice> puntosArticulacion(Comparable etOrigen) {
        LinkedList<TVertice> verticesArticulacion = new LinkedList<>();
        desvisitarVertices();
        TVertice vertice = this.vertices.get(etOrigen);

        if (vertice != null) { vertice.puntosArticulacion(verticesArticulacion, 0  ); }

        return verticesArticulacion;
    }

    @Override
    public boolean esConexo(){
        Collection<TVertice> grafo = this.bea(this.vertices.values().iterator().next().getEtiqueta());

        for (TVertice vertice: this.getVertices().values()){
            if (!grafo.contains(vertice)){
              return false;
            }
        }

        return true;
    }

    public boolean conectados(TVertice v,  TVertice w) {
        desvisitarVertices();

        return v.conectadoCon(w);
    }

    public int numBacon(Comparable actor) {
        for (TVertice vertice : getVertices().values()) {
            vertice.setVisitado(false);
            vertice.setBacon(0);
        }

        TVertice kevin = this.vertices.get("Kevin_Bacon");

        return kevin.beacon(actor);
    }
}
