class TicTacToe {
    int[][] board;
    int n;

    /** Initialize your data structure here. */
    public TicTacToe(int n) {
        this.board = new int[n][n];
        this.n = n;
    }
    
    /** Player {player} makes a move at ({row}, {col}).
        @param row The row of the board.
        @param col The column of the board.
        @param player The player, can be either 1 or 2.
        @return The current winning condition, can be either:
                0: No one wins.
                1: Player 1 wins.
                2: Player 2 wins. */
    public int move(int row, int col, int player) {
        board[row][col] = player;
        for (int[] dir: dirs) {
            int length = 1;
            int r = row, c = col;
            r += dir[0];
            c += dir[1];
            while (isInBound(r, c) && board[r][c] == player) {
                length++;
                r += dir[0];
                c += dir[1];
            }
            r = row;
            c = col;
            r -= dir[0];
            c -= dir[1];
            while (isInBound(r, c) && board[r][c] == player) {
                length++;
                r -= dir[0];
                c -= dir[1];
            }
            
            if (length == n) return player;
        }
        return 0;
    }
    
    private int[][] dirs = new int[][]{{1,0}, {0, 1}, {1,1}, {-1, 1}};
    
    private boolean isInBound(int row, int col) {
        return 0 <= row && row < n && 0 <= col && col < n;
    }
}

/**
 * Your TicTacToe object will be instantiated and called as such:
 * TicTacToe obj = new TicTacToe(n);
 * int param_1 = obj.move(row,col,player);
 */
