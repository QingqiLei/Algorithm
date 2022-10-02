package google;

/*
梨口贰佰变形，数湖泊数量，边界是海，followup 最大的陆地面积，继续
followup 如果能改变一个元素，最大陆地面积。
 */
public class islandNumber {
    // 0 : sea
    // 1 : land
    int findMax(int[][] matrix){
        int m = matrix.length, n = matrix[0].length, ans = 0;
        for(int i = 0; i  < m; i++){
            for(int j = 0; j < n;j ++){
                if(matrix[i][j] == 1) ans = Math.max(ans, dfs(matrix, i, j));
            }
        }
        return ans;
    }

    int[][] dires = new int[][]{{0,1},{0,-1}, {1,0},{-1,0}};

    int dfs(int[][] matrix, int x, int y){
        if(matrix[x][y] == 0) return 0;
        int count = 1;
        matrix[x][y] = 2;
        for(int[] dire: dires){
            int nx = x + dire[0], ny = y + dire[1];
            if(nx < 0 || ny < 0 || nx >= matrix.length || ny >= matrix[0].length || matrix[nx][ny] != 1) continue;
            count+= dfs(matrix, nx ,ny);
        }
        return count;
    }


    int findMaxWith1Change(int[][] matrix){
        int m = matrix.length, n = matrix[0].length, ans = 0;
        for(int i = 0; i  < m; i++){
            for(int j = 0; j < n;j ++){
                if(matrix[i][j] == 0) {
                    int land = 1;
                    for(int[] dire: dires){
                        int nx = i + dire[0], ny = j + dire[1];
                        if(nx < 0 || ny < 0 || nx >= matrix.length || ny >= matrix[0].length || matrix[nx][ny] != 1) continue;
                        land += dfs(matrix, nx, ny);
                    }
                    ans = Math.max(ans, land);
                }
            }
        }
        return ans;
    }
}
