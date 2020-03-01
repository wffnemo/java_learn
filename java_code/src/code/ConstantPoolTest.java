package code;

public class ConstantPoolTest{
    public static void main(String[] args) {
        String str1 = "I love China!";
        String str2 = "I love ";
        String str3 = "China!";
        String str4 = "I love "+"China!";
        String str5 = "I "+"love China!";
        String str6 = str2 + str3;
        String str7 = new String("I love China!");
        String str8 = new String("I love China!");
        System.out.println(str1==str4);
        System.out.println(str1==str5);
        System.out.println(str1==str6);
        System.out.println(str1==str7);
        System.out.println(str7==str8);
        System.out.println(str7==str6);
        System.out.println(str7.equals(str8));
    }
}