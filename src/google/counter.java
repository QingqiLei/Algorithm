package google;

import java.util.TreeSet;

/*
有1~n的数据不断传过来，设计一个函数和数据结构能找出来目前没有收到的整数里面最小的。这个函数会被call 很多次

 */
public class counter {
    TreeSet<Integer> set = new TreeSet<>();
    counter(int n){
        for(int i = 1; i <= n; i++) set.add(i);
    }
    public void add(int i){
        set.remove(i);
    }

    Integer get(){
        if(set.size() == 0) return null;
        else return set.first();
    }

    public static void main(String[] args) {
        counter c = new counter(5);
        c.add(5);
        c.add(1);
        c.add(0);
        c.add(7);
        System.out.println(c.get());
    }
}
