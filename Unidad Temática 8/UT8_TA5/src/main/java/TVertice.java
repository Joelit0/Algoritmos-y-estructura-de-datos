
import java.util.Collection;
import java.util.LinkedList;
import java.util.Queue;


public class TVertice<T> implements IVertice, IVerticeKevinBacon{

    private Comparable etiqueta;
    private LinkedList<TAdyacencia> adyacentes;
    private boolean visitado;
    private T datos;
    private int bacon;

    public Comparable getEtiqueta() {
        return etiqueta;
    }

    public LinkedList<TAdyacencia> getAdyacentes() {
        return adyacentes;
    }

    public T getDatos() {
        return datos;
    }

    public TVertice(Comparable unaEtiqueta) {
        this.etiqueta = unaEtiqueta;
        adyacentes = new LinkedList();
        visitado = false;
        bacon = 0;
    }

    public void setVisitado(boolean valor) {
        this.visitado = valor;
    }

    public boolean getVisitado() {
        return this.visitado;
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
    
    @Override
    public TVertice siguienteAdyacente(TVertice w) {
        TAdyacencia resultado;
        int indice = adyacentes.indexOf(w);
        if (indice != -1){
            resultado = adyacentes.get(indice+1);
            return resultado.getDestino();
        }
        return null;}

    @Override
    public boolean tieneCiclo(LinkedList<Comparable> camino) {
        for (Comparable c : camino){
            camino.removeFirst();
            if (camino.contains(c)){
                return true;
            }
        }
        return false;
    }

    @Override
    public void bea(Collection<TVertice> visitados) {

    }

    @Override
    public boolean conectadoCon(TVertice destino) {
	      boolean conectado = false;
        if (buscarAdyacencia(destino) != null){
            conectado = true;
        }
        return conectado;
    }

    @Override
    public int getBacon() {
        return bacon;
    }

    @Override
    public void setBacon(int newBacon) {
        this.bacon = newBacon;
    }
}
