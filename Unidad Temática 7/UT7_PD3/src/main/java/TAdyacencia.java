
public class TAdyacencia implements IAdyacencia {
    private Comparable etiqueta;
    private int costo;
    private TVertice destino;
    
    @Override
    public Comparable getEtiqueta() {
        return etiqueta;
    }
 
    @Override
    public int getCosto() {
        return costo;
    }

    @Override
    public TVertice getDestino() {
        return destino;
    }

    public TAdyacencia(int costo, TVertice destino) {
        this.etiqueta = destino.getEtiqueta();
        this.costo = costo;
        this.destino = destino;
    }
}
