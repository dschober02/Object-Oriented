package CodeGradeAssignement.Question2;
import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) {
        int a = 0;
        int c = 0;
        int e = 0;
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter a string: ");
        String string = scan.nextLine();
        for (char C : string.toCharArray()) {
            if (C == 'a')
                a++;
            if (C == 'c')
                c++;
            if (C == 'e')
                e++;
        }
        System.out.println(a + " a\n" + e + " e\n" + c + " c\n");
    }
}
