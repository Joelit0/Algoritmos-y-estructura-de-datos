package com.joel.ut3_pd3;

public class Main {
    public static void main(String[] args) {
        Lista<Integer> lista = new Lista<>(); // Creo la lista

        // Insertar elemento en lista
        // Los nodos que inserto tienen null en siguiente
        // debido a que el método se encarga asignar el siguiente.
        lista.insertar(new Nodo<>(3, 3, null));
        lista.insertar(new Nodo<>(4, 4, null));
        lista.insertar(new Nodo<>(5, 5, null));

        // Imprimir lista
        System.out.println(lista.imprimir());

        // Buscar elemento en una lista
        // Lo imprimo en pantalla para mostrar que cuando se encontró el elemento
        // imprime algo. En caso contrario, imprime null.
        System.out.println(lista.buscar(3));

        // Eliminar un elemento de la lista
        System.out.println(lista.eliminar(3)); // Elimino el elemento con la clave 3.
        System.out.println(lista.imprimir()); // Imprimo la lista para ver el resultado.
    }
}