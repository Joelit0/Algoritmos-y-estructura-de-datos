import java.util.LinkedList;


public class PruebaGrafo {
    public static void main(String[] args){
       //ejercicio 1:
       TGrafoNoDirigido gnd = UtilGrafos.cargarGrafo("src/vertices.txt", "src/aristas_prim.txt", false, TGrafoNoDirigido.class);
        
       TGrafoNoDirigido prim = gnd.Prim();
        
       Double[][] matrizgrafo = UtilGrafos.obtenerMatrizCostos(gnd.getVertices());
       UtilGrafos.imprimirMatrizMejorado(matrizgrafo, gnd.getVertices(), "Matriz de Grafo");
       
       
       Double[][] matriz = UtilGrafos.obtenerMatrizCostos(prim.getVertices());
       UtilGrafos.imprimirMatrizMejorado(matriz, prim.getVertices(), "Matriz de Prim");
       
       
       //ejercicio 2:
       LinkedList<TVertice> V = new LinkedList<>();
       for (TVertice vr : gnd.getVertices().values()){
            V.add(vr);
        }
       
       TVertice v = V.getFirst();
       TVertice w = V.getLast();
       if (v != w){
            System.out.println(v.getEtiqueta());
            System.out.println(w.getEtiqueta());
            System.out.println(gnd.connectados(v, w));
       }
    }
}
