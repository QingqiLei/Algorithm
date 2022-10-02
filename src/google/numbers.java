package google;

import java.util.Map;
import java.util.TreeMap;

/*
持续输入数字1，4，5，7，3，5，4实现median（）,mode(), mean‍‌‌‌‍‌‍‌‍‌‍‌‍‍‌‌‍‍‌‌() 应该是leetcode原题，多加了一个mode（众数），众数要求用O(1)实现，用map做就行。 有用的话麻烦加米谢谢！

 计算器
 拓展是加入新的运算符号? 就是Java里的那个a=boolean?c:d，0是false其他都是true
 */
public class numbers {
    Map<Integer, Integer> int2Cnt = new TreeMap<>();
    int sum = 0, count = 0;
    int mode = 0, cnt = 0;

    void add(int n){
        int2Cnt.put(n, int2Cnt.getOrDefault(n,0)+1);
        sum += n;
        count++;
        if(int2Cnt.get(n) > cnt){
            cnt = int2Cnt.get(n);
            mode = n;
        }
    }
    int mean(){
        return (sum/count);
    }
    int mode(){
        return mode;
    }
    int medium(){
        int midIndex = (count+1)/2;
        int t = 0;
        for(int i : int2Cnt.keySet()){
            t+= int2Cnt.get(i);
            if(t >= midIndex) return i;

        }
        return -1;
    }

    public static void main(String[] args) {
        numbers n = new numbers();
        n.add(1);
        n.add(2);
        n.add(3);
        n.add(4);
        n.add(5);
        n.add(6);
        n.add(7);
        n.add(7);
        System.out.println(n.medium());
        System.out.println(n.mode());
        System.out.println(n.mean());

    }

}
