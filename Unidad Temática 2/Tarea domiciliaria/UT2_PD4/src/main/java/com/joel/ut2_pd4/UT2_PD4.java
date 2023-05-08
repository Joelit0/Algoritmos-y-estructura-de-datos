package com.joel.ut2_pd4;
import java.util.HashMap;

public class UT2_PD4 {
    static HashMap<Integer, Integer> memoria = new HashMap<Integer, Integer>();

    public static int fibonacciTopDown(int n) {
        if (!memoria.containsKey(n)) {
            if (n <= 2) {
                memoria.put(n, 1);
                return 1;
            } else {
                int resultado = fibonacciTopDown(n - 1) + fibonacciTopDown(n - 2);
                memoria.put(n, resultado);
                return resultado;
            }
        } else {
            return memoria.get(n);
        }
    }
 
    public static int fibonacciBottomUp(int n) {
        int fibAnterior;
        int fibActual;
        int fibNuevo;
            
        if (n == 0) {
            return 0;
        } else {
            fibAnterior = 0;
            fibActual = 1;
            
            for (int i = 0; i < (n - 1); i++) {
                fibNuevo = fibAnterior + fibActual;
                fibAnterior = fibActual;
                fibActual = fibNuevo;
            }
            
            return fibActual;
        }
    }

    public static void main(String[] args) {
        System.out.println(fibonacciTopDown(40));
        System.out.println(fibonacciBottomUp(40));
    }
}
