


public class Tire{

    Tire[] next = new Tire[26];
    int cnt = 0;

    void add(String word, int index){
        if(index < word.length() && next[word.charAt(index) -'a'] == null) next[word.charAt(index) -'a'] = new Tire();

        if(index != 0) cnt++;
        if(index == word.length()){
            return;
        }else{
            next[word.charAt(index) -'a'].add(word, index +1);
        }
    }

    int quary(String word, int index){
        if(index == word.length()){
            return cnt;
        }else {
            return cnt + next[word.charAt(index) - 'a'].quary(word, index + 1);
        }
    }


}