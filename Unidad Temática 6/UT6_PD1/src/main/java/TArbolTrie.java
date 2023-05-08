import java.io.Serializable;
import java.util.LinkedList;

public class TArbolTrie implements IArbolTrie, Serializable {
    private TNodoTrie raiz;

    @Override
    public void insertar(String palabra) {
        if (raiz == null) {
            raiz = new TNodoTrie("");
        }
        raiz.insertar(palabra);
    }

    @Override
    public void imprimir() {
        if (raiz != null) {
            raiz.imprimir();
        }
    }

    @Override
    public int buscar(String palabra) {
        return this.raiz.buscar(palabra);
    }

    @Override
    public LinkedList predecir(String prefijo) {
        if (raiz == null) {
            return new LinkedList();
        }
        return raiz.predecir(prefijo);
    }
    
    
}
