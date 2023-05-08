package TA6;

import java.util.Collection;
import java.util.LinkedList;

public interface IVertice {

    TAdyacencia buscarAdyacencia(TVertice verticeDestino);

    TAdyacencia buscarAdyacencia(Comparable etiquetaDestino);

    boolean eliminarAdyacencia(Comparable nomVerticeDestino);

    boolean insertarAdyacencia(Double costo, TVertice verticeDestino);

    Double obtenerCostoAdyacencia(TVertice verticeDestino);

    TVertice primerAdyacente();

    TVertice siguienteAdyacente(TVertice w);

    public void bpf(Collection<TVertice> visitados);

    public TCaminos todosLosCaminos(Comparable etVertDest, TCamino caminoPrevio, TCaminos todosLosCaminos);

    public boolean tieneCiclo(LinkedList<Comparable> camino);

    public void bea(Collection<TVertice> visitados);
}