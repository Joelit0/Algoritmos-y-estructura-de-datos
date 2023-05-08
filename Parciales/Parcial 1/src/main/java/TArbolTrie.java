import java.util.LinkedList;

public class TArbolTrie implements IArbolTrie {
  private TNodoTrie raiz;

  @Override
  public LinkedList<TDispositivo> buscarDispositivos(String mascaraSubRed) {
    LinkedList res = new LinkedList<TDispositivo>();

    if (raiz != null) {
      raiz.buscarDispositivos(mascaraSubRed, res);
    }

    return res;
  }

  @Override
  public void insertar(TDispositivo unDispositivo) {
    if (raiz == null) {
      raiz = new TNodoTrie("");
    }

    raiz.insertar(unDispositivo);
  }
}
