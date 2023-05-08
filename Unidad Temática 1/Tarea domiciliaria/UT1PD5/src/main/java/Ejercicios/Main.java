package Ejercicios;

public class Main {
    public static void main(String[] args) {
        Days.printDays();
        
        System.out.println(StringDemo.palindrome("Dot saw I was Tod"));
        System.out.println(StringDemo.palindrome("Dot saw. I. wa.s To.d"));
        System.out.println(StringDemo.palindrome("Dot saw; I was ;Tod"));
        System.out.println(StringDemo.palindrome("dot sAw I WAs TOd"));
    }
}
