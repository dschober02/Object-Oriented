package OldHomework.HW2;
import java.io.File;
import java.util.*;

//  Name: Derek Schober
//
//  Assignment: Homework 2
//
//  MorseTranslator has 2 member-variables that are ArrayLists - _morse and _letter -
//  these ArrayLists are parallel. Each letter has it's corresponding morse value at the
//  same index. The user has three options given to them in a menu driven interface;
//  these options are: translate morse code to a word, translate a word to english code,
//  and exit the program.

class MorseTranslator{
    private ArrayList<String> _morse;
    private ArrayList<Character> _letter;
    private static MorseTranslator _uniqueInstance;
    private int find(String morse){
        for (int i = 0; i < _morse.size(); i++){
            if (_morse.get(i).equals(morse)){
                return i;
            }
        }
        return -1;
    }
    private int find(char letter){
        for (int i = 0; i < _letter.size(); i++){
            if (_letter.get(i).equals(letter)){
                return i;
            }
        }
        return -1;
    }
    private MorseTranslator(){
        _morse = new ArrayList<>();
        _letter = new ArrayList<>();
    }
    private MorseTranslator(int size){
        _morse = new ArrayList<>(size);
        _letter = new ArrayList<>(size);
    }
    public static MorseTranslator getInstance() {
        if (_uniqueInstance == null) {
            _uniqueInstance = new MorseTranslator();
        }
        return _uniqueInstance;
    }
    public static MorseTranslator getInstance(int size) {
        if (_uniqueInstance == null) {
            _uniqueInstance = new MorseTranslator(size);
        }
        return _uniqueInstance;
    }
    public void add(char letter, String morse){
        _letter.add(letter);
        _morse.add(morse);
    }
    public char getLetter(int idx) {
        if(0 > idx || idx > _morse.size())
            return ' ';
        return _letter.get(idx);
    }
    public String getMorse(int idx){
        if(0 > idx || idx > _morse.size())
            return " ";
        return _morse.get(idx);
    }
    public boolean remove(int idx){
        boolean removed = false;
        if(0 > idx || idx > _morse.size()) {
            return removed;
        } else {
            _morse.remove(idx);
            _letter.remove(idx);
            removed = true;
        }
        return removed;
    }
    // morse and letter have the same size
    public int size(){return _morse.size();}

    // Modify takes a passed in char and finds the letter and it's stored morse value
    // if there is a corresponding letter-morse pair, the passed in string will replace the morse.
    public boolean modify(char letter, String newMorse) {
        boolean changed = false;
        int idx = find(letter);
        if (idx != -1){
            _morse.set(idx, newMorse);
            changed = true;
        }
        return changed;
    }
    // Morse -> Alpha
    public char findLetter(String morse){
        int idx = find(morse);
        if (idx != -1){
            return getLetter(idx);
        }
        else return ' ';
    }
    // Alpha -> Letter
    public String findMorse(char letter){
        int idx = find(letter);
        if (idx != -1){
            return getMorse(idx);
        }
        else return "";
    }
}
public class Main {
    public static int menu(){
        Scanner keyboard = new Scanner(System.in);
        System.out.println("    Morse Translator Program");
        System.out.println("=================================");
        System.out.println("    Please Select an Option:");
        System.out.println("1. Translate alpha to morse (no spaces)");
        System.out.println("2. Translate morse to alpha (spaces between morse letters)");
        System.out.println("3. Exit");
        System.out.println("_________________________________");
        System.out.print("Select an Option: ");
        // Making option a string to avoid red text if user puts in non-numeric characters
        String option = keyboard.next();
        while (!option.equals("1") && !option.equals("2") && !option.equals("3")){
            System.out.print("Please Select an Option by entering 1, 2, or 3: ");
            option = keyboard.next();
        }
        return Integer.parseInt(option);
    }
    public static void populateFromFile(MorseTranslator mt) {
        try {
            File file = new File("morse.txt");
            Scanner infile = new Scanner(file);
            while (infile.hasNext()) {
                String[] strings = infile.nextLine().split(" ");
                // File line looks like: "P .--."
                mt.add(strings[0].charAt(0), strings[1]);
            }
            infile.close();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            System.exit(-1);
        }
    }
    // Morse -> Alpha
    public static String translateMorseCode(String morse, MorseTranslator mt){
        String word = "";
        String[] strings = morse.split(" ");
        for (String code : strings) {
            word += mt.findLetter(code);
        }
        return word;
    }
    // Alpha -> Morse
    public static String translateWord(String word, MorseTranslator mt){
        String morse = "";
        for (int i = 0; i < word.length(); i++){
            // Making all letters uppercase
            char c = Character.toUpperCase(word.charAt(i));
            // User input validation
            if (!Character.isLetter(word.charAt(i))){ return "-1";}
            morse += mt.findMorse(c);
            if (i != word.length()-1){
                morse += " ";
            }
        }
        return morse;
    }

    public static void runProgram(MorseTranslator mt){
        Scanner keyboard = new Scanner(System.in);
        int option = menu();
        String input;
        while (option != 3){
            switch (option){
                case 1:   // Alpha -> Morse
                    System.out.print("Please enter your word: ");
                    input = keyboard.nextLine();
                    String trans = translateWord(input, mt);
                    //  input validation before translating the string to an integer
                    while (trans.equals("-1")){
                        System.out.println("There cannot be spaces, numbers or special characters in your word.");
                        System.out.print("Please try again or enter 0 to exit: ");
                        input = keyboard.nextLine();
                        if (input.equals("0")) break;
                        trans = translateWord(input, mt);
                    }
                    if (!input.equals("0"))
                        System.out.println("\nYour code is: " + trans);
                    break;
                case 2:   // Morse -> Alpha
                    System.out.print("Please enter your Morse code: ");
                    input = keyboard.next();
                    //  trans was already used in the first case
                    String trans2 = translateMorseCode(input, mt);
                    //  if the function could not translate any of the morse code the program will prompt the user to try again
                    while (trans2.isEmpty()){
                        System.out.println("There was trouble translating your Morse code.");
                        System.out.print("Please try again or enter 0 to exit: ");
                        input = keyboard.next();
                        if (input.equals("0")) break;
                        trans2 = translateMorseCode(input, mt);
                    }
                    if (!input.equals("0"))
                        System.out.println("\nYour word is: " + trans2);
                    break;
                // Don't need default or switch for 3 because input validation of menu() will ensure
                // that option equals 1 or 2
            }
            System.out.println();
            option = menu();
        }
    }
    public static void main(String[] args) {
        MorseTranslator mt = MorseTranslator.getInstance(26); // 26 for letters in alphabet
        populateFromFile(mt);
        runProgram(mt);
        System.out.println("Have a nice day!");
    }
}