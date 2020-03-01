package code;

class Goose{
    private Animal animal = new Animal();
    public Goose(Animal animal){
        this.animal = animal;
    }
    public void breath(){
        animal.breath();
    }
    public void fly(){
        System.out.println("I'm flying!");
    }
}

class Dog{
    private Animal animal = new Animal();
    public Dog(Animal animal){
        this.animal = animal;
    }
    public void breath(){
        animal.breath();
    }
    public void run(){
        System.out.println("I'm running!");
    }
}

public class CombinationTest{
    public static void main(String[] args){
        Animal animal = new Animal();
        Goose goose = new Goose(animal);
        goose.breath();
        goose.fly();
        Dog dog = new Dog(animal);
        dog.breath();
        dog.run();
    }
}