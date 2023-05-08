

import java.util.Collection;
import java.util.LinkedList;


public class Main {

    /**
     * @param args
     */
    public static void main(String[] args){
        TArbolTrie trie = new TArbolTrie();

       // CARGAR EN EL TRIE LOS DISPOSITIVOS PARTIR DEL ARCHIVO DISPOSITIVOS.TXT
        
        
        String subRed = "" ; // utilizar el indicado en el archivo "subredes.txt"
        
        LinkedList<TDispositivo> dispositivos = trie.buscarDispositivos(subRed);
        
        // crear el archivo "salida.txt", con los dispositivos(1 por linea) 
        // correspondientes a la subred indicada
        // imprimir Nombre y dirección de  IP, 
        
        
        ManejadorArchivosGenerico.escribirArchivo("./src/salida.txt",.....);
        
    }
}