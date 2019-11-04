class Solution {
    
    int m, n;
    // int[][][][] next;
    int[][] maze;
    int[] start;
    int[] dest;
    int[][] minDist;
    String[][] minPath;
    
    private static char[] dirChars = new char[]{'u', 'l', 'd', 'r'};
    
    class State implements Comparable<State> {
        int x, y;
        int dist;
        String path;
        
        State(int x, int y, int dist, String path) {
            this.x = x;
            this.y = y;
            this.dist = dist;
            this.path = path;
        }
        
        
        @Override
        public int compareTo(State o) {
            return Integer.compare(dist, o.dist);
        }
    }
    
    
    public String findShortestWay(int[][] maze, int[] ball, int[] hole) {
        
        if (maze == null || maze.length == 0 || maze[0].length == 0) 
            return "impossible";
        m = maze.length;
        n = maze[0].length;
        this.maze = maze;
        // next = new int[m][n][4][2];
        this.start = ball;
        this.dest = hole;
        this.minDist = new int[m][n];
        this.minPath = new String[m][n];
        
        
        PriorityQueue<State> queue = new PriorityQueue<>();
        queue.add(new State(start[0], start[1], 0, ""));
        minDist[start[0]][start[1]] = 0;
        
        for (int[] row: minDist) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }
        
        boolean flag = false;
        while (!queue.isEmpty()) {
            State state = queue.poll();
            //System.out.println(String.format("x,y,path:%d,%d,%s", state.x, state.y, state.path));
            
            if (state.dist > minDist[dest[0]][dest[1]]) break;
            for (int dir = 0; dir < 4; ++dir) {
                int[] nextxy = next(state.x, state.y, dir); // next[state[0]][state[1]][dir];
                int x = nextxy[0], y = nextxy[1];
                if (x == state.x && y == state.y) continue;
                
                if (passingTarget(x, y, state.x, state.y)) {
                    flag = true;
                    x = dest[0];
                    y = dest[1];
                }
                
                
                
                int nextdist = state.dist + 
                                    Math.abs(x - state.x) + 
                                    Math.abs(y - state.y);
                if (minDist[x][y] < nextdist) continue;
                String nextPath = state.path + dirChars[dir];
                if (minDist[x][y] == nextdist) {
                    if (minPath[x][y] == null 
                        || nextPath.compareTo(minPath[x][y]) < 0) {
                        minPath[x][y] = nextPath;
                        
                        queue.add(new State(x, y, nextdist, nextPath));
                    }
                }
                else {
                    minDist[x][y] = nextdist;
                    minPath[x][y] = nextPath;
                    
                    queue.add(new State(x, y, nextdist, nextPath));
                }
                
            }
        }
        //System.out.println(minDist[dest[0]][dest[1]]);
        if (flag) return minPath[dest[0]][dest[1]];
        return "impossible";
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
    
    private boolean between(int x, int nx, int tx) {
        return (x <= tx && tx <= nx) || (nx <= tx && tx <= x);
    }
    
    private boolean passingTarget(int x, int y, int nx, int ny) {
        return between(x, nx, dest[0]) && between(y, ny, dest[1]);
    }
}
