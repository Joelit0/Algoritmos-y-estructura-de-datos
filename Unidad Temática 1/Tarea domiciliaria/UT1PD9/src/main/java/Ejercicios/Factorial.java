/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package Ejercicios;

/**
 *
 * @author joela
 */
public class Factorial {
    public static int factorial(int num) {
        int suma = 1;
        
        for (int i = 1; i <= num; i++) {
            suma *= i;
        }
        
        return suma;
    }
}
