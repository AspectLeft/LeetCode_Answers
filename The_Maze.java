class Solution {
    int m, n;
    // int[][][][] next;
    int[][] maze;
    boolean[][] visited;
    int[] dest;
    
    class State implements Comparable<State> {
        int x, y;
        int step;
        int dist;
        
        State(int x, int y, int step) {
            this.x = x;
            this.y = y;
            this.step = step;
            this.dist = Math.abs(x - dest[0]) + Math.abs(y - dest[1]);
        }
        
        
        @Override
        public int compareTo(State o) {
            return Integer.compare(step + dist, o.step + o.dist);
        }
    }
    
    public boolean hasPath(int[][] maze, int[] start, int[] destination) {
        if (maze == null || maze.length == 0 || maze[0].length == 0) return false;
        m = maze.length;
        n = maze[0].length;
        this.maze = maze;
        // next = new int[m][n][4][2];
        visited = new boolean[m][n];
        this.dest = destination;
        /*
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (maze[i][j] == 1) continue;
                
                //left
                if (canGoTo(i - 1, j)) {
                    next[i][j][0] = next[i - 1][j][0];
                }
                else {
                    next[i][j][0][0] = i;
                    next[i][j][0][1] = j;
                }
                
                //up
                if (canGoTo(i, j - 1)) {
                    next[i][j][1] = next[i][j - 1][1];
                }
                else {
                    next[i][j][1][0] = i;
                    next[i][j][1][1] = j;
                }
            }
        }
        
        for (int i = m - 1; i >=0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                if (maze[i][j] == 1) continue;
                
                //right
                if (canGoTo(i + 1, j)) {
                    next[i][j][2] = next[i + 1][j][2];
                }
                else {
                    next[i][j][2][0] = i;
                    next[i][j][2][1] = j;
                }
                
                //down
                if (canGoTo(i, j + 1)) {
                    next[i][j][3] = next[i][j + 1][3];
                }
                else {
                    next[i][j][3][0] = i;
                    next[i][j][3][1] = j;
                }
            }
        }
        */
        //System.out.println(String.format("%d,%d", next[0][3][3][0], 
        //                                 next[0][3][3][1]));
        
        LinkedList<int[]> queue = new LinkedList<>();
        queue.addLast(start);
        visited[start[0]][start[1]] = true;
        while (!queue.isEmpty()) {
            int[] state = queue.removeLast();
            if (state[0] == dest[0] && state[1] == dest[1]) return true;
            //System.out.println(String.format("x,y:%d,%d", state.x, state.y));
            for (int dir = 0; dir < 4; ++dir) {
                int[] nextxy = next(state[0], state[1], dir); // next[state[0]][state[1]][dir];
                int x = nextxy[0], y = nextxy[1];
                if (x == state[0] && y == state[1]) continue;
                if (visited[x][y]) continue;
                visited[x][y] = true;
                queue.addLast(new int[]{x, y});
            }
        }
        return false;
    }
    
    private boolean canGoTo(int i, int j) {
        return i >= 0 && i < m && j >= 0 && j < n && maze[i][j] == 0;
    }
    
    private int[] next(int i, int j, int dir) {
        switch (dir) {
            case 0:
                while (i >= 0 && maze[i][j] == 0) i--;
                return new int[]{i + 1, j};
            case 1:
                while (j >= 0 && maze[i][j] == 0) j--;
                return new int[]{i, j + 1};
            case 2:
                while (i < m && maze[i][j] == 0) i++;
                return new int[]{i - 1, j};
            case 3:
                while (j < n && maze[i][j] == 0) j++;
                return new int[]{i, j - 1};
        }
        return null;
    }
}
