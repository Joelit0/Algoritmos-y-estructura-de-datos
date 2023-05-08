
import java.util.LinkedList;

public class MedicionPredecirTrie extends Medible{

    private TArbolTrie TArbolTrie;

    public MedicionPredecirTrie(TArbolTrie TArbolTrie) {
        this.TArbolTrie = TArbolTrie;
    }
    
    @Override
    public void ejecutar(Object... params) {
        int repeticion = (int) params[0];
        String[] palabras = (String[]) params[1];
        for(int i = 0; i < repeticion; i++){
            for(String palabra : palabras){
                TArbolTrie.predecir(palabra);
            }
        }
    }

    @Override
    public Object getObjetoAMedirMemoria() {
        return this.TArbolTrie;
    }
    
    
}
