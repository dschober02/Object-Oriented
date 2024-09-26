package InClassWork.Week1.Wednesday;

import java.util.*;


public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String vowels = "aeiouAEIOU";
        int count = 0;
        System.out.println("Enter a word: ");
        String word = scan.nextLine();
        for (char c : word.toCharArray()) {
            if (vowels.contains(String.format("%c", c))) {
                count++;
            }
            System.out.println("Number of vowels: " + count);
        }
    }
}