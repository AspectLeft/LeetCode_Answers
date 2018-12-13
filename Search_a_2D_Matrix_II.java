class Solution {
    int m, n, target;
    
    private int colIndex(int[][] matrix, int x, int yl, int yr) { // largetest y s.t. matrix[x][y] <= target
        if (yl > yr) return yr - 1;
        if (yl == yr) {
            if (matrix[x][yl] <= target) return yl;
            return yl - 1;
        }
        int y = (yl + yr) / 2;
        if (matrix[x][y] > target) return colIndex(matrix, x, yl, y - 1);
        return colIndex(matrix, x, y + 1, yr);
    }
    
    private int rowIndex(int[][] matrix, int y, int xl, int xr) {
        if (xl > xr) return xr - 1;
        if (xl == xr) {
            if (matrix[xl][y] <= target) return xl;
            return xl - 1;
        }
        int x = (xl + xr) / 2;
        if (matrix[x][y] > target) return rowIndex(matrix, y, xl, x - 1);
        return rowIndex(matrix, y, x + 1, xr);
    }
    
    private boolean searchRow(int[][] matrix, int x, int yl, int yr) {
        if (yl > yr) return false;
        if (yl == yr) return matrix[x][yl] == target;
        int y = (yl + yr) / 2;
        if (matrix[x][y] == target) return true;
        if (matrix[x][y] > target) return searchRow(matrix, x, yl, y - 1);
        return searchRow(matrix, x, y + 1, yr);
    }
    
    private boolean searchCol(int[][] matrix, int y, int xl, int xr) {
        if (xl > xr) return false;
        if (xl == xr) return matrix[xl][y] == target;
        int x = (xl + xr) / 2;
        if (matrix[x][y] == target) return true;
        if (matrix[x][y] > target) return searchCol(matrix, y, xl, x - 1);
        return searchCol(matrix, y, x + 1, xr);
    }
    
    private boolean searchMatrix(int[][] matrix, int xl, int xr, int yl, int yr) {
        if (xl > xr || yl > yr) return false;
        if (xl == xr && yl == yr) return matrix[xl][yl] == target;
        if (xl == xr) return searchRow(matrix, xl, yl, yr);
        if (yl == yr) return searchCol(matrix, yl, xl, xr);
        int x = (xl + xr) / 2, y = (yl + yr) / 2, xp, yp;
        if (matrix[x][y] == target) return true;
        if (matrix[x][y] > target) {
            if (searchMatrix(matrix, xl, x - 1, yl, y - 1)) {
                return true;
            }
            yp = colIndex(matrix, x, yl, y - 1);
            if (yp >= yl) {
                if (matrix[x][yp] == target) return true;
                if (searchMatrix(matrix, x + 1, xr, yl, yp)) return true;
            }
            xp = rowIndex(matrix, y, xl, x - 1);
            if (xp >= xl) {
                if (matrix[xp][y] == target) return true;
                if (searchMatrix(matrix, xl, xp, y + 1, yr)) return true;
            }
        }
        if (searchMatrix(matrix, x + 1, xr, y + 1, yr)) return true;
        yp = colIndex(matrix, x, y + 1, yr);
        if (yp <= yr && yp >= yl) {
            if (matrix[x][yp] == target) return true;
            if (searchMatrix(matrix, xl, x - 1, yp + 1, yr)) return true;
        }
        xp = rowIndex(matrix, y, x + 1, xr);
        if (xp <= xr && xp >= xl) {
            if (matrix[xp][y] == target) return true;
            if (searchMatrix(matrix, xp + 1, xr, yl, y - 1)) return true;
        }
        return false;
    }
    
    
    
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return false;
        m = matrix.length;
        n = matrix[0].length;
        this.target = target;
        int x = 0, y = n - 1, v;
        while (x < m && y >= 0) {
            v = matrix[x][y];
            if (v == target) return true;
            if (v > target) y--;
            else x++;
        }
        return false;
    }
}
