public class TAbonado implements Comparable {
    private String nombre;
    private String telefono;

    public TAbonado(String unNombre, String unTelefono) {
        this.nombre = unNombre;
        this.telefono = unTelefono;
    }

    public String getNombre() {
        return this.nombre;
    }

    public String getTelefono() {
        return this.telefono;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Override
    public int compareTo(Object o) {
        return (this.nombre.compareTo(((TAbonado)o).getNombre()));
    }
}
