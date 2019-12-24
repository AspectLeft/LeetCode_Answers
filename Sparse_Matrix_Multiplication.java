class Solution {
    public int[][] multiply(int[][] A, int[][] B) {
        List<List<int[]>> rows = toRows(A);
        List<List<int[]>> cols = toCols(B);
        int m = rows.size(), n = cols.size();
        int[][] output = new int[m][n];
        List<int[]> row, col;
        int k1, k2;
        int[] r, c;
        
        
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                row = rows.get(i);
                col = cols.get(j);
                k1 = 0;
                k2 = 0;
                while (k1 < row.size() && k2 < col.size()) {
                    r = row.get(k1);
                    c = col.get(k2);
                    if (r[0] == c[0]) {
                        output[i][j] += r[1] * c[1];
                        k1++;
                        k2++;
                    }
                    else if (r[0] > c[0]) {
                        k2++;
                    }
                    else {
                        k1++;
                    }
                }
            }
        }
        return output;
    }
    
    private List<List<int[]>> toRows(int[][] A) {
        List<List<int[]>> rows = new ArrayList<>();
        for (int[] r: A) {
            List<int[]> row = new ArrayList<>();
            for (int j = 0; j < r.length; ++j) {
                if (r[j] != 0) {
                    row.add(new int[]{j, r[j]});
                }
            }
            rows.add(row);
        }
        return rows;
    }
    
    private List<List<int[]>> toCols(int[][] B) {
        List<List<int[]>> cols = new ArrayList<>();
        for (int j = 0; j < B[0].length; ++j) {
            List<int[]> col = new ArrayList<>();
            for (int i = 0; i < B.length; ++i) {
                if (B[i][j] != 0) {
                    col.add(new int[]{i, B[i][j]});
                }
            }
            cols.add(col);
        }
        return cols;
    }
}
