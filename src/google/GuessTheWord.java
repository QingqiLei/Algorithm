package google;

import java.util.ArrayList;
import java.util.List;

public class GuessTheWord {

    void findSecretWord(String[] wordlist, Master master){
        for(int t = 0, x = 0; t < 10 && x < 6; t++){
            int[][] count = new int[6][26];
            for(String word: wordlist)
                for(int i = 0; i < 6; i++)
                    count[i][word.charAt(i) -'a']++;
            String guess = wordlist[0];
            int bestScore = 0;
            for(String word: wordlist){
                int score = 0;
                for(int i = 0; i < 6; i++) score += count[i][word.charAt(i)-'a'];
                if(score > bestScore){
                    guess = word;
                    bestScore = score;
                }
            }
            x = master.guess(guess);
            List<String> wordList2 = new ArrayList<>();
            for(String w: wordlist) if(match(guess, w) == x) wordList2.add(w);
            wordlist = wordList2.toArray(new String[0]);
        }
    }


    int match(String a, String b){
        int matches  = 0;
        for(int i = 0; i < a.length(); i++)
            if(a.charAt(i) == b.charAt(i))
                matches++;
        return matches;
    }

    class Master{
        int guess(String word){
            return 6;
        }
    }
}
