package google;

import java.util.*;

/*
input=[a, ab,abc,ac],找出最多数目的字符可以连成下一个字符等于上一个字符在末尾加一个字母。
e.g., a->ab->abc长度为3； a->ac长度只有2。
 */
public class longestWordPath {
    int solve(List<String> words){
        Collections.sort(words, Comparator.comparingInt(a-> a.length()));
        Map<String ,Integer> dp = new HashMap<>();
        int res = 0;
        for(String word: words){
            dp.put(word, dp.getOrDefault(word.substring(0, word.length() -1),0)+1);
            res = Math.max(res, dp.get(word));
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(new longestWordPath().solve(Arrays.asList("a","ab","abc","ac")));
    }
}
