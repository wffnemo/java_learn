package code;

import java.util.Collection;
import java.util.HashSet;

public class CollectionEach {
    public static void main(String[] args) {
        Collection books = new HashSet();
        books.add("1");
        books.add("2");
        books.add("3");
        books.forEach(obj->System.out.println("迭代集合元素"+obj));
        
    }
}