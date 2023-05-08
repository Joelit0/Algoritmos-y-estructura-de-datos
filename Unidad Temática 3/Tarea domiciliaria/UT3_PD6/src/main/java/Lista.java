public class Lista<T> implements ILista<T> {
    private INodo<T> primero;

    @Override
    public void insertar(INodo<T> nodo) {
        if (this.primero == null) {
           this.primero = nodo;
           return;
        }

        INodo<T> temp = this.primero;
        
        while (temp.getSiguiente() != null) {
            temp = temp.getSiguiente();
        }
        
        temp.setSiguiente(nodo);
    }

    @Override
    public INodo<T> buscar(Comparable clave) {
        if (this.primero == null) {
            return null;
        }

        INodo<T> temp = this.primero;

        while (temp != null) {
            int equal = temp.getEtiqueta().compareTo(clave);

            if (equal == 0) {
                return temp;
            }

            temp = temp.getSiguiente();
        }

        return null;
    }

    @Override
    public boolean eliminar(Comparable clave) {
        if (this.primero == null) {
            return false;
        }

        int equals = this.primero.getEtiqueta().compareTo(clave);

        if (equals == 0) {
            this.primero = this.primero.getSiguiente();
            return true;
        }

        INodo<T> temp = this.primero;

        while (temp.getSiguiente() != null) {
            equals = temp.getSiguiente().getEtiqueta().compareTo(clave);

            if (equals == 0) {
                temp.setSiguiente(temp.getSiguiente().getSiguiente());
                return true;
            } else {
                temp = temp.getSiguiente();
            }
        }

        return false;
    }

    @Override
    public String imprimir() {
        String claves = "";
        INodo<T> temp = this.primero;

        while (temp != null) {
            claves += temp.getEtiqueta() + ", ";
            temp = temp.getSiguiente();
        }

        return claves;
    }

    @Override
    public String imprimir(String separador) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int cantElementos() {
        INodo<T> temp = this.primero;
        int total = 0;

        while (temp != null) {
            total += 1;
            temp = temp.getSiguiente();
        }

        return total;
    }

    @Override
    public boolean esVacia() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public INodo<T> getPrimero() {
        return this.primero;
    }

    @Override
    public void setPrimero(INodo<T> unNodo) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
