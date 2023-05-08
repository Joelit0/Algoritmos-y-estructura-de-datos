package com.joel.ut3_pd9;
import java.util.Stack;

public class Expresion {
    public static boolean controlCorchetes(char[] listaDeEntrada) {
        Stack stack = new Stack();
        
        for (char element : listaDeEntrada) {
            if (element == '{') {
                stack.push(1);
            } 
            
            if (element == '}') {
                if (stack.isEmpty()) {
                    return false;
                } else {
                    stack.pop();
                }
            }
        }
                
        return stack.isEmpty();
    }
    
    public static void main(String[] args) {
        System.out.println(controlCorchetes("{}{{}}".toCharArray()));
        System.out.println(controlCorchetes("{{}{{}".toCharArray()));
    }
}
