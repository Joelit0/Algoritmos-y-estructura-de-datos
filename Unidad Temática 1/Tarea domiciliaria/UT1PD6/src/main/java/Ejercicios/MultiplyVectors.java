/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ejercicios;

/**
 *
 * @author joela
 */
public class MultiplyVectors {
    public static int multiply(int[] vect1, int[] vect2) {
        int total = 0;

        if (vect1.length == vect2.length) {
            for(int i = 0; i < vect2.length; i++) {
                total += vect1[i] * vect2[i];
            }
        }
        
        return total;
    }
}
