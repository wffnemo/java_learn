package code;

import java.util.HashSet;
import java.util.Iterator;

class R{
    int count;
    public R(int count){
        this.count = count; 
    }
    public String toString(){
        return "R["+count+"]";
    }
    public boolean equals(Object obj){
        if(this == obj){
            return true;
        }
        else{
            if(obj!=null&&obj.getClass()==R.class){
                R r = (R)obj;
                return r==this;
            }
            return false;
        }
    }
    public int hashCode(){
        return count;
    }
}

public class HashSetTest{
    public static void main(String[] args) {
        HashSet hs = new HashSet();
        hs.add(new R(1));
        hs.add(new R(2));
        hs.add(new R(3));
        hs.add(new R(4));
        System.out.println(hs);
        Iterator it = hs.iterator();
        R first = (R)it.next();
        first.count = 2;
        System.out.println(hs);
        hs.remove(new R(2));
        System.out.println(hs);
        System.out.println(hs.contains(new R(1)));
        System.out.println(hs.contains(new R(2)));
        System.out.println(hs.contains(new R(3)));
    }
}