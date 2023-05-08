package TA6;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
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
        for (TVertice vr : this.getVertices().values()) {
            verts.add(vr.getEtiqueta());
        }
        LinkedList<Comparable> U = new LinkedList<>();
        TAristas aris = new TAristas();
        if (this.esConexo() == true) {
            U.add(verts.getFirst());
            verts.removeFirst();
            while (!verts.isEmpty()) {
                TArista ar = this.getLasAristas().buscarMin(U, verts);
                aris.add(ar);
                U.add(ar.etiquetaDestino);
                verts.remove(ar.getEtiquetaDestino());
            }
        }
        LinkedList<TVertice> UVertices = new LinkedList<>();
        for (Comparable u : U) {
            TVertice vert = new TVertice(u);
            UVertices.add(vert);
        }
        TGrafoNoDirigido prim = new TGrafoNoDirigido(UVertices, aris);
        return prim;
    }

    @Override
    public TGrafoNoDirigido Kruskal() {
        double costoKruskal = 0;

        // Ordeno las Aristas por costo
        // O(Alog(A))
        lasAristas.sort((o1, o2) -> {
            return (int) (o1.getCosto() - o2.getCosto());
        });

        // Defino los Componentes
        HashMap<Comparable, Integer> componentes = new HashMap<>();

        int compActual = 0;
        for (TVertice vertice : getVertices().values()) {
            componentes.put(vertice.getEtiqueta(), compActual);
            compActual++;
        }

        TAristas AristasAAM = new TAristas();

        for (TArista ady : lasAristas) {
            int componenteU = componentes.get(ady.getEtiquetaOrigen());
            int componenteV = componentes.get(ady.getEtiquetaDestino());
            // Si perteneces a componentes distintos
            if (componenteU != componenteV) {
                AristasAAM.add(ady);
                costoKruskal += ady.getCosto();
                // Uno los dos componentes en U
                componentes.replaceAll((clave, valor) -> {
                    // Si Pertenece al componente de V
                    if (valor == componenteV) {
                        return componenteU;
                    }
                    return valor;
                });
            }
        }

        System.out.println("El costo de Kruskal es: " + costoKruskal);
        LinkedList<TVertice> verticesCopia = new LinkedList<>();
        getVertices().keySet().forEach((v -> verticesCopia.add(new TVertice((v)))));
        // crear nuevoGrafo 
        return new TGrafoNoDirigido(verticesCopia, AristasAAM);
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
    public boolean esConexo() {
        Collection<TVertice> grafo = this.bea(lasAristas.pollFirst().etiquetaOrigen);
        for (TVertice v : grafo) {
            if (this.getVertices().values().contains(v) == false) {
                return false;
            }
        }
        return true;
    }
}