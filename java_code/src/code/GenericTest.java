package code;

import java.util.List;
import java.util.ArrayList;

public class GenericTest{
    public static void main(String[] args) {
        // List<String> strList = new ArrayList<String>();
        List<String> strList = new ArrayList<>();//菱形语法
        strList.add("A");
        strList.add("B");
        // strList.add(5);
        strList.forEach(str->{System.out.println(str);});
    }
}