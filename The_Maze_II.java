class Solution {
    int m, n;
    // int[][][][] next;
    int[][] maze;
    int[] dest;
    int[][] minDist;
    
    class State implements Comparable<State> {
        int x, y;
        int dist;
        
        State(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }
        
        
        @Override
        public int compareTo(State o) {
            return Integer.compare(dist, o.dist);
        }
    }
    
    
    public int shortestDistance(int[][] maze, int[] start, int[] destination) {
        if (maze == null || maze.length == 0 || maze[0].length == 0) return -1;
        m = maze.length;
        n = maze[0].length;
        this.maze = maze;
        // next = new int[m][n][4][2];
        this.dest = destination;
        this.minDist = new int[m][n];
        
        
        PriorityQueue<State> queue = new PriorityQueue<>();
        queue.add(new State(start[0], start[1], 0));
        minDist[start[0]][start[1]] = 0;
        
        for (int[] row: minDist) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }
        
        boolean flag = false;
        while (!queue.isEmpty()) {
            State state = queue.poll();
            //System.out.println(String.format("x,y:%d,%d", state.x, state.y));
            if (state.x == dest[0] && state.y == dest[1]) {
                if (state.dist < minDist[state.x][state.y]) 
                    minDist[state.x][state.y] = state.dist;
                flag = true;
            };
            if (state.dist > minDist[dest[0]][dest[1]]) break;
            for (int dir = 0; dir < 4; ++dir) {
                int[] nextxy = next(state.x, state.y, dir); // next[state[0]][state[1]][dir];
                int x = nextxy[0], y = nextxy[1];
                if (x == state.x && y == state.y) continue;
                int nextdist = state.dist + 
                                    Math.abs(x - state.x) + 
                                    Math.abs(y - state.y);
                if (minDist[x][y] <= nextdist) continue;
                minDist[x][y] = nextdist;
                queue.add(new State(x, y, nextdist));
            }
        }
        //System.out.println(minDist[dest[0]][dest[1]]);
        if (flag) return minDist[dest[0]][dest[1]];
        return -1;
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
