package TA6;

import java.util.LinkedList;

public class PruebaGrafo {

    public static void main(String[] args) {
        TGrafoNoDirigido gnd = (TGrafoNoDirigido) UtilGrafos.cargarGrafo(
                "src/TA6/vert2.txt",
                "src/TA6/ari2.txt",
                false, TGrafoNoDirigido.class);

        TGrafoNoDirigido grafoPrim = gnd.Prim();
        System.out.println("cantidad de vertices del grafo por prim:" + grafoPrim.getVertices().size());
        /*
        mostrar las aristas del AAM por Prim y el costo total
         */

        TGrafoNoDirigido grafoKruskal = gnd.Kruskal();
        System.out.println("cantidad de vertices del grafo por kruskal:" + grafoKruskal.getVertices().size());
        /*
        mostrar las aristas del AAM por Kruskal y el costo total
         */
        LinkedList<TArista> aristas = grafoKruskal.getLasAristas();
        int costoTotal = 0;
        for (TArista a : aristas) {
            System.out.println(a.getEtiquetaOrigen() + "," + a.getEtiquetaDestino());
            costoTotal += a.getCosto();
        }
        System.out.println("Costo total de Kruskal: " + costoTotal / 2);
    }
}