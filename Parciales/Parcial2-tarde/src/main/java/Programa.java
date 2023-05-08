import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;


public class Programa {

    
    private static Path FILE_ROOT_PATH = Paths.get("src/main/java/");

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        TProyecto gd = (TProyecto) UtilGrafos.cargarGrafo("src/main/java/modulos.txt", "src/main/java/dependencias.txt",
                false, TProyecto.class);
        
        
        LinkedList<Comparable> laLista = gd.compilarDependencias("junit");
        for (Comparable comp : laLista){
            System.out.println(comp);
        }
        
        escribirArchivoCompilacion("salida.txt", laLista);
        
        LinkedList<Comparable> laLista1 = gd.compilarDependencias("antlr");
        for (Comparable comp : laLista1){
            System.out.println(comp);
        }
        escribirArchivoCompilacion("salida.txt", laLista1);
        
        /* 1. crear un grafo dirigido del tipo "Proyecto", y 
        cargarlo a partir de los archivos "modulos.txt" y "dependencias.txt"
        
        
           2. Ejecutar el método  compilarDependencias con parámetro "junit" (debe
        emitir el listado COMO SE INDICA EN LA LETRA en el archivo "salida.txt"
        
           2. Ejecutar el método  compilarDependencias con parámetro "antlr" (debe
        agregar resultados al listado como se indica en la letra en el archivo "salida.txt"
       
         */

    }
     private static void escribirArchivoCompilacion(String nombreArchivo, LinkedList<Comparable> contenido) {
        String[] lineasArc = new String[contenido.size()];
        String[] cabecera = new String[1];
        cabecera[0] = "compilaciones para " + contenido.getLast();
        contenido.toArray(lineasArc);
        String nombreCompletoArchivo = FILE_ROOT_PATH.resolve(nombreArchivo).toString();

        ManejadorArchivosGenerico.escribirArchivo(nombreCompletoArchivo, cabecera);
        ManejadorArchivosGenerico.escribirArchivo(nombreCompletoArchivo, lineasArc);
    }

}
