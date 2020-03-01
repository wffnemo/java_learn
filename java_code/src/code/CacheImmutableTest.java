// 缓存实例的不可变类
package code;

class Name{
    private static final int MAX_SIZE = 5;
    private static int NUM;
    private static Name[] cache = new Name[MAX_SIZE];
    private final String name;
    private Name(String name){
        this.name = name;
    };
    public static Name createName(String name){
        for(int i=0;i<NUM;i++){
            if(Name.cache[i]!=null&&Name.cache[i].name.equals(name)){
                return Name.cache[i];
            }
        }
        if(NUM<MAX_SIZE){
            Name name_ = new Name(name);
            Name.cache[NUM++] = name_;
            return name_;
        }
        else{
            Name name_ = new Name(name);
            cache[0] = name_;
            return name_;
        }
    }
}

public class CacheImmutableTest{
    public static void main(String[] args) {
        Name a = Name.createName("a");
        Name b = Name.createName("b");
        Name c = Name.createName("c");
        Name d = Name.createName("d");
        Name e = Name.createName("e");
        Name f = Name.createName("f");
        Name g = Name.createName("a");
        System.out.println(a==g);
        Name h = Name.createName("b");
        System.out.println(b==h);
        Name i = Name.createName("c");
        System.out.println(c==i);
    }
}