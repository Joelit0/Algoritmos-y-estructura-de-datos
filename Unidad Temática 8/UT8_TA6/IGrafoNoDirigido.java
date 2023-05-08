package TA6;

import java.util.Collection;

public interface IGrafoNoDirigido {

    public Collection<TVertice> bea();

    public Collection<TVertice> bea(Comparable etiquetaOrigen);

    public TGrafoNoDirigido Prim();

    public TGrafoNoDirigido Kruskal();

    public boolean esConexo();
}