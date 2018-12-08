class Solution {
    private int min(int a, int b) {
        return a < b ? a : b;
    }
    
    private int max(int a, int b) {
        return a > b ? a : b;
    }
    
    public int maximalSquare(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return 0;
        if (matrix.length == 1) {
            for (char c: matrix[0])
                if (c == '1')
                    return 1;
            return 0;
        }
        if (matrix[0].length == 1) {
            for (int i = 0; i < matrix.length; ++i)
                if (matrix[i][0] == '1')
                    return 1;
            return 0;
        }
        int m = matrix.length, n = matrix[0].length;
        int[][] c = new int[m][n];
        int maxa = 0;
        if (matrix[0][0] == '1') {
            c[0][0] = 1;
            maxa = 1;
        }
        for (int i = 1; i < m; ++i)
            if (matrix[i][0] == '1') {
                c[i][0] = 1;
                maxa = 1;
            }
        for (int j = 1; j < n; ++j)
            if (matrix[0][j] == '1') {
                c[0][j] = 1;
                maxa = 1;
            }
        for (int i = 1; i < m; ++i) {
            for (int j = 1; j < n; ++j) {
                if (matrix[i][j] == '1') {
                    c[i][j] = 1 + min(c[i - 1][j - 1], min(c[i - 1][j], c[i][j - 1]));
                    maxa = max(maxa, c[i][j] * c[i][j]);
                }
            }
        }
        return maxa;
    }
}
