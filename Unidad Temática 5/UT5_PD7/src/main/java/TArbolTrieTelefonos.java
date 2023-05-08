import java.util.LinkedList;

public class TArbolTrieTelefonos implements IArbolTrieTelefonos {
    private TNodoTrieTelefonos raiz = new TNodoTrieTelefonos("");

    public TNodoTrieTelefonos getRaiz() {
        return raiz;
    }

    public void setRaiz(TNodoTrieTelefonos raiz) {
        this.raiz = raiz;
    }

    @Override
    public LinkedList<TAbonado> buscarTelefonos(String pais, String area) {
        LinkedList<TAbonado> lista = new LinkedList<>();

        this.raiz.buscarTelefonos(pais, lista);

        return lista;
    }

    @Override
    public void insertar(TAbonado unAbonado) {
        String numero = unAbonado.getTelefono();
        String nombre = unAbonado.getNombre();

        TNodoTrieTelefonos nodoPadre = this.raiz;

        for (char digito : numero.toCharArray()) {
            TNodoTrieTelefonos hijo = nodoPadre.obtenerHijo(digito);

            if (hijo == null) {
                nodoPadre.insertarHijo(digito);
                nodoPadre = nodoPadre.obtenerHijo(digito);
            } else {
                nodoPadre = hijo;
            }
        }

        nodoPadre.setNombreCliente(nombre);
    }
}


