package google;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class lc417 {
    int[][] dires = new int[][]{{0,1},{0,-1},{1,0},{-1,0}};
    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        int m = heights.length, n = heights[0].length;
        int[][] visited = new int[m][n];
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(i == 0 || j == 0) dfs(visited, heights, i, j, 1);
                if(i ==m-1 || j == n -1) dfs(visited, heights, i, j, 2);

            }
        }
        List<List<Integer>> res = new ArrayList<>();
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(visited[i][j] == 3) res.add(Arrays.asList(i,j));
            }
        }
        return res;
    }

    void dfs(int[][] visited, int[][] heights, int x, int y, int ocean){
        if(visited[x][y] == ocean || visited[x][y] == 3) return;
        visited[x][y] += ocean;
        for(int[] dire: dires){
            int nx = x + dire[0], ny = y + dire[1];
            if(nx < 0 || ny < 0 || nx >= heights.length || ny >= heights[0].length
                    || heights[nx][ny] < heights[x][y]) continue;
            dfs(visited, heights, nx, ny, ocean);
        }
    }
}
