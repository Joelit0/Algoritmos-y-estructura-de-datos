package Ejercicios;

import java.util.ArrayList;
import java.util.Random;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

// No me quedó del todo clara la parte C de este ejercicio. Lo que yo entendí
// por la letra es que se lean 2 líneas aleatorias de un archivo de texto y
// luego se utilizen esas líneas para el método desarrollado en la parte B.
// Como dicho método recibe un vector de palabras, asumí que tendría que separar en un vector
// de palabras cada una de las 2 líneas agarradas y luego enviarlas como parámetro al método.

// No estoy del todo seguro si el ejercicio pide esto(asumo que debe ser mas simple)
// Pero no queda muy claro cual es el problema y tiende a interpretarse distinto

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        // Obtiene un ArrayList con todas las palabras de una linea aleatoria
        // Para cada variable
        ArrayList<String> palabras_1 = getRandomLineWords("src\\main\\java\\Ejercicios\\file.txt");
        ArrayList<String> palabras_2 = getRandomLineWords("src\\main\\java\\Ejercicios\\file.txt");

        // Aqui imprimo ambos arrays para poder ver si el resultado es válido
        // Verifico a ojo cuales palabras tienen en comun y veo si el resultado es válido
        System.out.println(palabras_1);
        System.out.println(palabras_2);

        // Llama al metodo que se pide en la parte B
        System.out.println(
            ContadorPalabras.palabrasComunes(
                palabras_1, palabras_2
            )
        );
    }
    
    // En este metodo obtengo un ArrayList con las palabras de una linea aleatoria
    private static ArrayList<String> getRandomLineWords(String path) throws FileNotFoundException {
        File file = new File(path);
        Scanner scanner = new Scanner(file);
        int cantLines = getCantLines(file);
        int randomNumb = new Random().nextInt(cantLines);
        String line = "";

        int index = 0;

        while (scanner.hasNextLine()) {
            if (index == randomNumb) {
                line = scanner.nextLine();
                break;
            }
            
            index++;
            scanner.nextLine();
        }
        
        return getLineWords(line);
    }

    // Este método es una especie de helper que en base a un archivo me dice cuantas líneas tiene
    private static int getCantLines(File file) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);

        int cantLines = 0;

        while (scanner.hasNextLine()) {
            scanner.nextLine();
            cantLines++;
        }

        return cantLines;
    }

    // Este método me devuelve un ArrayList con las palabras de un String
    private static ArrayList<String> getLineWords(String line) {
        ArrayList<String> palabras = new ArrayList<String>();
        
        line = line.trim() + " ";
        String palabra = "";
 
        for(char element : line.toCharArray()) {
            if (!isSpace(element)) {
                palabra += element;
            } else {
                palabras.add(palabra);
                palabra = "";
            }
        }
        
        return palabras;
    }
    
    // Este método verifica si un char es un espacio o no. Devuelve un booleano
    private static boolean isSpace(char text) {
        return text == ' ';
    }
}
