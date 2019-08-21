class Solution {
    private int[][] dirs = new int[][]{{-1, -1}, {-1, 0}, {-1, 1},
                                     {0, -1},           {0, 1},
                                     {1, -1}, {1, 0}, {1, 1}};
    
    public char[][] updateBoard(char[][] board, int[] click) {
        int x = click[0], y = click[1];
        if (x < 0 || x >= board.length || y < 0 || y >= board[0].length) return board;
        char c = board[x][y];
        if (c == 'B') return board;
        if (c == 'M') {
            board[x][y] = 'X';
            return board;
        }
        if (c == 'E') {
            board[x][y] = (char) ('0' + countAdjMines(board, x, y));
            if (board[x][y] == '0') {
                board[x][y] = 'B';
                for (int[] dir: dirs) {
                    updateBoard(board, new int[]{x + dir[0], y + dir[1]});
                }
            }
        }
        return board;
    }
    
    private int countAdjMines(char[][] board, int x, int y) {
        int count = 0;
        if (x > 0) {
            count += board[x - 1][y] == 'M' ? 1 : 0;
            if (y > 0) count += board[x - 1][y - 1] == 'M' ? 1 : 0;
            if (y < board[0].length - 1) count += board[x - 1][y + 1] == 'M' ? 1 : 0;
        }
        if (y > 0) count += board[x][y - 1] == 'M' ? 1 : 0;
        if (y < board[0].length - 1) count += board[x][y + 1] == 'M' ? 1 : 0;
        if (x < board.length - 1) {
            count += board[x + 1][y] == 'M' ? 1 : 0;
            if (y > 0) count += board[x + 1][y - 1] == 'M' ? 1 : 0;
            if (y < board[0].length - 1) count += board[x + 1][y + 1] == 'M' ? 1 : 0;
        }
        return count;
    }
    
}
