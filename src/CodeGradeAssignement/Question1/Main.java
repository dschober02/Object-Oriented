package CodeGradeAssignement.Question1;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int smallest = Integer.MAX_VALUE;
        int biggest = Integer.MIN_VALUE;
        int input;
        System.out.println("Enter a positive integer. Enter -1 when you are finished: ");
        input = scan.nextInt();
        while (input != -1) {
            if (input < smallest) {
                smallest = input;
            }
            if (input > biggest) {
                biggest = input;
            }
            System.out.println("Enter a positive integer. Enter -1 when you are finished: ");
            input = scan.nextInt();
        }
        System.out.println("Smallest is  " + smallest);
        System.out.println("Largest is " + biggest);
    }
}
