import java.util.*;

public class Main {
  public static void main(String[] args) {
    // Ejercicio 1
    HashMap<String, Integer> map = new HashMap<String, Integer>();

    map.put("primera", 1);
    map.put("segunda", 2);
    map.put("tercera", null);
    map.put("cuarta", 4);
    map.put("quinta", 5);
    map.put("sexta", null);
    map.put("septima", null);

    System.out.println("Antes:");
    printHash(map);
    cleanHash(map);
    System.out.println("Despues:");
    printHash(map);

    // Ejercicio 2

    HashMap<String, String> map2 = new HashMap<String, String>();

    map2.put("Izquierda", "Derecha");
    map2.put("Agua", "Fuego");
    map2.put("Arriba", "Abajo");

    System.out.println("Antes:");
    printHash(map2);

    try {
      revertHash(map2);
    } catch (DuplicatedValuesException e) {
      System.out.println(e.getMessage());
    }

    System.out.println("Despues:");
    printHash(map2);

    // Ejercicio 3

    String palabra;
    Scanner sn = new Scanner(System.in);
    ArrayList<String> palabras = new ArrayList<>();

    while (true) {
      palabra = sn.next();

      if (palabra.equalsIgnoreCase("/salir")) {
        break;
      } else {
        palabras.add(palabra);
      }
    }


    Collections.sort(palabras, new Comparator<String>() {
      public int compare(String o1, String o2) {
       int result;

        if (o1.length() > o2.length()) {
          result = 1;
        } else { result = -1; }

        return result;
      }
    });

    for (String elemento : palabras) {
      System.out.println(elemento);
    }
  }

  // Ejercicio 1
  public static void cleanHash(HashMap<String, Integer> map) {
    Object[] claves = map.keySet().toArray();

    for (Object clave : claves) {
      if (map.get(clave) == null) { map.remove(clave); }
    }
  }

  // Ejercicio 2
  public static void revertHash(HashMap<String, String> map) throws DuplicatedValuesException {
    Object[] claves = map.keySet().toArray();

    for (Object clave : claves) {
      if (clave.equals(map.get(clave))) {
        throw new DuplicatedValuesException("Valores duplicados");
      }

      map.put(map.get(clave), String.valueOf(clave));
      map.remove(clave);
    }
  }

  public static void printHash(HashMap map) {
    System.out.println("---------------------------------------");
    for (Object clave : map.keySet()) {
      System.out.println(clave + " " + map.get(clave));
    }
    System.out.println("---------------------------------------");
  }
}
