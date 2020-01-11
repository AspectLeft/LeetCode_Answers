class Solution {
    public int longestLine(int[][] M) {
        if (M == null || M.length == 0 || M[0].length == 0) return 0;
        int m = M.length;
        int n = M[0].length;
        int result = 0;
        
        int l = 0;
        for (int[] row: M) {
            l = 0;
            for (int v: row) {
                if (v == 0) l = 0;
                else {
                    l++;
                    if (l > result) result = l;
                }
            }
        }
        
        for (int j = 0; j < n; ++j) {
            l = 0;
            for (int i = 0; i < m; ++i) {
                if (M[i][j] == 0) l = 0;
                else {
                    l++;
                    if (l > result) result = l;
                }
            }
        }
        
        int i, j;
        for (int sum = 0; sum <= m - 1 + n - 1; ++sum) {
            l = 0;
            i = Math.max(0, sum - n + 1);
            j = sum - i;
            while (i < m && j >= 0) {
                if (M[i][j] == 0) l = 0;
                else {
                    l++;
                    if (l > result) result = l;
                }
                
                i++;
                j--;
            }
        }
        
        for (int diff = 0 - (m - 1); diff <= n - 1; ++diff) {
            l = 0;
            i = Math.max(0, - diff);
            j = i + diff;
            while (i < m && j < n) {
                if (M[i][j] == 0) l = 0;
                else {
                    l++;
                    if (l > result) result = l;
                }
                
                i++;
                j++;
            }
        }
        
        return result;
    }
}
