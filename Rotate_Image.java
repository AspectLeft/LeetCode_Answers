class Solution {
    
    private void swap4(int[][] matrix, int i, int j) {
        int n = matrix.length;
        int tmp = matrix[i][j];
        matrix[i][j] = matrix[n - 1 - j][i];
        matrix[n - 1 - j][i] = matrix[n - 1 - i][n - 1 - j];
        matrix[n - 1 - i][n - 1 - j] = matrix[j][n - 1 - i];
        matrix[j][n - 1 - i] = tmp;
    }

    public void rotate(int[][] matrix) {
        int n = matrix.length;
        if (n <= 1) return;
        for (int i = 0; i < n / 2; ++i) {
            for (int j = i; j < n - 1 - i; ++j) {
                swap4(matrix, i, j);
            }
        }
    }

}
