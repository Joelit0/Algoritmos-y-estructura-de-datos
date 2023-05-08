import java.util.Collection;
import java.util.LinkedList;
import java.util.Queue;

public class TVertice<T> implements IVertice {
    private Comparable etiqueta;
    private LinkedList<TAdyacencia> adyacentes;
    private boolean visitado;
    private T datos;
    private int numBajo;
    private int numBp;
    private int bacon;

    public TVertice(Comparable unaEtiqueta) {
        this.etiqueta = unaEtiqueta;
        adyacentes = new LinkedList();
        visitado = false;
    }

    public int getBacon() {
        return bacon;
    }

    public void setBacon(int newBacon) {
        this.bacon = newBacon;
    }

    public Comparable getEtiqueta() {
        return etiqueta;
    }

    public LinkedList<TAdyacencia> getAdyacentes() {
        return adyacentes;
    }

    public T getDatos() {
        return datos;
    }

    public boolean getVisitado() {
        return this.visitado;
    }

    public void setVisitado(boolean valor) {
        this.visitado = valor;
    }

    @Override
    public TAdyacencia buscarAdyacencia(TVertice verticeDestino) {
        if (verticeDestino != null) {
            return buscarAdyacencia(verticeDestino.getEtiqueta());
        }

        return null;
    }

    @Override
    public Double obtenerCostoAdyacencia(TVertice verticeDestino) {
        TAdyacencia ady = buscarAdyacencia(verticeDestino);

        if (ady != null) {
            return ady.getCosto();
        }

        return Double.MAX_VALUE;
    }

    @Override
    public boolean insertarAdyacencia(Double costo, TVertice verticeDestino) {
        if (buscarAdyacencia(verticeDestino) == null) {
            TAdyacencia ady = new TAdyacencia(costo, verticeDestino);
            return adyacentes.add(ady);
        }

        return false;
    }

    @Override
    public boolean eliminarAdyacencia(Comparable nomVerticeDestino) {
        TAdyacencia ady = buscarAdyacencia(nomVerticeDestino);

        if (ady != null) {
            adyacentes.remove(ady);
            return true;
        }

        return false;
    }

    @Override
    public TVertice primerAdyacente() {
        if (this.adyacentes.getFirst() != null) {
            return this.adyacentes.getFirst().getDestino();
        }
        return null;
    }

    @Override
    public TAdyacencia buscarAdyacencia(Comparable etiquetaDestino) {
        for (TAdyacencia adyacencia : adyacentes) {
            if (adyacencia.getDestino().getEtiqueta().compareTo(etiquetaDestino) == 0) {
                return adyacencia;
            }
        }
        return null;
    }
  
    @Override
    public void bpf(Collection<TVertice> visitados) {
        setVisitado(true);
        visitados.add(this);

        for (TAdyacencia adyacente : adyacentes) {
            TVertice vertAdy = adyacente.getDestino();

            if (!vertAdy.getVisitado()) {
                vertAdy.bpf(visitados);
            }
        }
    }

    @Override
    public TCaminos todosLosCaminos(Comparable etVertDest, TCamino caminoPrevio, TCaminos todosLosCaminos) {
        this.setVisitado(true);

        for (TAdyacencia adyacencia : this.getAdyacentes()) {
            TVertice destino = adyacencia.getDestino();
            if (!destino.getVisitado()) {
                if (destino.getEtiqueta().compareTo(etVertDest) == 0) {
                    TCamino copia = caminoPrevio.copiar();
                    copia.agregarAdyacencia(adyacencia);
                    todosLosCaminos.getCaminos().add(copia);
                } else {
                    caminoPrevio.agregarAdyacencia(adyacencia);
                    destino.todosLosCaminos(etVertDest, caminoPrevio, todosLosCaminos);
                    caminoPrevio.eliminarAdyacencia(adyacencia);
                }
            }
        }

        this.setVisitado(false);

        return todosLosCaminos;
    }

    @Override
    public void bea(Collection<TVertice> visitados) {
        LinkedList<TVertice> cola = new LinkedList<>();
        TVertice verticeActual;

        setVisitado(true);
        visitados.add(this);
        cola.add(this);

        while (!cola.isEmpty()){
            verticeActual = cola.removeFirst();
            LinkedList<TAdyacencia> ady = verticeActual.getAdyacentes();

            for (TAdyacencia adyacencia : ady){
                if (!adyacencia.getDestino().getVisitado()){
                    adyacencia.getDestino().setVisitado(true);
                    visitados.add(adyacencia.getDestino());
                    cola.add(adyacencia.getDestino());
                }
            }
        }
    }

    public void puntosArticulacion(LinkedList<TVertice> verticesArticulacion, int prof) {
        prof++;
        this.numBp = prof;
        this.numBajo = prof;
        this.visitado = true;

        LinkedList<TVertice> hijos = new LinkedList<>();

        for (TAdyacencia adyacencia : this.adyacentes) {
            TVertice adyacente = adyacencia.getDestino();

            if (!adyacente.getVisitado()) {
                adyacente.puntosArticulacion(verticesArticulacion, prof);
                hijos.add(adyacente);
                this.numBajo = Math.min(this.numBajo, adyacente.numBajo);
            } else {
                this.numBajo = Math.min(this.numBajo, adyacente.numBp);
            }
        }

        if (this.numBp > 1) {
            for (TVertice hijo : hijos) {
                if (hijo.numBajo >= this.numBp) {
                    verticesArticulacion.add(this);
                }
            }
        } else {
            if (hijos.size() > 1) {
                verticesArticulacion.add(this);
            }
        }
    }

    @Override
    public TVertice siguienteAdyacente(TVertice w) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean tieneCiclo(LinkedList<Comparable> camino) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean conectadoCon(TVertice destino) {
        LinkedList<TVertice> visitados = new LinkedList<>();
        bea(visitados);

        return visitados.contains(destino);
    }

    public int beacon(Comparable actor) {
        LinkedList<TVertice> cola = new LinkedList<>();

        setVisitado(true);
        cola.add(this);

        while (!cola.isEmpty()){
            TVertice x = cola.removeFirst();
            LinkedList<TAdyacencia> ady = x.getAdyacentes();

            for (TAdyacencia y : ady){
                if (!y.getDestino().getVisitado()){
                    y.getDestino().setVisitado(true);
                    cola.add(y.getDestino());
                    y.getDestino().setBacon(x.bacon + 1);
                }

                if (y.getDestino().getEtiqueta().compareTo(actor) == 0) {
                    return y.getDestino().getBacon();
                }
            }
        }

        return -1;
    }
}
