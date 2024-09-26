package CodeGradeAssignement.Question3;
import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        ArrayList<Character> forward = new ArrayList<>();
        ArrayList<Character> backward = new ArrayList<>();
        System.out.print("Enter a string: ");
        String input = scan.nextLine();
        for (char c : input.toCharArray()) {
            if (Character.isLetter(c)) {
                forward.add(c);
                backward.addFirst(c);
            }
        }
        if (forward.equals(backward))
            System.out.println(input + " is a palindrome");
        else
            System.out.println(input + " is not a palindrome");
    }
}
