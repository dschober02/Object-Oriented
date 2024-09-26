package InClassWork.Week3.DogDayCare;

import java.util.*;
import java.io.*;
class Dog {
    private String _name;
    private String _breed;
    private int _age;
    public Dog() {
        System.out.println("In default Dog Constructor");
        _name = null;
        _breed = null;
        _age = 0;
    }
    public Dog(String name, String breed, int age){
        System.out.println("In n-arg Dog Constructor");
        _name = name;
        _breed = breed;
        _age = age;
    }
    public void setName(String name) {_name = name;}
    public void setBreed(String b){_breed = b;}
    public void setAge(int x){_age = x;}
    public String getName() {return _name;}
    public String getBreed() {return _breed;}
    public int getAge(){return _age;}
}
class Daycare{
    private ArrayList<Dog> _dogs;
    private static Daycare _uniqueInstance;

    // non-exhaustive search of name
    private int find(String name){
        boolean found = false;
        int i = 0;
        while(i < _dogs.size() && !found){
            if (_dogs.get(i).getName().equals(name))
                found = true;
            else
                i++;
        }
        if (!found)
            return -1;
        else
            return i;
    }

    private Daycare(){
        System.out.println("In Daycare()");
        _dogs = new ArrayList<Dog>();
    }
    private Daycare(int size){
        System.out.println("In Daycare(size)");
        _dogs = new ArrayList<Dog>(size);
    }
    public static Daycare getInstance(){
        System.out.println("In getInstance()");
        if (_uniqueInstance == null)
            _uniqueInstance = new Daycare();
        return _uniqueInstance;
    }
    public static Daycare getInstance(int size){
        System.out.println("In getInstance(size)");
        if (_uniqueInstance == null)
            _uniqueInstance = new Daycare(size);
        return _uniqueInstance;
    }
    public void addDog(String n, String b, int a){
        System.out.println("In addDog(n,b,a)");
        _dogs.add(new Dog(n, b, a));
    }
    public boolean removeDog(String n){
        System.out.println("In removeDog(name)");
        int idx = find(n);
        if (idx == -1)
            return false;
        else {
            _dogs.remove(idx);
            return true;
        }
    }
    public boolean modifyAge(String n, int a){
        System.out.println("In modifyAge(name, age)");
        int idx = find(n);
        if (idx == -1)
            return false;
        else {
            _dogs.get(idx).setAge(a);
            return true;
        }
    }
    public int findAge(String name){
        System.out.println("In findAge method");
        int idx = find(name);
        if (idx == -1)
            return -1;
        else return _dogs.get(idx).getAge();
    }
    public String getName(int idx){
        System.out.println("In getName method");
        if (idx < _dogs.size() && idx >= 0)
            return _dogs.get(idx).getName();
        else return "";
    }
    public int getAge(int idx){
        System.out.println("In getAge method");
        if (idx < _dogs.size() && idx >= 0)
            return _dogs.get(idx).getAge();
        else return -1;
    }
    public int size(){return _dogs.size();}

    public void print(){
        System.out.print("[");
        for (Dog d : _dogs) {
            System.out.print(d.getName() + ", " + d.getBreed() + ", " + d.getAge());
        }
        System.out.println("]");
    }
}
public class DogDayCare {
    public static void main(String[] args) throws IOException{
        // testing default
        Daycare dd = Daycare.getInstance();
        Daycare d1 = Daycare.getInstance(1);
        // testing addDog on an empty container
        dd.addDog("Ro", "Morkie", 5);

        // testing matching Daycare addresses
        System.out.print("Should be Ro: ");
        System.out.println(d1.getName(0));

        // testing false remove
        dd.removeDog("Morkie");
        dd.print();
        // testing true remove
        dd.removeDog("Ro");
        dd.print();
        // testing Modify age
        dd.addDog("Riley", "German Shepard", 3);
        dd.print();
            // testing ModifyAge false
        dd.modifyAge("3", 12);
        dd.print();
            // testing ModifyAge true
        dd.modifyAge("Riley", 4);
        dd.print();
        // findAge false
        System.out.println(dd.findAge("Xeni"));
        // findAge true
        System.out.println(dd.findAge("Riley"));
        // getName out of bounds
        System.out.println(dd.getName(-1));
        // getName true
        System.out.println(dd.getName(0));
        // getAge out of bounds
        System.out.println(dd.getAge(-1));
        // getAge true
        System.out.println(dd.getAge(0));
    }
}
