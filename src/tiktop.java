import java.util.*;

public class tiktop {

    public static void main(String[] args) {
//        System.out.println(moves(Arrays.asList(6,3,4,5)));
//        System.out.println(counting("000000111111"));
//        System.out.println(lcs("and","bear"));
//        System.out.println(longestChain(Arrays.asList("abcdefg", "abcefg", "abceg", "abce", "ace", "ce", "e")));
//        System.out.println(longestChain(Arrays.asList("a","b","ba","bca","bda","bdca")));
//        System.out.println(~0);
//        int[] tmp = new int[]{1,2};
//        PriorityQueue<Integer> q = new PriorityQueue<>(Comparator.comparingInt(a -> tmp[a]));
//        q.add(0);
//        q.add(1);
//        System.out.println(q.peek());
//        tmp[0] = 3;
//        q.add(1);
        System.out.println(1<0?1:0-1);


    }

    public List<String> topKFrequent(String[] words, int k) {
        HashMap<String , Integer> word2Count = new HashMap<>();
        int maxCount = 0;
        for(String word: words) {
            word2Count.put(word, word2Count.getOrDefault(word, 0)+1);
            maxCount = Math.max(maxCount, word2Count.get(word));
        }
        List<String> res = new ArrayList<>();
        for(String word: word2Count.keySet()){
            if(word2Count.get(word) == maxCount) res.add(word);
        }
        Collections.sort(res, String::compareTo);
        TreeMap<Integer, Integer> map = new TreeMap<>();

        return res;

    }

    public static int moves(List<Integer> arr) {
        int res = 0;
        for (int left = 0, right = arr.size() - 1; left < right; left++, right--) {
            while (left < right && arr.get(left) % 2 == 0) left++;
            while (left < right && arr.get(right) % 2 == 1) right--;
            if (left >= right) break;
            res++;
        }
        return res;
    }

    static int counting(String s) {
        int pre = 0, cur = 1, res = 0;
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) != s.charAt(i - 1)) {
                res += Math.min(pre, cur);
                pre = cur;
                cur = 1;
            } else cur++;
        }
        return res + Math.min(pre, cur);
    }

    public static int longestChain(List<String> words) {
        Map<Integer, Set<String>> len2Str = new HashMap<>();
        for (String word : words) {
            if (!len2Str.containsKey(word.length())) len2Str.put(word.length(), new HashSet<>());
            len2Str.get(word.length()).add(word);
        }
        Map<String, Integer> wordChain = new HashMap<>();
        int res = 0;
        for (String word : words) {
            res = Math.max(res, dfs(wordChain, len2Str, word));
        }
        return res;
    }

    static int dfs(Map<String, Integer> wordChain, Map<Integer, Set<String>> len2Str, String cur) {
        if (wordChain.containsKey(cur)) return wordChain.get(cur);
        int res = 1;
        if (len2Str.containsKey(cur.length() - 1))
            for (String word : len2Str.get(cur.length() - 1)) {
                if (lcs(word, cur)) res = Math.max(res, 1 + dfs(wordChain, len2Str, word));
            }
        wordChain.put(cur, res);
        return res;
    }

    // a is shorter
    static boolean lcs(String a, String b) {
        boolean meet = false;
        for (int i = 0; i < a.length(); i++) {
            if (meet) {
                if (a.charAt(i) != b.charAt(i + 1)) return false;
            }else if (a.charAt(i) != b.charAt(i)) {
                meet = true;
                i--;
            }
        }
        return true;
    }



}
