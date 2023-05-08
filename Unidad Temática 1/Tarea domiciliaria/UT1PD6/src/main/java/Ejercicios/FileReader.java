/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ejercicios;

import java.io.File;
import java.util.Scanner;

public class FileReader {
    public static void leerEntradaArchivo(String rutaArchivo) throws Exception {
      File file = new File(rutaArchivo);
      Scanner sc = new Scanner(file);
      int enteroLeido = Integer.parseInt(sc.nextLine());
      float flotanteLeido = Float.parseFloat(sc.nextLine());
      String nombreLeido = sc.nextLine();
      float suma = enteroLeido + flotanteLeido;
      int divisionEntera = Math.round(flotanteLeido) / enteroLeido;
      float restoDivision = flotanteLeido % enteroLeido;

      System.out.println(
          "El entero leído es: " + enteroLeido + "\n" +
          "El número de punto flotante es: " + flotanteLeido + "\n" +
          "La cadena leída es: '" + nombreLeido + "'\n" +
          "¡Hola " + nombreLeido + "! La suma de " + enteroLeido + " y " + flotanteLeido + " es " + suma + "\n" +
          "La división entera de " + flotanteLeido + " y " + enteroLeido + " es " + divisionEntera + " y su resto es " + restoDivision 
      );
    }

    public static void leerEntradaStdin() throws Exception {
        final double pi = 3.14;  
        Scanner input = new Scanner(System.in);
        System.out.print("Ingrese el radio: ");
        int radio = input.nextInt();

        System.out.println("El área del círculo es: " + pi*(radio*radio));  
         
        System.out.println("El perímetro del círculo es: " + 2*(pi*radio));
    }
}