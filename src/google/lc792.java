package google;

/*
Given a string s and an array of strings words, return the number of words[i] that is a subsequence of s.

A subsequence of a string is a new string generated from the original string with some characters (can be none) deleted without changing the relative order of the remaining characters.

For example, "ace" is a subsequence of "abcde".
 */

import java.util.ArrayList;
import java.util.List;

public class lc792 {

    public int numMatchingSubseq(String s, String[] words) {
        List<Node>[] heads = new List[26];
        for (int i = 0; i < 26; i++) heads[i] = new ArrayList<>();
        for (String word : words) {
            heads[word.charAt(0) - 'a'].add(new Node(word, 0));
        }
        int count = 0;
        for (char c : s.toCharArray()) {
            List<Node> oldList = heads[c - 'a'];
            heads[c - 'a'] = new ArrayList<>();
            for (Node node : oldList) {
                node.index++;
                if (node.word.length() == node.index) count++;
                else heads[node.word.charAt(node.index) - 'a'].add(node);
            }
        }
        return count;
    }

    class Node {
        String word;
        int index;

        Node(String word, int index) {
            this.word = word;
            this.index = index;
        }
    }
}
