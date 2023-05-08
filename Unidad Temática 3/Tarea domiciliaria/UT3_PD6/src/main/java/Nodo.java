public class Nodo<T> implements INodo<T> {
    private final Comparable etiqueta;
    private T dato;
    private INodo<T> siguiente = null;

    public Nodo(Comparable etiqueta, T dato ) {
        this.etiqueta = etiqueta;
        this.dato = dato;
    }

    public T getDato() {
        return this.dato;
    }

    public void setDato(T dato) {
        this.dato = dato;
    }

    @Override
    public Comparable getEtiqueta() {
        return this.etiqueta;
    }

    @Override
    public int compareTo(INodo<T> unNodo) {
        return 0;
    }

    @Override
    public void imprimir() {
        System.out.println(dato.toString());
    }

    @Override
    public void imprimirEtiqueta() {
        System.out.println(this.etiqueta);
    }

    public Nodo<T> clonar() {
        return new Nodo<>(this.etiqueta, this.dato);
    }

    public boolean equals(Nodo unNodo) {
        return this.dato.equals(unNodo.getDato());
    }

    @Override
    public int compareTo(Comparable etiqueta) {
        return this.etiqueta.compareTo(etiqueta);
    }

    @Override
    public INodo<T> getSiguiente() {
        return this.siguiente;
    }

    @Override
    public void setSiguiente(INodo<T> nodo) {
        this.siguiente = nodo;
    }

}
