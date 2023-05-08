public class MedicionBuscarTrie extends Medible{

    private TArbolTrie TArbolTrie;

    public MedicionBuscarTrie(TArbolTrie TArbolTrie) {
        this.TArbolTrie = TArbolTrie;
    }
    
    @Override
    public void ejecutar(Object... params) {
        int repeticion = (int) params[0];
        String[] palabras = (String[]) params[1];
        for(int i = 0; i < repeticion; i++){
            for(String palabra : palabras){
                TArbolTrie.buscar(palabra);
            }
        }
    }

    @Override
    public Object getObjetoAMedirMemoria() {
        return this.TArbolTrie;
    }
}
