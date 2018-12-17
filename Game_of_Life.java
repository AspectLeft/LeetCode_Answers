class Solution {
    private int[] status = {0, // dead
                            1, // live
                            2, // living
                            3, // dying
                           };
    
    
    public void gameOfLife(int[][] board) {
        if (board == null || board.length == 0 || board[0].length == 0) return;
        int m = board.length, n = board[0].length;
        int c;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                c = 0;
                if (i > 0) {
                    if (board[i - 1][j] % 2 == 1) c++;
                    if (j > 0 && board[i - 1][j - 1] % 2 == 1) c++;
                    if (j < n - 1 && board[i - 1][j + 1] % 2 == 1) c++;
                }
                if (i < m - 1) {
                    if (board[i + 1][j] % 2 == 1) c++;
                    if (j > 0 && board[i + 1][j - 1] % 2 == 1) c++;
                    if (j < n - 1 && board[i + 1][j + 1] % 2 == 1) c++;
                }
                if (j > 0 && board[i][j - 1] % 2 == 1) c++;
                if (j < n - 1 && board[i][j + 1] % 2 == 1) c++;
                if (board[i][j] == 1) {
                    if (c < 2) board[i][j] = 3;
                    else if (c < 4) board[i][j] = 1;
                    else board[i][j] = 3;
                }
                else {
                    if (c == 3) board[i][j] = 2;
                }
            }
        }
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (board[i][j] == 2)
                    board[i][j] = 1;
                else if (board[i][j] == 3)
                    board[i][j] = 0;
            }
        }
    }
}
