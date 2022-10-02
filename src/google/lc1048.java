package google;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class lc1048 {
    public int longestStrChain(String[] words) {
        Arrays.sort(words, Comparator.comparingInt(a-> a.length()));
        Map<String, Integer> dp = new HashMap<>();
        int res = 0;
        for(String word: words){
            int longest = 0;
            for(int i = 0; i < word.length(); i++){
                String prev = word.substring(0,i) + word.substring(i+1);
                longest = Math.max(longest, dp.getOrDefault(prev,0)+1);
            }
            dp.put(word, longest);
            res = Math.max(res, longest);
        }
        return res;
    }
}
