package Ejercicios;

// Las llamadas a cada uno de los ejercicios se encuentran en este Main
// Cuando se quiera ver el resultado de uno de los ejercicios recomiendo
// comentar el resto para que no haya tanta cosa, aunque funciona igual si no
// se comentan.

public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println("============================");
        System.out.println("Ejercicio 1");
        System.out.println("============================");
        Tablero.imprimirTablero(8, 8);
        
        System.out.println("============================");
        System.out.println("Ejercicio 2");
        System.out.println("============================");
        System.out.println("Parte A");
        System.out.println("----------------------------");
        FileReader.leerEntradaArchivo("src\\main\\java\\Ejercicios\\entrada.txt");
        System.out.println("----------------------------");
        System.out.println("Parte B");
        System.out.println("----------------------------");
        FileReader.leerEntradaStdin();
        System.out.println("----------------------------");

        System.out.println("============================");
        System.out.println("Ejercicio 3");
        System.out.println("============================");
        System.out.println("Parte A");
        System.out.println("----------------------------");
        T9.transformarTextoT9("src\\main\\java\\Ejercicios\\entradaTextoT9.txt");
        System.out.println("----------------------------");
        System.out.println("Parte B");
        System.out.println("----------------------------");
        T9.transformarT9Texto("src\\main\\java\\Ejercicios\\entradaT9Texto.txt");
        System.out.println("----------------------------");

        System.out.println("============================");
        System.out.println("Ejercicio 4");
        System.out.println("============================");
        System.out.println(
            MultiplyVectors.multiply(
                new int[] {2, 2},
                new int[] {10, 2}
            )
        );
    }
}
