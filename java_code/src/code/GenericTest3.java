package code;

class Apple2<T extends Number>{
    T size;
    public Apple2(){}
    public Apple2(T size){
        this.size = size;
    }
    public void setSize(T size){
        this.size = size;
    }
    public T getSize(){
        return this.size;
    }
}

public class GenericTest3{
    public static void main(String[] args) {
        Apple2<Integer> a = new Apple2<>(6);
        Integer as = a.getSize();
        Apple2 b = a;  //泛型擦粗，当把一个带泛型信息的变量赋值给一个不带泛型信息的变量时，泛型信息会被擦除，默认是声明该泛型形参时的上限类型
        Number bs = b.getSize();
        System.out.println(bs);
        // Integer bs2 = b.getSize();  //此时会报错，泛型信息被擦除，因此bs类型应为Number
        Apple2<Integer> c = b;  //泛型转换，这里b实际上引用的还是Apple2<Integer>类型，因此这里c也应该是Apple2<Integer>类型
        Integer cs = c.getSize();
        System.out.println(cs);
    }
}