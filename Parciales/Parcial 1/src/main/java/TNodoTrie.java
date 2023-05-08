import java.util.ArrayList;
import java.util.LinkedList;

public class TNodoTrie implements INodoTrie {
  private TNodoTrie primerHijo;
  private TNodoTrie hermanoDerecho;
  private Comparable etiqueta;
  private boolean esPalabra;
  private TDispositivo dispositivo;

  public void setEsPalabra(boolean esPalabra) {
    this.esPalabra = esPalabra;
  }

  public void setDispositivo(TDispositivo dispositivo) {
    this.dispositivo = dispositivo;
  }

  public boolean isEsPalabra() {
    return esPalabra;
  }

  public TDispositivo getDispositivo() {
    return dispositivo;
  }

  public TNodoTrie(Comparable etiqueta) {
    this.etiqueta =  etiqueta;
  }

  public Comparable getEtiqueta() {
    return etiqueta;
  }

  public TNodoTrie getPrimerHijo() {
    return primerHijo;
  }

  public TNodoTrie getHermanoDerecho() {
    return hermanoDerecho;
  }

  public void setHermanoDerecho(TNodoTrie hermanoDerecho) {
    this.hermanoDerecho = hermanoDerecho;
  }

  @Override
  public void insertar(TDispositivo unDispositivo) {
    String ip = unDispositivo.getDirIP();
    TNodoTrie nodoPadre = this;

    for (char numero : ip.toCharArray()) {
      String numeroToString = String.valueOf(numero);

      if (!numeroToString.equals(".")) {
        TNodoTrie hijo = nodoPadre.obtenerHijo(numeroToString);

        if (hijo == null) {
          nodoPadre.insertarHijo(numeroToString);
          nodoPadre = nodoPadre.obtenerHijo(numeroToString);
        } else {
          nodoPadre = hijo;
        }
      }
    }

    nodoPadre.setDispositivo(unDispositivo);
    nodoPadre.setEsPalabra(true);
  }

  public TNodoTrie obtenerHijo(Comparable etiqueta) {
    TNodoTrie aux = this.primerHijo;

    while (aux != null) {
      if (aux.getEtiqueta().compareTo(etiqueta) == 0) {
        return aux;
      }

      aux = aux.getHermanoDerecho();
    }

    return null;
  }

  public boolean insertarHijo(Comparable etiqueta) {
    if (this.primerHijo == null) {
      this.primerHijo = new TNodoTrie(etiqueta);
    } else {
      TNodoTrie aux = this.primerHijo;

      while (aux.getHermanoDerecho() != null) {
        aux = aux.getHermanoDerecho();
      }

      aux.setHermanoDerecho(new TNodoTrie(etiqueta));
    }

    return true;
  }

  @Override
  public void buscarDispositivos(String primerosDigitos, LinkedList<TDispositivo> dispositivos) {
    TNodoTrie nodo = this;

    for (char digito : primerosDigitos.toCharArray()) {
      String numeroToString = String.valueOf(digito);

      if (!numeroToString.equals(".")) {
        TNodoTrie hijo = nodo.obtenerHijo(numeroToString);

        if (hijo == null) {
          return;
        } else {
          nodo = hijo;
        }
      }
    }

    this.preOrden(primerosDigitos, dispositivos, nodo);
  }

  public void preOrden(String s, LinkedList acumulador, TNodoTrie nodo) {
    if (nodo != null) {
      if (nodo.esPalabra) {
        acumulador.add(nodo.getDispositivo());
      }

      for (TNodoTrie hijo : nodo.getHijos()) {
          preOrden(s + this.etiqueta, acumulador, hijo);
      }
    }
  }

  public ArrayList<TNodoTrie> getHijos() {
    ArrayList<TNodoTrie> res = new ArrayList<TNodoTrie>();

    if (this.primerHijo != null) {
      TNodoTrie aux = this.primerHijo;

      while (aux != null) {
        res.add(aux);
        aux = aux.getHermanoDerecho();
      }
    }

    return res;
  }
}


