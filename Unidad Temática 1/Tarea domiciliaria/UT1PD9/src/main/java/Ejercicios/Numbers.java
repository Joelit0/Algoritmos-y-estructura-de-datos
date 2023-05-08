package Ejercicios;

public class Numbers {
    public static int sumarImparesOParesPrevios(int num) {
        int sum = 0;
        int i = 0;
        
        // Decidí usar un solo while ya que me pareció innecesario usar uno para cada caso
        // Aprovechando de que no cambio el valor de num en ningua parte, en cada llamada a este método
        // el while va a tomar solo un camino, si es primo o no

        while (i <= num) {
            if (isPrime(num)) {
                if (esPar(i)) { sum += i; }
            } else {
                if (!esPar(i)) { sum += i; }
            }
            
            i++;
        }
        
        return sum;
    }

    private static boolean esPar(int num) {
        return (num % 2 == 0);
    }

    private static boolean isPrime(long n) {
        boolean prime = true;
        
        for (long i = 3; i <= Math.sqrt(n); i += 2) {
            if (n % i == 0) {
                prime = false;
                break;
            }
        }

        if (( n%2 !=0 && prime && n > 2) || n == 2) {
            return true;
        } else {
            return false;
        }
    }
}
