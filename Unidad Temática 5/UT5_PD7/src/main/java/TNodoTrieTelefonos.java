

import sun.awt.image.ByteComponentRaster;

import java.util.LinkedList;

public class TNodoTrieTelefonos implements INodoTrieTelefonos {
    private Comparable etiqueta;
    private TNodoTrieTelefonos primerHijo;
    private TNodoTrieTelefonos hermanoDerecho;
    private String nombreCliente;

    public TNodoTrieTelefonos(Comparable etiqueta) {
        this.etiqueta = etiqueta;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public Comparable getEtiqueta() {
        return etiqueta;
    }

    public TNodoTrieTelefonos getPrimerHijo() {
        return primerHijo;
    }

    public TNodoTrieTelefonos getHermanoDerecho() {
        return hermanoDerecho;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public void setEtiqueta(Comparable etiqueta) {
        this.etiqueta = etiqueta;
    }

    public void setPrimerHijo(TNodoTrieTelefonos primerHijo) {
        this.primerHijo = primerHijo;
    }

    public void setHermanoDerecho(TNodoTrieTelefonos hermanoDerecho) {
        this.hermanoDerecho = hermanoDerecho;
    }

    @Override
    public void buscarTelefonos(String primerosDigitos, LinkedList<TAbonado> abonados) {
        TNodoTrieTelefonos nodoPadre = this;

        for (char digito : primerosDigitos.toCharArray()) {
            TNodoTrieTelefonos hijo = nodoPadre.obtenerHijo(digito);

            if (hijo == null) {
                return;
            } else {
                nodoPadre = hijo;
            }
        }
    }

    @Override
    public void insertar(TAbonado unAbonado) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public TNodoTrieTelefonos obtenerHijo(Comparable etiqueta) {
        TNodoTrieTelefonos aux = this.primerHijo;

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
            this.primerHijo = new TNodoTrieTelefonos(etiqueta);
        } else {
            TNodoTrieTelefonos aux = this.primerHijo;

            while (aux.getHermanoDerecho() != null) {
                aux = aux.getHermanoDerecho();
            }

            aux.setHermanoDerecho(new TNodoTrieTelefonos(etiqueta));
        }

        return true;
    }
}
