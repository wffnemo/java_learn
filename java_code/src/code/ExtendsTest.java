package code;

class Animal{
    private void beat(){
        System.out.println("my heart is beating!");
    }

    public void breath(){
        System.out.println("I'm breathing!");
    } 

    public void show(){
        beat();
        breath();
    }
}

class Bird extends Animal{
    public void fly(){
        System.out.println("I'm flying!");
    }
}

class Wolf extends Animal{
    public void run(){
        System.out.println("I'm running!");
    }
}

public class ExtendsTest{
    public static void main(String[] args){
        Bird bird = new Bird();
        bird.show();
        bird.fly();
        Wolf wolf = new Wolf();
        wolf.show();
        wolf.run();
    }
}