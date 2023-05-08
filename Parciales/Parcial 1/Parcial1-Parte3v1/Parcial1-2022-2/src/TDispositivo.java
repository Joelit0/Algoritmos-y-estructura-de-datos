/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ernesto
 */
public class TDispositivo implements Comparable {
     private String direccionIP;
    private String nombre;
    private Object datos;

    TDispositivo(String dirIP, String unNombre, Object unosDatos) {

        this.direccionIP = dirIP;
        this.nombre = unNombre;
        this.datos = unosDatos;
    }

    public String getDirIP() {
        return this.direccionIP;
    }
    public String getNombre() {
        return this.nombre;
    }

    public Object getDatos() {
        return this.datos;
    }

    
    @Override
    public int compareTo(Object o) {
        return (this.nombre.compareTo(((TDispositivo)o).getDirIP()));
    }
}
