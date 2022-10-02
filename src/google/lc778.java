package google;

import java.util.Comparator;
import java.util.PriorityQueue;

public class lc778 {
    public int swimInWater(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        boolean[][] visited = new boolean[m][n];
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[2]));
        pq.offer(new int[]{0,0,grid[0][0]});
        visited[0][0] =true;
        int[][] dires = new int[][]{{1,0}, {-1,0},{0,1},{0,-1}};
        while(pq.size() > 0){
            int cur[] = pq.poll(), x = cur[0], y = cur[1], time = cur[2];
            if(x == m-1 && y == n-1) return time;
            for(int[] dire: dires){
                int nx = x + dire[0], ny = y + dire[1];
                if(nx < 0 || ny < 0 || nx >= m || ny >=n || visited[nx][ny]) continue;
                pq.add(new int[]{nx, ny, Math.max(grid[nx][ny], time)});
                visited[nx][ny] = true;
            }
        }

        return -1;
    }
}
