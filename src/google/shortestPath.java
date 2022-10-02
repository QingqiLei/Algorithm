package google;

/*
经典的最短路径问题，给你一个0, 1
2*N矩阵(0代表空可以走， 1代表墙不能走），
问左上角到右下角的最短路径。上来先问了算法和数据结构，不要求写代码。
我先说了一下BFS解法，面试官问我有没有其他解法，回答DFS，
也大致说了一下数据结构和算法，大致代码结构是怎么样的，然后问了复杂度。
follow up问能不能优化， 我说可能不行，得全部遍历一遍才行。
面试官：空间呢？这个时候我才反应过来他估计想要我给一个tabulation dp的解法。
有点像蠡口用dp求最大正方形那题，但发现有cross dependency。
面试官说对，这就是这道题最tricky的部分。想了想没想出来，
给了Hint: Math.min的时候左下， 右下一块考虑， 这就是为什么给的矩阵是2 * N 的。
（这里我解释的可能不要好， 举个例子 二维矩阵第一行 0 1 0 0 第二行 0 1 1 0，
当你update 黑体1那个位置的dp矩阵时，
把第二行的第一个和第二个一块考虑）我按照他的提示给了答案最后被要求写代码。
面试官还挺好的，已经到点了，让我继续写，最后写完超时2分钟，还问我有没有什么问题。
这一轮感觉面试官非常nice，非常的interactive.
 */
/*

dp[0]
dp[1]

 */


public class shortestPath {
    int getLong(int[][] matrix){
        int up = 0, down = matrix[1][0] == 1?-1:1;

        for(int i = 1; i < matrix[0].length; i++){
            int up1 = up, down1 = up;
            if(matrix[0][i] == 1) up = -1;

        }
        return down;

    }
}
