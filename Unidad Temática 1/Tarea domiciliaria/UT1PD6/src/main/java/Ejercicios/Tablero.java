package Ejercicios;

public class Tablero {
    public static void imprimirTablero(int largo, int ancho) {
        for (int n = 0; n < largo; n++) {
            String row = "";

            for (int m = 0; m < ancho; m++) {
                row += "# ";
            }
            
            System.out.println(row);
        }
    }
}
