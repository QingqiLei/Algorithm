package google;

import java.util.Arrays;

/*
input是二维矩阵，求从第一行的任何元素到最后一行的任意元素的最长距离，
可以下左右三个方向走。下面这个例子，答案要是9.
11001
11100
00000
 */
public class longestPath {
    int findLongest(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        int[] dp = new int[n];
        helper(matrix[0], dp);
        for (int i = 1; i < m; i++) {
            int[] dp1 = dp;
            dp = new int[n];
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0 && dp1[j] > 0) {
                    for (int count = dp1[j] + 1, k = j; k >= 0 && matrix[i][k] == 0; k--, count++) {
                        dp[k] = Math.max(dp[k], count);
                    }
                    for (int count = dp1[j] + 1, k = j; k < n && matrix[i][k] == 0; k++, count++) {
                        dp[k] = Math.max(dp[k], count);
                    }
                }
            }
        }
        int res = 0;
        for (int i : dp) res = Math.max(res, i);
        return res;

    }

    void helper(int[] matrix, int[] dp) {
        int n = matrix.length;
        for (int i = 0; i < n; i++) {
            if (matrix[i] == 0) {
                int j = i;
                for (int counter = 1; j < n && matrix[j] == 0; j++, counter++) {
                    dp[j] = counter;
                }
                i = j - 1;
                j--;
                for (int counter = 1; j >= 0 && matrix[j] == 0; j--, counter++) {
                    dp[j] = Math.max(dp[j], counter);
                }
            }
        }

    }

    int findShortest(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        int[] dp = new int[n];
        Arrays.fill(dp, Integer.MAX_VALUE);
        for (int i = 0; i < n; i++) if (matrix[0][i] == 0) dp[i] = 1;
        System.out.println(Arrays.toString(dp));
        for (int i = 1; i < m; i++) {
            int[] dp1 = dp;
            dp = new int[n];
            Arrays.fill(dp, Integer.MAX_VALUE);
            for (int j = 1; j < n; j++) {
                if (matrix[i][j] == 1 || dp1[j] == Integer.MAX_VALUE) continue;
                System.out.println(j+" "+ (dp1[j]+1));

                for (int k = j, count = dp1[j]+1; k >= 0 && matrix[i][k] == 0; k--, count++)
                    dp[k] = Math.min(dp[k], count);
                for (int k = j, count = dp1[j]+1; k < n && matrix[i][k] == 0; k++, count++)
                    dp[k] = Math.min(dp[k], count);
            }
            System.out.println(Arrays.toString(dp));
        }
        int res = Integer.MAX_VALUE;
        for(int i: dp) res = Math.min(res, i);
        return res == Integer.MAX_VALUE? -1: res;
    }



    public static void main(String[] args) {
        longestPath l = new longestPath();
        int[] dp = new int[10];
        System.out.println(l.findShortest(new int[][]{{1, 1, 0, 0, 1}, {1, 1, 1, 0, 0}, {0, 0, 0, 0, 0}}));
    }
}
