class Solution {
    class State {
        int[][] board;
        int step;
        public State(int[][] b, int s) {
            board = b;
            step = s;
        }
        
        public int toInt() {
            return board[0][0] + 10 * board[0][1] + 100 * board[0][2]
                + 1000 * board[1][0] + 10000 * board[1][1] + 100000 * board[1][2];
        }
    }
    
    private boolean goalTest(int[][] board) {
        return board[0][0] == 1 && board[0][1] == 2 && board[0][2] == 3
            && board[1][0] == 4 && board[1][1] == 5 && board[1][2] == 0;
    }
    
    private boolean invalid(int[][] board) {
        return board[0][0] == 1 && board[0][1] == 2 && board[0][2] == 3
            && board[1][0] == 5 && board[1][1] == 4 && board[1][2] == 0;
        
    }
    
    private State move(State state, int di, int dj, int i0, int j0) {
        int[][] newboard = new int[2][3];
        for (int i = 0; i < 2; i++)
            for (int j = 0; j < 3; j++)
                newboard[i][j] = state.board[i][j];
        newboard[i0][j0] = newboard[i0 + di][j0 + dj];
        newboard[i0 + di][j0 + dj] = 0;
        return new State(newboard, state.step + 1);
    }
    
    private List<State> next(State state) {
        int i = 0, j = 0;
        boolean flag = false;
        for (i = 0; i < 2; i++) {
            for (j = 0; j < 3; j++) {
                if (state.board[i][j] == 0) {
                    flag = true;
                    break;
                }
            }
            if (flag) break;
        }
        List<State> nexts = new ArrayList<>();
        if (i > 0) {
            nexts.add(move(state, -1, 0, i, j));
        }
        if (i < 1) {
            nexts.add(move(state, 1, 0, i, j));
        }
        if (j > 0) {
            nexts.add(move(state, 0, -1, i, j));
        }
        if (j < 2) {
            nexts.add(move(state, 0, 1, i, j));
        }
        return nexts;
    }
    
    
    
    public int slidingPuzzle(int[][] board) {
        List<State> level = new ArrayList<>();
        level.add(new State(board, 0));
        BitSet bitSet = new BitSet();
        while (!level.isEmpty()) {
            List<State> nextLevel = new ArrayList<>();
            for (State state: level) {
                if (goalTest(state.board)) {
                    return state.step;
                }
                if (state.step > 20 || invalid(state.board))
                    return -1;
                bitSet.set(state.toInt());
                for (State nextState: next(state)) {
                    if (!bitSet.get(nextState.toInt()))
                        nextLevel.add(nextState);
                }
            }
            level = nextLevel;
        }
        return -1;
    }
}
