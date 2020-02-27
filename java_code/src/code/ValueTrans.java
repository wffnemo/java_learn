package code;

//Java中所有的参数传递方式都是值传递
public class ValueTrans
{
    public ValueTrans(int a,int b){
        this.a = a;
        this.b = b;
    }
    int a;
    int b;
    public void exchange(){
        int tmp;
        tmp = this.a;
        this.a = this.b;
        this.b = tmp;
    }
    public static void main(String[] args){
        ValueTrans vt = new ValueTrans(6,9);
        vt.exchange();
        System.out.println(vt.a);
        System.out.println(vt.b);
    }
}