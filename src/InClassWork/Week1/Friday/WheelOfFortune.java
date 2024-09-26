package InClassWork.Week1.Friday;

import java.io.File;
import java.util.*;

public class WheelOfFortune {
    public boolean win = true;
    public boolean lose = false;
    public static void populate(ArrayList<String> list){
        try {
            Scanner infile = new Scanner(new File("files/data.txt"));
            while (infile.hasNext()) {
                list.add(infile.nextLine());
            }
        }
        catch(Exception e) {
            System.out.println("Problem reading in file");
            System.out.println(e);
            System.exit(1);
        }
    }

    public static String wordPicker(ArrayList<String> list) {
        Random rand = new Random();
        return list.get(rand.nextInt(0, list.size() - 1));
    }

    public static boolean inputValidation(String guess, ArrayList<String> wrongList, ArrayList<String> rightList) {
        boolean valid = true;
        for (char c : guess.toCharArray()) {
            if (!Character.isLetter(c)) {
                valid = false;
                System.out.println("Your input has non-letter characters!");
                continue;
            }
            if (wrongList.contains(guess) || rightList.contains(guess)){
                valid = false;
                System.out.println("You already guessed that!");
            }
        }
        return valid;
    }

    public static boolean solved(char[] guessed, String answer) {
        boolean solved = true;
        char[] answerCharArray = answer.toCharArray();
        for (int i = 0; i < answer.length(); i++) {
            if (!(guessed[i] == Character.toLowerCase(answerCharArray[i]))) {
                solved = false;
                break;
            }
        }
        return solved;
    }
    public static boolean playGame(char[] guessed, String answer) {
        int ctr = 0;
        ArrayList<String> wrongList = new ArrayList<>();  // list for wrong guesses
        ArrayList<String> rightList = new ArrayList<>();  // list for right guesses
        Scanner keyboard = new Scanner(System.in);
        while (ctr < 8 && !solved(guessed, answer)) {
            System.out.print("Enter a letter or word: ");
            String guess = keyboard.nextLine();    // inputs the users guess
            while (!inputValidation(guess, wrongList, rightList)) {
                System.out.print("Enter a letter or word: ");
                guess = keyboard.nextLine();
            }
            char[] answerArray = answer.toCharArray();
            boolean found = false;
            if (guess.equals(answer))
                for (int i = 0; i < guess.length(); i++) {
                    guessed[i] = answerArray[i];
                    found = true;
                }
            if (guess.length() == 1)
                for (int i = 0; i < answer.length(); i++) {
                    if (answerArray[i] == guess.charAt(0)) {
                        found = true;
                        guessed[i] = answerArray[i];
                        if (!rightList.contains(guess))
                            rightList.add(guess);
                    }
                }
            if (!found) {
                wrongList.add(guess);
                ctr++;
            } else
                rightList.add(guess);
            displayBoard(guessed, found);
        }
        if (ctr == 8) // if user hits 8 then they lose
            return false;
        else return true;
    }

    static void displayBoard(char[] guessed, boolean found) {
        StringBuilder hangman = new StringBuilder();
        for (char c : guessed)
            hangman.append(c);
        if (found)
            System.out.println("Correct! Here's the board: " + hangman);
        else
            System.out.println("Incorrect! Here's the board: " + hangman);
    }
    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        String playAgain = "Yes";
        ArrayList<String> list = new ArrayList<>();     // instantiating the list for the puzzle words
        populate(list);
        while (playAgain.equals("Yes")) {
            String word = wordPicker(list);
            char[] guessed = new char[word.length()];
            for (int i = 0; i < word.length(); i++) {
                guessed[i] = '_';
            }
            System.out.println("The word has " + word.length() + " letters.");
            if (!playGame(guessed, word)){
                System.out.println("\nDefeat! Better luck next time.");
                System.out.println("The word was " + word + ".");
            }else System.out.println("\nNice job! You win!");
            System.out.println("Would you like to play again? (Yes/No): ");
            playAgain = keyboard.nextLine();
        }
    }
}

