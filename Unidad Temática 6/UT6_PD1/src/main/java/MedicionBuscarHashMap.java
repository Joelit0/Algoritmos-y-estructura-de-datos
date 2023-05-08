import java.util.HashMap;

public class MedicionBuscarHashMap extends Medible {
    private TArbolTrieHashMap arbol;

    public MedicionBuscarHashMap() {
        arbol = new TArbolTrieHashMap();
        String[] palabras = ManejadorArchivosGenerico.leerArchivo("src//main//java//palabras1.txt");

        for (String palabra : palabras) {
            this.arbol.insertar(palabra);
        }
    }
    
    @Override
    public void ejecutar(Object... params) {
        String[] palabras = (String[]) params[0];

        for(String palabra : palabras){
            this.arbol.buscar(palabra);
        }
    }

    @Override
    public Object getObjetoAMedirMemoria() {
        return this.arbol;
    }
}
