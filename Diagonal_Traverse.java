class Solution {
    public int[] findDiagonalOrder(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return new int[]{};
        int m = matrix.length, n = matrix[0].length;
        int sum = 0;
        boolean xUp = false;
        int i = 0;
        int[] result = new int[m * n];
        for (; sum <= m + n - 2; ++sum) {
            if (xUp) {
                for (int x = Math.max(0, sum - (n - 1)); x < Math.min(m, sum + 1); ++x) {
                    result[i] = matrix[x][sum - x];
                    i++;
                }
            }
            else {
                for (int x = Math.min(m - 1, sum); x >= Math.max(0, sum - (n - 1)); --x) {
                    result[i] = matrix[x][sum - x];
                    i++;
                }
            }
            xUp = !xUp;
        }
        return result;
    }
}
