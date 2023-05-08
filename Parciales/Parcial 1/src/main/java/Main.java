

import java.util.Collection;
import java.util.LinkedList;

public class Main {

    /**
     * @param args
     */
    public static void main(String[] args){
        TArbolTrie trie = new TArbolTrie();

        String[] lineas = ManejadorArchivosGenerico.leerArchivo("src//main//java//dispositivos.txt");

        for (String linea : lineas) {
            String[] splittedLinea = linea.split(",");

            String ip = splittedLinea[0].trim();
            String dispositivo = splittedLinea[1].trim();

            TDispositivo disp = new TDispositivo(ip, dispositivo, null);

            trie.insertar(disp);
        }

        String[] subredes = ManejadorArchivosGenerico.leerArchivo("src//main//java//subredes.txt");

        for (String linea : subredes) {
            LinkedList<TDispositivo> dispositivos = trie.buscarDispositivos(linea);

            for (TDispositivo disp : dispositivos) {
                String[] res = new String[1];

                res[0] = disp.getDirIP() + " " + disp.getNombre();

                ManejadorArchivosGenerico.escribirArchivo(
                  "src//main//java//salida.txt",
                  res
                );
            }
        }

    }
}