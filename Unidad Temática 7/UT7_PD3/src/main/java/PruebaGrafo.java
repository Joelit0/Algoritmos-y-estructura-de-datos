import java.util.Collection;

public class PruebaGrafo {

    public static void main(String[] args) {
        TGrafoDirigido gd = (TGrafoDirigido) UtilGrafos.cargarGrafo(
          "src/main/java/aeropuertos.txt",
          "src/main/java/conexiones.txt",
          false,
          TGrafoDirigido.class
        );

        int[][] matriz = UtilGrafos.obtenerMatrizCostos(gd.getVertices());
        UtilGrafos.imprimirMatrizMejorado(matriz, gd.getVertices(), "Matriz");

        int[][] mfloyd = gd.floyd();
        UtilGrafos.imprimirMatrizMejorado(mfloyd, gd.getVertices(), "Matriz luego de FLOYD");

        // El costo de volar de Mvdeo a Rio de janeiro es 3780
        // El costo de volar de Mvdeo a Curitiba es 2580
        // Los servicios de mantenimiento se instalan en Porto Alegre

        // Indica si existe arista entre el origen y destino
        System.out.println("Existe conexión entre Montevideo y Curitiba? " + gd.existeConexion("Montevideo", "Curitiba"));
        System.out.println("Existe conexión entre Porto Alegre y Santos? " + gd.existeConexion("Porto_Alegre", "Santos"));

        Collection<TVertice> recorrido = gd.bpf();

        for (TVertice etVert : recorrido) {
            System.out.print(etVert.getEtiqueta() + " ");
        }
    }
}
