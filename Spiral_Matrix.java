class Solution {
    List<Integer> spiral;

    private void spiralOrder(int[][] matrix, int x1, int y1, int x2, int y2) {
        if (x1 > x2 || y1 > y2) return;
        if (x1 == x2) {
            for (int j = y1; j <= y2; ++j)
                spiral.add(matrix[x1][j]);
        }
        else if (y1 == y2) {
            for (int i = x1; i <= x2; ++i)
                spiral.add(matrix[i][y1]);
        }
        else {
            for (int j = y1; j < y2; ++j)
                spiral.add(matrix[x1][j]);
            for (int i = x1; i < x2; ++i)
                spiral.add(matrix[i][y2]);
            for (int j = y2; j > y1; --j)
                spiral.add(matrix[x2][j]);
            for (int i = x2; i > x1; --i)
                spiral.add(matrix[i][y1]);
            spiralOrder(matrix, x1 + 1, y1 + 1, x2 - 1, y2 - 1);
        }
    }

    public List<Integer> spiralOrder(int[][] matrix) {
        int m = matrix.length;
        if (m == 0) return new ArrayList<>();
        int n = matrix[0].length;
        if (n == 0) return new ArrayList<>();
        spiral = new ArrayList<>(m * n);
        spiralOrder(matrix, 0, 0, m - 1, n - 1);
        return spiral;
    }
}
