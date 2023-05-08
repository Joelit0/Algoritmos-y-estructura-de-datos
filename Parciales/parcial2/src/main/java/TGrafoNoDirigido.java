
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

public class TGrafoNoDirigido extends TGrafoDirigido implements IGrafoNoDirigido 
{

    protected TAristas lasAristas = new TAristas();

    /**
     *
     * @param vertices
     * @param aristas
     */
    public TGrafoNoDirigido(Collection<TVertice> vertices, Collection<TArista> aristas)
    {
        super(vertices, aristas);
        lasAristas.insertarAmbosSentidos(aristas);

    }

    @Override
    public boolean insertarArista(TArista arista)
    {
        boolean tempbool = false;
        TArista arInv = new TArista(arista.getEtiquetaDestino(), arista.getEtiquetaOrigen(), arista.getCosto());
        tempbool = (super.insertarArista(arista) && super.insertarArista(arInv));
        return tempbool;
    }

    public TAristas getLasAristas()
    {
        return lasAristas;
    }
    

    public boolean conectados(TVertice origen, TVertice destino)
    {   
        boolean conectado = false;
        Collection<TVertice> visitados = new LinkedList<>();
        if (origen != null && destino != null)
        {
            desvisitarVertices();
            origen.bpf(visitados);
            conectado = visitados.contains(destino);
        }
        return conectado;
          
    }
  
    // devuelve un nuevo grafo AACM, que es el Arbol Abarcador de Costo M�nimo
        //crear la lista  de vertices V de trabajo, y agregarle todos los vertices
        //crear la lista  de vertices U (seleccionados), vacia
        // pasar el primero de V a U
        // mientras no este vacia V
            // elegir una arista de costo minimo que vaya de U a V, 
            // si no hay arista, terminar 
	    // agregar la arista a la lista de aristas del AACM
            // quitar el vertice v de V y agregarlo a U
        // crear el nuevo  grafo con los vertices originales y las aristas halladas
        // devolver el nuevo grafo

    public TGrafoNoDirigido Prim() {
       double costoMin = 0;
       LinkedList<TVertice> verticesPrim = new LinkedList<>();
       LinkedList<TArista> aristasPrim = new LinkedList<>();
       TAristas aristasCopia = (TAristas) this.lasAristas.clone();                         // Creo una copia de las aristas.

       Collection<Comparable> U = new LinkedList<>();

       Comparable primerVerticeEjecutar = getVertices().keySet().iterator().next();
       verticesPrim.add(new TVertice(primerVerticeEjecutar));
       Collection<Comparable> verticesTotales = new LinkedList<>(getVertices().keySet());  // Universo con todos los vértices
       U.add(primerVerticeEjecutar);                                                       // Agrego el primer valor de todos
       verticesTotales.remove(primerVerticeEjecutar);                                      // Remuevo el primer vértice sino me va a contar la arista inversa también.
       TArista aristaTemp;

       while (!verticesTotales.isEmpty()) {
           aristaTemp = aristasCopia.buscarMin(U, verticesTotales);
           aristasCopia.remove(aristaTemp);                                                // Elimino arista para no volver a pasar sobre ella.
           U.add(aristaTemp.getEtiquetaDestino());                                         // Agrego el nuevo vértice al universo.
           aristasPrim.add(aristaTemp);                                                    // Guardo arista de menor costo.
           verticesPrim.add(new TVertice(aristaTemp.getEtiquetaDestino()));                // Agrego el vértice
           verticesTotales.remove(aristaTemp.getEtiquetaDestino());                        // Remuevo el vértice del todos los vértices
           costoMin += aristaTemp.getCosto();
       }
       System.out.println("Costo de Prim: " + costoMin);

       //Descomentar lo de abajo para imprimir los vertices del AACM

        /*System.out.println("-----Vertices del arbol abarcador de costo minimo-------");
        for(TVertice ver : verticesPrim){
            System.out.println(ver.getEtiqueta());
        }*/

       return new TGrafoNoDirigido(verticesPrim, aristasPrim);
    }

    // crear un nuevo grafo con los vertices originales y una lista de aristas vacia
         // ordenar la lista de aristas del grafo original
	 // por cada arista de la lista de aristas del grafo original
         //	obtener en el nuevo grafo el vertice origen de la arista
	 //	obtener en el nuevo grafo el vertices destino de la arista
	 //	si los vertices no estan conectados, agregar la arista al nuevo grafo
         //	si se agregaron n-1 aristas al nuevo grafo, salir del bucle
	 // devolver el nuevo grafo

    public TGrafoNoDirigido Kruskal()
    {
        TGrafoNoDirigido grafoNoDirigido = new TGrafoNoDirigido(this.getVertices().values(), new LinkedList<TArista>());
        this.lasAristas.ordenar();
        TVertice verticeOrigen;
        TVertice verticeDestino;
        for (TArista tArista : lasAristas) {
            verticeOrigen = grafoNoDirigido.getVertices().get(tArista.etiquetaOrigen);
            verticeDestino = grafoNoDirigido.getVertices().get(tArista.etiquetaDestino);
            if (!grafoNoDirigido.conectados(verticeOrigen, verticeDestino)) {
                grafoNoDirigido.insertarArista(tArista);
                grafoNoDirigido.lasAristas.add(tArista);
                grafoNoDirigido.lasAristas.add(tArista.aristaInversa());
                if (grafoNoDirigido.getLasAristas().size() == grafoNoDirigido.getVertices().size() - 1 )
                {
                    return grafoNoDirigido;
                }
            }
        }
        return grafoNoDirigido;
    }


    public Collection<TVertice> bea(Comparable etiquetaOrigen) {
        Collection<TVertice> salida = new LinkedList<>();
        TVertice verticeOrigen = this.getVertices().get(etiquetaOrigen);
        if (verticeOrigen != null) {
            desvisitarVertices();
            verticeOrigen.bea(salida);
        }
        return salida;
    }
	 

    public LinkedList<TVertice> puntosArticulacion(Comparable etOrigen) {
        this.desvisitarVertices();
        LinkedList<TVertice> puntosDeArticulacion = new LinkedList<>();
        TVertice verticeInicio = buscarVertice(etOrigen);
        int[] numeroBP = {0};
        if (verticeInicio != null) {
            verticeInicio.puntosArticulacion(puntosDeArticulacion, numeroBP);
        }
        return puntosDeArticulacion;
    }
    

    public boolean esConexo(){
        boolean esConexo = false;
        Collection<TVertice> visitados = new LinkedList<>();
        TVertice verticeOrigen = this.getVertices().values().iterator().next();
        if (verticeOrigen != null) {
            desvisitarVertices();
            verticeOrigen.bpf(visitados);
            esConexo = visitados.size() == this.getVertices().size();
        }
        return esConexo;
        
    }

    public LinkedList<TVertice> puntosArticulacion(){
        LinkedList<TVertice> puntos = new LinkedList<>();
        int[] count = new int[1];
        desvisitarVertices();
        TVertice verticeOrigen = this.getVertices().values().iterator().next();
        if (verticeOrigen != null) {
            verticeOrigen.puntosArticulacion(puntos, count);
        }
        return puntos;

    }

}
