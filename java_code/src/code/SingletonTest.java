// 测试单例类
package code;

class Typer{
    private static Typer cache=null;
    private Typer(){}
    public static Typer createTyper(){
        if(cache == null){
            cache = new Typer();
        }
        return cache;
    }
}

public class SingletonTest{
    public static void main(String[] args) {
        Typer tp1 = Typer.createTyper();
        Typer tp2 = Typer.createTyper();
        System.out.println(tp1 == tp2);
    }
}