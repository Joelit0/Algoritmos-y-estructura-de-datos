import java.util.ArrayList;

public class MedicionBuscarArrayList extends Medible {
    private TArbolTrie arbol ;

    public MedicionBuscarArrayList() {
        arbol = new TArbolTrie();
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
