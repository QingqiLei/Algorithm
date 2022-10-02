package google;

import java.util.HashMap;
import java.util.Map;

public class lc1027 {

    public int longestArithSeqLength(int[] A) {
    // dp[index][diff]
    // dp[index][different]: the length of longest Arith SeqLength at index
    // dp[index][diff] = 1 + dp[j][diff]
        Map<Integer, Integer>[] dp = new Map[A.length];
        int res = 1;
        for(int i =0; i < A.length; i++){
            dp[i] = new HashMap<>();
            for(int j = 0; j < i; j++){
                int diff = A[i] - A[j];
                dp[i].put(diff, dp[j].getOrDefault(diff,1)+1);
                res = Math.max(res, dp[i].get(diff));
            }
        }
        return res;
    }
}
