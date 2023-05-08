package Ejercicios;

enum Day {
  SUNDAY,
  MONDAY,
  TUESDAY,
  WEDNESDAY,
  THURSDAY,
  FRIDAY,
  SATURDAY
}

public class Days {
    public static void printDays() {
        // Aquí lo que hago es imprimir cada uno de los valores de mi enum
        // Como lo dice el ejercicio, values() devuelve los valores del enum en el orden que se declararon
        for (Day day : Day.values()) {
            System.out.println(day);
        }
   }
}
