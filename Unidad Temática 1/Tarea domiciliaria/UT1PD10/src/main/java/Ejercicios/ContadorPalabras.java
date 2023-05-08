// Lenguaje natural:
//      Se necesita una función que sea capaz de devolver los elementos comunes 
//      entre 2 vectores de Strings. La solución pensada fue recorrer cada uno de los
//      elementos del primer vector y por cada elemento, recorrer todos los del otro
//      en cada iteración de esas verificar si los elementos son iguales. En caso de que si,
//      agregarlo a la lista de resultado(que será la retornada por el método)

package Ejercicios;

// Para este código utilizé el contadorPalabras desarrollado en clase con mi equipo

import java.util.ArrayList; // Decidi usar ArrayList ya que puedo almacenar elementos de manera dinamica
                            // Por lo que no necesito definir el largo del Array previamente, ademas
                            // La forma de agregar elementos es mucho mas simple.
                            // Al fin y al cabo, el problema queda solucionado y es mas legible a mi parecer

public class ContadorPalabras{
    public static ArrayList<String> palabrasComunes(ArrayList<String> palabras_1, ArrayList<String> palabras_2){
       ArrayList<String> resultado = new ArrayList<String>();
  
       for (int i = 0; i < palabras_1.size(); i++) {
            String element_palabras_1 = palabras_1.get(i);
           
            for (int x = 0; x < palabras_2.size(); x++) {
                String element_palabras_2 = palabras_2.get(x);

                if (element_palabras_1.equals(element_palabras_2) & !resultado.contains(element_palabras_1))
                {
                    resultado.add(element_palabras_1);
                }
            }

       }
       
       return resultado;
    }
        
    private static boolean isSpace(char text) { return text == ' '; }
    
    
}