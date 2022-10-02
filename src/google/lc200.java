package google;

import java.util.HashMap;
import java.util.Map;

/*
ship game，input 10x10 2d matrix，0和1构成，
海是0船是1，船是一维直线的1（横向纵向都行），不存在斜着摆放的船，
两个船之间横向/纵向/对角线不能靠的太近（两个船之间至少隔一个0），
如果input不满足此条件之间return false。
最后需要return船的数量和size
（size是船的长度，例如size为5的船有1个，size为4的船有两个，etc）
 */
public class lc200 {


    boolean valid(int[][] matrix){
        int m = matrix.length, n = matrix[0].length;
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(matrix[i][j] == 0) continue;
                boolean vertical = false, horizontal = false;
                if(i-1 >=0) vertical |= (matrix[i-1][j] == 1);
                if(i+1 < m)  vertical |= (matrix[i+1][j] == 1);
                if(j-1 >=0) horizontal |= (matrix[i][j-1] == 1);
                if(j+1 >=0) horizontal |= (matrix[i][j+1] == 1);
                if(vertical && horizontal) return false;

            }
        }
        return true;
    }

    Map<Integer, Integer> countBoat(int[][] matrix){
        Map<Integer, Integer> size2cnt = new HashMap<>();
        int m = matrix.length, n = matrix[0].length;
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(matrix[i][j] == 0) continue;
                int size = dfs(matrix, i, j);
                size2cnt.put(size, size2cnt.getOrDefault(size, 0)+1);

            }
        }
        return size2cnt;
    }

    int dfs(int[][] matrix, int x, int y){
        if(x <0 || y < 0 || x >= matrix.length || y >= matrix[0].length || matrix[x][y] == 0) return 0;
        matrix[x][y] = 0;
        return 1+ dfs(matrix, x+1, y) + dfs(matrix, x, y+1) + dfs(matrix, x-1, y) + dfs(matrix, x, y-1);

    }
}
