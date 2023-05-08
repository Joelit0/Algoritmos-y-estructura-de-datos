import java.util.Collection;

public class PruebaGrafo {
    public static void main(String[] args) {
        TGrafoNoDirigido gnd = UtilGrafos.cargarGrafo(
          "src/main/java/verticesBEA.txt",
          "src/main/java/aristasBEA.txt",
          false,
          TGrafoNoDirigido.class
        );

        Collection<TVertice> vertices;

        vertices = gnd.bea("d");

        for (TVertice vertice : vertices){
            System.out.print(vertice.getEtiqueta());
        }
    }
}
