import java.util.LinkedList;
import java.util.List;

public class Main {
    public static void main(String[] args){
        TArbolTrieTelefonos trieAbonados = new TArbolTrieTelefonos();

        String[] abonados = ManejadorArchivosGenerico.leerArchivo("src//main//java//abonados.txt");

        LinkedList<TAbonado> ListaAbonados = new LinkedList<TAbonado>();

        // Insertar en trie
        for (String abonado : abonados) {
            String numero = abonado.split(",")[0];
            String nombre = abonado.split(",")[1];

            TAbonado nuevoAbonado = new TAbonado(numero, nombre);
            ListaAbonados.add(nuevoAbonado);
            trieAbonados.insertar(nuevoAbonado);
        }



        String codigoPais = "598" ; // utilizar el indicado en el archivo "codigos.txt"
        String codigoArea = "93" ;// utilizar el indicado en el archivo "codigos.txt"
        // crear el archivo "salida.txt", con los abonados (1 por linea) 
        // correspondientes al pais y area 
        // imprimir Nombre y teléfono, 
        // ordenados alfabeticamente por nombre
    }
}