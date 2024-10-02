package InClassWork.Week6.Animals;

import java.util.ArrayList;

class Animal {
    private String _name;
    private String _breed;
    private int _age;
    public Animal() {
        System.out.println("in Animal default");
    }
    public Animal(String s1, String s2, int x){
        System.out.println("in Animal n-arg");
        _name = s1;
        _breed = s2;
        _age = x;
    }
    public void setName(String n) {_name = n;}
    public void setBreed(String b){_breed = b;}
    public void setAge(int x){_age = x;}
    public String getName() {return _name;}
    public String getBreed() {return _breed;}
    public int getAge(){return _age;}
    public void print(){
        System.out.println(_name + " " + _breed + " " + _age);
    }
}
class Dog extends Animal {
    private boolean _guard;
    public Dog() {System.out.println("in Dog default");}
    public Dog(String s1, String s2, int x, boolean g){
        super(s1, s2, x);
        System.out.println("in Dog n-arg");
        _guard = g;
    }
    public void print() {
        super.print();
        System.out.println(_guard);
    }
    public void setGuard(boolean g) {_guard = g;}
    public boolean getGuard(){return _guard;}
}
class Cat extends Animal {
    private boolean _indoor;
    public Cat() {
        System.out.println("in Cat default");
    }
    public Cat(String s1, String s2, int x, boolean indoor) {
        super(s1, s2, x);
        System.out.println("in Cat n-arg");
        _indoor = indoor;
    }
    public void set_indoor(boolean _indoor) {this._indoor = _indoor;}
    public boolean get_indoor(){return _indoor;}
    public void print() {
        super.print();
        System.out.println(_indoor);
    }
}
public class Main {
    public static void main(String[] args) {
        ArrayList<Animal> animals = new ArrayList<>();
        animals.add(new Cat("Casper", "Calico", 5, true));
        animals.add(new Dog("Fido", "German Shepard", 3, true));
        animals.add(new Cat("Ro", "Yorkshire Terrier", 6, false));
        for (Animal a : animals) {
            System.out.print(a.getName() + " "
                    + a.getBreed() + " "
                    + a.getAge() + " years old "
            );
            if (a instanceof Dog)
                System.out.println("is guard = " + ((Dog) a).getGuard());
            if (a instanceof Cat)
                System.out.println("is indoor = " + ((Cat) a).get_indoor());
        }
    }
}
