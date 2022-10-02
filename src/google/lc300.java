package google;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A subsequence is a sequence that can be derived from an array by deleting some or
 * no elements without changing the order of the
 * remaining elements. For example, [3,6,2,7] is a subsequence of the array [0,3,1,6,2,2,7].
 */
public class lc300 {
    public int lengthOfLIS1(int[] nums){
        int n = nums.length, res = Integer.MIN_VALUE;
        // dp[i] = max(dp[i], dp[j]+1) if nums[i] > nums[j]

        int[] dp = new int[n+1];
        Arrays.fill(dp, 1);
        for(int i = 0; i < n; i++){
           for(int j = i-1; j >=0; j--){
               if(nums[i] > nums[j])
                   dp[i+1] = Math.max(dp[i+1], dp[j+1]+1);
           }
           res = Math.max(res, dp[i+1]);
        }
        return res;
    }

    public List<Integer> lengthOfLIS(int[] nums){
        List<Integer> list = new ArrayList<>();
        list.add(nums[0]);
        for(int i = 1; i < nums.length; i++){
            int num = nums[i];
            if(num > list.get(list.size()-1))
                list.add(num);
            else{
                int j = 0;
                while (num > list.get(j)) j++;
                list.set(j,nums[i]);

            }
        }
        return list;
    }

    public static void main(String[] args) {
        lc300 l  = new lc300();
        System.out.println(l.lengthOfLIS(new int[]{10,9,2,5,3,7,101,18}));
    }
}
