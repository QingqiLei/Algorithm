package google;

/*
给你一个n * 3的grid，3种block (1*3, 2*3, 3*3)，问你怎样fill这个grid

1 2 3 4 5  6 7 8
1 2 4 7 13
 */
public class print {
    int solve(int n){
        long res = 0, mod = (long)(1e9+7);
        long[] dp = new long[n+1];
        dp[0] = 1;
        for(int i = 1; i<=n; i++){
            for(int j = 1; j <=3; j++){
                if(i-j >=0) dp[i] += dp[i-j];
            }
            dp[i] %= mod;
        }
        return (int)dp[n];
    }
}
