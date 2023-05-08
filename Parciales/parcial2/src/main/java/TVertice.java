
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class TVertice implements IVertice
{

    private final Comparable etiqueta;
    private final LinkedList<TAdyacencia> adyacentes;
    private boolean visitado;
    private int saltos = 0;
    private Object datos;
    private int numBp;
    private int numBajo;
    private TVertice predecesor;
    
    
    public int getSaltos()
    {
        return saltos;
    }

    public void setSaltos(int saltos)
    {
        this.saltos = saltos;
    }

    @Override
    public Comparable getEtiqueta()
    {
        return etiqueta;
    }
    public TVertice getPredecesor() {
        return predecesor;
    }

    public TVertice setPredecesor(TVertice predecesor) {
        return this.predecesor = predecesor;
    }

    public Object getDatos() {
        return datos;
    }

    public TVertice(Comparable unaEtiqueta) {
        this.etiqueta = unaEtiqueta;
        adyacentes = new LinkedList();
        visitado = false;
    }

    public void setVisitado(boolean valor) {
        this.visitado = valor;
    }

    public boolean getVisitado() {
        return this.visitado;
    }

    public int getNumero_bp() {
        return numBp;
    }

    public void setNumero_bp(int valor) {
        this.numBp = valor;
    }

    public int getNumeroBajo() {
        return numBajo;
    }

    public void setNumeroBajo(int valor) {
        this.numBajo = valor;
    }

    @Override
    public LinkedList<TAdyacencia> getAdyacentes()
    {
        return adyacentes;
    }

    @Override
    public TAdyacencia buscarAdyacencia(TVertice verticeDestino)
    {
        if (verticeDestino != null)
        {
            return buscarAdyacencia(verticeDestino.getEtiqueta());
        }
        return null;
    }

    @Override
    public Double obtenerCostoAdyacencia(TVertice verticeDestino)
    {
        TAdyacencia ady = buscarAdyacencia(verticeDestino);
        if (ady != null)
        {
            return ady.getCosto();
        }
        return Double.MAX_VALUE;
    }

    @Override
    public boolean insertarAdyacencia(Double costo, TVertice verticeDestino)
    {
        if (buscarAdyacencia(verticeDestino) == null)
        {
            TAdyacencia ady = new TAdyacencia(costo, verticeDestino);
            return adyacentes.add(ady);
        }
        return false;
    }

    @Override
    public boolean eliminarAdyacencia(Comparable nomVerticeDestino)
    {
        TAdyacencia ady = buscarAdyacencia(nomVerticeDestino);
        if (ady != null)
        {
            adyacentes.remove(ady);
            return true;
        }
        return false;
    }

    @Override
    public TVertice primerAdyacente()
    {
        if (this.adyacentes.getFirst() != null)
        {
            return this.adyacentes.getFirst().getDestino();
        }
        return null;
    }

    @Override
    public TAdyacencia buscarAdyacencia(Comparable etiquetaDestino)
    {
        for (TAdyacencia adyacencia : adyacentes)
        {
            if (adyacencia.getDestino().getEtiqueta().compareTo(etiquetaDestino) == 0)
            {
                return adyacencia;
            }
        }
        return null;
    }

    
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
    
    public void bea(int[] servidores) {
        int counter = 0;
        this.setVisitado(true);
        String resultado = "";
        LinkedList<TAdyacencia> adyacentes;
        LinkedList<TVertice> cola = new LinkedList<TVertice>();
        cola.add(this);
        resultado = resultado + this.etiqueta;
        TVertice vertice;
        while (!cola.isEmpty()) {
            vertice = cola.removeFirst();
            adyacentes = vertice.getAdyacentes();
            for (TAdyacencia adyacente : adyacentes) {
                TVertice aux = adyacente.getDestino();
                if (!aux.getVisitado()) {
                    aux.setSaltos(servidores[counter] + 1);
                    adyacente.getDestino().setVisitado(true);
                    cola.add(adyacente.getDestino());
                    resultado += adyacente.getDestino().etiqueta;
                } 
            }
            counter++;
        }
    }


    public TCaminos todosLosCaminos(Comparable etVertDest, TCamino caminoPrevio, TCaminos todosLosCaminos) {
        this.setVisitado(true);
        for(TAdyacencia ady :  this.adyacentes){
            caminoPrevio.agregarAdyacencia(ady);
            if(ady.getDestino().etiqueta.equals(etVertDest)){
                todosLosCaminos.getCaminos().add(caminoPrevio.copiar());
            }else{
                if(!ady.getDestino().getVisitado())
                    ady.getDestino().todosLosCaminos(etVertDest, caminoPrevio, todosLosCaminos);
            }
            caminoPrevio.eliminarAdyacencia(ady);
        }
        this.setVisitado(false);
        return todosLosCaminos;
    }


    public void bea(Collection<TVertice> visitados) {
        this.setVisitado(true);
        String resultado = "";
        LinkedList<TAdyacencia> adyacentes;
        LinkedList<TVertice> cola = new LinkedList<TVertice>();
        cola.add(this);
        resultado = resultado + this.etiqueta;
        TVertice vertice;
        while (!cola.isEmpty()) {
            vertice = cola.removeFirst();
            adyacentes = vertice.getAdyacentes();
            for (TAdyacencia adyacente : adyacentes) {
                if (!adyacente.getDestino().getVisitado()) {
                    adyacente.getDestino().setVisitado(true);
                    visitados.add(adyacente.getDestino());
                    cola.add(adyacente.getDestino());
                    resultado += adyacente.getDestino().etiqueta;
                }
                
            }
            
        }
    }
    


    public TVertice siguienteAdyacente(TVertice w) {
        TAdyacencia adyacente = buscarAdyacencia(w.getEtiqueta());
        int index = adyacentes.indexOf(adyacente);
        if (index + 1 < adyacentes.size()) {
            return adyacentes.get(index + 1).getDestino();
        }
        return null;
    }

    public boolean tieneCiclo(LinkedList<Comparable> camino) {
        boolean result = false;
        this.setVisitado(true);
        camino.add(this.getEtiqueta());
        for (TAdyacencia adyacente : adyacentes) {
            if (!adyacente.getDestino().getVisitado()) {
                if(adyacente.getDestino().tieneCiclo(camino));
                return true;
            } else {
                if (camino.contains(adyacente.getDestino().getEtiqueta())) {
                    return true;
                }
            }
        }
        camino.remove(this.getEtiqueta());
        return false;
       /* 
        Set<Comparable> vistos = new HashSet<Comparable>();
        for (Comparable etiqueta : camino) {
            if (!vistos.add(etiqueta)) {
                return true;
            }
        }
        return false;
        */
    }


    public boolean conectadoCon(TVertice destino) {
          this.setVisitado(true);
		  for (TAdyacencia tAdyacencia : adyacentes) {
            if (!tAdyacencia.getDestino().getVisitado()) {
                if (tAdyacencia.getDestino().getEtiqueta().equals(destino.getEtiqueta()))
                {
                    return true;
                }else{
                    if(tAdyacencia.getDestino().conectadoCon(destino)){
                        return true;
                    }
                }

            }
          }
          return false;
      
    }

    public void clasificacionTopologica(LinkedList<TVertice> lista){
        this.setVisitado(true);
        for (TAdyacencia adyacente : adyacentes) {
            if (!adyacente.getDestino().getVisitado()) {
                adyacente.getDestino().clasificacionTopologica(lista);
            }
        }
        lista.addFirst(this);
    }

    public void puntosArticulacion(Collection<TVertice> puntosDeArticulacion, int[] numerobp) {
        setVisitado(true);
        numerobp[0]++;
        setNumero_bp(numerobp[0]);
        setNumeroBajo(numerobp[0]);

        int numerobpSiguiente = numerobp[0];
        LinkedList<TVertice> hijos = new LinkedList<>();
        for (TAdyacencia adyacente : adyacentes) {
            TVertice destino = adyacente.getDestino();
            if (!destino.getVisitado()) {
                destino.puntosArticulacion(puntosDeArticulacion, numerobp);
                hijos.add(destino); //Agrego nuevo hijo a la lista.
                if (destino.getNumeroBajo() < getNumeroBajo()) {
                    setNumeroBajo(destino.getNumeroBajo());
                }
            } else {
                if (destino.getNumero_bp() < getNumeroBajo()) {
                    setNumeroBajo(destino.getNumero_bp());
                }
            }
        }
        if (getNumero_bp() == 1) {
            if (hijos.size() >= 2) {
                puntosDeArticulacion.add(this);
            }
        } else {
            for (TVertice v : hijos) {
                if (v.getNumeroBajo() >= getNumero_bp()) {
                    puntosDeArticulacion.add(this);
                }
            }
        }
    }


    public void menosCosto(Comparable etVertDest, TCamino caminoPrevio, TCaminos todosLosCaminos){
        this.setVisitado(true);
        for(TAdyacencia ady :  this.adyacentes){
            caminoPrevio.agregarAdyacencia(ady);
            if(ady.getDestino().etiqueta.equals(etVertDest)){
                todosLosCaminos.getCaminos().add(caminoPrevio.copiar());
            }else{
                if(!ady.getDestino().getVisitado())
                    ady.getDestino().todosLosCaminos(etVertDest, caminoPrevio, todosLosCaminos);
            }
            caminoPrevio.eliminarAdyacencia(ady);
        }
        this.setVisitado(false);
        
    }
    
    public void beaCustom(Comparable origen, Comparable destino){
        this.setVisitado(true);
        LinkedList<TAdyacencia> adyacencias;
        LinkedList<TVertice> cola = new LinkedList<>();
        cola.add(this);
        while (!cola.isEmpty()){
            TVertice vertice = cola.removeFirst();
            adyacencias = vertice.getAdyacentes();
            for (TAdyacencia adyacente : adyacencias) {
                if (!adyacente.getDestino().getVisitado()) {
                    adyacente.getDestino().setPredecesor(vertice);
                    if (adyacente.getDestino().getEtiqueta().equals(destino)) {
                        return;
                    }
                    adyacente.getDestino().setVisitado(true);
                    cola.add(adyacente.getDestino());
                }
            }
        }
    }
}
