package google;

public class lc416 {
    public boolean canPartition(int[] nums) {
        int sum = 0;
        for(int i: nums) sum +=i;
        if(sum%2 == 1) return false;
        sum /=2;
        boolean[] dp = new boolean[sum+1];
        dp[0] = true;
        for(int num: nums){
            for(int i = sum; i >=num; i--)
                dp[i] = dp[i]|| dp[i-num];
        }
        return dp[sum];
    }


}
