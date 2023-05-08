import java.util.Collection;
import java.util.LinkedList;

public class PruebaGrafo {
    public static void main(String[] args) {
        TGrafoDirigido gd = (TGrafoDirigido) UtilGrafos.cargarGrafo(
          "src/main/java/aeropuertos.txt",
          "src/main/java/conexiones.txt",
          false,
          TGrafoDirigido.class
        );

        Double[][] matriz = UtilGrafos.obtenerMatrizCostos(gd.getVertices());
        UtilGrafos.imprimirMatrizMejorado(matriz, gd.getVertices(), "Matriz de costos");

        boolean[][] mwarshall = gd.warshall();
        UtilGrafos.imprimirMatrizWarshall(mwarshall, gd.getVertices(), "Matriz de WARSHALL");

        Double[][] mfloyd = gd.floyd();
        UtilGrafos.imprimirMatrizMejorado(mfloyd, gd.getVertices(), "Matriz de FLOYD");

        Double[] dijkstra= gd.dijkstra("Asuncion");

        for (Double punto : dijkstra) {
            System.out.print(punto + " - ");
        }

        System.out.println();
        System.out.println(gd.centroDelGrafo());
        System.out.println("Existe conexión entre Montevideo y Curitiba? " + gd.existeConexion("Montevideo", "Curitiba"));
        System.out.println("Existe conexión entre Porto Alegre y Santos? " + gd.existeConexion("Porto_Alegre", "Santos"));

        Collection<TVertice> recorrido = gd.bpf();

        for (TVertice etVert : recorrido) {
            System.out.print(etVert.getEtiqueta() + " ");
        }

        System.out.println();

        LinkedList<TVertice> vertices = gd.clasToplogica("Asuncion");

        for(TVertice vertice : vertices) {
            System.out.print(vertice.getEtiqueta() + " - ");
        }

        TCaminos caminos = gd.todosLosCaminos("Santos", "Curitiba");
        caminos.imprimirCaminosConsola();
    }
}
