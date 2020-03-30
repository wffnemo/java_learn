package code;

import java.util.List;
import java.util.ArrayList;

class Fruit{
    String name;
    public Fruit(String name){
        this.name = name;
    }
    public String toString(){
        return this.name;
    }
}

class Apple extends Fruit{
    public Apple(String name){
        super(name);
    }
} 

public class GenericTest2{
    public GenericTest2(){}
    public static void main(String[] args) {
        List<Fruit> lstFruit = new ArrayList<>();
        lstFruit.add(new Fruit("A"));
        lstFruit.add(new Fruit("B"));
        System.out.println(lstFruit);
        lstFruit.add(new Apple("C"));
        lstFruit.add(new Apple("D"));
        System.out.println(lstFruit);
        // **********************不可变****************************
        // List<Apple> lstApple = new ArrayList<>();
        // lstFruit = lstApple;
        // System.out.println(lstFruit); //List<Fruit> and List<Apple> 是同一个类，不存在继承关系
        // ********************协变******************************
        // List<? extends Fruit> lstF = new ArrayList<>();
        // lstF.add(new Apple("E"));  //泛型协变不支持添加元素
        // lstF.add(null); //只能添加null    
        // *********************逆变********************************
        // List<? super Apple> lstA = new ArrayList<>();
        // lstA = lstFruit;
        // System.out.println(lstA);  //泛型逆变的使用
        // lstA.add(new Fruit("F"));  //泛型逆变不能添加父类的实例
        // lstA.add(new Apple("G"));
        // System.out.println(lstA);  //泛型支持添加通配符下限类及其子类的实例元素
    }
}