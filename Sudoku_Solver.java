class Solution {
    private char[] SudokuTable = new char[]{'1', '2', '3', '4', '5', '6', '7', '8', '9'};
    
    private boolean finished = false;
    
    private void dfsSudoku(char[][] board, int m , int n) {
        if (m <= 8 && n <= 8 && !finished) {
            char c = board[m][n];
            if (c == '.') {
                boolean[] valid = new boolean[9];
                Arrays.fill(valid, true);
                for (int i = 0; i < 9; ++i) {
                    if (i != m) {
                        if (board[i][n] != '.') {
                            valid[board[i][n] - '1'] = false;
                        }
                        
                    }
                    if (i != n) {
                        if (board[m][i] != '.') {
                            valid[board[m][i] - '1'] = false;
                        }
                    }
                    
                }
                for (int i = 0; i < 3; ++i) {
                    for (int j = 0; j < 3; ++j) {
                        if (m / 3 * 3 + i != m || n / 3 * 3 + j != n) {
                            char c1 = board[m / 3 * 3 + i][n / 3 * 3 + j];
                            if (c1 != '.') {
                                valid[c1 - '1'] = false;
                            } 
                        }
                        
                    }
                }
                for (int i = 0; i < 9; ++i) {
                    if (valid[i] && !finished) {
                        board[m][n] = SudokuTable[i];
                        int newM = m, newN = n;
                        newN += 1;
                        newM += newN / 9;
                        newN %= 9;
                        dfsSudoku(board, newM, newN);
                        
                        if (!finished) {
                            board[m][n] = '.';
                        }
                    }
                }
            }
            else {
                n += 1;
                m += n / 9;
                n %= 9;
                dfsSudoku(board, m, n);
            }

        }
        else {
            finished = true;
        }
    }

    public void solveSudoku(char[][] board) {

        dfsSudoku(board, 0, 0);
    }
}
