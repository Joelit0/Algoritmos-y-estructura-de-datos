
public class TAdyacencia implements IAdyacencia {
    private Comparable etiqueta;
    private Double costo;
    private TVertice destino;
    
    @Override
    public Comparable getEtiqueta() {
        return etiqueta;
    }
 
    @Override
    public Double getCosto() {
        return costo;
    }

    @Override
    public TVertice getDestino() {
        return destino;
    }

    public TAdyacencia(Double costo, TVertice destino) {
        this.etiqueta = destino.getEtiqueta();
        this.costo = costo;
        this.destino = destino;
    }
}
