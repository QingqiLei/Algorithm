package google;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/*
一个int 2D array，全是非负数，要求从左上角走到右下角，只能向下或向右走，
求经过路径中最大值的最小值是多少。追问，如果四个方向都可以走，
路径中的最大值的最小值是多少。
 */
public class matrixPath {

    int getmin(int[][] matrix){
        int m = matrix.length, n = matrix[0].length;
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                int value = matrix[i][j];
                if(j > 0 && i > 0) matrix[i][j] = value + Math.min(matrix[i-1][j], matrix[i][j-1]);
                else if(i > 0) matrix[i][j] = value + matrix[i-1][j];
                else if(j > 0) matrix[i][j] = value + matrix[i][j-1];

            }
        }
        return matrix[m-1][n-1];
    }

    int getmax(int[][] matrix){
        int m = matrix.length, n = matrix[0].length;
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                int value = matrix[i][j];
                if(j > 0 && i > 0) matrix[i][j] = value + Math.max(matrix[i-1][j], matrix[i][j-1]);
                else if(i > 0) matrix[i][j] = value + matrix[i-1][j];
                else if(j > 0) matrix[i][j] = value + matrix[i][j-1];

            }
            System.out.println(Arrays.toString(matrix[i]));
        }
        return matrix[m-1][n-1];
    }

    int getmin1(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        int[][] dires = new int[][]{{0,1},{0,-1},{1,0},{-1,0}};
        int[][] dis = new int[m][n];
        for(int[] i: dis) Arrays.fill(i, Integer.MAX_VALUE);
        // [x,y, length]
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a-> a[2]));
        pq.add(new int[]{0,0,matrix[0][0]});
        dis[0][0] = matrix[0][0];
        while(pq.size() > 0){
            int cur[] = pq.poll(), x = cur[0], y = cur[1], value = cur[2];
            for(int[] dire: dires){
                int nx = x + dire[0], ny = y + dire[1];
                if(nx < 0 || ny < 0 || nx >= m || ny >= n ) continue;
                if(value + matrix[nx][ny] < dis[nx][ny]) {
                    dis[nx][ny] = value + matrix[nx][ny];
                    pq.add(new int[]{nx,ny,value + matrix[nx][ny]});
                }
            }
        }
        return dis[m-1][n-1];
    }

    public static void main(String[] args) {
        System.out.println(new matrixPath().getmax1(new int[][]{{1, 1, 0, 0, 100}, {1, 1, 1, 0, 0}, {0, 0, 0, 0, 0}}));
    }

    int getmax1(int[][] matrix){
        int m = matrix.length, n = matrix[0].length;
        boolean[][] visited = new boolean[m][n];
        visited[0][0] = true;
        return dfs(matrix, 0,0,visited);
    }
    int[][] dires = new int[][]{{0,1},{0,-1},{1,0},{-1,0}};
    int dfs(int[][] matrix, int x, int y, boolean[][] visited){
        if(x == matrix.length-1 && y == matrix[0].length-1) return matrix[x][y];
        int max = -2;
        for(int[] dire: dires){
            int nx = x + dire[0], ny = y + dire[1];
            if(nx < 0 || ny < 0 || nx >= matrix.length || ny >= matrix[0].length || visited[nx][ny] ) continue;
            visited[nx][ny] = true;
            int len = dfs(matrix, nx, ny, visited);
            if(len == -1) continue;
            max = Math.max(max, len);
            visited[nx][ny] = false;
        }
        if(max == -2) return -1;

        return matrix[x][y] + max;
    }
}
