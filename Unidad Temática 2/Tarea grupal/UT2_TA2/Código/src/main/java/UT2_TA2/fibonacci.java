package UT2_TA2;

public class fibonacci {
   static int count = 0;

    public static int fibonacciBinario(int n) {
        count++;
        
        if (n == 0 || n == 1) {
            System.out.println("Caso base: " + n);
            return n;
        } else {
            System.out.println("Caso recursivo: " + n);
            return fibonacciBinario(n-1) + fibonacciBinario(n-2);
        }
    }

    public static int[] fibonacciLineal(int k){
        int[] fibo = {1,0};
        if (k==1){
            return fibo;
        }
        else{         
            fibo = fibonacciLineal(k-1);
            int j = fibo[0];
            fibo[0] = j + fibo[1];
            fibo[1] = j;
        }
        
        return fibo;
    }
    
    public static void main(String[] args) {
        System.out.println(fibonacciBinario(5));
        System.out.println(count);
        
        for (Object object : fibonacciLineal(5)) {
            System.out.println(object);            
        }
    }
}
