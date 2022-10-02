public class BinaryIndexTree2D {
    int[][] matrix;
    int[][] tree;
    public void NumMatrix(int[][] matrix) {
        int n = matrix.length, m = matrix[0].length;
        this.matrix = new int[n][m];
        tree = new int[n+1][m+1];
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m;j ++){
                update(i,j,matrix[i][j]);
            }
        }

    }

    public void update(int row, int col, int val) {
        int delta = val - matrix[row][col];
        matrix[row][col] = val;
        for(int i = row+1; i < tree.length; i += i & -i){
            for(int j = col +1; j < tree[0].length; j += j & -j){
                tree[i][j] += delta;
            }
        }
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        return sum(row2, col2) + sum(row1 -1, col1 -1) - sum(row1 -1, col2) - sum(row2, col1-1);
    }

    public int sum(int row, int col){
        int res = 0;
        for(int i = row+1; i >0; i -= i & -i){
            for(int j = col +1; j > 0; j -= j & -j){
                res += tree[i][j];
            }
        }
        return res;
    }
}
