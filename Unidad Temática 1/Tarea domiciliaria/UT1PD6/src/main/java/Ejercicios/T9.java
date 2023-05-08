package Ejercicios;

import java.io.*;
import java.io.File;
import java.util.Scanner;
import java.util.Hashtable;
import java.util.Map;

public class T9 {
    public static void transformarTextoT9(String rutaArchivo) throws Exception {
        Hashtable<String, Integer> table = new Hashtable<>(){
            {
                put("ABC", 2); put("DEF", 3); put("GHI", 4); put("JKL", 5);
                put("MNO", 6); put("PQRS", 7); put("TUV", 8); put("WXYZ", 9);
                put(" ", 0); put(".", 1);
            }
        };

        File file = new File(rutaArchivo);
        Scanner scanner = new Scanner(file);
        PrintWriter outputFile = new PrintWriter("src\\main\\java\\Ejercicios\\salidaTextoT9.txt", "UTF-8");

        while (scanner.hasNextLine()) {
            char letter = scanner.nextLine().charAt(0);
            
            for (Map.Entry<String, Integer> e : table.entrySet()) {
                if (e.getKey().contains(String.valueOf(letter).toUpperCase())) {
                   outputFile.println(e.getValue());
                   break;
                }
            }
        }
        
        outputFile.close();
    }

    public static void transformarT9Texto(String rutaArchivo) throws Exception {
        Hashtable<Integer, String> table = new Hashtable<>(){
            {
                put(2, "ABC"); put(3,"DEF"); put(4, "GHI"); put(5, "JKL");
                put(6, "MNO"); put(7, "PQRS"); put(8, "TUV"); put(9, "WXYZ");
                put(0, " "); put(1, ".");
            }
        };

        File file = new File(rutaArchivo);
        Scanner scanner = new Scanner(file);
        PrintWriter outputFile = new PrintWriter("src\\main\\java\\Ejercicios\\salidaT9Texto.txt", "UTF-8");

        while (scanner.hasNextLine()) {
            int number = Integer.parseInt(scanner.nextLine());
            
            for (Map.Entry<Integer, String> e : table.entrySet()) {                
                if (table.containsKey(number)) {
                   String element = table.get(number);
                   outputFile.println(element);
                   break;
                }
            }
        }
        
        outputFile.close();
    }
}