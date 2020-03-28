// 非静态内部类示例
package code;

class Cow{
    private static double weight;
    private static String color;
    public Cow(){}
    public Cow(double weight,String color){
        Cow.weight = weight;
        Cow.color = color;
    }
    private class CowLeg{
        private double length;
        private CowLeg(){}
        private CowLeg(double length){
            this.length = length;
        }
    private void info(){
        System.out.println("color:"+color+"\nweight:"+weight+"\nleglength:"+length);
    }
    }
    // show()方法不能是private方法，否则无法在其他类中被调用
    public void show(){
        CowLeg cl = new CowLeg(5);
        cl.info();
    }
}

public class InnerClassTest{
    public static void main(String[] args) {
        Cow cow = new Cow(500,"red");
        cow.show();
    }
}