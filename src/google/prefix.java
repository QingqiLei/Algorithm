package google;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/*
给了一个list of sorted string 和一个prefix
找list里面有几个和prefix string match的单词
先brute force 写了代码，然后binary search，
 */
public class prefix {
    public int solve(List<String> words, String prefix) {
        int index = firstPrefix(words, prefix);
        if (!words.get(index).startsWith(prefix)) return 0;
        return lastPrefix(words, prefix) - index + 1;
    }

    int firstPrefix(List<String> words, String prefix) {
        int left = 0, right = words.size() - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (words.get(mid).compareTo(prefix) > 0) right = mid - 1;
            else left = mid + 1;
        }
        return left;
    }

    int lastPrefix(List<String> words, String prefix) {
        int left = 0, right = words.size() - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (words.get(mid).compareTo(prefix) <= 0 || words.get(mid).startsWith(prefix))
                left = mid + 1;
            else right = mid - 1;
        }
        return right;
    }


    public static void main(String[] args) {
        List<String> list = Arrays.asList("a", "aa", "b", "efs", "rgtg", "sdfw", "ghgh", "ghght", "z", "zvg", "zdfg");
        Collections.sort(list);
        System.out.println(list);
        System.out.println(new prefix().solve(list, "gh"));
    }
}
