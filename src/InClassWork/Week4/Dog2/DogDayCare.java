package InClassWork.Week4.Dog2;

import javax.print.DocFlavor;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

class Dog {
    private String _name;
    private String _breed;
    private int _age;
    private String _color;

    public Dog() {
        System.out.println("In default Dog Constructor");
        _name = null;
        _breed = null;
        _age = 0;
        _color = null;
    }

    public Dog(String name, String breed, int age) {
        System.out.println("In n-arg Dog Constructor");
        _name = name;
        _breed = breed;
        _age = age;
    }

    public Dog(Dog dog) {
        // needed for iterator
        _name = dog.getName();
        _breed = dog.getBreed();
        _age = dog.getAge();
        _color = dog.getColor();
    }



    public Dog(String name, String breed, int age, String color) {
        System.out.println("In n-arg Dog Constructor");
        _name = name;
        _breed = breed;
        _age = age;
        _color = color;
    }

    public String getColor() {return _color;}

    public void setName(String name) {_name = name;}

    public void setBreed(String b) {_breed = b;}

    public void setAge(int x) {_age = x;}

    public String getName() {return _name;}

    public String getBreed() {return _breed;}

    public int getAge() {return _age;}

}

class Daycare {
    private ArrayList<Dog> _dogs;
    private static Daycare _uniqueInstance;
    private int _next;

    // non-exhaustive search of name
    private int find(String name) {
        boolean found = false;
        int i = 0;
        while (i < _dogs.size() && !found) {
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

    private Daycare() {
        System.out.println("In Daycare()");
        _dogs = new ArrayList<Dog>();
        _next = 0;
    }

    private Daycare(int size) {
        System.out.println("In Daycare(size)");
        _dogs = new ArrayList<Dog>(size);
        _next = 0;
    }

    public static Daycare getInstance() {
        System.out.println("In getInstance()");
        if (_uniqueInstance == null)
            _uniqueInstance = new Daycare();
        return _uniqueInstance;
    }

    public static Daycare getInstance(int size) {
        System.out.println("In getInstance(size)");
        if (_uniqueInstance == null)
            _uniqueInstance = new Daycare(size);
        return _uniqueInstance;
    }

    public void addDog(Dog d) {
        System.out.println("In addDog(Dog d)");
        _dogs.add(d);
    }

    public boolean removeDog(String n) {
        System.out.println("In removeDog(name)");
        int idx = find(n);
        if (idx == -1)
            return false;
        else {
            _dogs.remove(idx);
            return true;
        }
    }

    public boolean modifyAge(String n, int a) {
        System.out.println("In modifyAge(name, age)");
        int idx = find(n);
        if (idx == -1)
            return false;
        else {
            _dogs.get(idx).setAge(a);
            return true;
        }
    }

    public int findAge(String name) {
        System.out.println("In findAge method");
        int idx = find(name);
        if (idx == -1)
            return -1;
        else return _dogs.get(idx).getAge();
    }

    public String getName(int idx) {
        System.out.println("In getName method");
        if (idx < _dogs.size() && idx >= 0)
            return _dogs.get(idx).getName();
        else return "";
    }

    public int getAge(int idx) {
        System.out.println("In getAge method");
        if (idx < _dogs.size() && idx >= 0)
            return _dogs.get(idx).getAge();
        else return -1;
    }

    public int size() {
        return _dogs.size();
    }

    public void print() {
        System.out.print("[");
        for (Dog d : _dogs) {
            System.out.print(d.getName() + ", " + d.getBreed() + ", " + d.getAge());
        }
        System.out.println("]");
    }

    // iterator methods
    public void start(){
        _next = 0;
    }

    public boolean hasNext(){
        return (_next < size());
    }

    public Dog next() {
        // create a copy that is returned
        return new Dog(_dogs.get(_next++));
    }
}

public class DogDayCare {
    public static void main(String[] args) throws IOException {
        // testing default
        Daycare d = Daycare.getInstance();
        d.addDog(new Dog("Chaser", "Poodle", 7));
        d.addDog(new Dog("Fido", "German Shepard", 7));
        d.start();
        while (d.hasNext()) {
            System.out.println(d.next().getName());
        }
        d.next().setAge(9);
    }
}
