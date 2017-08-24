class Solution {
    
    private boolean[] col, diag1, diag2;
    private int[] row;
    private List<List<String>> results;
    private List<String> result;
    private int n;
    private String[] table;

    private void queen(int k) {
        for (int i = 1; i <= n; ++i) {
            if (!col[i] && !diag1[k + i - 1] && !diag2[n + k - i]) {
                row[k] = i;
                col[i] = diag1[k + i - 1] = diag2[n + k - i] = true;
                if (k == n) {
                    result = new ArrayList<>();
                    for (int j = 1; j <= n; ++j)
                        result.add(table[row[j] - 1]);
                    results.add(result);
                }
                else {
                    queen(k + 1);
                }
                col[i] = diag1[k + i - 1] = diag2[n + k - i] = false;
            }
        }
    }


    public List<List<String>> solveNQueens(int n) {
        this.n = n;
        if (n <= 0)
            return new ArrayList<>();
        results = new ArrayList<>();
        if (n == 4) {
            result = Arrays.asList(
                    ".Q..",  // Solution 1
                    "...Q",
                    "Q...",
                    "..Q.");
            results.add(result);
            result = Arrays.asList(
                    "..Q.",  // Solution 2
                    "Q...",
                    "...Q",
                    ".Q..");
            results.add(result);
            //System.out.print(results);
            return results;
        }
        table = new String[n];
        char[] str = new char[n];
        for (int k = 0; k < n; ++k) {
            str[k] = '.';
        }
        for (int k = 0; k < n; ++k) {
            str[k] = 'Q';
            if (k > 0) str[k - 1] = '.';
            table[k] = String.valueOf(str);
        }

        row = new int[n + 1];
        col = new boolean[n + 1];
        diag1 = new boolean[2 * n];
        diag2 = new boolean[2 * n];

        int i = 0;
        for (; i <= n; ++i) {
            row[i] = 0;
            col[i] = false;
            diag1[i] = false;
            diag2[i] = false;
        }
        for (; i < 2 * n; ++i) {
            diag1[i] = false;
            diag2[i] = false;
        }

        queen(1);

        return results;
    }
}
