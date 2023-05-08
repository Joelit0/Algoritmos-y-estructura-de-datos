import java.sql.Array;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        String[] clavesInsertar = ManejadorArchivosGenerico.leerArchivo("src/main/java/claves_insertar.txt");
        String[] clavesBuscar = ManejadorArchivosGenerico.leerArchivo("src/main/java/claves_buscar.txt");

        ArrayList<Integer> repeticiones = new ArrayList<Integer>();

        repeticiones.add(70);
        repeticiones.add(75);
        repeticiones.add(80);
        repeticiones.add(85);
        repeticiones.add(90);
        repeticiones.add(91);
        repeticiones.add(92);
        repeticiones.add(93);
        repeticiones.add(94);
        repeticiones.add(95);
        repeticiones.add(96);
        repeticiones.add(97);
        repeticiones.add(98);
        repeticiones.add(99);

        for (int carga : repeticiones) {
            System.out.println("===================================================================");
            System.out.println("FACTOR DE CARGA: " + carga);
            System.out.println("");

            HashNumToNum hashNums= new HashNumToNum(clavesInsertar.length + carga);

            for (String clave : clavesInsertar) { hashNums.insertar(Integer.valueOf(clave)); }

            System.out.println("Inserccion: " + HashNumToNum.inserccion_comp);

            for (String clave : clavesBuscar) { hashNums.buscar(Integer.valueOf(clave)); }

            System.out.println("Busquedas Exitosas: " + HashNumToNum.busqueda_exitosa_comp);
            System.out.println("Busquedas Fallidas: " + HashNumToNum.busqueda_fallida_comp);
            System.out.printf("===================================================================");


        }
    }
}
