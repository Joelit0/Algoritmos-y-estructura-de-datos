public class Programa {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        TGrafoNoDirigido gnd = UtilGrafos.cargarGrafo(
          "src/main/java/actores.csv",
          "src/main/java/en_pelicula.csv",
          false,
          TGrafoNoDirigido.class
        );

        System.out.println("Kevin_Bacon - John_Goodman: " + gnd.numBacon("John_Goodman"));
        System.out.println("Kevin_Bacon - Tom_Cruise: " + gnd.numBacon("Tom_Cruise"));
        System.out.println("Kevin_Bacon - Jason_Statham: " + gnd.numBacon("Jason_Statham"));
        System.out.println("Kevin_Bacon - Lukas_Haas: " + gnd.numBacon("Lukas_Haas"));
        System.out.println("Kevin_Bacon - Djimon_Hounsou: " + gnd.numBacon("Djimon_Hounsou"));
    }

}
