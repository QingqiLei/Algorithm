package google;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class lc1293 {
    public int shortestPath(int[][] grid, int k) {
        int m = grid.length, n = grid[0].length;
        if(k >=(grid.length + grid[0].length -2)) return grid.length + grid[0].length -2;
        int[][] dires = new int[][]{{0,1},{0,-1},{1,0},{-1,0}};
        Queue<int[]> q = new LinkedList<>();
        Set<String> seen = new HashSet<>();
        // x y steps k
        q.add(new int[]{0,0,0,k});
        seen.add(encode(0,0,k));
        int miniStep = Integer.MAX_VALUE;
        while(q.size() > 0){
            int cur[] = q.poll(), x = cur[0], y = cur[1], steps = cur[2], leftk = cur[3];
            if(x == m -1 && y == n -1) miniStep = Math.min(miniStep, steps);
            for(int[] dire: dires){
                int nx = x + dire[0], ny = y + dire[1], leftk1 = leftk;
                if(nx < 0 || ny < 0 || nx >= m || ny >= n) continue;
                if(grid[nx][ny] == 1) leftk1--;
                if(leftk1 < 0 || seen.contains(encode(nx, ny, leftk1))) continue;
                q.add(new int[]{nx, ny, steps+1, leftk1});
                seen.add(encode(nx, ny, leftk1));
            }
        }
        return miniStep == Integer.MAX_VALUE? -1: miniStep;
    }

    String encode(int x, int y, int k){
        return x+":"+y+":"+k;
    }

}
