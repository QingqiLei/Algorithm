package google;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
这轮OOD问了个猜水果的问题，给了一个method输入question返回true或false，
水果有很多种character, 每个问题能问一种character，比如水果是不是红色，
甜不甜，软不软之类。猜对了返回true猜错了返回false。
最后要实现一个method返回正确的水果。之前没怎么准备过OOD这轮感觉爆炸了，
跟小哥说了思路是每一轮问一个问题，把符合条件的水果留下不符合的删掉。
 */

class Fruit{
    // property  value
    Map<String, Integer> map = new HashMap<>();
}
public class GuessFruit {
    int INVALID = Integer.MIN_VALUE;
    List<Fruit> filter(List<Fruit> fruits){
        Map<String ,List<Integer>> map = new HashMap<>();
        for(Fruit f: fruits){
            for(String property: f.map.keySet()){
                map.putIfAbsent(property, new ArrayList<>());
                map.get(property).add(f.map.get(property));
            }
        }
        for(String property: map.keySet()){
            for(Integer value: map.get(property)){
                boolean ans = ask(property, value);
                List<Fruit> fruits2 = new ArrayList<>();
                if(!ans){
                    for(Fruit f: fruits){
                        if(f.map.getOrDefault(property, INVALID) != value) fruits2.add(f);
                    }
                    fruits = fruits2;
                }
            }
        }
        return fruits;
    }

    boolean ask(String property, int value){
        return true;
    }
}
