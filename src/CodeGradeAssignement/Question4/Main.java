package CodeGradeAssignement.Question4;
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        ArrayList<Integer> nums = new ArrayList<>();
        System.out.print("Enter a positive number (Enter a negative number to exit): ");
        int sentinelChecker = scan.nextInt();
        while (sentinelChecker >= 0){
            nums.add(sentinelChecker);
            System.out.print("Enter a positive number (Enter a negative number to exit): ");
            sentinelChecker = scan.nextInt();
        }
        StringBuilder output = new StringBuilder();
        if (nums != null)
            nums.sort(Comparator.naturalOrder());
        for (int i : nums) {
            output.append(i).append(" ");
        }
        System.out.println(output);

    }
}
