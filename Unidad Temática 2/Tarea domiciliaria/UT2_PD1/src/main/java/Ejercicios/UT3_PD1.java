package Ejercicios;

import java.io.File;
import java.util.Scanner;

public class UT3_PD1 {
    public static void miFuncion(int n, int[] arr) {
        int contador = 0;
        
        for (int i = 1; i != n - 1; i++) {
            for (int j = n - 1; j != i-1; j--) {
                contador++;

                if (arr[j] < arr[j-1]) {
                    int savedElement = arr[j];
                    
                    arr[j] = arr[j-1];
                    arr[j-1] = savedElement;
                }
            }
        }
 
        System.out.println("Primer elemento: " + arr[0]);
        System.out.println("Segundo elemento: " + arr[n-1]);
        System.out.println("Contador: " + contador);
    }
}

class Main {
    public static void main(String[] args) throws Exception {
        File file = new File("src\\main\\java\\Ejercicios\\numeros.txt");
        Scanner sc = new Scanner(file);
        
        int largoLista = Integer.parseInt(sc.nextLine());
        int[] array = obtenerArreglo(sc, largoLista);
        
        UT3_PD1.miFuncion(largoLista, array);
    }
    
    public static int[] obtenerArreglo(Scanner sc, int largoLista) {
        int[] array = new int[largoLista];

        for (int i = 0; i < largoLista; i++) {
           array[i] = Integer.parseInt(sc.nextLine()); 
        }
        
        return array;
    }
}
