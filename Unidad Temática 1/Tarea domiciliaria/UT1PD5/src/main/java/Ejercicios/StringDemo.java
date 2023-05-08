package Ejercicios;

public class StringDemo {
  public static String palindrome(String text) {
    int len = text.length();

    char[] tempCharArray = new char[len];
    char[] charArray = new char[len];

    for (int i = 0; i < len; i++) {
      tempCharArray[i] = text.charAt(i);
    }

    for (int j = 0; j < len; j++) {
      charArray[j] = tempCharArray[len - 1 - j];
    }

    return new String(charArray);
  }
}