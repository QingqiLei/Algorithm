package google;

public class lc767 {
    /*
    Given a string s, rearrange the characters of s so that any
    two adjacent characters are not the same.

Return any possible rearrangement of s or return "" if not possible.


     */
    public String reorganizeString(String S) {
        int n = S.length();
        int[] count = new int[26];
        int max = 0, letter = 0;
        for(char c: S.toCharArray()){
            count[c-'a']++;
            if(count[c-'a'] > max){
                max = count[c-'a'];
                letter = c-'a';
            }
        }
        if(max > (n+1)/2) return "";
        char[] res = new char[n];
        int index = 0;
        while(count[letter] > 0){
            res[index] = (char)(letter+'a');
            index+=2;
            count[letter]--;
        }
        for(int i = 0; i < 26; i++){

            while(count[i] > 0){
                if(index >= n) n = 1;
                res[index] = (char)(i+'a');
                count[i]--;
                index+=2;
            }
        }
        return new String(res);
    }

}
