package code;

import java.util.ArrayList;
import java.util.Collection;

public class GenericMethodTest{
    static <T> void formArrayToCollection(T[] a,Collection<T> c){
        for(T ele:a){
            c.add(ele);
        }
    }
    public static void main(String[] args) {
        String[] as = new String[100];
        Collection<String> cs = new ArrayList<>();
        formArrayToCollection(as,cs);
        Object[] ao = new Object[100];
        Collection<Object> co = new ArrayList<>();
        formArrayToCollection(ao, co);
        formArrayToCollection(as, co);
    }
}