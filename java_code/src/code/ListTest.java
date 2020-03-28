package code;

import java.util.ArrayList;
import java.util.List;

public class ListTest {
    public static void main(String[] args) {
        List books = new ArrayList();
        books.add(new String("A"));
        books.add(new String("B"));
        books.add(new String("C"));
        books.add(new String("D"));
        System.out.println(books);
        books.add(1,new String("E"));
        for(int i=0;i<books.size();i++){
            System.out.println(books.get(i));
        }
        books.remove(2);
        System.out.println(books);
        System.out.println(books.indexOf(new String("C")));
        books.set(1,new String("F"));
        System.out.println(books);
        System.out.println(books.subList(1,2));
    }
}