import java.lang.instrument.Instrumentation;
import java.lang.instrument.Instrumentation;

public class Main {
    public static void main(String[] args){
        // Cambiar estructura a HashMap e insertar palabras
        TArbolTrieHashMap trie = new TArbolTrieHashMap();

        String[] palabras = ManejadorArchivosGenerico.leerArchivo("src//main//java//palabras1.txt");

        for (String p : palabras) { trie.insertar(p); }

        trie.imprimir();

        // Medir diferencias entre usar Array y HashMap
        Medible[] medibles = new Medible[2];
        medibles[0] = new MedicionBuscarArrayList();
        medibles[1] = new MedicionBuscarHashMap();

        Object[] params = {
          ManejadorArchivosGenerico.leerArchivo(
          "src//main//java//palabras1.txt"
          )
        };

        Medicion mi;

        for (Medible m : medibles) {
            mi = m.medir(params);
            mi.print();
            System.out.println(mi.getTexto() + "," + mi.getTiempoEjecucion().toString() + "," + mi.getMemoria().toString());
        }
    }
}