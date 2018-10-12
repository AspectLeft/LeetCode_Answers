class Solution {
    private boolean survived[][];
    private int m, n;
    private char[][] board;
    public void solve(char[][] board) {
        if (board == null || board.length == 0) return;
        this.board = board;
        m = board.length;
        n = board[0].length;
        survived = new boolean[m][n];
        for (int j = 0; j < n; ++j) 
            expand(0, j);
        for (int i = 0; i < m; ++i)
            expand(i, n - 1);
        for (int j = n - 1; j >= 0; --j)
            expand(m - 1, j);
        for (int i = m - 1; i >= 0; --i)
            expand(i, 0);
        for (int i = 0; i < m; ++i)
            for (int j = 0; j < n; ++j)
                if (board[i][j] == 'O' && !survived[i][j])
                    board[i][j] = 'X';
    }
    
    private void expand(int i, int j) {
        if (i >= 0 && i < m && j >= 0 && j < n && board[i][j] == 'O' && !survived[i][j]) {
            survived[i][j] = true;
            expand(i - 1, j);
            expand(i + 1, j);
            expand(i, j - 1);
            expand(i, j + 1);
        }
    }
}
