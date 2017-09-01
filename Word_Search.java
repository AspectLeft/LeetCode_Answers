class Solution {
    
    private int m, n;

    private boolean exist(char[][] board, String word, int i, boolean[][] valid, int x, int y) {
        if (i >= word.length()) return true;
        char c = word.charAt(i);
        if (0 <= x - 1 && c == board[x - 1][y] && valid[x - 1][y]) {
            valid[x - 1][y] = false;
            if (exist(board, word, i + 1, valid, x - 1, y)) return true;
            valid[x - 1][y] = true;
        }
        if (x + 1 < m && c == board[x + 1][y] && valid[x + 1][y]) {
            valid[x + 1][y] = false;
            if (exist(board, word, i + 1, valid, x + 1, y)) return true;
            valid[x + 1][y] = true;
        }
        if (0 <= y - 1 && c == board[x][y - 1] && valid[x][y - 1]) {
            valid[x][y - 1] = false;
            if (exist(board, word, i + 1, valid, x, y - 1)) return true;
            valid[x][y - 1] = true;
        }
        if (y + 1 < n && c == board[x][y + 1] && valid[x][y + 1]) {
            valid[x][y + 1] = false;
            if (exist(board, word, i + 1, valid, x, y + 1)) return true;
            valid[x][y + 1] = true;
        }
        return false;
    }


    public boolean exist(char[][] board, String word) {
        if (word.equals("")) return true;
        m = board.length;
        if (m == 0) return false;
        n = board[0].length;
        if (n == 0) return false;
        boolean[][] valid = new boolean[m][n];
        for (int i = 0; i < m; ++i) Arrays.fill(valid[i], true);
        char c = word.charAt(0);
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (board[i][j] == c) {
                    valid[i][j] = false;
                    if (exist(board, word, 1, valid, i, j)) return true;
                    valid[i][j] = true;
                }
            }
        }
        return false;
    }
}
