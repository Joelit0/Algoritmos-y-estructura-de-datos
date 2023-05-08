import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.LinkedList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Ernesto
 */
public class Programa {
    private static Path FILE_ROOT_PATH = Paths.get("src/main/java/");

    public static void main(String[] args) {
        TGrafoDirigido vuelosAED = (TGrafoDirigido) UtilGrafos.cargarGrafo(
          "src/main/java/aeropuertos.txt",
          "src/main/java/vuelos.txt",
          false,
          TGrafoDirigido.class
        );

        String[] aeropuertos = new String[4];

        aeropuertos[0] = "PHL";
        aeropuertos[1] = "ORF";
        aeropuertos[2] = "ABQ";
        aeropuertos[3] = "ZZZ";

        for (String aeropuerto : aeropuertos) {
            String[] lista = new String[1];
            String value;
            boolean esPosible = vuelosAED.esPosibleLlegarATodos(aeropuerto);

            if (esPosible) {
                value = "S";
            } else {
                value = "N";
            }

            System.out.println(aeropuerto + ", " + value);

            lista[0] = aeropuerto + ", " + value;
            escribirArchivoCompilacion("salida.txt", lista);
        }
    }

    private static void escribirArchivoCompilacion(String nombreArchivo, String[] contenido) {
        String nombreCompletoArchivo = FILE_ROOT_PATH.resolve(nombreArchivo).toString();

        ManejadorArchivosGenerico.escribirArchivo(nombreCompletoArchivo, contenido);
    }
}


