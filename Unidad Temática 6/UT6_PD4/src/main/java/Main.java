import java.util.HashMap;
import java.util.Map.Entry;
import java.util.List;
import java.util.LinkedList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Map;
import java.util.LinkedHashMap;

public class Main {
  public static void main(String[] args) {
    // Usar un HashMap para almacenar las palabras me pareció lo más adecuado debido
    // a que por concepto, cada palabra debería "apuntar" a un número que indique la
    // cantidad de veces que aparece. Me pareció una estructura bastante rápida y
    // favorecía a la implementación debido a que primero pregunto si la clave existe,
    // si es así, agarro su valor y le sumo 1. En caso contario creo la clave con el
    // valor 1 ya que indica que la palabra aparece por primera vez.

    HashMap<String, Integer> hashFrecuenciaPalabras = new HashMap<String, Integer>();
    String[] lineas = ManejadorArchivosGenerico.leerArchivo("src/main/java/libro.txt");

    for (String linea : lineas) {
      if (!linea.isEmpty()) {
        String[] palabras = linea.split(" ");

        for (String palabra : palabras) {
          palabra = palabra.replaceAll("[^a-zA-Z0-9]", " ").trim();

          if (hashFrecuenciaPalabras.get(palabra) == null) {
            hashFrecuenciaPalabras.put(palabra, 1);
          } else {
            hashFrecuenciaPalabras.put(palabra, hashFrecuenciaPalabras.get(palabra) + 1);
          }
        }
      }
    }

    Map<String, Integer> sortedHashMap = sortByValue(false, hashFrecuenciaPalabras);

    System.out.println("-----------------------------------------");
    System.out.println("Top 10 palabras más frecuentes del libro:");

    int i = 0;

    for (Entry<String, Integer> entry : sortedHashMap.entrySet())
    {
      if (i == 10) { break; }

      System.out.println("    - " + entry.getKey() + ": " + entry.getValue());

      i++;
    }

    System.out.println("-----------------------------------------");
  }

  // Obtuve este fragmento de código el cual me ayuda a ordenar un Hash Map por su valor
  // Ordena sus claves en  ascendiente o descendiente, dependiento de lo especificado
  // por parámetros

  //https://www.javatpoint.com/how-to-sort-hashmap-by-value#:~:text=In%20Java%2C%20sorting%20HashMap%20by,convert%20Set%20into%20the%20List.

  public static Map<String, Integer> sortByValue(boolean order, HashMap map)
  {
    //Convierte el HashMap en una List
    List<Entry<String, Integer>> list = new LinkedList<Entry<String, Integer>>(map.entrySet());

    //Ordena la lista de elementos
    Collections.sort(list, new Comparator<Entry<String, Integer>>()
    {
      public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2)
      {
        if (order)
        {
          // Compara 2 objetos y devuelve un entero
          return o1.getValue().compareTo(o2.getValue());}
        else
        {
          return o2.getValue().compareTo(o1.getValue());
        }
      }
    });

    Map<String, Integer> sortedMap = new LinkedHashMap<String, Integer>();

    for (Entry<String, Integer> entry : list)  { sortedMap.put(entry.getKey(), entry.getValue()); }

    return sortedMap;
  }
}

