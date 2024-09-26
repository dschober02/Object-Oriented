package InClassWork.Week1.Wednesday;

import java.util.*;

public class Sample1 {
    public static void main(String[] args) {
        ArrayList<Integer> n = new ArrayList<Integer>();
        n.add(5);
        n.add(3);
        n.add(7);
        System.out.println(n);
        int index = 0;
        for ( int x: n) {
            System.out.println("Value of element " + index++ + " is: " + x);
        }
    }
}
