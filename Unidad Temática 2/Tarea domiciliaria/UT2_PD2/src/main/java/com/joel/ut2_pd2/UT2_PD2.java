package com.joel.ut2_pd2;

public class UT2_PD2 {

    public static int factorial(int n) {
        if (n == 0) {
            return 1;
        } else {
            return factorial(n-1) * n;
        }
    }
    
    public static int sumaLineal(int[] array, int n) {
        if (n == 1) {
            return array[0];
        } else {
            return sumaLineal(array, n-1) + array[n-1];
        }
    }
    
    public static int potencia(int base, int exponente) {
        if (exponente == 0) {
            return 1;
        } else {
            return potencia(base, exponente - 1) * base;
        }
    }
    
    public static void invertirArray(int [] array, int i, int j) {
        if (i < j) {
            // Intercambio el elemento
            int savedElement = array[i]; 
            array[i] = array[j];
            array[j] = savedElement;
            
            // Vuelvo a llamar a la funcion
            invertirArray(array, i+1, j-1);
        }
    }

    public static void main(String[] args) {
        System.out.println("========================");
        System.out.println("--------Factorial-------");
        System.out.println("========================");
        
        System.out.println("Factorial de 4: " + factorial(4));
        System.out.println("Factorial de 5: " + factorial(5));
        System.out.println("Factorial de 0: " + factorial(0));

        // Cuando se intenta con numeros negativos, java lanza un eror
        // StackOverflowError que en este caso hace referencia a que se hicieron
        // demasiadas llamadas recursivas las cuales no han sido cumplida, es decir,
        // entra en una especie de bucle infinito y se produjo un debordamiento de la pila
        
        System.out.println("========================");
        System.out.println("-------Suma Lineal------");
        System.out.println("========================");
        System.out.println("La suma de los 3 primeros elementos del array es: " +
                           sumaLineal(new int[] {20, 4, 9, 15}, 3));
        
        // Si se le pasa un array vacío a la función se producirá un error con los índices
        // debido a que se intentará acceder a pocisiones que el array no tiene. En otras
        // palabras, el largo del array es de 0 y se quiere acceder a por ejemplo los 3 primeros
        // elementos.
        
        // En otro caso, si se pasa un n negativo o igual a 0 ocurrirá el mismo error de
        // stack overflow explicado anteriormente.
        
        System.out.println("========================");
        System.out.println("---------Potencia-------");
        System.out.println("========================");
        System.out.println("2 elevado a 8 es: " + potencia(2, 8));
        
        // Si se cambiasen los tipos de datos de los parámetros sí sería posible que acepte ciertos numeros reales
        // . Al menos probé cambiando el tipo de dato a double para pasar números decimales y sí aceptó. Y si se
        // utilizara alguna librería que permita el uso de raíces creo que sería posible
        
        // Respondiendo a la pregunta de qué pasaría si los parámetros son números negativos puedo decir que 
        // no habría ningun problema cuando la base sea negativa, el problema se presentaría cuando el exponente
        // lo sea. En este caso ocurriría el problema de StackOverflow explicado en los ejercicios anteriores.
        
        System.out.println("========================");
        System.out.println("------Invertir Array----");
        System.out.println("========================");
                
        // =============================
        // Caso pequeño
        // =============================
        
        int[] array = new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
      
        for (int element : array) {
            System.out.print(element + " ");
        }
        
        System.out.println("");

        invertirArray(array, 3, 8);
        
         for (int element : array) {
            System.out.print(element + " ");
        }
         
        // =============================
        // Vector con solo un elemento
        // =============================
//        int[] array = new int[] {1};
//      
//        for (int element : array) {
//            System.out.print(element + " ");
//        }
//        
//        System.out.println("");
//
//        invertirArray(array, 0, 0);
//        
//         for (int element : array) {
//            System.out.print(element + " ");
//        }

        // =============================
        // Vector vacío
        // =============================

//        int[] array = new int[] {};
//      
//        for (int element : array) {
//            System.out.print(element + " ");
//        }
//        
//        System.out.println("");
//
//        invertirArray(array, 0, 0);
//        
//         for (int element : array) {
//            System.out.print(element + " ");
//        }

        // =============================
        // Indices fuera de rango
        // =============================

//        int[] array = new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
//      
//        for (int element : array) {
//            System.out.print(element + " ");
//        }
//        
//        System.out.println("");
//
//        invertirArray(array, 10, 11);
//        
//         for (int element : array) {
//            System.out.print(element + " ");
//        }
    }
}
